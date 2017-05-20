package com.godplan.web.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.godplan.dao.HeadComboDao;
import com.godplan.entity.HeadCombo;
import com.wt.web.dao.WtCondition;
import com.wt.web.service.AbstractServiceImpl;

@Service("headComboService")
public class HeadComboServiceImpl extends AbstractServiceImpl<HeadCombo>
		implements HeadComboService {
	@Resource
	private HeadComboDao headComboDao;

	@Override
	public Class<HeadCombo> getEntityClass() {
		return HeadCombo.class;
	}

	@Override
	public HeadCombo getByKey(String key) throws Exception {
		List<WtCondition> conditions = new ArrayList<WtCondition>();
		conditions.add(new WtCondition(WtCondition.EQ, "keyId", key));
		return headComboDao.findByConditionUnique(conditions);
	}

}
