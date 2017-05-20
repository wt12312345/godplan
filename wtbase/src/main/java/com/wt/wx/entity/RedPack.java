package com.wt.wx.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.wt.web.entity.AbstractEntity;

@Entity
public class RedPack extends AbstractEntity{
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	 private String mchPath = "";
	/**
	 * 红包描述
	 */
	 private String wxRemark = "";
	/**
	 * 红包祝福语
	 */
	 private String wishing = "";
	/**
	 * 发送者名称
	 */
	 private String send_name = "";
	/**
	 * IP
	 */
	 private String wxIp = "";
	/**
	 * 发送给用户的openid
	 */
	 private String re_openid = "";
	/**
	 *红包金额
	 */
	 private int total_amount = 0;
	/**
	 * 发放人数
	 */
	 private int number = 1;
	/**
	 * 商户号
	 */
	 private String mchId = "";
	/**
	 * 公众账号appid
	 */
	 private String appId = "";
	/**
	 * 红包名称
	 */
	 private String act_name = "";
	/**
	 * 商户支付秘匙
	 */
	 private String wxKey = "";
	
	public String getMchPath() {
		return mchPath;
	}
	public void setMchPath(String mchPath) {
		this.mchPath = mchPath;
	}
	public String getWishing() {
		return wishing;
	}
	public void setWishing(String wishing) {
		this.wishing = wishing;
	}
	public String getSend_name() {
		return send_name;
	}
	public void setSend_name(String send_name) {
		this.send_name = send_name;
	}
	public String getRe_openid() {
		return re_openid;
	}
	public void setRe_openid(String re_openid) {
		this.re_openid = re_openid;
	}
	public int getTotal_amount() {
		return total_amount;
	}
	public void setTotal_amount(int total_amount) {
		this.total_amount = total_amount;
	}
	public String getMchId() {
		return mchId;
	}
	public void setMchId(String mchId) {
		this.mchId = mchId;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getAct_name() {
		return act_name;
	}
	public void setAct_name(String act_name) {
		this.act_name = act_name;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getWxRemark() {
		return wxRemark;
	}
	public void setWxRemark(String wxRemark) {
		this.wxRemark = wxRemark;
	}
	public String getWxIp() {
		return wxIp;
	}
	public void setWxIp(String wxIp) {
		this.wxIp = wxIp;
	}
	public String getWxKey() {
		return wxKey;
	}
	public void setWxKey(String wxKey) {
		this.wxKey = wxKey;
	}
	
}
