package com.hq.web.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.directwebremoting.annotations.RemoteProperty;
import org.springframework.format.annotation.DateTimeFormat;

@MappedSuperclass
public class AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	@RemoteProperty
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected long id;

	@Version
	@Column(columnDefinition = "bigint default 0")
	private long version;
	/**
	 * 发送时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date createTime = new Date();

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
