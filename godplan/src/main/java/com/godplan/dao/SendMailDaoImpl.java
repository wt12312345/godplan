package com.godplan.dao;

import org.springframework.stereotype.Repository;

import com.wt.web.dao.AbstractDaoImpl;
import com.godplan.entity.SendMail;

@Repository(value = "sendMailDao")
public class SendMailDaoImpl extends AbstractDaoImpl<SendMail> implements
		SendMailDao {

	@Override
	public Class<SendMail> getEntityClass() {
		return SendMail.class;
	}

}
