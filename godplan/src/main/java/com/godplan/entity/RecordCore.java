package com.godplan.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;

import com.wt.web.entity.AbstractEntity;

/**
 * 核心错误信息
 * 
 * @author wt12312345
 * 
 */
@Entity
public class RecordCore extends AbstractEntity {

	private static final long serialVersionUID = 1L;
	/**
	 * 模块名/功能名
	 */
	private String name = "";
	@Lob
	private String remark = "";
	/**
	 * 1 新记录，2 标记为已处理
	 */
	@Column(columnDefinition = "int default 0")
	private int status = 0;

	public String getName() {
		return name;
	}

	public String getRemark() {
		return remark;
	}

	public int getStatus() {
		return status;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
