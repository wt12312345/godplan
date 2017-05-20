package com.wt.wx.dao.impl;

import org.springframework.stereotype.Repository;
import com.wt.web.dao.AbstractDaoImpl;
import com.wt.wx.dao.WxUserScanDao;
import com.wt.wx.entity.WxUserScan;

@Repository("wxUserScanDao")
public class WxUserScanDaoImpl extends AbstractDaoImpl<WxUserScan> implements
		WxUserScanDao {

	@Override
	public Class<WxUserScan> getEntityClass() {
		return WxUserScan.class;
	}
}
