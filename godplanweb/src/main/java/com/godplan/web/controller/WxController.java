package com.godplan.web.controller;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.security.MessageDigest;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.godplan.SessionName;
import com.godplan.entity.User;
import com.godplan.entity.UserSys;
import com.godplan.web.service.UserService;
import com.godplan.web.service.UserSysService;
import com.wt.base.util.HttpNewUtil;
import com.wt.base.util.TypeUtil;
import com.wt.base.webconf.PathConf;
import com.wt.web.controller.AbstractController;
import com.wt.web.domain.JsonResponse;
import com.wt.wx.constant.WxUrl;
import com.wt.wx.entity.WxAccount;
import com.wt.wx.service.WxAccountService;
import com.wt.wx.vo.WxjsVo;

@Controller
@RequestMapping("/wx")
public class WxController extends AbstractController {
	@Resource
	private WxAccountService wxService;
	@Resource
	private UserService userService;
	@Resource
	private UserSysService userSysService;

	@RequestMapping(value = "/callback/{apptype}")
	public ModelAndView callback(@PathVariable String apptype,
			@RequestParam String code, @RequestParam String state,
			HttpServletRequest request, HttpSession session,
			HttpServletResponse response) {
		logger.info("授权回调INFO：" + apptype + " code=" + code);
		try {
			if (TypeUtil.isEmpty(code)) {
				/* 用户不同意授权，游客身份进来，仍然根据APP模块帮他们导航 */
				logger.info("用户不同意授权");
			} else {
				logger.info("开始处理");
				if (WxUrl.AppId == null) {
					WxAccount wxAccount = wxService.getWxAccount();
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
					logger.info("获取openid 微信服务器请求失败，获取信息为null，授权失败，再次跳转授权");
					// wxService.clearToken();
					response.sendRedirect(WxUrl.URL_AUTHOR()
							+ WxUrl.URL_AUTHOR_END_INFO);
					return null;
				}
				logger.info("获取openid：" + getOpenid);
				JSONObject openidJson = new JSONObject(getOpenid);
				if (openidJson.has(WxUrl.ERROR_CODE)) {
					logger.info("获取openid 微信服务器请求回调包含错误：");
					logger.info(openidJson.getInt(WxUrl.ERROR_CODE)
							+ "。授权失败，再次跳转授权");
					// wxService.clearToken();
					response.sendRedirect(WxUrl.URL_AUTHOR()
							+ WxUrl.URL_AUTHOR_END_INFO);
					return null;
				}
				String openid = openidJson.getString("openid");
				String jsToken = openidJson.getString("access_token");
				// 获取用户信息，subscribe参数是0的时候表示他没有关注公众号。
				String getUserInfoUrl = WxUrl.URL_USERINFO_USERINFO(jsToken,
						openid);
				String infos = httpTemp.getNew(getUserInfoUrl);
				logger.info("获取用户信息：" + infos);
				if (infos == null) {
					logger.info("获取用户信息 微信服务器请求失败，获取信息为null，授权失败，再次跳转授权");
					// wxService.clearToken();
					response.sendRedirect(WxUrl.URL_AUTHOR()
							+ WxUrl.URL_AUTHOR_END_INFO);
					return null;
				}
				JSONObject infoJson = new JSONObject(infos);
				if (infoJson.has(WxUrl.ERROR_CODE)) {
					logger.info("获取到用户信息 微信服务器请求回调包含错误：");
					logger.info(infoJson.getInt(WxUrl.ERROR_CODE)
							+ "。授权失败，再次跳转授权");
					// wxService.clearToken();
					response.sendRedirect(WxUrl.URL_AUTHOR()
							+ WxUrl.URL_AUTHOR_END_INFO);
					return null;
				}
				User user = userService.getWxUser(infoJson);

				/* 设置用户的登陆信息 */
				session.setAttribute(SessionName.UserWx, user.getOpenid());
				user.setPassword("");
				user.setOpenid("");
				if (!TypeUtil.isEmpty(user.getLoginName())) {
					// 如果已经绑定了帐号，自动登录
					session.setAttribute(SessionName.User, user);
				}
				
				//我自己的帐号，直接登录后台
				if (user.getId() == 1 || user.getId() == 5) {
					UserSys userSys = userSysService.getEntity(1);
					session.setAttribute(SessionName.UserSys, userSys);
				}
				String backUrl = TypeUtil.toString(session
						.getAttribute("requestUrlKey"));
				logger.info("授权成功后处理成功，返回进来时的链接：" + backUrl);
				backUrl = backUrl.replace(PathConf.getRoot(), "/");
				redirectToPage(response, backUrl);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取js sdk授权
	 * 
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/getWxSdk", method = { RequestMethod.GET,
			RequestMethod.POST })
	@ResponseBody
	public JsonResponse getWxSdk(HttpServletRequest request,
			HttpServletResponse response, String id) {
		JsonResponse jr = new JsonResponse();
		try {
			String url = request.getHeader("Referer");
			WxjsVo wxjsVo;
			wxjsVo = wxService.getWxJs(url);
			jr.setObj(wxjsVo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jr;
	}

	/**
	 * 公众号对接
	 * 
	 * @param session
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/wxJoin", method = { RequestMethod.GET })
	public ModelAndView wxJoin(HttpSession session, HttpServletRequest request,
			HttpServletResponse response) {
		try {

			WxAccount wx = wxService.getWxAccount();

			String sign = wx.getAppId();
			/* 加密以后的签名 */
			String signature = request.getParameter("signature");
			/* 消息创建时间戳 */
			String timestamp = request.getParameter("timestamp");
			/* 随机数 */
			String nonce = request.getParameter("nonce");
			/* 验证消息的来源 */
			String echostr = null;
			if (validateMessage(sign, signature, timestamp, nonce,
					wx.getConnectToken())) {
				echostr = request.getParameter("echostr");
				/* 回复微信服务器 */
				response(response, echostr);
			}
		} catch (Exception e) {
		}
		return null;
	}

	private void response(HttpServletResponse response, String content) {
		logger.debug("Reply " + content + " To Weixin Server");
		OutputStreamWriter osw = null;
		try {
			osw = new OutputStreamWriter(response.getOutputStream(), "utf-8");
			osw.write(content);
		} catch (IOException e) {
			logger.error("IOException During Send Msg To Weixin Server");
		} finally {
			try {
				osw.flush();
				osw.close();
			} catch (Exception e) {
				logger.error("IO Can Not Be closed");
			}
		}
	}

	private boolean validateMessage(String sign, String signature,
			String timestamp, String nonce, String connectToken) {
		if (TypeUtil.isEmpty(sign)) {
			return false;
		}
		if (TypeUtil.isEmpty(timestamp)) {
			return false;
		}
		if (TypeUtil.isEmpty(nonce)) {
			return false;
		}

		List<String> list = new LinkedList<String>();
		list.add(timestamp);
		list.add(nonce);
		list.add(connectToken);
		Collections.sort(list);
		// the largest is the biggest position
		StringBuffer sb = new StringBuffer();
		// 0 is the time stamp
		sb.append(list.get(0));
		// token and nonce
		sb.append(list.get(1));
		sb.append(list.get(2));
		String signGen = encode("SHA1", sb.toString());
		if (signGen.equals(signature)) {
			// compare gen with sign
			return true;
		}
		return false;

	}

	private static String encode(String algorithm, String str) {
		if (str == null) {
			return null;
		}
		try {
			MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
			messageDigest.update(str.getBytes(CHAR_SET));
			return getFormattedText(messageDigest.digest());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private static String getFormattedText(byte[] bytes) {
		int len = bytes.length;
		StringBuilder buf = new StringBuilder(len * 2);
		for (int j = 0; j < len; j++) {
			buf.append(HEX_DIGITS[(bytes[j] >> 4) & 0x0f]);
			buf.append(HEX_DIGITS[bytes[j] & 0x0f]);
		}
		return buf.toString();
	}

	private static final String CHAR_SET = "UTF-8";
	private static final char[] HEX_DIGITS = { '0', '1', '2', '3', '4', '5',
			'6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	@RequestMapping(value = "/wxJoin", method = { RequestMethod.POST })
	public void wxPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			String strXml = IOUtils.toString(request.getInputStream(), "utf-8");
			logger.debug("接收XML" + strXml);
			// AbstractMessage absMsg = null;
			Document document = null;
			document = DocumentHelper.parseText(strXml);
			Element root = document.getRootElement();
			Iterator<?> iter = root.elementIterator();
			String content = "";
			String openid = "";
			String fromUserName = "";
			String createTime = "";
			while (iter.hasNext()) {
				Element ele = (Element) iter.next();
				if (ele.getName().equals("MsgType")) {
				} else if (ele.getName().equals("Content")) {
					content = ele.getText();
				} else if (ele.getName().equals("ToUserName")) {
					fromUserName = ele.getText();
				} else if (ele.getName().equals("FromUserName")) {
					openid = ele.getText();
				} else if (ele.getName().equals("CreateTime")) {
					createTime = ele.getText();
				} else if (ele.getName().equals("Event")) {
				} else if (ele.getName().equals("EventKey")) {
				}
			}
			content = "此号为授权而生，请移步订阅号[阅二十一]！";
			String message = "<xml><ToUserName><![CDATA["
					+ openid
					+ "]]></ToUserName><FromUserName><![CDATA["
					+ fromUserName
					+ "]]></FromUserName><CreateTime>"
					+ createTime
					+ "</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA["
					+ content + "]]></Content></xml>";
			response(response, message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
