package com.wt.wx.entity;
import javax.persistence.Entity;

import com.wt.web.entity.AbstractEntity;


/**
 * 通过上传素材获取MediaId
 * @author Administrator
 *
 */
@Entity
public class WxImgReply extends AbstractEntity{
	private static final long serialVersionUID = 1L;
	private String mediaid="";
	private String type="";
	private String picname="";
	String path="";
	
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getPicname() {
		return picname;
	}
	public void setPicname(String picname) {
		this.picname = picname;
	}
	public String getMediaid() {
		return mediaid;
	}
	public void setMediaid(String mediaid) {
		this.mediaid = mediaid;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	

}
