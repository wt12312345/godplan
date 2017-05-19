package com.godplan.dao;

import org.springframework.stereotype.Repository;

import com.godplan.entity.Article;
import com.wt.web.dao.AbstractDaoImpl;

@Repository("articleDao")
public class ArticleDaoImpl extends AbstractDaoImpl<Article> implements
		ArticleDao {

	@Override
	public Class<Article> getEntityClass() {
		return Article.class;
	}

}
