package com.hq.dao;

import org.springframework.stereotype.Repository;

import com.hq.entity.User;
import com.hq.web.dao.AbstractDaoImpl;

@Repository("userDao")
public class UserDaoImpl extends AbstractDaoImpl<User> implements UserDao {

	@Override
	public Class<User> getEntityClass() {
		return User.class;
	}

	
}
