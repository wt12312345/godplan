package com.godplan.web.interceptor;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.godplan.web.SessionUtil;
import com.godplan.web.service.UserService;
import com.godplan.web.vo.UserVo;
import com.wt.base.webconf.PathConf;
import com.wt.wx.service.WxAccountService;

/** 微信用户拦截器 */
public class UserInterceptor implements HandlerInterceptor {

	private Logger logger = Logger.getLogger(this.getClass());
	@Resource
	private WxAccountService wxAccountService;
	/**
	 * Session key
	 */
	private String sessionKey;
	@Resource
	private UserService userService;
	/** url to redirect */
	private String redirectUrl;

	/** 存入session中用户页面跳转回原访问页面的key */
	private String requestUrlKey;

	public String getRedirectUrl() {
		return redirectUrl;
	}

	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}

	public String getSessionKey() {
		return sessionKey;
	}

	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}

	public String getRequestUrlKey() {
		return requestUrlKey;
	}

	public void setRequestUrlKey(String requestUrlKey) {
		this.requestUrlKey = requestUrlKey;
	}

	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {

	}

	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {

	}

	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		UserVo user = SessionUtil.getUser(request);
		if (user == null) {
			response.sendRedirect(PathConf.getRoot() + "login");
			return false;
		}
		return true;
	}

	/**
	 * Determines whether the specified HTTP request is authorized.
	 * 
	 * @param request
	 *            HTTP request
	 * @return <code>true</code> if the request is valid;<br>
	 *         <code>false</code> otherwise
	 * @throws IOException
	 *             if an unexpected I/O exception occurs
	 * @throws ServletException
	 *             if an unexpected servlet exception occurs
	 */
	protected boolean isAuthorizedRequest(HttpServletRequest request)
			throws IOException, ServletException {
		HttpSession session = request.getSession(false);
		if (session == null) {
			return false;
		}

		Object sessionObject = session.getAttribute(getSessionKey());

		return isValidSessionObject(sessionObject);
	}

	/**
	 * Determines whether the found session object is valid
	 * 
	 * @param sessionObject
	 *            the found session object
	 * @return <code>true</code> if the session object is valid;<br>
	 *         <code>false</code> otherwise
	 */
	protected boolean isValidSessionObject(Object sessionObject) {
		return sessionObject != null;
	}

	/**
	 * 
	 * @param request
	 * @param response
	 */
	public boolean redirectLoginPage(HttpServletRequest request,
			HttpServletResponse response) {
		logger.error(request.getRemoteAddr() + "用户未登录或超时");
		// ajax请求 session过期时 返回字符串
		if (request.getHeader("x-requested-with") != null
				&& request.getHeader("x-requested-with").equalsIgnoreCase(
						"XMLHttpRequest"))// 如果是ajax请求响应头会有，x-requested-with；
		{
			response.setHeader("sessionstatus", "timeout");// 在响应头设置session状态
			response.setHeader("location", request.getContextPath() + "/"
					+ redirectUrl);
		} else {
			try {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<html><head>");
				out.println("</head><body><SCRIPT LANGUAGE=\"JavaScript\"><!--");
				out.println("var wnd=this.window;");
				out.println("while(1){");
				out.println("if(wnd==wnd.parent){");
				out.println("break;");
				out.println("}else");
				out.println("wnd=wnd.parent;");
				out.println("}");
				out.println("wnd.location.href='"
						+ ((HttpServletRequest) request).getContextPath() + "/"
						+ redirectUrl + "'");
				out.println("//--></SCRIPT>");
				out.println("</table></body></html>");
				out.flush();
				out.close();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @param request
	 * @param response
	 */
	public void forbiddenRedirect(ServletRequest request,
			ServletResponse response) {
		logger.error("用户未登录或登录已超时");
		try {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<html><head>");
			out.println("</head><body><SCRIPT LANGUAGE=\"JavaScript\"><!--");
			out.println("var wnd=this.window;");
			out.println("while(1){");
			out.println("if(wnd==wnd.parent){");
			out.println("break;");
			out.println("}else");
			out.println("wnd=wnd.parent;");
			out.println("}");
			out.println("alert('无权限的非法操作！');");
			out.println("//--></SCRIPT>");
			out.println("</table></body></html>");
			out.flush();
			out.close();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	/** 获取重定向url，并将它存入session中 */
	public String putRequestUrl(HttpServletRequest request) {
		// 获取请求地址，存入session
		String requestUrl = request.getRequestURL().toString();
		// 获取request中的参数，将参数拼装
		Enumeration<String> e = request.getParameterNames();
		int i = 0;
		while (e.hasMoreElements()) {
			String param = e.nextElement();
			if (i == 0) {
				requestUrl = requestUrl + "?" + param + "="
						+ request.getParameter(param);
			} else {
				requestUrl = requestUrl + "&" + param + "="
						+ request.getParameter(param);
			}
			i++;
		}
		// System.out.println("requestUrl:" + requestUrl);
		request.getSession().setAttribute(requestUrlKey, requestUrl);
		return requestUrl;
	}

}
