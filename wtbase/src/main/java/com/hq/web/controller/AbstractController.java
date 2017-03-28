package com.hq.web.controller;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hq.base.util.TypeUtil;
import com.hq.base.webconf.PathConf;
import com.hq.web.domain.JsonResponse;
import com.hq.web.domain.JsonResponseNew;

/** 控制层公用的一些方法 */
public abstract class AbstractController {

	public AbstractController() {
	}

	/** 日志 */
	protected Logger logger = Logger.getLogger(this.getClass());

	/**
	 * 跳转到本项目路径
	 * 
	 * @param response
	 * @param path
	 */
	protected void redirectToPage(HttpServletResponse response, String path) {
		String root = PathConf.getRoot();
		try {
			logger.debug("RedirectPath:" + root + path);
			response.sendRedirect(root + path);
		} catch (Exception e) {
			logger.error(e);
		}
	}

	/**
	 * 跳转到全路径
	 * 
	 * @param response
	 * @param path
	 */
	protected void redirectToOrPage(HttpServletResponse response, String path) {
		try {
			logger.debug("RedirectPath:" + path);
			response.sendRedirect(path);
		} catch (Exception e) {
			logger.error(e);
		}
	}

	/**
	 * 获取访问者IP 在一般情况下使用Request.getRemoteAddr()即可，但是经过nginx等反向代理软件后，这个方法会失效。
	 * 本方法先从Header中获取X-Real-IP，如果不存在再从X-Forwarded-For获得第一个IP(用,分割)，
	 * 如果还不存在则调用Request.getRemoteAddr()。
	 * 
	 * @param request
	 * @return
	 */
	protected String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("X-Real-IP");
		if (!TypeUtil.isEmpty(ip) && !"unknown".equalsIgnoreCase(ip)) {
			return ip;
		}
		ip = request.getHeader("X-Forwarded-For");
		if (!TypeUtil.isEmpty(ip) && !"unknown".equalsIgnoreCase(ip)) {
			// 多次反向代理后会有多个IP值，第一个为真实IP。
			int index = ip.indexOf(',');
			if (index != -1) {
				return ip.substring(0, index);
			} else {
				return ip;
			}
		} else {
			return request.getRemoteAddr();
		}
	}

	protected JsonResponse setJsonFailed(JsonResponse jr, String msg) {
		jr.setCode(10001);
		jr.setMsg("err：" + msg);
		logger.error("err：" + msg);
		return jr;
	}

	protected JsonResponse setJsonFailed(JsonResponse jr, Exception e) {
		jr.setCode(10001);
		jr.setMsg("err：" + e.getMessage());
		logger.error("err：" + e.getMessage());
		return jr;
	}

	protected JsonResponse setJsonFailed(JsonResponse jr, Throwable t) {
		jr.setCode(10001);
		jr.setMsg(t.getMessage());
		StringWriter stringWriter = new StringWriter();
		PrintWriter writer = new PrintWriter(stringWriter);
		t.printStackTrace(writer);
		StringBuffer buffer = stringWriter.getBuffer();
		logger.error(buffer.toString());
		return jr;
	}

	protected JsonResponseNew setJsonFailed(JsonResponseNew jr, String msg) {
		jr.setCode(10001);
		jr.setMsg("err：" + msg);
		logger.error("err：" + msg);
		return jr;
	}
	
	protected JsonResponseNew setJsonFailed(JsonResponseNew jr, Exception e) {
		jr.setCode(10001);
		jr.setMsg("err：" + e.getMessage());
		logger.error("err：" + e.getMessage());
		return jr;
	}
}
