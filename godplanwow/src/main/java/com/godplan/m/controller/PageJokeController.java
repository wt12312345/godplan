package com.godplan.m.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.godplan.m.service.JokeService;
import com.wt.web.controller.AbstractController;
import com.wt.web.domain.WebView;

@Controller
@RequestMapping("/m/page")
public class PageJokeController extends AbstractController {
	@Resource
	private JokeService jokeService;
	private ModelAndView mav = null;

	@RequestMapping(value = "/jokeList", method = { RequestMethod.GET })
	public ModelAndView annals(HttpServletRequest request, HttpServletResponse response) {
		mav = new WebView("m/joke/jokeList");
		return mav;
	}

}
