package com.wt.wx.service.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.wt.web.service.AbstractServiceImpl;
import com.wt.wx.dao.WxImgReplyDao;
import com.wt.wx.entity.WxImgReply;
import com.wt.wx.service.WxImgReplyService;

@Service("wxImgReplyService")
public class WxImgReplyServiceImpl extends AbstractServiceImpl<WxImgReply>
		implements WxImgReplyService {
	@Resource
	private WxImgReplyDao wxImgReplyDao;

	@Override
	public Class<WxImgReply> getEntityClass() {
		return WxImgReply.class;
	}

	@Override
	public WxImgReply getmediaid(String picname) throws Exception {
		return wxImgReplyDao.findUnique("picname", picname);
	}

}
