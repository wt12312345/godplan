package com.godplan.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.wt.web.dao.AbstractDaoImpl;
import com.godplan.entity.UserSys;

@Repository("userSysDao")
@SuppressWarnings("all")
public class UserSysDaoImpl extends AbstractDaoImpl<UserSys> implements UserSysDao{

	@Override
	public Class getEntityClass() {
		return UserSys.class;
	}

	@Override
	public UserSys findUserByNameAndPwd(String loginName, String password) {
		logger.debug("User Login: Username:" + loginName);
		String hql = "from UserSys where loginName = ? and password = ?";
		Session session = getSession();
		Query query = session.createQuery(hql);
		query.setString(0, loginName);
		query.setString(1, password);
		return (UserSys) query.uniqueResult();
	}

	/*@Override
	public List<UserSys> findManagerWithPaging(int pageSize, int page) {
		return null;
	}*/

}
