package com.wt.wx.dao.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.wt.web.dao.AbstractDaoImpl;
import com.wt.wx.dao.WxCruxReplyDao;
import com.wt.wx.entity.WxCruxReply;

@Repository("wxCruxReplyDao")
public class WxCruxReplyDaoImpl extends AbstractDaoImpl<WxCruxReply> implements WxCruxReplyDao{

	@Override
	public WxCruxReply getKeywordsReply(String cruxword) throws Exception {
		Query query = getSession().createQuery(
				"from WxCruxReply where cruxword=?");
		query.setString(0, cruxword);
		return (WxCruxReply) query.uniqueResult();
	}

	@Override
	public Class<WxCruxReply> getEntityClass() {
		return WxCruxReply.class;
	}

	@Override
	public WxCruxReply getwordtype(String wordtype) throws Exception {
		Query query = getSession().createQuery(
				"from WxCruxReply where wordtype=?");
		query.setString(0, wordtype);
		return (WxCruxReply) query.uniqueResult();
	}

}
