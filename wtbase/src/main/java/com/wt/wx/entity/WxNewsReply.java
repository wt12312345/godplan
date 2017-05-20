package com.wt.wx.entity;
import javax.persistence.Entity;

import com.wt.web.entity.AbstractEntity;

@Entity
public class WxNewsReply extends AbstractEntity{
	
	private static final long serialVersionUID = 1L;
	
	private String type="";
	/**
	 * 图片本地地址
	 */
	private String link="";
	/**
	 * 网络地址
	 */
	private String networklink="";
	/**
	 * 关键字
	 */
	private String cruxword="";
	/**
	 * 标题
	 */
	private String title="";
	/**
	 * 描述
	 */
	private String description="";
	/**
	 * 点击图片链接
	 */
	private String Url="";
	/**
	 * 多图文排序
	 */
	private int rank=0;
	
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUrl() {
		return Url;
	}
	public void setUrl(String url) {
		Url = url;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getNetworklink() {
		return networklink;
	}
	public void setNetworklink(String networklink) {
		this.networklink = networklink;
	}
	public String getCruxword() {
		return cruxword;
	}
	public void setCruxword(String cruxword) {
		this.cruxword = cruxword;
	}
	
	

}
