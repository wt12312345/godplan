package com.godplan.m.service;

import java.util.List;

import com.godplan.entity.Annals;
import com.godplan.m.service.bo.SearchBo;
import com.wt.web.domain.Page;
import com.wt.web.service.AbstractService;

public interface AnnalsService extends AbstractService<Annals> {
	List<Annals> getList(Page page, String orderBy, SearchBo search);
}
