package com.wt.wx.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.wt.web.dao.WtCondition;
import com.wt.web.service.AbstractServiceImpl;
import com.wt.wx.dao.WxNewsReplyDao;
import com.wt.wx.entity.WxNewsReply;
import com.wt.wx.service.WxNewsReplyService;

@Service("wxNewsReplyService")
public class WxNewsReplyServiceImpl extends AbstractServiceImpl<WxNewsReply> implements WxNewsReplyService{

	@Resource
	private WxNewsReplyDao wxNewsReplyDao;
	
	@Override
	public Class<WxNewsReply> getEntityClass() {
		return WxNewsReply.class;
	}

//	@Override
//	public List<WxNewsReply> getwt(String cruxword) throws Exception {
//		return wxNewsReplyDao.getwt(cruxword);
//	}
	@Override
	public List<WxNewsReply> getwt(String cruxword) throws Exception {
		List<WtCondition> condition = new ArrayList<WtCondition>();
		condition.add(new WtCondition(WtCondition.DESC, "rank"));
		condition.add(new WtCondition(WtCondition.EQ, "cruxword", cruxword));
		return wxNewsReplyDao.findList("cruxword", cruxword);
	}

}
