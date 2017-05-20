package com.godplan.m.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.godplan.entity.Article;
import com.godplan.entity.ArticleSort;
import com.godplan.m.service.ArticleService;
import com.wt.base.util.TypeUtil;
import com.wt.web.controller.AbstractController;
import com.wt.web.domain.WebView;

@Controller
@RequestMapping("/m/page")
public class PageArticleController extends AbstractController {
	@Resource
	private ArticleService articleService;
	private ModelAndView mav = null;

	@RequestMapping(value = "/articleList", method = { RequestMethod.GET })
	public ModelAndView articleList(HttpServletRequest request, HttpServletResponse response) {
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

	@RequestMapping(value = "/articleEdit", method = { RequestMethod.GET })
	public ModelAndView articleEdit(HttpServletRequest request, HttpServletResponse response) {
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

	@RequestMapping(value = "/articleCategoryList", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView articleCategoryList() {
		try {
			mav = new WebView("m/article/articleCategoryList");
			List<ArticleSort> listSort = articleService.getSortList();
			mav.addObject("sortList", listSort);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav;
	}

	@RequestMapping(value = "/articleCategoryEdit", method = { RequestMethod.GET })
	public ModelAndView articleCategoryEdit(HttpServletRequest request, HttpServletResponse response) {
		try {
			mav = new WebView("m/article/articleCategoryEdit");
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
}
