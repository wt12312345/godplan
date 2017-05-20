package com.wt.wx.dao.impl;

import org.springframework.stereotype.Repository;
import com.wt.web.dao.AbstractDaoImpl;
import com.wt.wx.dao.WxUserScanHistoryDao;
import com.wt.wx.entity.WxUserScanHistory;

@Repository("wxUserScanHistoryDao")
public class WxUserScanHistoryDaoImpl  extends AbstractDaoImpl<WxUserScanHistory> implements WxUserScanHistoryDao{

	@Override
	public Class<WxUserScanHistory> getEntityClass() {
		return WxUserScanHistory.class;
	}

}
