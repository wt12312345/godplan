package com.hq.manage.service;

import java.util.List;

import com.hq.entity.User;
import com.hq.web.service.AbstractService;

public interface UserService extends AbstractService<User> {
	List<User> getUserList(int page, int pageSize);

	List<User> getListById(List<Object> listId);

	User getUserByLoginName(String loginName);

	User getUserByMobile(String mobile);

}
