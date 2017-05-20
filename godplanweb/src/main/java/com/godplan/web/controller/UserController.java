package com.godplan.web.controller;

import java.util.Date;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.godplan.entity.User;
import com.godplan.web.SessionUtil;
import com.godplan.web.service.UserService;
import com.godplan.web.vo.UserVo;
import com.wt.base.constant.BegCode;
import com.wt.base.constant.Reg;
import com.wt.base.util.MD5Util;
import com.wt.base.util.TimeUtil;
import com.wt.base.util.TypeUtil;
import com.wt.web.controller.AbstractController;
import com.wt.web.domain.JsonResponse;
import com.wt.web.domain.WebView;

/**
 * 主页
 * */
@Controller
@RequestMapping("/web/i")
public class UserController extends AbstractController {
	private ModelAndView mav = null;
	@Resource
	private UserService userService;

	@RequestMapping("/")
	public ModelAndView index(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		mav = new WebView("i/center");
		UserVo user = SessionUtil.getUser(request);
		mav.addObject("user", user);
		logger.info("进入首页");
		return mav;
	}

	/**
	 * 修改用户信息
	 */
	@ResponseBody
	@RequestMapping(value = "/editInfo", method = { RequestMethod.POST })
	public JsonResponse editInfo(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		JsonResponse jr = new JsonResponse();
		try {
			UserVo userVo = SessionUtil.getUser(session);

			String nickName = TypeUtil.toString(request
					.getParameter("nickName"));
			Date birthday = TimeUtil.getDateByStr(TypeUtil.toString(request
					.getParameter("birthday")));
			User user = userService.getEntity(userVo.getId());
			user.setNickName(nickName);
			user.setBirthday(birthday);
			User userEdit = userService.editInfo(user);
			userEdit.setPassword("");
			userEdit.setOpenid("");
			session.setAttribute("user", userEdit);
			jr.setCode(BegCode.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jr;
	}

	/**
	 * 微信和帐号绑定：目前实际上就是在微信里注册帐号，直接绑定
	 */
	@ResponseBody
	@RequestMapping(value = "/bindWx", method = { RequestMethod.POST })
	public JsonResponse bindWx(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		JsonResponse jr = new JsonResponse();
		try {
			String loginName = TypeUtil.toString(request
					.getParameter("loginName"));
			String password = MD5Util.string2MD5(TypeUtil.toString(request
					.getParameter("password")));
			UserVo userVo = SessionUtil.getUser(session);

			// 微信注册了一个，网站注册了一个，双帐号进行绑定。
			// 这种情况先不计，先统一微信注册帐号，直接进行绑定
			User user = userService.getEntity(userVo.getId());
			if (user != null && user.getLoginName().equals(null)) {
				// 微信用户已登录，但是没有用户名，可进行绑定操作
				if (!Pattern.matches(Reg.USERNAME, loginName)) {
					jr.setMsg("用户名格式不正确");
				} else if (!Pattern.matches(Reg.PASSWORD, password)) {
					jr.setMsg("密码格式不正确");
				} else {
					User userDb = userService.getByLoginName(loginName);
					if (userDb != null) {
						jr.setMsg("用户名已存在");
					} else {
						user.setLoginName(loginName);
						user.setPassword(MD5Util.string2MD5(password));
						userService.saveOrUpdate(user);
						jr.setCode(BegCode.SUCCESS);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jr;
	}

	/**
	 * 修改密码
	 */
	@ResponseBody
	@RequestMapping(value = "/editPassword", method = { RequestMethod.POST })
	public JsonResponse editPassword(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		JsonResponse jr = new JsonResponse();
		try {
			String loginName = TypeUtil.toString(request
					.getParameter("loginName"));
			String password = MD5Util.string2MD5(TypeUtil.toString(request
					.getParameter("password")));
			UserVo userVo = SessionUtil.getUser(session);

			// 微信注册了一个，网站注册了一个，双帐号进行绑定。
			// 这种情况先不计，先统一微信注册帐号，直接进行绑定
			User user = userService.getEntity(userVo.getId());
			if (user != null && user.getLoginName().equals(null)) {
				// 微信用户已登录，但是没有用户名，可进行绑定操作
				if (!Pattern.matches(Reg.USERNAME, loginName)) {
					jr.setMsg("用户名格式不正确");
				} else if (!Pattern.matches(Reg.PASSWORD, password)) {
					jr.setMsg("密码格式不正确");
				} else {
					User userDb = userService.getByLoginName(loginName);
					if (userDb != null) {
						jr.setMsg("用户名已存在");
					} else {
						user.setLoginName(loginName);
						user.setPassword(MD5Util.string2MD5(password));
						userService.saveOrUpdate(user);
						jr.setCode(BegCode.SUCCESS);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jr;
	}
}
