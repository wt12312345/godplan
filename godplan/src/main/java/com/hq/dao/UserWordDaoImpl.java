package com.hq.dao;

import org.springframework.stereotype.Repository;

import com.hq.entity.UserWord;
import com.hq.web.dao.AbstractDaoImpl;

@Repository(value = "userWordDao")
public class UserWordDaoImpl extends AbstractDaoImpl<UserWord> implements
		UserWordDao {

	@Override
	public Class<UserWord> getEntityClass() {
		return UserWord.class;
	}

}
