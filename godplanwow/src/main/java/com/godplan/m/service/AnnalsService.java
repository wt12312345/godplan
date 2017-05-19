package com.godplan.m.service;

import java.util.List;

import com.godplan.entity.Annals;
import com.wt.web.service.AbstractService;

public interface AnnalsService extends AbstractService<Annals> {
	List<Annals> getAnnalsList(String keyWord);

	List<Annals> getAnnalsList(int pageSize, int page);
}
