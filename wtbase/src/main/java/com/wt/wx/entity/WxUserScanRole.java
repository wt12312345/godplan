package com.wt.wx.entity;

import javax.persistence.Entity;

import com.wt.web.entity.AbstractEntity;

/**
 * 角色管理
 * @author Administrator
 *
 */
@Entity
public class WxUserScanRole extends AbstractEntity{

	private static final long serialVersionUID = 1L;
	
	private long keyId=0;
	private String name="";
	/**
	 * 0. 未刪除   -1.表示已刪除
	 */
	private int status=0;
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getKeyId() {
		return keyId;
	}
	public void setKeyId(long keyId) {
		this.keyId = keyId;
	}
	
}
