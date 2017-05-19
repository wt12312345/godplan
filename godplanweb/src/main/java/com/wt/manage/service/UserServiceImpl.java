package com.wt.manage.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wt.constant.EnumCom;
import com.wt.dao.UserDao;
import com.wt.entity.User;
import com.wt.web.dao.WtCondition;
import com.wt.web.service.AbstractServiceImpl;

@Service("userService")
public class UserServiceImpl extends AbstractServiceImpl<User> implements
		UserService {

	@Resource
	private UserDao userDao;

	@Override
	public Class<User> getEntityClass() {
		return User.class;
	}

	@Override
	public List<User> getUserList(int page, int pageSize) {
		List<WtCondition> conditions = new ArrayList<WtCondition>();
		conditions.add(new WtCondition(WtCondition.NE, "status", EnumCom.DELETE
				.getIndex()));
		return userDao.findByCondition(pageSize, conditions, page);
	}

	@Override
	public List<User> getListById(List<Object> listId) {
		if (listId.size() == 0) {
			return new ArrayList<>();
		}
		List<WtCondition> conditions = new ArrayList<WtCondition>();
		conditions.add(new WtCondition("id", listId));
		return userDao.findByCondition(conditions);
	}

	@Override
	public User getUserByLoginName(String loginName) {
		List<WtCondition> conditions = new ArrayList<WtCondition>();
		conditions.add(new WtCondition(WtCondition.EQ, "loginName", loginName));
		return userDao.findByConditionUnique(conditions);
	}

	@Override
	public User getUserByMobile(String mobile) {
		List<WtCondition> conditions = new ArrayList<WtCondition>();
		conditions.add(new WtCondition(WtCondition.EQ, "mobile", mobile));
		return userDao.findByConditionUnique(conditions);
	}
}
