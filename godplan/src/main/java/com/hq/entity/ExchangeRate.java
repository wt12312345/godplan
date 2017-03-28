package com.hq.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.hq.web.entity.AbstractEntity;

/** 汇率设置 */
@Entity
public class ExchangeRate extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(length = 100)
	private String name = "";
	@Column(length = 10)
	private String symbol = "";
	/**
	 * 7.2等等
	 */
	@Column(columnDefinition = "double default 0")
	private double rate = 0;

	public String getName() {
		return name;
	}

	public String getSymbol() {
		return symbol;
	}

	public double getRate() {
		return rate;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

}
