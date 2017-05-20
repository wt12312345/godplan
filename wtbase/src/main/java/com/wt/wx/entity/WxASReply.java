package com.wt.wx.entity;
import javax.persistence.Entity;

import com.wt.web.entity.AbstractEntity;
/**
 * 关注，自动回复
 * @author Administrator
 *
 */
@Entity
public class WxASReply extends AbstractEntity{

	private static final long serialVersionUID = 1L;
	//private String 
	/**
	 * 回复内容
	 */
	/*@ManyToOne
	@JoinColumn(name = "recontent_id", nullable = false)*/
	private String recontent="";
	/**
	 * 类型
	*/
	private String wordtype=""; 
	/**
	 * 回复标记       0标示关注回复       1标示自动回复
	 */
	private int aors=0;
	
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
	public int getAors() {
		return aors;
	}
	public void setAors(int aors) {
		this.aors = aors;
	}
	
	
}