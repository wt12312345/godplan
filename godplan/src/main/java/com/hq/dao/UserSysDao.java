package com.hq.dao;

import com.hq.web.dao.AbstractDao;
import com.hq.entity.UserSys;

public interface UserSysDao extends AbstractDao<UserSys>{

	public UserSys findUserByNameAndPwd(String loginName, String password);
	//public List<UserSys> findManagerWithPaging(int pageSize, int page);
}
