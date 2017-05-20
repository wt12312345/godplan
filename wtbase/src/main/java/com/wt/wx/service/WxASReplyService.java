package com.wt.wx.service;

import com.wt.web.service.AbstractService;
import com.wt.wx.entity.WxASReply;


public interface WxASReplyService extends AbstractService<WxASReply>{
	public WxASReply getWordType(String wordtType) throws Exception;
}
