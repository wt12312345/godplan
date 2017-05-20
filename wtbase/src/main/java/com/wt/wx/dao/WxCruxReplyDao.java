package com.wt.wx.dao;

import com.wt.web.dao.AbstractDao;
import com.wt.wx.entity.WxCruxReply;


public interface WxCruxReplyDao extends AbstractDao<WxCruxReply>{

	public WxCruxReply getKeywordsReply(String cruxword) throws Exception;
	public WxCruxReply getwordtype(String wordtype) throws Exception;
}
