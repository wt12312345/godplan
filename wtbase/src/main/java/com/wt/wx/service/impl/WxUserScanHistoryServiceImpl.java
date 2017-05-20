package com.wt.wx.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.wt.web.dao.WtCondition;
import com.wt.web.service.AbstractServiceImpl;
import com.wt.wx.dao.WxUserScanHistoryDao;
import com.wt.wx.entity.WxUserScanHistory;
import com.wt.wx.service.WxUserScanHistoryService;

@Service("wxUserScanHistoryService")
public class WxUserScanHistoryServiceImpl extends AbstractServiceImpl<WxUserScanHistory> implements WxUserScanHistoryService{

	@Resource
	private WxUserScanHistoryDao wxUserScanHistoryDao;
	
	@Override
	public Class<WxUserScanHistory> getEntityClass() {
		return WxUserScanHistory.class;
	}

	@Override
	public List<WxUserScanHistory> getOpenId(String openid,int status) throws Exception {
		List<WtCondition> conditions = new ArrayList<WtCondition>();
		conditions.add(new WtCondition(WtCondition.EQ, "openid", openid));
		conditions.add(new WtCondition(WtCondition.EQ, "status", status));
		return wxUserScanHistoryDao.findByCondition(conditions);
	}

}
