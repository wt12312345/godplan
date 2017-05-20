package com.wt.wx.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.wt.web.entity.AbstractEntity;

@Entity
public class WxAccount extends AbstractEntity {
	private static final long serialVersionUID = 1L;

	/** 微信接入成功后的appid */
	@Column(length = 50)
	private String appId = "";
	private String token = "";
	private long tokenTime = 0;
	private String jsApiTicket = "";
	private long jsApiTicketTime = 0;
	private String connectToken = "";
	/** 微信接入消息加密密匙 */
	@Column(length = 43)
	private String appSecret = "";
	private String mchId = "";
	private String mchKey = "";
	private String mchPath = "";
	@Column(length = 5000)
	private String menu = "";

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public long getTokenTime() {
		return tokenTime;
	}

	public void setTokenTime(long tokenTime) {
		this.tokenTime = tokenTime;
	}

	public String getJsApiTicket() {
		return jsApiTicket;
	}

	public void setJsApiTicket(String jsApiTicket) {
		this.jsApiTicket = jsApiTicket;
	}

	public long getJsApiTicketTime() {
		return jsApiTicketTime;
	}

	public void setJsApiTicketTime(long jsApiTicketTime) {
		this.jsApiTicketTime = jsApiTicketTime;
	}

	public String getConnectToken() {
		return connectToken;
	}

	public void setConnectToken(String connectToken) {
		this.connectToken = connectToken;
	}

	public String getAppSecret() {
		return appSecret;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

	public String getMchId() {
		return mchId;
	}

	public void setMchId(String mchId) {
		this.mchId = mchId;
	}

	public String getMchKey() {
		return mchKey;
	}

	public void setMchKey(String mchKey) {
		this.mchKey = mchKey;
	}

	public String getMchPath() {
		return mchPath;
	}

	public void setMchPath(String mchPath) {
		this.mchPath = mchPath;
	}

	public String getMenu() {
		return menu;
	}

	public void setMenu(String menu) {
		this.menu = menu;
	}

}
