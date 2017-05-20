package com.wt.wx.dao.impl;

import java.util.List;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.wt.web.dao.AbstractDaoImpl;
import com.wt.wx.dao.WxNewsReplyDao;
import com.wt.wx.entity.WxNewsReply;

@Repository("wxNewsReplyDao")
public class WxNewsReplyDaoImpl extends AbstractDaoImpl<WxNewsReply> implements WxNewsReplyDao{

	@Override
	public Class<WxNewsReply> getEntityClass() {
		return WxNewsReply.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<WxNewsReply> getwt(String cruxword) throws Exception {
		Query query = getSession().createQuery(
				"from WxNewsReply where cruxword=? order by rank" );
		query.setString(0, cruxword);
		return query.list();
	}

}
