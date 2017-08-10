package com.godplan.m.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.godplan.entity.Article;
import com.godplan.entity.ArticleSort;
import com.godplan.m.service.ArticleService;
import com.godplan.m.service.QuartzJokeService;
import com.wt.base.constant.BegCode;
import com.wt.web.controller.AbstractController;
import com.wt.web.domain.JsonResponse;

@Controller
@RequestMapping("/m")
public class ArticleController extends AbstractController {

	@Resource
	private ArticleService articleService;

	@ResponseBody
	@RequestMapping(value = "/getArticleList", method = { RequestMethod.POST })
	public JsonResponse getArticleList(HttpServletRequest request, HttpServletResponse response) {
		JsonResponse jr = new JsonResponse();
		try {
			jr.setCode(BegCode.SUCCESS);
		} catch (Exception e) {
			setJsonFailed(jr, e);
		}
		return jr;
	}

	@RequestMapping(value = "/article/{id}", method = { RequestMethod.POST, RequestMethod.PUT })
	public void saveArticle(HttpServletRequest request, HttpServletResponse response, @PathVariable long id, Article article) {
		try {
			articleService.saveOrUpdate(article);
			redirectToPage(response, "m/page/articleList");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/saveArticleSort", method = { RequestMethod.POST })
	public void saveArticleSort(HttpServletRequest request, HttpServletResponse response, ArticleSort articleSort) {
		try {
			articleService.saveOrUpdateEntity(articleSort);
			redirectToPage(response, "m/page/articleCategoryList");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
