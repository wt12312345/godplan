package com.hq.web.domain;

import java.util.Map;

import com.hq.base.constant.BegCode;

public class JsonResponseNew {
	private int code = 0;

	private int index = 0;

	private long id = 0;

	private int status = 0;

	private String msg = "";

	/** 存储的数据对象 */
	private Object obj;

	private Page page;

	private Map<String, Object> kv;

	public JsonResponseNew() {
		super();
	}

	public JsonResponseNew(int code) {
		super();
		this.code = code;
	}

	public JsonResponseNew(int code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}

	public boolean hasErr() {
		if (this.code != BegCode.FAILED && this.code != BegCode.SUCCESS) {
			return true;
		} else {
			return false;
		}
	}

	public int getCode() {
		return code;
	}

	public int getIndex() {
		return index;
	}

	public long getId() {
		return id;
	}

	public int getStatus() {
		return status;
	}

	public String getMsg() {
		return msg;
	}

	public Object getObj() {
		return obj;
	}

	public Page getPage() {
		return page;
	}

	public Map<String, Object> getKv() {
		return kv;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public void setKv(Map<String, Object> kv) {
		this.kv = kv;
	}

}
