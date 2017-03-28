package com.hq.web.service;

import java.util.List;

/**
 * 针对数据的Service 操作
 * */
public interface AbstractService<T> {
	public T getEntity(long id);

	public void deleteEntity(long id) throws Exception;

	public void deleteEntity(T entity) throws Exception;

	public List<T> getAll();

	public void saveOrUpdate(List<T> entitys) throws Exception;

	public void saveOrUpdate(T entity) throws Exception;

}
