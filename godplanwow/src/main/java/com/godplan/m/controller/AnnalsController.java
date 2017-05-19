package com.godplan.m.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.godplan.entity.Annals;
import com.godplan.m.service.AnnalsService;
import com.godplan.m.vo.AnnalsVo;
import com.wt.base.constant.BegCode;
import com.wt.base.util.TypeUtil;
import com.wt.web.controller.AbstractController;
import com.wt.web.domain.JsonResponse;
import com.wt.web.domain.WebView;

@Controller
@RequestMapping("/m/annals")
public class AnnalsController extends AbstractController {

	@Resource
	private AnnalsService annalsService;
	private ModelAndView mav = null;

	@RequestMapping(value = "/list", method = { RequestMethod.GET })
	public ModelAndView list(HttpServletRequest request,
			HttpServletResponse response) {
		mav = new WebView("m/annals/annalsList");
		return mav;
	}

	@ResponseBody
	@RequestMapping(value = "/getList", method = { RequestMethod.POST })
	public JsonResponse getList(HttpServletRequest request,
			HttpServletResponse response) {
		JsonResponse jr = new JsonResponse();
		List<Annals> listAnnals = annalsService.getAnnalsList(0, 0);
		// String keyWord = TypeUtil.toString(request.getParameter("keyWord"));
		// if (keyWord.isEmpty()) {
		// int page = TypeUtil.toInt(request.getParameter("page"));
		// if (page == 0) {
		// page = 1;
		// }
		// listAnnals = myLifeService.getAnnalsList(30, page);
		// } else {
		// listAnnals = myLifeService.getAnnalsList(keyWord);
		// }
		jr.setObj(listAnnals);
		jr.setCode(BegCode.SUCCESS);
		return jr;
	}

	@RequestMapping(value = "/edit", method = { RequestMethod.GET })
	public ModelAndView edit(HttpServletRequest request,
			HttpServletResponse response) {
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

	@ResponseBody
	@RequestMapping(value = "/getAnnals", method = { RequestMethod.POST,
			RequestMethod.GET })
	public JsonResponse getAnnals(HttpServletRequest request,
			HttpServletResponse response) {
		JsonResponse jr = new JsonResponse();
		long id = TypeUtil.toLong(request.getParameter("id"));
		Annals item = null;
		if (id == 0) {
			item = new Annals();
		} else {
			item = annalsService.getEntity(id);
			if (item == null) {
				redirectToPage(response, "m/annals/list");
				return jr;
			}
		}
		jr.setObj(AnnalsVo.getVo(item));
		jr.setCode(BegCode.SUCCESS);
		return jr;
	}

	@RequestMapping(value = "/saveAnnals", method = { RequestMethod.POST })
	@ResponseBody
	public JsonResponse saveAnnals(HttpServletRequest request,
			HttpServletResponse response, AnnalsVo annalsVo) {
		JsonResponse jr = new JsonResponse();
		try {
			Annals item = null;
			if (annalsVo.getId() == 0) {
				item = new Annals();
			} else {
				item = annalsService.getEntity(annalsVo.getId());
				if (item == null) {
					jr.setMsg("数据不存在");
					return jr;
				}
			}
			item.setContent(annalsVo.getContent());
			item.setTitle(annalsVo.getTitle());
			item.setYear(annalsVo.getYear());
			item.setMonth(annalsVo.getMonth());
			item.setDay(annalsVo.getDay());
			item.setHour(annalsVo.getHour());
			item.setMinute(annalsVo.getMinute());
			item.setSecond(annalsVo.getSecond());
			item.setTags(annalsVo.getTags());
			annalsService.saveOrUpdate(item);
			jr.setObj(AnnalsVo.getVo(item));
			jr.setCode(BegCode.SUCCESS);
		} catch (Exception e) {
			setJsonFailed(jr, e);
		}
		return jr;
	}
}
