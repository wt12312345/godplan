package com.wt.web.controller;

/**
 * Created by Wang on 2016/5/2.
 */
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.wt.base.util.WebUtil;

//  提供跨域支持
@Component
public class CORSFilter implements Filter {

	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;

		// response.setHeader("Access-Control-Allow-Methods",
		// "POST, GET, OPTIONS, DELETE");
		// response.setHeader("Access-Control-Max-Age", "3600");
		// response.setHeader("Access-Control-Allow-Headers",
		// "x-requested-with");
		// response.setHeader("P3P", "CP=CAO PSA OUR");

		response.setHeader("Access-Control-Allow-Origin",
				request.getHeader("Origin"));
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
		// response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Credentials", "true"); // 是否支持cookie跨域
		response.setHeader("Access-Control-Allow-Methods",
				"POST, GET, PUT, DELETE");
		System.out.println("跨域中............................");
		
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		chain.doFilter(req, res);
	}

	public void init(FilterConfig filterConfig) {
	}

	public void destroy() {
	}

}