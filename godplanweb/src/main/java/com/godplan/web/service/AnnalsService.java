package com.godplan.web.service;

import java.util.List;

import com.godplan.entity.Annals;
import com.wt.web.service.AbstractService;

public interface AnnalsService extends AbstractService<Annals> {
	List<Annals> getList(int status, int page, int pageSize) throws Exception;
}
