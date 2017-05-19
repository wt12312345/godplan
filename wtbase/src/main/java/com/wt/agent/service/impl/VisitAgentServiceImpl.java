package com.wt.agent.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wt.agent.dao.VisitAgentDao;
import com.wt.agent.entity.VisitAgent;
import com.wt.agent.service.VisitAgentService;
import com.wt.web.dao.WtCondition;
import com.wt.web.service.AbstractServiceImpl;

@Service("userAgentService")
public class VisitAgentServiceImpl extends AbstractServiceImpl<VisitAgent>
		implements VisitAgentService {
	@Resource
	private VisitAgentDao userAgentDao;

	@Override
	public Class<VisitAgent> getEntityClass() {
		return VisitAgent.class;
	}

	@Override
	public List<VisitAgent> getInfo(String agent) {
		List<WtCondition> conditions = new ArrayList<WtCondition>();
		conditions.add(new WtCondition(WtCondition.EQ, "info", agent));
		return userAgentDao.findByCondition(0, conditions, 0);
	}
}
