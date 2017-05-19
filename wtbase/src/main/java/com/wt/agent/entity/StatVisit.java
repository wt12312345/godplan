package com.wt.agent.entity;

import java.util.Date;

import com.wt.web.entity.AbstractEntity;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 访问统计
 * 
 * @author Administrator
 * 
 */
@Entity
public class StatVisit extends AbstractEntity {

	private static final long serialVersionUID = 1L;
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date addTime;
	/**
	 * 数量
	 */
	private int pv = 0;
	/**
	 * 用户数量
	 */
	private int uv = 0;
	/**
	 * 进入模块：1首页。。。等等，各个项目都可以自定义
	 */
	private int module = 0;
	/**
	 * 描述
	 */
	private String remark = "";
	/**
	 * 通用ID
	 */
	private long longId = 0;
	/**
	 * 标题
	 */
	private String title = "";
	/**
	 * 年
	 */
	private int year = 0;
	/**
	 * 月
	 */
	private int month = 0;
	/**
	 * 日
	 */
	private int day = 0;

	public int getModule() {
		return module;
	}

	public void setModule(int module) {
		this.module = module;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getPv() {
		return pv;
	}

	public void setPv(int pv) {
		this.pv = pv;
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

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public int getUv() {
		return uv;
	}

	public void setUv(int uv) {
		this.uv = uv;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public long getLongId() {
		return longId;
	}

	public void setLongId(long longId) {
		this.longId = longId;
	}

}
