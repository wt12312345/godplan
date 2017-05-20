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
@RequestMapping("/")
public class JumpController extends AbstractController {
	@Resource
	private UserSysService userSysService;
	private ModelAndView mav = null;

	@RequestMapping(value = "/", method = { RequestMethod.GET })
	public void init(HttpServletRequest request, HttpServletResponse response) {
		redirectToPage(response, "m/page/login");
	}

	@RequestMapping(value = "/m", method = { RequestMethod.GET })
	public void init2(HttpServletRequest request, HttpServletResponse response) {
		redirectToPage(response, "m/page/login");
	}

}
