package com.wt.wx.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wt.base.util.TimeUtil;
import com.wt.web.dao.WtCondition;
import com.wt.web.service.AbstractServiceImpl;
import com.wt.wx.dao.WxUserScanDao;
import com.wt.wx.entity.WxUserScan;
import com.wt.wx.service.WxUserScanService;

@Service("wxUserScanService")
public class WxUserScanServiceImpl extends AbstractServiceImpl<WxUserScan> implements WxUserScanService{

	@Resource
	private WxUserScanDao wxUserScanDao;
	
	@Override
	public Class<WxUserScan> getEntityClass() {
		return WxUserScan.class;
	}

	@Override
	public WxUserScan getOpenid(String openid) throws Exception {
		return wxUserScanDao.findUnique("openid", openid);
	}

	@Override
	public List<WxUserScan> getSum(long keyId) throws Exception {
		return wxUserScanDao.findList("keyId", keyId);
	}
	@Override
	public List<WxUserScan> getRoleByTime(String startTime, String endTime)
			throws Exception {
		List<WtCondition> conditions = new ArrayList<WtCondition>();
		conditions.add(new WtCondition(WtCondition.GT, "createTime",TimeUtil.getTimeByStr(startTime)));
		conditions.add(new WtCondition(WtCondition.LT, "createTime",TimeUtil.getTimeByStr(endTime)));
		return wxUserScanDao.findByCondition(conditions);
	}
}
