package com.hq.web.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import com.hq.base.util.TypeUtil;
import com.hq.web.dao.AbstractDao;
import com.hq.web.service.AbstractService;

public abstract class AbstractServiceImpl<T> implements AbstractService<T> {
	public Logger logger = Logger.getLogger(this.getClass());

	@SuppressWarnings(value = "all")
	public abstract Class getEntityClass();

	@Resource
	private ApplicationContext applicationContext;

	@Override
	public void deleteEntity(long id) throws Exception {
		try {
			getDao().delete(id);
		} catch (Exception e) {
			logger.error("Error Delete Entity", e);
		}
	}

	@SuppressWarnings(value = "unchecked")
	@Override
	@Transactional
	public void deleteEntity(T entity) {
		try {
			getDao().delete(entity);
		} catch (Exception e) {
			logger.error("Error Delete Entity", e);
		}
	}

	@SuppressWarnings(value = "unchecked")
	@Override
	public List<T> getAll() {
		try {
			return getDao().findAll();
		} catch (Exception e) {
			logger.error("Error Find All Entity", e);
			return null;
		}
	}

	@SuppressWarnings(value = "unchecked")
	@Override
	public T getEntity(long id) {
		try {
			return (T) getDao().find(id);
		} catch (Exception e) {
			logger.error("Error Find Entity:" + id, e);
			return null;
		}
	}

	/*@SuppressWarnings(value = "unchecked")
	@Override
	public List<T> getListWithPaging(int pageSize, int pageNo) {
		try {
			return getDao().findWithPaging(null, null, true, pageNo, pageSize);
		} catch (Exception e) {
			logger.error("Error Find Page List", e);
			return null;
		}
	}*/

	@SuppressWarnings(value = "all")
	@Override
	@Transactional
	public void saveOrUpdate(T entity) throws Exception {
		try {
			getDao().saveOrUpdate(entity);
		} catch (Exception e) {
			logger.error("Error Save Entity:", e);
		}
	}

	@SuppressWarnings(value = "all")
	@Override
	@Transactional
	public void saveOrUpdate(List<T> entitys) throws Exception {
		try {
			getDao().saveOrUpdate(entitys);
		} catch (Exception e) {
			logger.error("Error Save Entity:", e);
		}
	}

	/*@SuppressWarnings(value = "all")
	@Override
	public void getPage(T query, Page<T> page, String orderBy) {
		List<WhereClauseParam> whereList = null;
		try {
			whereList = getDao().createWhereList(query, false, true);
			for (int i = 0; i < whereList.size(); i++) {
				System.out.println(whereList.get(i).getName() + " 111 "
						+ whereList.get(i).getValue());
			}
			getDao().popPage(page, whereList, orderBy, page.getSize());
		} catch (Exception e) {
			logger.error("Error Get Page:", e);
		}
	}*/

	@SuppressWarnings(value = "all")
	private AbstractDao getDao() {
		return (AbstractDao<T>) applicationContext
				.getBean(getDaoName(getEntityClass()));
	}

	@SuppressWarnings(value = "all")
	protected String getDaoName(Class clazz) {
		String simpleName = clazz.getSimpleName();
		String daoName = simpleName.substring(0, 1).toLowerCase()
				+ simpleName.substring(1) + "Dao";
		return daoName;
	}

	/** 获取查询时like字符串，即在字符串两边加上%% */
	protected String getLikeString(String str) {
		if (TypeUtil.isEmpty(str)) {
			return null;
		} else {
			return "%" + str + "%";
		}
	}

}
