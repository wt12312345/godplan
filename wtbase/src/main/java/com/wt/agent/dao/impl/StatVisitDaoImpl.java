package com.wt.agent.dao.impl;

import org.springframework.stereotype.Repository;
import com.wt.agent.dao.StatVisitDao;
import com.wt.agent.entity.StatVisit;
import com.wt.web.dao.AbstractDaoImpl;

@Repository("userVisiRecordtDao")
public class StatVisitDaoImpl extends AbstractDaoImpl<StatVisit> implements
StatVisitDao {
	@Override
	public Class<StatVisit> getEntityClass() {
		return StatVisit.class;
	}
}
