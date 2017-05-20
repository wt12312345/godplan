package com.wt.wx.dao;

import com.wt.web.dao.AbstractDao;
import com.wt.wx.entity.WxImgReply;


public interface WxImgReplyDao extends AbstractDao<WxImgReply>{
	public WxImgReply getmediaid(String picname) throws Exception;
}
