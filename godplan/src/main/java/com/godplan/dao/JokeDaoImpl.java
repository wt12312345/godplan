package com.godplan.dao;

import org.springframework.stereotype.Repository;

import com.godplan.entity.Joke;
import com.wt.web.dao.AbstractDaoImpl;

@Repository(value = "jokeDao")
public class JokeDaoImpl extends AbstractDaoImpl<Joke> implements JokeDao {

	@Override
	public Class<Joke> getEntityClass() {
		return Joke.class;
	}
}
