package com.godplan.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.wt.web.entity.AbstractEntity;

/**
 * 流行词
 * 
 * @author wt12312345
 * 
 */
@Entity
public class Buzzword extends AbstractEntity {
	/**
	 *     
	 */
	private static final long serialVersionUID = 1L;

	@Column(length = 100)
	private String title = "";
	@Column(length = 1000)
	private String tags = "";
	@Lob
	private String content = "";
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date time = new Date();

	public String getTitle() {
		return title;
	}

	public String getTags() {
		return tags;
	}

	public String getContent() {
		return content;
	}

	public Date getTime() {
		return time;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setTime(Date time) {
		this.time = time;
	}

}