package com.wt.wx.dao.impl;

import java.util.List;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import com.wt.web.dao.AbstractDaoImpl;
import com.wt.wx.dao.WxASReplyDao;
import com.wt.wx.entity.WxASReply;

@Repository("wxASReplyDao")
public class WxASReplyDaoImpl extends AbstractDaoImpl<WxASReply> implements WxASReplyDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<WxASReply> findCruxWord() throws Exception {
		Query query = getSession().createQuery(
				"from WxASReply");
		return (List<WxASReply>)query.list();
	}
	
 
	@Override
	public Class<WxASReply> getEntityClass() {
		return WxASReply.class;
	}


	@Override
	public WxASReply getwordtype(String wordtype) throws Exception {
		Query query = getSession().createQuery(
				"from WxASReply where wordtype=?");
		query.setString(0, wordtype);
		return (WxASReply) query.uniqueResult();
	}

}
