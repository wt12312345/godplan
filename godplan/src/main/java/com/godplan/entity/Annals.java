package com.godplan.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;

import com.wt.web.entity.AbstractEntity;

/**
 * 编年史
 * 
 * @author wt12312345
 * 
 */
@Entity
public class Annals extends AbstractEntity {
	/**
	 *     
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 0：冻结、1可用、-1删除
	 */
	@Column(columnDefinition = "int default 0")
	private int status = 0;
	@Column(length = 100)
	private String title = "";
	@Column(length = 1000)
	private String tags = "";
	@Lob
	private String content = "";
	@Column(columnDefinition = "int default 0")
	private int year = 0;
	@Column(columnDefinition = "int default 0")
	private int month = 0;
	@Column(columnDefinition = "int default 0")
	private int day = 0;
	@Column(columnDefinition = "int default 0")
	private int hour = 0;
	@Column(columnDefinition = "int default 0")
	private int minute = 0;
	@Column(columnDefinition = "int default 0")
	private int second = 0;
	@Column(columnDefinition = "int default 0")
	private int level = 0;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public int getMinute() {
		return minute;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}

	public int getSecond() {
		return second;
	}

	public void setSecond(int second) {
		this.second = second;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

}