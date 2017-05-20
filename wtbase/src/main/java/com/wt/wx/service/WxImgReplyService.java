package com.wt.wx.service;

import com.wt.web.service.AbstractService;
import com.wt.wx.entity.WxImgReply;


public interface WxImgReplyService extends AbstractService<WxImgReply>{
	public WxImgReply getmediaid(String picName) throws Exception;
}
