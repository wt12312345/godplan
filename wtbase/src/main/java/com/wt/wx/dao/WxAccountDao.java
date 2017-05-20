package com.wt.wx.dao;

import com.wt.web.dao.AbstractDao;
import com.wt.wx.entity.WxAccount;

public interface WxAccountDao extends AbstractDao<WxAccount> {
	public WxAccount getWxAccount() throws Exception;
}
