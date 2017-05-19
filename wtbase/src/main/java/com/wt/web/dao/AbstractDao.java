package com.wt.web.dao;

import java.util.List;
import java.util.Map;

public interface AbstractDao<T> {
	void saveOrUpdate(T obj) throws Exception;

	void saveOrUpdate(List<T> objs) throws Exception;

	void delete(T obj) throws Exception;

	void delete(long id) throws Exception;

	void delete(String where) throws Exception;

	long findCount() throws Exception;

	T find(long id) throws Exception;

	/**
	 * 根据字段获取entity
	 * 
	 * @param columnName
	 *            字段名
	 * @param 字段值
	 */
	List<T> findList(String columnName, Object columnValue) throws Exception;

	List<T> findAll() throws Exception;

	int findCountByCondition(List<WtCondition> conditions);

	List<T> findByCondition(int pageSize, List<WtCondition> conditions, int page);

	List<T> findByCondition(int page, int pageSize,
			List<WtCondition> conditions,
			Map<String, List<WtCondition>> mapTableConditions);

	List<T> findByCondition(List<WtCondition> conditions);

	T findUnique(String columnName, Object columnValue) throws Exception;

	T findByConditionUnique(List<WtCondition> conditions);

	/**
	 * 執行函數返回結果
	 * */
	Object executeHql(String hql) throws Exception;

	Object attachObject(Object o) throws Exception;

	void refresh(Object o) throws Exception;

	/** 获取返回的class */
	@SuppressWarnings(value = "all")
	abstract Class getEntityClass();

}
