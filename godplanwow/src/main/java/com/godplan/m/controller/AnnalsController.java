package com.godplan.m.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.godplan.entity.Annals;
import com.godplan.m.service.AnnalsService;
import com.godplan.m.service.bo.SearchBo;
import com.godplan.m.vo.AnnalsVo;
import com.wt.base.constant.BegCode;
import com.wt.base.util.TypeUtil;
import com.wt.web.controller.AbstractController;
import com.wt.web.domain.JsonResponse;
import com.wt.web.domain.Page;

@Controller
@RequestMapping("/m")
public class AnnalsController extends AbstractController {

	@Resource
	private AnnalsService annalsService;

	@ResponseBody
	@RequestMapping(value = "/annalses", method = { RequestMethod.GET })
	public JsonResponse annalsList(HttpServletRequest request, HttpServletResponse response) {
		JsonResponse jr = new JsonResponse();
		Page page = new Page(0, 0, -1);
		String keyWord = TypeUtil.toString(request.getParameter("keyWord"));
		String orderBy = TypeUtil.toString(request.getParameter("orderBy"));
		List<Annals> listAnnals = annalsService.getList(page, orderBy, new SearchBo(keyWord));
		jr.setObj(listAnnals);
		jr.setCode(BegCode.SUCCESS);
		return jr;
	}

	@ResponseBody
	@RequestMapping(value = "/annalses/{id}", method = { RequestMethod.GET })
	public JsonResponse annals(HttpServletRequest request, HttpServletResponse response, @PathVariable long id) {
		JsonResponse jr = new JsonResponse();
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

	@ResponseBody
	@RequestMapping(value = "/annalses/{id}", method = { RequestMethod.POST, RequestMethod.PUT })
	public JsonResponse annalsEdit(HttpServletRequest request, HttpServletResponse response, @PathVariable long id,
			AnnalsVo annalsVo) {
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
