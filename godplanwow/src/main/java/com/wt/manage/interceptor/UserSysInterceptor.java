package com.wt.manage.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.wt.manage.SessionUtil;
import com.wt.manage.vo.UserSysVo;
import com.wt.web.domain.JsonResponse;

public class UserSysInterceptor implements HandlerInterceptor {

	protected Logger logger = Logger.getLogger(this.getClass());

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		UserSysVo user = SessionUtil.getSystemManager(request);
		if (user == null) {
			JsonResponse jr = new JsonResponse();
			jr.setMsg("请登录");
			return false;
		} else {
			// 取得访问的链接
			String url = request.getRequestURI();
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
