package com.godplan.m.service;

import java.util.List;

import com.godplan.entity.Article;
import com.godplan.entity.ArticleSort;
import com.wt.web.service.AbstractService;

public interface ArticleService extends AbstractService<Article> {
	List<ArticleSort> getSortList() throws Exception;

	List<Article> getList(long sortId) throws Exception;

	ArticleSort getSort(long id) throws Exception;

	void saveOrUpdateEntity(ArticleSort entity) throws Exception;
}
