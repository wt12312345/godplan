package com.wt.wx.service;

import java.util.List;

import com.wt.web.service.AbstractService;
import com.wt.wx.entity.WxUserScanHistory;

public interface WxUserScanHistoryService extends AbstractService<WxUserScanHistory>{
	public List<WxUserScanHistory> getOpenId(String openId,int status) throws Exception;

}
