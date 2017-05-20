package com.wt.wx.dao;

import java.util.List;

import com.wt.web.dao.AbstractDao;
import com.wt.wx.entity.WxNewsReply;


public interface WxNewsReplyDao extends AbstractDao<WxNewsReply>{

	public List<WxNewsReply> getwt(String cruxword) throws Exception;
}
