package com.wt.entity;

import javax.persistence.Entity;
import javax.persistence.Lob;

import com.wt.web.entity.AbstractEntity;

@Entity
public class RecordSMS extends AbstractEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String mobile = "";
	@Lob
	private String content = "";
	private String msg = "";
	private String err = "";
	/**
	 * 验证码
	 */
	private String authCode = "";
	private String ip = "";
	/**
	 * 0失败，1成功
	 */
	private int status = 0;

	public String getMobile() {
		return mobile;
	}

	public String getContent() {
		return content;
	}

	public String getMsg() {
		return msg;
	}

	public String getErr() {
		return err;
	}

	public String getAuthCode() {
		return authCode;
	}

	public String getIp() {
		return ip;
	}

	public int getStatus() {
		return status;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public void setErr(String err) {
		this.err = err;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
