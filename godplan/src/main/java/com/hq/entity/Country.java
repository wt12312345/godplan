package com.hq.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.hq.web.entity.AbstractEntity;

/**
 * 国家
 * 
 * @author wt12312345
 * 
 */
@Entity
public class Country extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 国家名
	 */
	@Column(length = 100, unique = true)
	private String name;
	/**
	 * 国旗图片
	 */
	@Column(length = 100)
	private String imgFlag = "";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImgFlag() {
		return imgFlag;
	}

	public void setImgFlag(String imgFlag) {
		this.imgFlag = imgFlag;
	}

}