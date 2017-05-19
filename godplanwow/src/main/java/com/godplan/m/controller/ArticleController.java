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

import com.godplan.entity.Article;
import com.godplan.entity.ArticleSort;
import com.godplan.m.service.ArticleService;
import com.wt.base.constant.BegCode;
import com.wt.base.util.TypeUtil;
import com.wt.web.controller.AbstractController;
import com.wt.web.domain.JsonResponse;
import com.wt.web.domain.WebView;

@Controller
@RequestMapping("/m")
public class ArticleController extends AbstractController {

	@Resource
	private ArticleService articleService;
	private ModelAndView mav = null;

	@RequestMapping(value = "/articleList", method = { RequestMethod.GET })
	public ModelAndView articleList(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			mav = new WebView("m/article/articleList");

			long sortId = TypeUtil.toLong(request.getParameter("sortId"));
			mav.addObject("sortId", sortId);
			List<Article> listArticle = articleService.getList(sortId);
			mav.addObject("listArticle", listArticle);
			List<ArticleSort> listSort = articleService.getSortList();
			mav.addObject("sortList", listSort);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav;
	}

	@ResponseBody
	@RequestMapping(value = "/getArticleList", method = { RequestMethod.POST })
	public JsonResponse getArticleList(HttpServletRequest request,
			HttpServletResponse response) {
		JsonResponse jr = new JsonResponse();
		try {
			jr.setCode(BegCode.SUCCESS);
		} catch (Exception e) {
			setJsonFailed(jr, e);
		}
		return jr;
	}

	@RequestMapping(value = "/articleEdit", method = { RequestMethod.GET })
	public ModelAndView articleEdit(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			mav = new WebView("m/article/articleEdit");
			long articleId = TypeUtil.toLong(request.getParameter("id"));
			Article article = null;
			if (articleId == 0) {
				article = new Article();
			} else {
				article = articleService.getEntity(articleId);
			}
			mav.addObject("article", article);
			List<ArticleSort> listSort = articleService.getSortList();
			mav.addObject("sortList", listSort);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav;
	}

	@RequestMapping(value = "/saveArticle", method = { RequestMethod.POST })
	public ModelAndView saveArticle(HttpServletRequest request,
			HttpServletResponse response, Article article) {
		try {
			articleService.saveOrUpdate(article);
			redirectToPage(response, "m/articleList");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav;
	}

	@RequestMapping(value = "/articleSortList", method = { RequestMethod.GET,
			RequestMethod.POST })
	public ModelAndView articleSortList() {
		try {
			mav = new WebView("m/article/articleSortList");
			List<ArticleSort> listSort = articleService.getSortList();
			mav.addObject("sortList", listSort);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav;
	}

	@RequestMapping(value = "/articleSortEdit", method = { RequestMethod.GET })
	public ModelAndView articleSortEdit(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			mav = new WebView("m/article/articleSortEdit");
			long sortId = TypeUtil.toLong(request.getParameter("id"));
			ArticleSort articleSort = null;
			if (sortId == 0) {
				articleSort = new ArticleSort();
			} else {
				articleSort = articleService.getSort(sortId);
			}
			mav.addObject("sort", articleSort);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav;
	}

	@RequestMapping(value = "/saveArticleSort", method = { RequestMethod.POST })
	public ModelAndView saveArticleSort(HttpServletRequest request,
			HttpServletResponse response, ArticleSort articleSort) {
		try {
			articleService.saveOrUpdateEntity(articleSort);
			redirectToPage(response, "m/articleSortList");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav;
	}
}
