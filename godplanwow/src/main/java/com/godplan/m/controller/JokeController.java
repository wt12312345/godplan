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

import com.godplan.Conf;
import com.godplan.constant.EnumCom;
import com.godplan.entity.Joke;
import com.godplan.m.service.JokeService;
import com.wt.base.constant.BegCode;
import com.wt.base.util.TypeUtil;
import com.wt.web.controller.AbstractController;
import com.wt.web.domain.JsonResponse;
import com.wt.web.domain.WebView;

@Controller
@RequestMapping("/m/joke")
public class JokeController extends AbstractController {

	@Resource
	private JokeService jokeService;
	private ModelAndView mav = null;

	@RequestMapping(value = "/list", method = { RequestMethod.GET })
	public ModelAndView annals(HttpServletRequest request,
			HttpServletResponse response) {
		mav = new WebView("m/joke/jokeList");
		return mav;
	}

	@ResponseBody
	@RequestMapping(value = "/getJokeList", method = { RequestMethod.GET })
	public JsonResponse getJokeList(HttpServletRequest request,
			HttpServletResponse response) {
		JsonResponse jr = new JsonResponse();
		try {
			// 0 待审核；1已通过审核；2未通过审核
			int page = TypeUtil.toInt(request.getParameter("page"));
			if (page == 0) {
				page = 1;
			}

			List<Joke> listJoke = jokeService.getList(page, Conf.PageSize);
			jr.setObj(listJoke);
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
	@RequestMapping(value = "/changeStatus", method = { RequestMethod.POST })
	@ResponseBody
	public JsonResponse changeStatus(HttpServletRequest request,
			HttpServletResponse response, long id) {
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
				jr.setStatus(joke.getStatus());
				jr.setCode(BegCode.SUCCESS);
			} else {
				jr.setMsg("不存在");
			}
		} catch (Exception e) {
			setJsonFailed(jr, e);
		}
		return jr;
	}

	@RequestMapping(value = "/saveJoke", method = { RequestMethod.POST })
	@ResponseBody
	public JsonResponse saveJoke(HttpServletRequest request,
			HttpServletResponse response) {
		JsonResponse jr = new JsonResponse();
		try {
			long id = TypeUtil.toLong(request.getParameter("id"));
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
