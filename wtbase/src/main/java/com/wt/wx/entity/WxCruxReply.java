package com.wt.wx.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.wt.web.entity.AbstractEntity;

@Entity
public class WxCruxReply extends AbstractEntity {

	private static final long serialVersionUID = 1L;
	/**
	 * 关键词
	 */
	@Column(unique = true)
	private String cruxword = "";
	/**
	 * 类型
	 */
	private String wordtype = "text";
	/**
	 * 文本回复内容
	 */
	private String recontent = "";
	/**
	 * 回复内容 链接地址
	 */
	private String linkadd = "";
	/**
	 * 链接回复内容
	 */
	private String linkcontent = "";

	public String getLinkadd() {
		return linkadd;
	}

	public void setLinkadd(String linkadd) {
		this.linkadd = linkadd;
	}

	public String getCruxword() {
		return cruxword;
	}

	public void setCruxword(String cruxword) {
		this.cruxword = cruxword;
	}

	public String getWordtype() {
		return wordtype;
	}

	public void setWordtype(String wordtype) {
		this.wordtype = wordtype;
	}

	public String getRecontent() {
		return recontent;
	}

	public void setRecontent(String recontent) {
		this.recontent = recontent;
	}

	/*
	 * public Wxrecontent getRecontent() { return recontent; } public void
	 * setRecontent(Wxrecontent recontent) { this.recontent = recontent; }
	 */
	public String getLinkcontent() {
		return linkcontent;
	}

	public void setLinkcontent(String linkcontent) {
		this.linkcontent = linkcontent;
	}

}
