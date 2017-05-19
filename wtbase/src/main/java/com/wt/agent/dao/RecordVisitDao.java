package com.wt.agent.dao;


import java.util.List;

import com.wt.agent.entity.RecordVisit;
import com.wt.web.dao.AbstractDao;

public interface RecordVisitDao extends AbstractDao<RecordVisit> {
	
	List<RecordVisit> queryUserVisitMonthOrMin(int year, int month, int yesrmin,
			int monthmin, int model);

	List<RecordVisit> queryUserVisitDayOrMin(int year, int month, int day,
			int monthmin, int daymin, int model);
}
