package com.hq.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.hq.web.entity.AbstractEntity;

/** 用户留言 */
@Entity
public class UserWord extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(length = 20)
	private String realName = "";

	@Column(length = 11)
	private String mobile = "";

	public String getRealName() {
		return realName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

}
