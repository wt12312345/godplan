package com.godplan.m.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.godplan.m.service.UserSysService;
import com.wt.web.controller.AbstractController;
import com.wt.web.domain.WebView;

@Controller
@RequestMapping("/m/page")
public class PageIndexController extends AbstractController {
	@Resource
	private UserSysService userSysService;
	private ModelAndView mav = null;

	@RequestMapping(value = "/login", method = { RequestMethod.GET })
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response) {
		mav = new WebView("/m/login");
		return mav;
	}

	/** 用户退出 */
	@RequestMapping(value = "/loginOut", method = { RequestMethod.GET })
	public void loginOut(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().invalidate();
		redirectToPage(response, "m/login");
	}

	@RequestMapping(value = "/index", method = { RequestMethod.GET })
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {
		mav = new WebView("/m/index");
		return mav;
	}

}
