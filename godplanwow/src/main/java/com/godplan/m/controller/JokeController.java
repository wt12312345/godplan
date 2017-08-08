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

import com.godplan.constant.EnumCom;
import com.godplan.entity.Joke;
import com.godplan.m.service.JokeService;
import com.godplan.m.service.bo.SearchBo;
import com.wt.base.constant.BegCode;
import com.wt.base.util.TypeUtil;
import com.wt.web.controller.AbstractController;
import com.wt.web.domain.JsonResponse;
import com.wt.web.domain.Page;

/**
 * 阅无止境
 * 
 * @author wt12312345
 *
 */
@Controller
@RequestMapping("/m")
public class JokeController extends AbstractController {

	@Resource
	private JokeService jokeService;

	@ResponseBody
	@RequestMapping(value = "/jokes", method = { RequestMethod.GET })
	public JsonResponse jokeList(HttpServletRequest request, HttpServletResponse response) {
		JsonResponse jr = new JsonResponse();
		try {
			String keyWord = TypeUtil.toString(request.getParameter("keyWord"));
			Page page = new Page(request);
			// 1可用；2冻结
			List<Joke> listJoke = jokeService.getList(page, "", new SearchBo(keyWord));
			jr.setObj(listJoke);
			jr.setPage(page);
			jr.setCode(BegCode.SUCCESS);
		} catch (Exception e) {
			setJsonFailed(jr, e);
		}
		return jr;
	}

	/**
	 * 审核通过
	 * 
	 * @param request
	 * @param response
	 * @param id
	 * @param mode
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/jokes/{id}/approval", method = { RequestMethod.POST })
	public JsonResponse changeStatus(HttpServletRequest request, HttpServletResponse response, @PathVariable long id) {
		JsonResponse jr = new JsonResponse();
		try {
			Joke joke = jokeService.getEntity(id);
			if (joke != null) {
				if (joke.getStatus() == EnumCom.Freeze.getIndex()) {
					joke.setStatus(EnumCom.Enable.getIndex());
				} else if (joke.getStatus() == EnumCom.Enable.getIndex()) {
					joke.setStatus(EnumCom.Freeze.getIndex());
				} else {
					joke.setStatus(EnumCom.Freeze.getIndex());
				}
				jokeService.saveOrUpdate(joke);
				jr.setObj(joke.getStatus());
				jr.setCode(BegCode.SUCCESS);
			} else {
				jr.setMsg("数据不存在");
			}
		} catch (Exception e) {
			setJsonFailed(jr, e);
		}
		return jr;
	}

	@ResponseBody
	@RequestMapping(value = "/jokes/{id}", method = { RequestMethod.POST, RequestMethod.PUT })
	public JsonResponse saveJoke(HttpServletRequest request, HttpServletResponse response, @PathVariable long id) {
		JsonResponse jr = new JsonResponse();
		try {
			String name = TypeUtil.toString(request.getParameter("name"));
			String content = TypeUtil.toString(request.getParameter("content"));
			Joke joke = jokeService.getEntity(id);
			if (joke != null) {
				joke.setName(name);
				joke.setContent(content);
				jokeService.saveOrUpdate(joke);
				jr.setCode(BegCode.SUCCESS);
			} else {
				jr.setMsg("不存在");
			}
		} catch (Exception e) {
			setJsonFailed(jr, e);
		}
		return jr;
	}
}
