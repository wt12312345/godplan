package com.godplan.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.wt.web.entity.AbstractEntity;

/**
 * 段子
 * 
 * @author wt12312345
 * 
 */

@Entity
public class Joke extends AbstractEntity {
	/**
	 *     
	 */
	private static final long serialVersionUID = 1L;

	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;
	/**
	 * 0：冻结、1可用、-1删除
	 */
	private int status = 0;
	@Column(length = 20)
	private String name;
	@Column(length = 20)
	private String nickName;
	@Lob
	private String content;
	@Temporal(TemporalType.TIME)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date checkTime;
	@Temporal(TemporalType.TIME)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date delTime;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}

	public Date getDelTime() {
		return delTime;
	}

	public void setDelTime(Date delTime) {
		this.delTime = delTime;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

}