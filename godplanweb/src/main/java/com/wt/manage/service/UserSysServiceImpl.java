package com.wt.manage.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wt.CallBackBo;
import com.wt.base.util.MD5Util;
import com.wt.base.util.TypeUtil;
import com.wt.dao.UserSysDao;
import com.wt.entity.UserSys;
import com.wt.web.dao.WtCondition;
import com.wt.web.service.AbstractServiceImpl;

@Service("userSysService")
@SuppressWarnings("all")
public class UserSysServiceImpl extends AbstractServiceImpl<UserSys> implements
		UserSysService {

	@Resource
	private UserSysDao userSysDao;

	@Override
	public UserSys getByLoginName(String loginName) {
		List<WtCondition> conditions = new ArrayList<WtCondition>();
		conditions.add(new WtCondition(WtCondition.EQ, "loginName", loginName));
		return userSysDao.findByConditionUnique(conditions);
	}

	@Override
	public CallBackBo changePwd(long userSysId, String oldpwd, String newpwd) {
		CallBackBo cb = new CallBackBo();
		try {
			UserSys userSys = getEntity(userSysId);
			if (userSys == null) {
				cb.setErr("用户不存在");
			} else if (!oldpwd.equals(userSys.getPassword())) {
				cb.setErr("密码错误");
			} else {
				userSys.setPassword(MD5Util.string2MD5(newpwd));
				userSysDao.saveOrUpdate(userSys);
				cb.setResult(true);
			}
		} catch (Exception e) {
			logger.error("修改密码异常：" + e.getMessage(), e);
			cb.setErr("系统错误！");
		}
		return cb;
	}

	@Override
	public Class getEntityClass() {
		return UserSys.class;
	}

	@Override
	public List<UserSys> getUserSysByRole(long roleId) throws Exception {
		List<WtCondition> conditions = new ArrayList<WtCondition>();
		conditions.add(new WtCondition(WtCondition.EQ, "role.id", roleId));
		return userSysDao.findByCondition(conditions);
	}

	@Override
	public List<UserSys> getUserSys() throws Exception {
		return getUserSys("");
	}

	@Override
	public List<UserSys> getUserSys(String keyWord) throws Exception {
		List<WtCondition> conditions = new ArrayList<WtCondition>();
		conditions.add(new WtCondition(WtCondition.NE, "status", -1));
		if (!TypeUtil.isEmpty(keyWord)) {
			conditions.add(new WtCondition(WtCondition.LIKE, "loginName",
					keyWord));
		}
		return userSysDao.findByCondition(conditions);
	}

	@Override
	public List<UserSys> getUserSysByStatus(int status) throws Exception {
		List<WtCondition> conditions = new ArrayList<WtCondition>();
		conditions.add(new WtCondition(WtCondition.EQ, "status", status));
		return userSysDao.findByCondition(conditions);
	}

	@Override
	public List<UserSys> getListByUriStorage(long storageId) throws Exception {
		List<WtCondition> conditions = new ArrayList<WtCondition>();
		conditions.add(new WtCondition(WtCondition.LIKE, "uriStorage",
				storageId + ","));
		return userSysDao.findByCondition(conditions);
	}

	@Override
	public List<UserSys> getListByUriMail(long mailId) throws Exception {
		List<WtCondition> conditions = new ArrayList<WtCondition>();
		conditions.add(new WtCondition(WtCondition.LIKE, "uriMail", mailId
				+ ","));
		return userSysDao.findByCondition(conditions);
	}

	@Override
	public List<UserSys> getListByUriSupplier(long supplierId) throws Exception {
		List<WtCondition> conditions = new ArrayList<WtCondition>();
		conditions.add(new WtCondition(WtCondition.LIKE, "uriSupplier",
				supplierId + ","));
		return userSysDao.findByCondition(conditions);
	}
}
