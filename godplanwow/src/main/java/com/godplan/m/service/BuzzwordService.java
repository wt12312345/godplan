package com.godplan.m.service;

import java.util.List;

import com.godplan.entity.Buzzword;
import com.wt.web.domain.Page;
import com.wt.web.service.AbstractService;

public interface BuzzwordService extends AbstractService<Buzzword> {
	List<Buzzword> getList(Page page, String orderBy, String keyWord);
}
