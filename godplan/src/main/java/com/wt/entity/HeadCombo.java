package com.wt.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;

import com.wt.web.entity.AbstractEntity;

/**
 * 微信头像组合
 * 
 * @author wt12312345
 * 
 */
@Entity
public class HeadCombo extends AbstractEntity {
	/**
	 *     
	 */
	private static final long serialVersionUID = 1L;

	private int num = 0;
	@Column(unique = true, length = 20)
	private String keyId;

	/**
	 * openid headimgurl,openid headimgurl,openid headimgurl,
	 */
	@Lob
	private String userInfo = "";

	private int numJoin = 0;
	private String openId;

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getNumJoin() {
		return numJoin;
	}

	public void setNumJoin(int numJoin) {
		this.numJoin = numJoin;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(String userInfo) {
		this.userInfo = userInfo;
	}

	public String getKeyId() {
		return keyId;
	}

	public void setKeyId(String keyId) {
		this.keyId = keyId;
	}

}