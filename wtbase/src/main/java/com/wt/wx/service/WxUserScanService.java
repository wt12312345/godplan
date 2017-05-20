package com.wt.wx.service;

import java.util.List;

import com.wt.web.service.AbstractService;
import com.wt.wx.entity.WxUserScan;

public interface WxUserScanService extends AbstractService<WxUserScan> {
	public WxUserScan getOpenid(String openId) throws Exception;

	public List<WxUserScan> getSum(long keyId) throws Exception;

	public List<WxUserScan> getRoleByTime(String startTime, String endTime)
			throws Exception;

}
