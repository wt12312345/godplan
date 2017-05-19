package com.godplan.m.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.godplan.constant.EnumCom;
import com.godplan.dao.JokeDao;
import com.godplan.entity.Joke;
import com.wt.web.dao.WtCondition;
import com.wt.web.service.AbstractServiceImpl;

@Service("jokeService")
public class JokeServiceImpl extends AbstractServiceImpl<Joke> implements
		JokeService {
	@Resource
	private JokeDao jokeDao;

	@Override
	public Class<Joke> getEntityClass() {
		return Joke.class;
	}

	@Override
	public List<Joke> getList(int page,int pageSize) throws Exception {
		List<WtCondition> conditions = new ArrayList<WtCondition>();
		return jokeDao.findByCondition(pageSize, conditions, page);
	}

	@Override
	public List<Joke> getListNew(long lastId) throws Exception {
		List<WtCondition> conditions = new ArrayList<WtCondition>();
		conditions.add(new WtCondition(WtCondition.GT, "id", lastId));
		conditions.add(new WtCondition(WtCondition.EQ, "status", 1));
		return jokeDao.findByCondition(conditions);
	}

	@Override
	public List<Joke> getListValid() throws Exception {
		List<WtCondition> conditions = new ArrayList<WtCondition>();
		conditions.add(new WtCondition(WtCondition.EQ, "status", EnumCom.Enable.getIndex()));
		return jokeDao.findByCondition(conditions);
	}
}
