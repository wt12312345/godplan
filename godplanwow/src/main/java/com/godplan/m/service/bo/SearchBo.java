package com.godplan.m.service.bo;

import java.util.Date;

public class SearchBo {
	private long id = 0;
	private int status = 0;
	private String keyWord = "";
	private Date startTime = null;
	private Date endTime = null;

	public SearchBo() {
	}

	public SearchBo(String keyWord) {
		this.keyWord = keyWord;
	}

	public SearchBo(Date startTime, Date endTime, String keyWord) {
		this.startTime = startTime;
		this.endTime = endTime;
		this.keyWord = keyWord;
	}

	public long getId() {
		return id;
	}

	public int getStatus() {
		return status;
	}

	public String getKeyWord() {
		return keyWord;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public Date getStartTime() {
		return startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
}
