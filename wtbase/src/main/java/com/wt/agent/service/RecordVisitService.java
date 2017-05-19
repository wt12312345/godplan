package com.wt.agent.service;

import java.util.List;

import com.wt.agent.entity.RecordVisit;
import com.wt.agent.entity.StatVisit;
import com.wt.web.service.AbstractService;

/**
 * 
 * @author Administrator
 * 
 */
public interface RecordVisitService extends AbstractService<RecordVisit> {
	List<RecordVisit> queryUserVisitMonthMin(int year, int month, int monthmin,
			int model);

	List<RecordVisit> queryUserVisitDayMin(int year, int month, int day,
			int daymin, int model);

	List<RecordVisit> queryUserVisitMonth(int year, int month, int model);

	List<RecordVisit> queryUserVisitDay(int year, int month, int day, int model);

	List<RecordVisit> getUserVisitList(int year, int month, int day);

	List<RecordVisit> queryUserVisitMonthOrMin(int year, int month, int yesrmin,
			int monthmin, int model);

	List<RecordVisit> queryUserVisitDayOrMin(int year, int month, int day,
			int monthmin, int daymin, int model);

	List<RecordVisit> getList(List<Object> listId, String time, String endTime);

	void saveOrUpdateEntity(StatVisit userVisitStat) throws Exception;

	StatVisit getList(Integer module, Integer day, Integer month,
			Integer year);

	List<StatVisit> getList(Integer day, Integer month, Integer year);

	List<StatVisit> getListMonth();

	List<StatVisit> getList();

	/**
	 * 定时任务 添加访问统计
	 */
	void getDayUserVisitStat();
}