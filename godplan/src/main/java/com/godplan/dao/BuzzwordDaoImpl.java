package com.godplan.dao;

import org.springframework.stereotype.Repository;

import com.godplan.entity.Buzzword;
import com.wt.web.dao.AbstractDaoImpl;

@Repository("buzzwordDao")
public class BuzzwordDaoImpl extends AbstractDaoImpl<Buzzword> implements BuzzwordDao {

	@Override
	public Class<Buzzword> getEntityClass() {
		return Buzzword.class;
	}

}
