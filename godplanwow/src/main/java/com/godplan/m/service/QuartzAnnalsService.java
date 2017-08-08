package com.godplan.m.service;

import com.godplan.entity.Annals;
import com.wt.web.service.AbstractService;

public interface QuartzAnnalsService extends AbstractService<Annals> {
	void createIndexHtml() throws Exception;

}
