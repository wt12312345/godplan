package com.godplan.m.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.godplan.SessionName;
import com.godplan.m.SessionUtil;
import com.godplan.m.vo.UserSysVo;
import com.wt.base.util.TypeUtil;
import com.wt.base.webconf.PathConf;

public class UserSysInterceptor implements HandlerInterceptor {

	protected Logger logger = Logger.getLogger(this.getClass());

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		UserSysVo user = SessionUtil.getUserSys(request);
		String url = request.getRequestURI();
		System.out.println(url);
		if (user == null) {
			logger.info("管理员用户未登录");
//			if (request.getSession().getAttribute(SessionName.BeforeLoginUrlThenLogin) == null && !url.equals("/m")
//					&& !url.equals("/m/") && !url.contains("m/page/login") && !url.contains("m/page/loginOut")) {
//				String urlFull = PathConf.getRoot().substring(0, PathConf.getRoot().length() - 1)
//						+ request.getRequestURI();
//				String arg = request.getQueryString();
//				if (arg != null) {
//					urlFull += "?" + arg;
//				}
//				if (urlFull.contains("/m/")) {
//					request.getSession().setAttribute(SessionName.BeforeLoginUrlThenLogin, urlFull);
//					System.out.println("有登录后需要跳转的url：" + urlFull);
//				}
//			}
			response.sendRedirect(PathConf.getRoot() + "m/page/login");
			return false;
		} else {
			// 取得访问的链接
			String arg = request.getQueryString();
			String urlAll = url;
			if (arg != null) {
				urlAll += "?" + arg;
			}
			logger.info("后台访问：" + user.getName() + " " + urlAll);

			String ip = request.getHeader("x-forwarded-for");
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("Proxy-Client-IP");
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("WL-Proxy-Client-IP");
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getRemoteAddr();
			}

//			if (url.equals("/m") || url.equals("/m/")) {
//				response.sendRedirect(PathConf.getRoot() + "m/index");
//				request.getSession().removeAttribute(SessionName.BeforeLoginUrlThenLogin);
//				return false;
//			}

//			// 以下链接直接访问。不做处理
//			if (url.contains("/m/index") || url.contains("/m/toChangePwd") || url.contains("/m/changePwd")
//					|| url.contains("/m/loginOut") || url.contains("/m/comm/") || url.contains("/m/doLogin")) {
//				if (url.contains("/m/index")) {
//					// 登录后先到首页，如果有session记录之前来的页面，跳转
//					String beforeLoginUrlThenLogin = TypeUtil
//							.toString(request.getSession().getAttribute(SessionName.BeforeLoginUrlThenLogin));
//					if (!beforeLoginUrlThenLogin.equals("")) {
//						response.sendRedirect(beforeLoginUrlThenLogin);
//						request.getSession().removeAttribute(SessionName.BeforeLoginUrlThenLogin);
//						return false;
//					}
//				}
//				return true;
//			}
			return true;
		}

	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}

}
