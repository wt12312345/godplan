package com.hq.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.hq.web.entity.AbstractEntity;

@Entity
public class Dictionary extends AbstractEntity {

	private static final long serialVersionUID = 1L;
	@Column(unique = true)
	private String keyId = "";
	private String name = "";
	private int valint = 0;
	private long vallong = 0;
	private double valdbl = 0.0;
	private String valstr = "";
	private String remark = "";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getValint() {
		return valint;
	}

	public void setValint(int valint) {
		this.valint = valint;
	}

	public double getValdbl() {
		return valdbl;
	}

	public void setValdbl(double valdbl) {
		this.valdbl = valdbl;
	}

	public String getValstr() {
		return valstr;
	}

	public void setValstr(String valstr) {
		this.valstr = valstr;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getKeyId() {
		return keyId;
	}

	public void setKeyId(String keyId) {
		this.keyId = keyId;
	}

	public long getVallong() {
		return vallong;
	}

	public void setVallong(long vallong) {
		this.vallong = vallong;
	}

}
