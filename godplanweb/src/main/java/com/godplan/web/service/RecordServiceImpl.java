package com.godplan.web.service;

import org.springframework.stereotype.Service;

import com.godplan.entity.RecordChose;
import com.wt.web.service.AbstractServiceImpl;

@Service("recordService")
public class RecordServiceImpl extends AbstractServiceImpl<RecordChose> implements RecordService {

	@Override
	public Class<RecordChose> getEntityClass() {
		return RecordChose.class;
	}

}
