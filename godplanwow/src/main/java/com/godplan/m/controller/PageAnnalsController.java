package com.godplan.m.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.godplan.m.service.AnnalsService;
import com.wt.web.controller.AbstractController;
import com.wt.web.domain.WebView;

@Controller
@RequestMapping("/m/page")
public class PageAnnalsController extends AbstractController {
	@Resource
	private AnnalsService annalsService;
	private ModelAndView mav = null;

	@RequestMapping(value = "/annalsList", method = { RequestMethod.GET })
	public ModelAndView list(HttpServletRequest request, HttpServletResponse response) {
		mav = new WebView("m/annals/annalsList");
		return mav;
	}
}
