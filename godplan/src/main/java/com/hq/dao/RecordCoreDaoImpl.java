package com.hq.dao;

import org.springframework.stereotype.Repository;

import com.hq.entity.RecordCore;
import com.hq.web.dao.AbstractDaoImpl;

@Repository(value = "recordCoreDao")
public class RecordCoreDaoImpl extends AbstractDaoImpl<RecordCore> implements
		RecordCoreDao {

	@Override
	public Class<RecordCore> getEntityClass() {
		return RecordCore.class;
	}

}
