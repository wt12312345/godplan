package com.godplan.m.service;

import java.util.List;

import com.godplan.entity.Joke;
import com.godplan.m.service.bo.SearchBo;
import com.wt.web.domain.Page;
import com.wt.web.service.AbstractService;

public interface JokeService extends AbstractService<Joke> {

	List<Joke> getList(Page page, String orderBy, SearchBo search) throws Exception;

}
