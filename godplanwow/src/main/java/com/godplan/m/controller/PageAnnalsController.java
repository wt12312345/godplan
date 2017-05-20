package com.godplan.m.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.godplan.entity.Annals;
import com.godplan.m.service.AnnalsService;
import com.godplan.m.vo.AnnalsVo;
import com.wt.base.util.TypeUtil;
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

	@RequestMapping(value = "/annalsEdit", method = { RequestMethod.GET })
	public ModelAndView edit(HttpServletRequest request, HttpServletResponse response) {
		try {
			mav = new WebView("m/annals/annalsEdit");
			long id = TypeUtil.toLong(request.getParameter("id"));
			Annals item = null;
			if (id == 0) {
				item = new Annals();
			} else {
				item = annalsService.getEntity(id);
				if (item == null) {
					redirectToPage(response, "m/annals/list");
					return null;
				}
			}
			mav.addObject("item", AnnalsVo.getVo(item));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav;
	}

}
