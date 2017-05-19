package com.godplan.dao;

import org.springframework.stereotype.Repository;

import com.godplan.entity.Annals;
import com.wt.web.dao.AbstractDaoImpl;

@Repository("annalsDao")
public class AnnalsDaoImpl extends AbstractDaoImpl<Annals> implements AnnalsDao {

	@Override
	public Class<Annals> getEntityClass() {
		return Annals.class;
	}

}
