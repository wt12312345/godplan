package com.godplan.dao;

import org.springframework.stereotype.Repository;

import com.godplan.entity.RecordCore;
import com.wt.web.dao.AbstractDaoImpl;

@Repository(value = "recordCoreDao")
public class RecordCoreDaoImpl extends AbstractDaoImpl<RecordCore> implements
		RecordCoreDao {

	@Override
	public Class<RecordCore> getEntityClass() {
		return RecordCore.class;
	}

}
