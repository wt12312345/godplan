package com.hq.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.hq.web.entity.AbstractEntity;

/** 仓储管理费用设置 */
@Entity
public class WarehouseFee extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(length = 100)
	private String name = "";
	@Column(columnDefinition = "double default 0")
	private double price = 0;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
