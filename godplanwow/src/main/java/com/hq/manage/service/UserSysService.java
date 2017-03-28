package com.hq.manage.service;

import java.util.List;

import com.hq.CallBackBo;
import com.hq.entity.UserSys;
import com.hq.web.service.AbstractService;

public interface UserSysService extends AbstractService<UserSys> {

	UserSys getByLoginName(String account) throws Exception;

	CallBackBo changePwd(long userSysId, String oldpwd, String newpwd);

	List<UserSys> getUserSysByRole(long roleId) throws Exception;

	List<UserSys> getUserSys() throws Exception;

	List<UserSys> getUserSys(String keyWord) throws Exception;

	List<UserSys> getUserSysByStatus(int status) throws Exception;

	List<UserSys> getListByUriStorage(long storageId) throws Exception;

	List<UserSys> getListByUriMail(long mailId) throws Exception;

	List<UserSys> getListByUriSupplier(long supplierId) throws Exception;
}
