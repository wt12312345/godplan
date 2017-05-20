package com.wt.web.domain;

import java.util.Map;

import com.wt.base.constant.BegCode;

/** 用于平台一般的ajax 返回信息 */
public class JsonResponse {
	/** 错误代码 */
	private int code = 0;
	private Page page;
	private int noMore = 0;
	/** 返回信息显示 */
	private String msg = "";

	/** 存储的数据对象 */
	private Object obj;

	public JsonResponse() {
		super();
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

	public Page getPage() {
		return page;
	}

	public int getNoMore() {
		return noMore;
	}

	public String getMsg() {
		return msg;
	}

	public Object getObj() {
		return obj;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public void setNoMore(int noMore) {
		this.noMore = noMore;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

}
