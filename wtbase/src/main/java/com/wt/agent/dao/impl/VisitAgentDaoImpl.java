package com.wt.agent.dao.impl;

import org.springframework.stereotype.Repository;

import com.wt.agent.dao.VisitAgentDao;
import com.wt.agent.entity.VisitAgent;
import com.wt.web.dao.AbstractDaoImpl;

@Repository("userAgentDao")
public class VisitAgentDaoImpl extends AbstractDaoImpl<VisitAgent> implements
		VisitAgentDao {

	@Override
	public Class<VisitAgent> getEntityClass() {
		return VisitAgent.class;
	}

}
