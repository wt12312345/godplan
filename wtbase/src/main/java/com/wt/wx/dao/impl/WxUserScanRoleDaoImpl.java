package com.wt.wx.dao.impl;

import org.springframework.stereotype.Repository;
import com.wt.web.dao.AbstractDaoImpl;
import com.wt.wx.dao.WxUserScanRoleDao;
import com.wt.wx.entity.WxUserScanRole;

@Repository("wxUserScanRoleDao")
public class WxUserScanRoleDaoImpl extends AbstractDaoImpl<WxUserScanRole> implements WxUserScanRoleDao{

	@Override
	public Class<WxUserScanRole> getEntityClass() {
		return WxUserScanRole.class;
	}

}
