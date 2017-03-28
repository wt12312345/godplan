package com.hq.web.domain;

import java.util.Map;

import com.hq.base.constant.BegCode;

/** 用于平台一般的ajax 返回信息 */
public class JsonResponse {
	/** 错误代码 */
	private int code = 0;

	private long page = 0;

	private long pageMax = 0;

	private int index = 0;

	private long id = 0;

	private int status = 0;

	private int noMore = 0;
	/** 返回信息显示 */
	private String msg = "";

	/** 需要跳转的页面 */
	private String nextUrl = "";

	/** 存储的数据对象 */
	private Object obj;

	/** 存放多组键值对 */
	private Map<String, Object> kv;

	public JsonResponse() {
		super();
	}

	public JsonResponse(int code) {
		super();
		this.code = code;
	}

	public JsonResponse(int code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}

	public JsonResponse(int code, String msg, String nextUrl) {
		super();
		this.code = code;
		this.msg = msg;
		this.nextUrl = nextUrl;
	}

	public boolean hasErr() {
		if (this.code != BegCode.FAILED && this.code != BegCode.SUCCESS) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isWell() {
		if (this.code == BegCode.FAILED) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isOk() {
		if (this.code == BegCode.SUCCESS) {
			return true;
		} else {
			return false;
		}
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getNextUrl() {
		return nextUrl;
	}

	public void setNextUrl(String nextUrl) {
		this.nextUrl = nextUrl;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public Map<String, Object> getKv() {
		return kv;
	}

	public void setKv(Map<String, Object> kv) {
		this.kv = kv;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getNoMore() {
		return noMore;
	}

	public void setNoMore(int noMore) {
		this.noMore = noMore;
	}

	public long getPage() {
		return page;
	}

	public void setPage(long page) {
		this.page = page;
	}

	public long getPageMax() {
		return pageMax;
	}

	public void setPageMax(long pageMax) {
		this.pageMax = pageMax;
	}

}
