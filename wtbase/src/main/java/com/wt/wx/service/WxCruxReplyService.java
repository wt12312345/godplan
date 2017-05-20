package com.wt.wx.service;

import com.wt.web.service.AbstractService;
import com.wt.wx.entity.WxCruxReply;


public interface WxCruxReplyService extends AbstractService<WxCruxReply>{

	//public JsonReply getSubSribeReply();
	public WxCruxReply getKeyWordsReply(String cruxWord);
	//public JsonReply getAutoReply();
	public WxCruxReply getWordType(String wordType) throws Exception;
	
}
