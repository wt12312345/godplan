package com.hq.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.hq.web.entity.AbstractEntity;

/** 系统管理人员角色 */
@Entity
public class UserSys extends AbstractEntity {
	private static final long serialVersionUID = 1L;

	@Column(length = 100)
	private String password = "";

	@Column(unique = true, length = 30)
	private String loginName;

	/** 最后登录时间 */
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date lastLoginTime;
	/**
	 * 菜单id，格式为：1,2,3
	 */
	private String menus = "";
	private String email = "";
	/**
	 * 是否有效 1 有效 0 禁用 -1删除
	 */
	private int status = 0;

	/**
	 * 管理名称
	 */
	private String name = "";

	/**
	 * 操作权限
	 */
	@Column(length = 1000)
	private String uri = "";
	/**
	 * 仓库权限
	 */
	private String uriStorage = "";
	/**
	 * 供应商权限
	 */
	private String uriSupplier = "";
	/**
	 * 邮件权限
	 */
	private String uriMail = "";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMenus() {
		return menus;
	}

	public void setMenus(String menus) {
		this.menus = menus;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUri() {
		return uri;
	}

	public String getUriStorage() {
		return uriStorage;
	}

	public String getUriSupplier() {
		return uriSupplier;
	}

	public String getUriMail() {
		return uriMail;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public void setUriStorage(String uriStorage) {
		this.uriStorage = uriStorage;
	}

	public void setUriSupplier(String uriSupplier) {
		this.uriSupplier = uriSupplier;
	}

	public void setUriMail(String uriMail) {
		this.uriMail = uriMail;
	}

}
