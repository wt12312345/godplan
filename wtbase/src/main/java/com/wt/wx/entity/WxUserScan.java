package com.wt.wx.entity;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;
import com.wt.web.entity.AbstractEntity;

@Entity
public class WxUserScan extends AbstractEntity {

	private static final long serialVersionUID = 1L;
	private String openid = "";
	private long keyId = 0;
	// 取消关注时间
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date unsubscribetime;
	// 姓名
	private String name = "";
	// 状态 0 未关注或取消了关注 1 关注
	private int status = 0;

	public Date getUnsubscribetime() {
		return unsubscribetime;
	}

	public void setUnsubscribetime(Date unsubscribetime) {
		this.unsubscribetime = unsubscribetime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public long getKeyId() {
		return keyId;
	}

	public void setKeyId(long keyId) {
		this.keyId = keyId;
	}

}
