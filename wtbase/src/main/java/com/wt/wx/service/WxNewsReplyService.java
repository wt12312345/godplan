package com.wt.wx.service;

import java.util.List;
import com.wt.web.service.AbstractService;
import com.wt.wx.entity.WxNewsReply;

public interface WxNewsReplyService extends AbstractService<WxNewsReply>{

	public List<WxNewsReply> getwt(String cruxWord) throws Exception;
}
