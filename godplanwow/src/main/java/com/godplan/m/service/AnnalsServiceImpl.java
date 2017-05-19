package com.godplan.m.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.godplan.dao.AnnalsDao;
import com.godplan.entity.Annals;
import com.wt.web.dao.WtCondition;
import com.wt.web.service.AbstractServiceImpl;

@Service("annalsService")
public class AnnalsServiceImpl extends AbstractServiceImpl<Annals> implements
		AnnalsService {
	@Resource
	AnnalsDao annalsDao;

	@Override
	public Class<Annals> getEntityClass() {
		return Annals.class;
	}

	@Override
	public List<Annals> getAnnalsList(String keyWord) {
		List<WtCondition> conditions = new ArrayList<WtCondition>();
		conditions.add(new WtCondition(WtCondition.LIKE, "name", keyWord));
		conditions.add(new WtCondition(WtCondition.LIKE, "content", keyWord));
		List<Annals> listUser = annalsDao.findByCondition(0, conditions, 0);
		return listUser;

	}

	@Override
	public List<Annals> getAnnalsList(int pageSize, int page) {
		List<WtCondition> conditions = new ArrayList<WtCondition>();
		conditions.add(new WtCondition(WtCondition.DESC, "year"));
		conditions.add(new WtCondition(WtCondition.DESC, "month"));
		conditions.add(new WtCondition(WtCondition.DESC, "day"));
		conditions.add(new WtCondition(WtCondition.DESC, "hour"));
		conditions.add(new WtCondition(WtCondition.DESC, "minute"));
		conditions.add(new WtCondition(WtCondition.DESC, "second"));
		List<Annals> listUser = annalsDao.findByCondition(pageSize, conditions,
				page);
		return listUser;

	}
}
