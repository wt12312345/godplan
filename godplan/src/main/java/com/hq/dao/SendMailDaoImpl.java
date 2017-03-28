package com.hq.dao;

import org.springframework.stereotype.Repository;

import com.hq.web.dao.AbstractDaoImpl;
import com.hq.entity.SendMail;

@Repository(value = "sendMailDao")
public class SendMailDaoImpl extends AbstractDaoImpl<SendMail> implements
		SendMailDao {

	@Override
	public Class<SendMail> getEntityClass() {
		return SendMail.class;
	}

}
