package com.hq.dao;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.hq.web.dao.AbstractDaoImpl;
import com.hq.entity.Dictionary;

@Repository("dictionaryDao")
@SuppressWarnings("all")
public class DictionaryDaoImpl extends AbstractDaoImpl<Dictionary> implements
DictionaryDao {

	@Override
	public Class<Dictionary> getEntityClass() {
		return Dictionary.class;
	}
}
