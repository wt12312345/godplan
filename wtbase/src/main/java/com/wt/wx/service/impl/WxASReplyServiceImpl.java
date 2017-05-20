package com.wt.wx.service.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.wt.web.service.AbstractServiceImpl;
import com.wt.wx.dao.WxASReplyDao;
import com.wt.wx.entity.WxASReply;
import com.wt.wx.service.WxASReplyService;

@Service("wxASReplyService")
public class WxASReplyServiceImpl extends AbstractServiceImpl<WxASReply>
		implements WxASReplyService {

	@Resource
	private WxASReplyDao wxASReplyDao;

	@Override
	public Class<WxASReply> getEntityClass() {
		return WxASReply.class;
	}

	@Override
	public WxASReply getWordType(String wordtype) throws Exception {
		return wxASReplyDao.findUnique("wordtype", wordtype);
	}

}
