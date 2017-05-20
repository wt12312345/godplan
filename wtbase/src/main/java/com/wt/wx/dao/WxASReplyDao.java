package com.wt.wx.dao;

import java.util.List;
import com.wt.web.dao.AbstractDao;
import com.wt.wx.entity.WxASReply;

public interface WxASReplyDao extends AbstractDao<WxASReply>{
	
	public List<WxASReply> findCruxWord() throws Exception;
	public WxASReply getwordtype(String wordtype) throws Exception;

}
