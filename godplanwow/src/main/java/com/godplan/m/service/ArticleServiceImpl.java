package com.godplan.m.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.godplan.dao.ArticleDao;
import com.godplan.dao.ArticleSortDao;
import com.godplan.entity.Article;
import com.godplan.entity.ArticleSort;
import com.wt.web.dao.WtCondition;
import com.wt.web.service.AbstractServiceImpl;

@Service("articleService")
public class ArticleServiceImpl extends AbstractServiceImpl<Article> implements
		ArticleService {
	@Resource
	private ArticleDao articleDao;
	@Resource
	private ArticleSortDao articleSortDao;

	@Override
	public Class<Article> getEntityClass() {
		return Article.class;
	}

	@Override
	public List<ArticleSort> getSortList() throws Exception {
		return articleSortDao.findAll();
	}

	@Override
	public List<Article> getList(long sortId) throws Exception {
		List<WtCondition> conditions = new ArrayList<WtCondition>();
		if (sortId > 0) {
			conditions.add(new WtCondition(WtCondition.EQ, "articleSort.id",
					sortId));
		}
		conditions.add(new WtCondition(WtCondition.DESC, "id"));
		List<Article> listArticle = articleDao.findByCondition(0, conditions, 0);
		return listArticle;
	}

	@Override
	public ArticleSort getSort(long id) throws Exception {
		return articleSortDao.find(id);
	}

	@Override
	public void saveOrUpdateEntity(ArticleSort entity) throws Exception {
		articleSortDao.saveOrUpdate(entity);
	}

}
