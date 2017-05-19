package com.godplan.dao;

import org.springframework.stereotype.Repository;

import com.godplan.entity.HeadCombo;
import com.wt.web.dao.AbstractDaoImpl;

@Repository("headComboDao")
public class HeadComboDaoImpl extends AbstractDaoImpl<HeadCombo> implements HeadComboDao {

	@Override
	public Class<HeadCombo> getEntityClass() {
		return HeadCombo.class;
	}

}
