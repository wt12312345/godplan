package com.hq.dao;

import org.springframework.stereotype.Repository;

import com.hq.entity.ExchangeRate;
import com.hq.web.dao.AbstractDaoImpl;

@Repository(value = "exchangeRateDao")
public class ExchangeRateDaoImpl extends AbstractDaoImpl<ExchangeRate>
		implements ExchangeRateDao {

	@Override
	public Class<ExchangeRate> getEntityClass() {
		return ExchangeRate.class;
	}

}
