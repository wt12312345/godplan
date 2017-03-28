package com.hq.dao;

import org.springframework.stereotype.Repository;

import com.hq.web.dao.AbstractDaoImpl;
import com.hq.entity.Country;

@Repository(value = "countryDao")
public class CountryDaoImpl extends AbstractDaoImpl<Country> implements
		CountryDao {

	@Override
	public Class<Country> getEntityClass() {
		return Country.class;
	}

}
