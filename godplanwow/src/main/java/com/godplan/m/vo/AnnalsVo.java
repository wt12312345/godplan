package com.godplan.m.vo;

import java.util.ArrayList;
import java.util.List;

import com.godplan.entity.Annals;

public class AnnalsVo {
	private long id = 0;
	private int status = 0;
	private String title = "";
	private String tags = "";
	private String content = "";
	private int year = 0;
	private int month = 0;
	private int day = 0;
	private int hour = 0;
	private int minute = 0;
	private int second = 0;
	private int level = 0;

	public static List<AnnalsVo> getVo(List<Annals> list) {
		List<AnnalsVo> listVo = new ArrayList<AnnalsVo>();
		for (int i = 0; i < list.size(); i++) {
			Annals item = list.get(i);
			listVo.add(getVo(item));
		}
		return listVo;
	}

	public static AnnalsVo getVo(Annals item) {
		AnnalsVo itemVo = new AnnalsVo();
		itemVo.setId(item.getId());
		itemVo.setTitle(item.getTitle());
		itemVo.setContent(item.getContent());
		itemVo.setTags(item.getTags());
		itemVo.setYear(item.getYear());
		itemVo.setMonth(item.getMonth());
		itemVo.setDay(item.getDay());
		itemVo.setHour(item.getHour());
		itemVo.setMinute(item.getMinute());
		itemVo.setSecond(item.getSecond());
		itemVo.setLevel(item.getLevel());
		return itemVo;
	}

	public int getStatus() {
		return status;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

	public int getYear() {
		return year;
	}

	public int getMonth() {
		return month;
	}

	public int getDay() {
		return day;
	}

	public int getHour() {
		return hour;
	}

	public int getMinute() {
		return minute;
	}

	public int getSecond() {
		return second;
	}

	public int getLevel() {
		return level;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}

	public void setSecond(int second) {
		this.second = second;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

}
