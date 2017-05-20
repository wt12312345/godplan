package com.godplan.web.controller;

import java.util.Date;
import java.util.Enumeration;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.quartz.ee.jmx.jboss.QuartzService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.godplan.SessionName;
import com.godplan.entity.User;
import com.godplan.web.SessionUtil;
import com.godplan.web.service.JokeService;
import com.godplan.web.service.UserService;
import com.godplan.web.vo.UserVo;
import com.wt.base.constant.BegCode;
import com.wt.base.constant.Reg;
import com.wt.base.util.EmojiFilter;
import com.wt.base.util.EmojiFilterUtils;
import com.wt.base.util.HttpNewUtil;
import com.wt.base.util.MD5Util;
import com.wt.base.util.TypeUtil;
import com.wt.web.controller.AbstractController;
import com.wt.web.domain.JsonResponse;
import com.wt.wx.constant.WxUrl;
import com.wt.wx.entity.WxAccount;
import com.wt.wx.service.WxAccountService;

/**
 * 主页
 */
@Controller
@RequestMapping("/web")
public class IndexController extends AbstractController {
	@Resource
	private UserService userService;
	@Resource
	private JokeService jokeService;
	@Resource
	private WxAccountService wxAccountService;

	/**
	 * 先用微信注册，直接绑定的模式。这里的注册就先不要了注册
	 */
	@ResponseBody
	@RequestMapping(value = "/doRegister", method = { RequestMethod.POST })
	public JsonResponse doRegister(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		JsonResponse jr = new JsonResponse();
		try {
			User user = null;
			// 如果是微信登录，先判断此用户是否绑定过帐号
			UserVo userWxFromSession = SessionUtil.getUserWx(session);
			if (userWxFromSession != null) {
				String openid = userWxFromSession.getOpenid();
				user = userService.getByOpenid(openid);
				if (user != null) {
					String loginNameDb = user.getLoginName();
					if (!loginNameDb.isEmpty()) {
						// 用户存在并且已经绑定帐号
						jr.setMsg("您的微信已经绑定帐号（" + loginNameDb + "）!无法进行注册");
						return jr;
					}
				}
			}

			// 然后判断参数正确性
			String loginName = TypeUtil.toString(request.getParameter("loginName"));
			String password = TypeUtil.toString(request.getParameter("password"));
			if (!Pattern.matches(Reg.USERNAME, loginName)) {
				jr.setMsg("用户名格式不正确");
			} else if (!Pattern.matches(Reg.PASSWORD, password)) {
				jr.setMsg("密码格式不正确");
			} else {
				User userCheckLoginNameUnique = userService.getByLoginName(loginName);
				if (userCheckLoginNameUnique != null) {
					jr.setMsg("用户名已存在");
				} else {
					if (user == null) {
						// 默认昵称为用户名，然后判断是否存在，一直到找到可用昵称
						String nickName = loginName;
						String newNickName = nickName;
						User userCheckNickNameUnique = userService.getByNickName(nickName);
						while (userCheckNickNameUnique != null) {
							newNickName = nickName + TypeUtil.toString(Math.random()).substring(3, 5);
							userCheckNickNameUnique = userService.getByNickName(newNickName);
						}

						user = new User();
						// 非微信注册，么有昵称，这里加上
						user.setNickName(newNickName);
					}
					// 先用微信登录过的帐号是有昵称的，不需要另外设置
					user.setLoginName(loginName);
					user.setPassword(MD5Util.string2MD5(password));
					userService.saveOrUpdate(user);
					user.setPassword("");
					session.setAttribute(SessionName.User, user);
					jr.setCode(BegCode.SUCCESS);
					jr.setObj(user);
					logger.info("新用户注册：" + loginName);
				}
			}
		} catch (Exception e) {
			setJsonFailed(jr, e);
		}
		return jr;
	}

