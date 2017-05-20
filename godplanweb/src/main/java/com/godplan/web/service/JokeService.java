package com.godplan.web.service;

import java.util.List;

import com.godplan.entity.Joke;
import com.wt.web.service.AbstractService;

public interface JokeService extends AbstractService<Joke> {

	List<Joke> getList(int page, int pageSize) throws Exception;
	
	List<Joke> getListValid() throws Exception;

	List<Joke> getListNew(long lastId) throws Exception;
}
