package com.wt.web.domain;

import javax.servlet.http.HttpServletRequest;

import com.wt.base.util.TypeUtil;

public class Page {
	/**
	 * 最大页码
	 */
	private int max = 0;
	/**
	 * 当前页码
	 */
	private int current = 0;
	/**
	 * 一页数量
	 */
	private int size = 0;
	/**
	 * 当前实际展示的数量
	 */
	private int num = 0;
	/**
	 * 总数
	 */
	private int total = 0;

	public Page(HttpServletRequest request) {
		int pageCurrent = TypeUtil.toInt(request.getParameter("pageCurrent"));
		int pageSize = TypeUtil.toInt(request.getParameter("pageSize"));
		int pageTotal = TypeUtil.toInt(request.getParameter("pageTotal"));
		if (pageCurrent == 0) {
			pageCurrent = 1;
		}
		if (pageSize == 0) {
			pageSize = 60;
		}
		if (pageSize > 100) {
			pageSize = 100;
		}
		this.current = pageCurrent;
		this.size = pageSize;
		this.total = pageTotal;
	}

	public Page(int pageCurrent, int pageSize, int pageTotal) {
		this.current = pageCurrent;
		this.size = pageSize;
		this.total = pageTotal;
	}

	public int getMax() {
		return max;
	}

	public int getCurrent() {
		return current;
	}

	public int getSize() {
		return size;
	}

	public int getNum() {
		return num;
	}

	public int getTotal() {
		return total;
	}

	public void setMax(int max) {
		this.max = max;
	}

	public void setCurrent(int current) {
		this.current = current;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public void setTotal(int total) {
		this.total = total;
		this.max = (int) Math.ceil(total / (this.size * 1.0));
	}

}
