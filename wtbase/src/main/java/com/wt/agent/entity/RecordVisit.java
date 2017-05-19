package com.wt.agent.entity;

import java.util.Date;

import com.wt.web.entity.AbstractEntity;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 用户访问记录
 * 
 * @author Administrator
 * 
 */
@Entity
public class RecordVisit extends AbstractEntity {

	private static final long serialVersionUID = 1L;
	/**
	 * 用户IP地址
	 */
	private String ip = "";
	/**
	 * 进入模块：1首页。。。等等，各个项目都可以自定义
	 */
	private int module = 0;
	private int year = 0;
	private int month = 0;
	private int day = 0;
	/**
	 * 操作系统
	 */
	private String os = "";
	/**
	 * 版本
	 */
	private String osVersion = "";
	/**
	 * 品牌
	 */
	private String phoneBrand = "";
	/**
	 * 机型
	 */
	private String phoneType = "";
	/**
	 * 网络类型
	 */
	private String networkType = "";
	/**
	 * 浏览器
	 */
	private String browser = "";
	/**
	 * 用户ID
	 */
	private long user_id = 0;
	/**
	 * 通用id
	 */
	private long longId = 0;
	/**
	 * 标题
	 */
	private String title = "";
	/**
	 * 描述
	 */
	private String remark = "";
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public int getModule() {
		return module;
	}
	public void setModule(int module) {
		this.module = module;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public String getOs() {
		return os;
	}
	public void setOs(String os) {
		this.os = os;
	}
	public String getOsVersion() {
		return osVersion;
	}
	public void setOsVersion(String osVersion) {
		this.osVersion = osVersion;
	}
	public String getPhoneBrand() {
		return phoneBrand;
	}
	public void setPhoneBrand(String phoneBrand) {
		this.phoneBrand = phoneBrand;
	}
	public String getPhoneType() {
		return phoneType;
	}
	public void setPhoneType(String phoneType) {
		this.phoneType = phoneType;
	}
	public String getNetworkType() {
		return networkType;
	}
	public void setNetworkType(String networkType) {
		this.networkType = networkType;
	}
	public String getBrowser() {
		return browser;
	}
	public void setBrowser(String browser) {
		this.browser = browser;
	}
	public long getUser_id() {
		return user_id;
	}
	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}
	public long getLongId() {
		return longId;
	}
	public void setLongId(long longId) {
		this.longId = longId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	


}
