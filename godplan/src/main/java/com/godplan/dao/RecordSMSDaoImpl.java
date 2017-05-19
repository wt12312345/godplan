package com.godplan.dao;

import org.springframework.stereotype.Repository;

import com.wt.web.dao.AbstractDaoImpl;
import com.godplan.entity.RecordSMS;

@Repository(value = "recordSMSDao")
public class RecordSMSDaoImpl extends AbstractDaoImpl<RecordSMS> implements
		RecordSMSDao {

	@Override
	public Class<RecordSMS> getEntityClass() {
		return RecordSMS.class;
	}

}
