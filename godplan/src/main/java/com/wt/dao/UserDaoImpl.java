package com.wt.dao;

import org.springframework.stereotype.Repository;

import com.wt.entity.User;
import com.wt.web.dao.AbstractDaoImpl;

@Repository("userDao")
public class UserDaoImpl extends AbstractDaoImpl<User> implements UserDao {

	@Override
	public Class<User> getEntityClass() {
		return User.class;
	}

	
}
