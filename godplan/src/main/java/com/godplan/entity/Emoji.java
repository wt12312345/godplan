package com.godplan.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.wt.web.entity.AbstractEntity;

@Entity
public class Emoji extends AbstractEntity {
	/**
	 *     
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(unique = true, length = 50)
	private String name;

	@Column(length = 1000)
	private String symbol = "";

	public String getName() {
		return name;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
}
