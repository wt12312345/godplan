package com.godplan.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.wt.web.entity.AbstractEntity;

/** 发送邮件 */
@Entity
public class SendMail extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 收件箱地址，用空格隔开
	 */
	@Lob
	private String mailAddr = "";

	@Column(length = 100)
	private String title;

	@Lob
	private String content;

	/**
	 * 触发的人员
	 */
	private String userName = "";

	/**
	 * -1删除，1待发送，2已发送，
	 */
	@Column(columnDefinition = "int default 1")
	private int status = 1;

	/**
	 * 尝试发送次数
	 */
	@Column(columnDefinition = "int default 0")
	private int numTry = 0;

	/**
	 * 最新的错误信息
	 */
	private String errMsg = "";

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date sendTime = new Date();

	public String getMailAddr() {
		return mailAddr;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

	public String getUserName() {
		return userName;
	}

	public int getStatus() {
		return status;
	}

	public void setMailAddr(String mailAddr) {
		this.mailAddr = mailAddr;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getNumTry() {
		return numTry;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setNumTry(int numTry) {
		this.numTry = numTry;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

}
