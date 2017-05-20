package com.wt.wx.service.impl;


import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.wt.web.service.AbstractServiceImpl;
import com.wt.wx.dao.WxUserScanRoleDao;
import com.wt.wx.entity.WxUserScanRole;
import com.wt.wx.service.WxUserScanRoleService;

@Service("wxUserScanRoleService")
public class WxUserScanRoleServiceImpl extends AbstractServiceImpl<WxUserScanRole> implements WxUserScanRoleService{

	@Resource
	private WxUserScanRoleDao wxUserScanRoleDao;
	
	@Override
	public Class<WxUserScanRole> getEntityClass() {
		return WxUserScanRole.class;
	}

	@Override
	public WxUserScanRole getKeyId(long keyId) throws Exception {
		return wxUserScanRoleDao.findUnique("keyId", keyId);
	}

	@Override
	public List<WxUserScanRole> getScanRoleByKeyId(int stutas) throws Exception {
		return wxUserScanRoleDao.findList("status", 0);
	}

}
