package com.godplan.m.service;

import java.util.List;

import com.wt.CallBackBo;
import com.godplan.entity.UserSys;
import com.wt.web.service.AbstractService;

public interface UserSysService extends AbstractService<UserSys> {

	UserSys getByLoginName(String account) throws Exception;

	CallBackBo changePwd(long userSysId, String oldpwd, String newpwd);

	List<UserSys> getUserSys() throws Exception;

	List<UserSys> getUserSys(String keyWord) throws Exception;

}
