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
import com.godplan.m.service.QuartzAnnalsService;
import com.godplan.m.service.bo.SearchBo;
import com.godplan.m.vo.AnnalsVo;
import com.wt.base.constant.BegCode;
import com.wt.base.util.TypeUtil;
import com.wt.web.controller.AbstractController;
import com.wt.web.domain.JsonResponse;
import com.wt.web.domain.Page;

/**
 * 编年史
 * 
 * @author wt12312345
 *
 */
@Controller
@RequestMapping("/m")
public class AnnalsController extends AbstractController {

	@Resource
	private AnnalsService annalsService;
	@Resource
	private QuartzAnnalsService quartzAnnalsService;

	@ResponseBody
	@RequestMapping(value = "/annalses/orderByTime", method = { RequestMethod.GET })
	public JsonResponse annalsListRefreshOrderByTime(HttpServletRequest request, HttpServletResponse response) {
		JsonResponse jr = new JsonResponse();
		try {
			Page page = new Page(0, 0, -1);
			String keyWord = TypeUtil.toString(request.getParameter("keyWord"));
			String orderBy = TypeUtil.toString(request.getParameter("orderBy"));
			List<Annals> listItem = annalsService.getList(page, orderBy, new SearchBo(keyWord));
			for (int i = 0; i < listItem.size(); i++) {
				Annals item = listItem.get(i);
				long orderByTime = item.getSecond() + item.getMinute() * 100 + item.getHour() * 10000
						+ item.getDay() * 1000000 + item.getMonth() * 100000000 + item.getYear() * 10000000000L;
				item.setOrderByTime(orderByTime);
				annalsService.saveOrUpdate(item);
			}
			jr.setCode(BegCode.SUCCESS);
		} catch (Exception e) {
			setJsonFailed(jr, e);
		}
		return jr;
	}

	@ResponseBody
	@RequestMapping(value = "/annalses/html", method = { RequestMethod.GET })
	public JsonResponse annalsRefreshHtml(HttpServletRequest request, HttpServletResponse response) {
		JsonResponse jr = new JsonResponse();
		try {
			quartzAnnalsService.createIndexHtml();
			jr.setCode(BegCode.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jr;
	}

	@ResponseBody
	@RequestMapping(value = "/annalses", method = { RequestMethod.GET })
	public JsonResponse annalsList(HttpServletRequest request, HttpServletResponse response) {
		JsonResponse jr = new JsonResponse();
		Page page = new Page(0, 0, -1);
		String keyWord = TypeUtil.toString(request.getParameter("keyWord"));
		String orderBy = TypeUtil.toString(request.getParameter("orderBy"));
		List<Annals> listItem = annalsService.getList(page, orderBy, new SearchBo(keyWord));
		jr.setObj(AnnalsVo.getVo(listItem));
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
	@RequestMapping(value = "/annalses", method = { RequestMethod.POST })
	public JsonResponse annalsEdit(HttpServletRequest request, HttpServletResponse response, AnnalsVo itemVo) {
		return annalsEdit(request, response, 0, itemVo);
	}

	@ResponseBody
	@RequestMapping(value = "/annalses/{id}", method = { RequestMethod.POST, RequestMethod.PUT })
	public JsonResponse annalsEdit(HttpServletRequest request, HttpServletResponse response, @PathVariable long id,
			AnnalsVo itemVo) {
		JsonResponse jr = new JsonResponse();
		try {
			Annals item = null;
			if (itemVo.getId() == 0) {
				item = new Annals();
			} else {
				item = annalsService.getEntity(itemVo.getId());
				if (item == null) {
					jr.setMsg("数据不存在");
					return jr;
				}
			}
			item.setContent(itemVo.getContent());
			item.setTitle(itemVo.getTitle());
			item.setYear(itemVo.getYear());
			item.setMonth(itemVo.getMonth());
			item.setDay(itemVo.getDay());
			item.setTags(itemVo.getTags());
			item.setGroupIndex(itemVo.getGroupIndex());
			item.setIconIndex(itemVo.getIconIndex());
			long orderByTime = item.getSecond() + item.getMinute() * 100 + item.getHour() * 10000
					+ item.getDay() * 1000000 + item.getMonth() * 100000000 + item.getYear() * 10000000000L;
			item.setOrderByTime(orderByTime);
			annalsService.saveOrUpdate(item);
			jr.setObj(AnnalsVo.getVo(item));
			jr.setCode(BegCode.SUCCESS);
		} catch (Exception e) {
			setJsonFailed(jr, e);
		}
		return jr;
	}
}
