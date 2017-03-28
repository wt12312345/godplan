package com.hq.agent.dao;


import java.util.List;

import com.hq.agent.entity.RecordVisit;
import com.hq.web.dao.AbstractDao;

public interface RecordVisitDao extends AbstractDao<RecordVisit> {
	
	List<RecordVisit> queryUserVisitMonthOrMin(int year, int month, int yesrmin,
			int monthmin, int model);

	List<RecordVisit> queryUserVisitDayOrMin(int year, int month, int day,
			int monthmin, int daymin, int model);
}
