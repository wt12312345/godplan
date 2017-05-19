package com.wt.manage.service;

import java.util.List;

import com.wt.entity.User;
import com.wt.web.service.AbstractService;

public interface UserService extends AbstractService<User> {
	List<User> getUserList(int page, int pageSize);

	List<User> getListById(List<Object> listId);

	User getUserByLoginName(String loginName);

	User getUserByMobile(String mobile);

}