	/**
	 * 登录
	 */
	@ResponseBody
	@RequestMapping(value = "/doLogin", method = { RequestMethod.POST })
	public JsonResponse doLogin(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		JsonResponse jr = new JsonResponse();
		try {
			// 如果是微信登录，先判断此用户是否绑定过帐号
			User userWx = null;
			String openid = TypeUtil.toString(session.getAttribute(SessionName.UserWx));
			if (!openid.isEmpty()) {
				userWx = userService.getByOpenid(openid);
				if (userWx != null) {
					String loginNameDb = userWx.getLoginName();
					if (!TypeUtil.isEmpty(loginNameDb)) {
						// 用户存在并且已经绑定帐号
						jr.setMsg("您的微信已经绑定帐号（" + loginNameDb + "），并且已自动登录!无法登录其他帐号");
						return jr;
					}
				}
			}

			String loginName = TypeUtil.toString(request.getParameter("loginName"));
			String password = MD5Util.string2MD5(TypeUtil.toString(request.getParameter("password")));
			User user = userService.getByLoginName(loginName);
			if (user == null) {
				jr.setMsg("用户名不存在");
			} else if (!user.getPassword().equals(password)) {
				jr.setMsg("密码错误");
			} else if (!openid.equals("") && !user.getOpenid().equals(openid)) {
				jr.setMsg("此帐号已绑定其他微信帐号，不能用其他微信号登录");
			} else {
				// 只有微信帐号，但是没有绑定帐号。这里登陆即实现绑定
				if (userWx != null && TypeUtil.isEmpty(userWx.getLoginName()) && TypeUtil.isEmpty(user.getOpenid())) {
					user.setOpenid(userWx.getOpenid());
					// userWx.setOpenid(userWx.getOpenid() + " to " +
					// user.getId());
					userService.saveOrUpdate(userWx);
					logger.info("用户 " + user.getId() + " 绑定帐号 " + user.getOpenid());
				}
				user.setLastLoginTime(new Date());
				userService.saveOrUpdate(user);

				UserVo userVo = new UserVo();
				userVo.setNickName(user.getNickName());
				userVo.setOpenid(user.getOpenid());
				userVo.setId(user.getId());
				session.setAttribute(SessionName.User, userVo);

				jr.setCode(BegCode.SUCCESS);
				jr.setObj(user);
				logger.info("用户登录：" + loginName + "(" + user.getId() + ")");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jr;
	}

	@ResponseBody
	@RequestMapping(value = "/checkLogin", method = { RequestMethod.GET })
	public JsonResponse checkLogin(HttpServletRequest request, HttpServletResponse response, HttpSession session) {

		Enumeration params = request.getParameterNames();
		while (params.hasMoreElements()) {
			// 得到参数名
			String name = params.nextElement().toString();
			// System.out.println("name===========================" + name +
			// "--");
			// 得到参数对应值
			String[] value = request.getParameterValues(name);
			for (int i = 0; i < value.length; i++) {
				System.out.println(value[i]);
			}
		}
		JsonResponse jr = new JsonResponse();
		try {
			UserVo user = SessionUtil.getUser(request);
			if (user == null) {

			} else {

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jr;
	}

	@ResponseBody
	@RequestMapping(value = "/checkWxAuthor", method = { RequestMethod.GET })
	public JsonResponse checkWxAuthor(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		JsonResponse jr = new JsonResponse();
		try {
			UserVo userWxFromSession = SessionUtil.getUserWx(session);

			if (userWxFromSession == null) {
				// 没有授权，前端跳转授权页面
				jr.setMsg("没有微信授权");
			} else {
				String openid = userWxFromSession.getOpenid();
				User user = userService.getByOpenid(openid);
				if (user != null) {
					UserVo userVo = new UserVo();
					userVo.setNickName(user.getNickName());
					userVo.setOpenid(user.getOpenid());
					userVo.setId(user.getId());
					session.setAttribute(SessionName.User, userVo);
					jr.setCode(BegCode.SUCCESS);
					jr.setObj(userVo);
				} else {
					jr.setMsg("路人");
					// 代表已微信授权，但是没有注册帐号，说明是路人用微信访问
					jr.setCode(2);
					jr.setObj(userWxFromSession);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jr;
	}

	/**
	 * 通过code获取微信用户信息
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getWxAuthorInfo", method = { RequestMethod.GET })
	public JsonResponse getWxAuthorInfo(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			String code) {
		JsonResponse jr = new JsonResponse();
		try {
			logger.info("授权回调INFO： code=" + code);
			if (TypeUtil.isEmpty(code)) {
				jr.setMsg("用户不同意授权");
				return jr;
			} else {
				logger.info("开始处理");
				if (WxUrl.AppId == null) {
					WxAccount wxAccount = wxAccountService.getWxAccount();
					WxUrl.AppId = wxAccount.getAppId();
					WxUrl.AppSecret = wxAccount.getAppSecret();
					WxUrl.PayKey = wxAccount.getMchKey();
					WxUrl.PayMchId = wxAccount.getMchId();
					WxUrl.PayMchPath = wxAccount.getMchPath();
					WxUrl.AppSecret = wxAccount.getAppSecret();
				}

				String getOpenidUrl = WxUrl.URL_OAUTH_TOKEN(code);
				logger.info("获取openid URL：" + getOpenidUrl);
				HttpNewUtil httpTemp = new HttpNewUtil();
				String getOpenid = httpTemp.getNew(getOpenidUrl);
				if (getOpenid == null) {
					jr.setMsg("获取openid 微信服务器请求失败，获取信息为null，授权失败，再次跳转授权");
					return jr;
				}
				logger.info("获取openid：" + getOpenid);
				JSONObject openidJson = new JSONObject(getOpenid);
				if (openidJson.has(WxUrl.ERROR_CODE)) {
					jr.setMsg("获取openid 微信服务器请求回调包含错误：" + openidJson.getInt(WxUrl.ERROR_CODE) + "。授权失败，再次跳转授权");
					return jr;
				}
				String openid = openidJson.getString("openid");
				String jsToken = openidJson.getString("access_token");
				// 获取用户信息，subscribe参数是0的时候表示他没有关注公众号。
				String getUserInfoUrl = WxUrl.URL_USERINFO_USERINFO(jsToken, openid);
				String infos = httpTemp.getNew(getUserInfoUrl);
				logger.info("获取用户信息：" + infos);
				if (infos == null) {
					jr.setMsg("获取用户信息 微信服务器请求失败，获取信息为null，授权失败，再次跳转授权");
					return jr;
				}
				JSONObject infoJson = new JSONObject(infos);
				if (infoJson.has(WxUrl.ERROR_CODE)) {
					jr.setMsg("获取到用户信息 微信服务器请求回调包含错误：" + infoJson.getInt(WxUrl.ERROR_CODE) + "。授权失败，再次跳转授权");
					return jr;
				}
				UserVo userVoForWx = new UserVo();
				String nickName = infoJson.getString("nickname");
				String headImg = infoJson.getString("headimgurl");
				nickName = EmojiFilter.filterEmoji(nickName);
				nickName = EmojiFilterUtils.filterEmoji(nickName);
				userVoForWx.setOpenid(openid);
				userVoForWx.setNickName(nickName);
				userVoForWx.setHeadImg(headImg);
				session.setAttribute(SessionName.UserWx, userVoForWx);
				logger.info("微信登录成功");
				User user = null;
				if (!openid.isEmpty()) {
					user = userService.getByOpenid(openid);
					if (user != null) {
						UserVo userVo = new UserVo();
						userVo.setNickName(user.getNickName());
						userVo.setOpenid(user.getOpenid());
						userVo.setId(user.getId());
						session.setAttribute(SessionName.User, userVo);
						logger.info("用户登录成功");
						jr.setCode(BegCode.SUCCESS);
						jr.setObj(userVo);
					} else {
						jr.setCode(2);
						jr.setObj(userVoForWx);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jr;
	}
}
