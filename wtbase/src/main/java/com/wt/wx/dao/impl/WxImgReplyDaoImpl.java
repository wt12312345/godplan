package com.wt.wx.dao.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.wt.web.dao.AbstractDaoImpl;
import com.wt.wx.dao.WxImgReplyDao;
import com.wt.wx.entity.WxImgReply;


@Repository("wxImgReplyDao")
public class WxImgReplyDaoImpl extends AbstractDaoImpl<WxImgReply> implements WxImgReplyDao{

	@Override
	public Class<WxImgReply> getEntityClass() {
		return WxImgReply.class;
	}

	@Override
	public WxImgReply getmediaid(String picname) throws Exception {
		Query query = getSession().createQuery(
				"from WxImgReply where picname=?");
		query.setString(0, picname);
		return (WxImgReply) query.uniqueResult();
	}

}
