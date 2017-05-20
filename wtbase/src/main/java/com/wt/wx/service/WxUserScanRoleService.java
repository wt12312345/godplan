package com.wt.wx.service;

import java.util.List;

import com.wt.web.service.AbstractService;
import com.wt.wx.entity.WxUserScanRole;

public interface WxUserScanRoleService extends AbstractService<WxUserScanRole>{
	public WxUserScanRole getKeyId(long keyId) throws Exception;
	public List<WxUserScanRole> getScanRoleByKeyId(int stutas) throws Exception;
	
}
