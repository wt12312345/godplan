package com.godplan.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import com.wt.web.entity.AbstractEntity;

/**
 * 文章
 * 
 * @author wt12312345
 * 
 */
@Entity
public class Article extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 标题
	 */
	private String title;
	/**
	 * 描述
	 */
	private String remark;
	/**
	 * 排序
	 */
	private int sortIndex = 0;
	/**
	 * 链接
	 */
	private String url;
	@Lob
	private String content;

	@ManyToOne
	@JoinColumn(name = "articleSort_id")
	private ArticleSort articleSort;
	/**
	 * 封面图片
	 */
	private String imgUrl;

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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public ArticleSort getArticleSort() {
		return articleSort;
	}

	public void setArticleSort(ArticleSort articleSort) {
		this.articleSort = articleSort;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

}
