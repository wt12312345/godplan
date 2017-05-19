package com.godplan.entity;

import javax.persistence.Entity;
import javax.persistence.Lob;

import com.wt.web.entity.AbstractEntity;

/**
 * 随机选择记录
 * 
 * @author wt12312345
 * 
 */
@Entity
public class RecordChose extends AbstractEntity {
	/**
	 *     
	 */
	private static final long serialVersionUID = 1L;

	private long user_id = 0;
	@Lob
	private String content;
	private String remark;

	public long getUser_id() {
		return user_id;
	}

	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}