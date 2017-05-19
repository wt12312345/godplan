package com.wt.entity;

import javax.persistence.Entity;

import com.wt.web.entity.AbstractEntity;

/**
 * 文章分类
 * 
 * @author wt12312345
 * 
 */
@Entity
public class ArticleSort extends AbstractEntity {

	private static final long serialVersionUID = 1L;
	/**
	 * 标题
	 */
	private String title = "";
	/**
	 * 描述
	 */
	private String remark = "";
	/**
	 * 排序
	 */
	private int sortIndex = 0;
	/**
	 * 封面图片
	 */
	private String imgUrl = "";

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getSortIndex() {
		return sortIndex;
	}

	public void setSortIndex(int sortIndex) {
		this.sortIndex = sortIndex;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

}
