package com.godplan.web.service;

import java.util.List;

import com.godplan.entity.User;
import com.wt.web.service.AbstractService;

public interface UserService extends AbstractService<User> {
	List<User> getUserList(int page, int pageSize);

	List<User> getListById(List<Object> listId);

	User getUserByLoginName(String loginName);

	User getUserByMobile(String mobile);

}
