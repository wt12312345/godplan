package com.godplan.dao;

import org.springframework.stereotype.Repository;

import com.godplan.entity.RecordChose;
import com.wt.web.dao.AbstractDaoImpl;

@Repository("recordChoseDao")
public class RecordChoseDaoImpl extends AbstractDaoImpl<RecordChose> implements
		RecordChoseDao {

	@Override
	public Class<RecordChose> getEntityClass() {
		return RecordChose.class;
	}

}
