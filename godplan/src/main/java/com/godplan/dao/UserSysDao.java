package com.godplan.dao;

import com.wt.web.dao.AbstractDao;
import com.godplan.entity.UserSys;

public interface UserSysDao extends AbstractDao<UserSys>{

	public UserSys findUserByNameAndPwd(String loginName, String password);
	//public List<UserSys> findManagerWithPaging(int pageSize, int page);
}
