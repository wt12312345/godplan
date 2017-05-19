package com.godplan.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.wt.web.entity.AbstractEntity;


/**
 * 每个菜单
 * 
 * @author Administrator
 * 
 */
@Entity
public class Menu extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	private String name = "";

	/**
	 * 链接地址
	 */
	private String url = "";
	/**
	 * 排列序号
	 */
	@Column(columnDefinition = "int default 0")
	private int sortIndex = 0;
	private long parentId = 0;
	private String iconName = "";
	private String action = "";

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSortIndex() {
		return sortIndex;
	}

	public void setSortIndex(int sortIndex) {
		this.sortIndex = sortIndex;
	}

	public String getIconName() {
		return iconName;
	}

	public void setIconName(String iconName) {
		this.iconName = iconName;
	}

	public long getParentId() {
		return parentId;
	}

	public void setParentId(long parentId) {
		this.parentId = parentId;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

}
