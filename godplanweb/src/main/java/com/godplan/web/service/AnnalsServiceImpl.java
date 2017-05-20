package com.godplan.web.service;

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
	private AnnalsDao annalsDao;

	@Override
	public Class<Annals> getEntityClass() {
		return Annals.class;
	}

	@Override
	public List<Annals> getList(int status, int page, int pageSize)
			throws Exception {
		List<WtCondition> conditions = new ArrayList<WtCondition>();
		conditions.add(new WtCondition(WtCondition.EQ, "status", status));
		return annalsDao.findByCondition(pageSize, conditions, page);
	}

}
