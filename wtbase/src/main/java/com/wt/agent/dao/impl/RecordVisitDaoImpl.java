package com.wt.agent.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.wt.agent.dao.RecordVisitDao;
import com.wt.agent.entity.RecordVisit;
import com.wt.web.dao.AbstractDaoImpl;

@SuppressWarnings("unchecked")
@Repository("userVisitDao")
public class RecordVisitDaoImpl extends AbstractDaoImpl<RecordVisit> implements
		RecordVisitDao {
	@Override
	public Class<RecordVisit> getEntityClass() {
		return RecordVisit.class;
	}

	@Override
	public List<RecordVisit> queryUserVisitMonthOrMin(int year, int month,
			int yesrmin, int monthmin, int model) {
		String hql = "from UserVisit where (year=? and month<=? and month>=1 and module=?) or (year=? and month>? and month<=12 and module=?) order by month desc";
		Query query = getSession().createQuery(hql);
		query.setInteger(0, year);
		query.setInteger(1, month);
		query.setInteger(2, model);
		query.setInteger(3, yesrmin);
		query.setInteger(4, monthmin);
		query.setInteger(5, model);
		return query.list();
	}

	@Override
	public List<RecordVisit> queryUserVisitDayOrMin(int year, int month, int day,
			int monthmin, int daymin, int model) {
		String hql = "from UserVisit where  (year=? and month=? and day<=? and day>=1 and  module=?) or (year=? and month=? and day>? and day<=30 and module=?) order by day desc";
		Query query = getSession().createQuery(hql);
		query.setInteger(0, year);
		query.setInteger(1, month);
		query.setInteger(2, day);
		query.setInteger(3, model);
		query.setInteger(4, year);
		query.setInteger(5, monthmin);
		query.setInteger(6, daymin);
		query.setInteger(7, model);
		return query.list();
	}
}
