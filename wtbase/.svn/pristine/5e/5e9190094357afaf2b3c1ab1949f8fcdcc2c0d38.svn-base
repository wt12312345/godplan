package com.hq.web.domain;

import org.springframework.web.servlet.ModelAndView;

import com.hq.base.webconf.PathConf;

public class WebView extends ModelAndView {
	public WebView() {
		super();
		setProperties();
	}

	public WebView(String viewname) {
		super(viewname);
		setProperties();
	}

	private void setProperties() {
		String root = PathConf.getRoot();
		String img = PathConf.getTrendsimg();
		String assets = PathConf.getAssets();
		String html = PathConf.getHtml();
		this.addObject("path", root);
		this.addObject("img", img);
		this.addObject("assets", assets);
		this.addObject("html", html);
	}
}
