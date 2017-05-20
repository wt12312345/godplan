package com.wt.wx.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.wt.web.dao.AbstractDaoImpl;
import com.wt.wx.dao.WxAccountDao;
import com.wt.wx.dao.impl.WxAccountDaoImpl;
import com.wt.wx.entity.WxAccount;

@Repository("wxAccountDao")
public class WxAccountDaoImpl extends AbstractDaoImpl<WxAccount> implements
		WxAccountDao {
	@SuppressWarnings(value = "all")
	@Override
	public Class getEntityClass() {
		return WxAccount.class;
	}

	@Override
	public WxAccount getWxAccount() throws Exception {
		List<WxAccount> list = findAll();
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

}
