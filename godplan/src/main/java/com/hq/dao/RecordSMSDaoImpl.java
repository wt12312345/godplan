package com.hq.dao;

import org.springframework.stereotype.Repository;

import com.hq.web.dao.AbstractDaoImpl;
import com.hq.entity.RecordSMS;

@Repository(value = "recordSMSDao")
public class RecordSMSDaoImpl extends AbstractDaoImpl<RecordSMS> implements
		RecordSMSDao {

	@Override
	public Class<RecordSMS> getEntityClass() {
		return RecordSMS.class;
	}

}
