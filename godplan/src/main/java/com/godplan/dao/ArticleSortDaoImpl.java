package com.godplan.dao;

import org.springframework.stereotype.Repository;

import com.godplan.entity.ArticleSort;
import com.wt.web.dao.AbstractDaoImpl;

@Repository("articleSortDao")
public class ArticleSortDaoImpl extends AbstractDaoImpl<ArticleSort> implements
		ArticleSortDao {

	@Override
	public Class<ArticleSort> getEntityClass() {
		return ArticleSort.class;
	}

}
