package com.hq.agent.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.hq.web.entity.AbstractEntity;

/**
 * 访问来源
 * 
 * @author Administrator
 * 
 */
@Entity
public class VisitAgent extends AbstractEntity {

	private static final long serialVersionUID = 1L;
	@Column(unique = true, length = 1000)
	private String info;

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
}
