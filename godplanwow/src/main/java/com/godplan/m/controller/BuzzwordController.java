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

import com.godplan.entity.Buzzword;
import com.godplan.m.service.BuzzwordService;
import com.godplan.m.service.bo.SearchBo;
import com.godplan.m.vo.BuzzwordVo;
import com.wt.base.constant.BegCode;
import com.wt.base.util.TimeUtil;
import com.wt.base.util.TypeUtil;
import com.wt.web.controller.AbstractController;
import com.wt.web.domain.JsonResponse;
import com.wt.web.domain.Page;

@Controller
@RequestMapping("/m")
public class BuzzwordController extends AbstractController {

	@Resource
	private BuzzwordService buzzwordService;

	@ResponseBody
	@RequestMapping(value = "/buzzwords", method = { RequestMethod.GET })
	public JsonResponse buzzwordList(HttpServletRequest request, HttpServletResponse response) {
		JsonResponse jr = new JsonResponse();
		Page page = new Page(0, 0, -1);
		String keyWord = TypeUtil.toString(request.getParameter("keyWord"));
		String orderBy = TypeUtil.toString(request.getParameter("orderBy"));
		List<Buzzword> list = buzzwordService.getList(page, orderBy, new SearchBo(keyWord));
		jr.setObj(BuzzwordVo.getVo(list));
		jr.setCode(BegCode.SUCCESS);
		return jr;
	}

	@ResponseBody
	@RequestMapping(value = "/buzzwords/{id}", method = { RequestMethod.GET })
	public JsonResponse buzzword(HttpServletRequest request, HttpServletResponse response, @PathVariable long id) {
		JsonResponse jr = new JsonResponse();
		Buzzword item = null;
		if (id == 0) {
			item = new Buzzword();
		} else {
			item = buzzwordService.getEntity(id);
			if (item == null) {
				redirectToPage(response, "m/annals/list");
				return jr;
			}
		}
		jr.setObj(BuzzwordVo.getVo(item));
		jr.setCode(BegCode.SUCCESS);
		return jr;
	}

	@ResponseBody
	@RequestMapping(value = "/buzzwords/{id}", method = { RequestMethod.POST, RequestMethod.PUT })
	public JsonResponse buzzwordEdit(HttpServletRequest request, HttpServletResponse response, @PathVariable long id,
			BuzzwordVo itemVo) {
		JsonResponse jr = new JsonResponse();
		try {
			Buzzword item = null;
			if (itemVo.getId() == 0) {
				item = new Buzzword();
			} else {
				item = buzzwordService.getEntity(itemVo.getId());
				if (item == null) {
					jr.setMsg("数据不存在");
					return jr;
				}
			}
			item.setTitle(itemVo.getTitle());
			item.setContent(itemVo.getContent());
			item.setTime(TimeUtil.getDateMonthByStr(itemVo.getTime()));
			item.setTags(itemVo.getTags());
			buzzwordService.saveOrUpdate(item);
			jr.setObj(BuzzwordVo.getVo(item));
			jr.setCode(BegCode.SUCCESS);
		} catch (Exception e) {
			setJsonFailed(jr, e);
		}
		return jr;
	}
}
