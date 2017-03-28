package com.hq.agent.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hq.agent.dao.RecordVisitDao;
import com.hq.agent.dao.StatVisitDao;
import com.hq.agent.entity.RecordVisit;
import com.hq.agent.entity.StatVisit;
import com.hq.agent.service.RecordVisitService;
import com.hq.base.util.TimeUtil;
import com.hq.base.util.TypeUtil;
import com.hq.web.dao.WtCondition;
import com.hq.web.service.AbstractServiceImpl;

@Service("userVisitService")
public class RecordVisitServiceImpl extends AbstractServiceImpl<RecordVisit>
		implements RecordVisitService {

	@Resource
	private RecordVisitDao userVisitDao;
	@Resource
	private StatVisitDao userVisitStatDao;
	@Resource
	private RecordVisitService userVisitService;

	@Override
	public Class<RecordVisit> getEntityClass() {
		return RecordVisit.class;
	}

	@Override
	public List<RecordVisit> queryUserVisitMonthMin(int year, int month,
			int monthmin, int model) {
		List<WtCondition> conditions = new ArrayList<WtCondition>();
		conditions.add(new WtCondition(WtCondition.EQ, "year", year));
		conditions.add(new WtCondition(WtCondition.LE, "month", month));
		conditions.add(new WtCondition(WtCondition.GE, "month", monthmin));
		conditions.add(new WtCondition(WtCondition.EQ, "module", model));
		conditions.add(new WtCondition(WtCondition.DESC, "month"));
		return userVisitDao.findByCondition(0, conditions, 0);
	}

	@Override
	public List<RecordVisit> queryUserVisitDayMin(int year, int month, int day,
			int daymin, int model) {
		List<WtCondition> conditions = new ArrayList<WtCondition>();
		conditions.add(new WtCondition(WtCondition.EQ, "year", year));
		conditions.add(new WtCondition(WtCondition.EQ, "month", month));
		conditions.add(new WtCondition(WtCondition.LE, "day", day));
		conditions.add(new WtCondition(WtCondition.GE, "day", daymin));
		conditions.add(new WtCondition(WtCondition.EQ, "module", model));
		conditions.add(new WtCondition(WtCondition.DESC, "month"));
		return userVisitDao.findByCondition(0, conditions, 0);
	}

	@Override
	public List<RecordVisit> queryUserVisitMonth(int year, int month, int model) {
		List<WtCondition> conditions = new ArrayList<WtCondition>();
		conditions.add(new WtCondition(WtCondition.EQ, "year", year));
		conditions.add(new WtCondition(WtCondition.EQ, "month", month));
		conditions.add(new WtCondition(WtCondition.EQ, "module", model));
		conditions.add(new WtCondition(WtCondition.DESC, "month"));
		return userVisitDao.findByCondition(0, conditions, 0);
	}

	@Override
	public List<RecordVisit> queryUserVisitDay(int year, int month, int day,
			int model) {
		List<WtCondition> conditions = new ArrayList<WtCondition>();
		conditions.add(new WtCondition(WtCondition.EQ, "year", year));
		conditions.add(new WtCondition(WtCondition.EQ, "month", month));
		conditions.add(new WtCondition(WtCondition.EQ, "day", day));
		conditions.add(new WtCondition(WtCondition.EQ, "module", model));
		conditions.add(new WtCondition(WtCondition.DESC, "month"));
		return userVisitDao.findByCondition(0, conditions, 0);
	}

	@Override
	public List<RecordVisit> queryUserVisitMonthOrMin(int year, int month,
			int yesrmin, int monthmin, int model) {
		return userVisitDao.queryUserVisitMonthOrMin(year, month, yesrmin,
				monthmin, model);
	}

	@Override
	public List<RecordVisit> queryUserVisitDayOrMin(int year, int month, int day,
			int monthmin, int daymin, int model) {
		return userVisitDao.queryUserVisitDayOrMin(year, month, day, monthmin,
				daymin, model);
	}

	@Override
	public List<RecordVisit> getList(List<Object> listId, String time,
			String endTime) {
		return null;
//		List<WtCondition> conditions = new ArrayList<WtCondition>();
//		if (!time.equals("") && !endTime.equals("")) {
//			try {
//				conditions.add(new WtCondition(WtCondition.GT, "createTime",
//						TimeUtil.getTimeByStr(time)));
//				conditions.add(new WtCondition(WtCondition.LT, "createTime",
//						TimeUtil.getTimeByStr(endTime)));
//			} catch (ParseException e) {
//				e.printStackTrace();
//				logger.info("访问查询String转Date异常" + e.getMessage(), e);
//			}
//		}
//		if (time.equals("") && endTime.equals("") && listId != null) {
//			conditions.add(new WtCondition("module", listId));
//		}
//		if (!time.equals("") && !endTime.equals("") || time.equals("")
//				&& endTime.equals("") && listId != null) {
//			conditions.add(new WtCondition(WtCondition.DESC, "module"));
//			conditions.add(new WtCondition(WtCondition.DESC, "phoneBrand"));
//			conditions.add(new WtCondition(WtCondition.DESC, "phoneType"));
//			conditions.add(new WtCondition(WtCondition.DESC, "networkType"));
//		}
//		return userVisitDao.findByCondition(conditions);
	}

	@Override
	public void saveOrUpdateEntity(StatVisit userVisitStat)
			throws Exception {
		userVisitStatDao.saveOrUpdate(userVisitStat);
	}

	/*
	 * (non-Javadoc) 定时统计任务 每天
	 * 
	 * @see com.hq.agent.service.UserVisitService#getMonthUserVisitStat()
	 */
	@Transactional
	@Override
	public void getDayUserVisitStat() {
		Calendar cal = Calendar.getInstance();
		int month = cal.get(Calendar.MONTH) + 1;
		int year = cal.get(Calendar.YEAR);
		int day = cal.get(Calendar.DATE);
		List<String> listPv = new ArrayList<String>();
		List<RecordVisit> listDayAll = userVisitService.getUserVisitList(2015,
				12, 8);
		// List<UserVisit> list1 = new ArrayList<UserVisit>();
		List<List<RecordVisit>> list2 = new ArrayList<List<RecordVisit>>();
		Map<String, List<RecordVisit>> countMapUV = new HashMap<String, List<RecordVisit>>();
		for (int i = 0; i < listDayAll.size(); i++) {
			RecordVisit userVisit = listDayAll.get(i);
			String pv = userVisit.getModule() + "," + userVisit.getLongId()
					+ "," + userVisit.getRemark() + "," + userVisit.getTitle();
			listPv.add(pv);
			// if (!list1.contains(listDayAll.get(i))) {
			// list1.add(listDayAll.get(i));
			// }
			String uv = userVisit.getModule() + "," + userVisit.getLongId()
					+ "," + userVisit.getRemark() + "," + userVisit.getTitle()
					+ userVisit.getUser_id();
			// 根据module，longId，remark，title,userId分类添加到map
			if (countMapUV.containsKey(uv)) {
				countMapUV.get(uv).add(userVisit);
			} else {
				List<RecordVisit> listUserVisit = new ArrayList<RecordVisit>();
				listUserVisit.add(userVisit);
				countMapUV.put(uv, listUserVisit);
			}
		}
		Iterator<String> keyUV = countMapUV.keySet().iterator();
		while (keyUV.hasNext()) {
			String keyName = keyUV.next();
			List<RecordVisit> list = countMapUV.get(keyName);
			list2.add(list);
		}
		List<List<RecordVisit>> list5 = new ArrayList<List<RecordVisit>>();
		for (int z = 0; z < list2.size(); z++) {
			List<RecordVisit> list3 = new ArrayList<RecordVisit>();
			List<RecordVisit> list4 = list2.get(z);
			for (int i = 0; i < list4.size(); i++) {
				if (!list3.contains(list2.get(z))) {
					list3.add(list4.get(i));
				}
			}
			list5.add(list3);
		}
		Set<String> uniqueSetPv = new HashSet<String>(listPv);
		// 根据module，longId，remark，title统计pv
		Map<String, Integer> countMapPv = new HashMap<String, Integer>();
		for (String temp : uniqueSetPv) {
			int countAll = Collections.frequency(listPv, temp);
			countMapPv.put(temp, countAll);
		}
		Iterator<String> key = countMapPv.keySet().iterator();
		while (key.hasNext()) {
			try {
				StatVisit userVisitStat = new StatVisit();
				String keyName = key.next();
				int countPv = countMapPv.get(keyName);
				String[] ary = keyName.split(",");
				int moduleNew = 0;
				String remarkNew = "";
				String titleNew = "";
				long longIdNew = 0;
				int countUv = 0;
				for (int i = 0; i < ary.length; i++) {
					moduleNew = TypeUtil.toInt(ary[0]);
					longIdNew = TypeUtil.toInt(ary[1]);
					remarkNew = ary[2];
					titleNew = ary[3];
				}
				// 统计UV
				for (int i = 0; i < list5.size(); i++) {
					List<RecordVisit> listSum = list5.get(i);
					String uv = listSum.get(0).getModule() + ","
							+ listSum.get(0).getLongId() + ","
							+ listSum.get(0).getRemark() + ","
							+ listSum.get(0).getTitle();
					if (uv.equals(keyName)) {
						countUv = listSum.size();
					}
				}
				userVisitStat.setAddTime(new Date());
				userVisitStat.setDay(day);
				userVisitStat.setModule(moduleNew);
				userVisitStat.setMonth(month);
				userVisitStat.setPv(countPv);
				userVisitStat.setRemark(remarkNew);
				userVisitStat.setTitle(titleNew);
				userVisitStat.setUv(countUv);
				userVisitStat.setYear(year);
				userVisitStat.setLongId(longIdNew);
				userVisitStatDao.saveOrUpdate(userVisitStat);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public StatVisit getList(Integer module, Integer day, Integer month,
			Integer year) {
		List<WtCondition> conditions = new ArrayList<WtCondition>();
		if (module != null) {
			conditions.add(new WtCondition(WtCondition.EQ, "module", module));
		}
		if (day != null) {
			conditions.add(new WtCondition(WtCondition.EQ, "day", day));
		}
		if (month != null) {
			conditions.add(new WtCondition(WtCondition.EQ, "month", month));
		}
		if (year != null) {
			conditions.add(new WtCondition(WtCondition.EQ, "year", year));
		}
		return userVisitStatDao.findByConditionUnique(conditions);
	}

	@Override
	public List<StatVisit> getList(Integer day, Integer month, Integer year) {
		List<WtCondition> conditions = new ArrayList<WtCondition>();
		conditions.add(new WtCondition(WtCondition.EQ, "day", day));
		conditions.add(new WtCondition(WtCondition.EQ, "month", month));
		conditions.add(new WtCondition(WtCondition.EQ, "year", year));
		return userVisitStatDao.findByCondition(conditions);
	}

	@Override
	public List<StatVisit> getListMonth() {
		List<WtCondition> conditions = new ArrayList<WtCondition>();
		Calendar ca = Calendar.getInstance();
		ca.setTime(new Date());
		int year = ca.get(Calendar.YEAR);// 获取年份
		conditions.add(new WtCondition(WtCondition.EQ, "year", year));
		conditions.add(new WtCondition(WtCondition.GT, "month", 0));
		conditions.add(new WtCondition(WtCondition.DESC, "month"));
		return userVisitStatDao.findByCondition(conditions);
	}

	@Override
	public List<StatVisit> getList() {
		List<WtCondition> conditions = new ArrayList<WtCondition>();
		conditions.add(new WtCondition(WtCondition.EQ, "day", 0));
		return userVisitStatDao.findByCondition(conditions);
	}

	@Override
	public List<RecordVisit> getUserVisitList(int year, int month, int day) {
		List<WtCondition> conditions = new ArrayList<WtCondition>();
		conditions.add(new WtCondition(WtCondition.EQ, "day", day));
		conditions.add(new WtCondition(WtCondition.EQ, "month", month));
		conditions.add(new WtCondition(WtCondition.EQ, "year", year));
		// conditions.add(new WtCondition(WtCondition.EQ, "module", 2009));
		return userVisitDao.findByCondition(conditions);
	}
}
