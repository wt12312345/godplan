package com.wt.dao;

import org.springframework.stereotype.Repository;

import com.wt.web.dao.AbstractDaoImpl;
import com.wt.entity.RecordSMS;

@Repository(value = "recordSMSDao")
public class RecordSMSDaoImpl extends AbstractDaoImpl<RecordSMS> implements
		RecordSMSDao {

	@Override
	public Class<RecordSMS> getEntityClass() {
		return RecordSMS.class;
	}

}
