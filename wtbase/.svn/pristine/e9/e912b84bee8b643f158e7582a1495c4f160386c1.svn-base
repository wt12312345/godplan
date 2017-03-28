package com.iwilley.b1ec2.api.request;

import java.util.Date;
import java.util.Map;

import com.iwilley.b1ec2.api.B1EC2Request;
import com.iwilley.b1ec2.api.internal.util.B1EC2HashMap;
import com.iwilley.b1ec2.api.response.StockTransferQueryResponse;

public class StockTransferQueryRequest implements B1EC2Request<StockTransferQueryResponse> {

	// 创建开始时间
	public Date startTime;

	// 创建结束时间
	public Date endTime;

	// 页码。取值范围:大于零的整数;默认值:1。
	public Integer pageNum;

	// 每页条数。取值范围：1~100，默认值：50
	public Integer pageSize;

	// 转储单号
	public String stockTransferNo;

	// 转储类型 10:仓内调拨；20仓外调拨
	public Integer stockTransferType;

	// 转储状态
	public Integer stockTransferStatus;

	@Override
	public String getApiMethodName() {
		return "B1EC2.StockTransfer.Query";
	}

	@Override
	public Map<String, String> GetParameters() {
		B1EC2HashMap parameters = new B1EC2HashMap();
		parameters.put("StartTime", startTime);
		parameters.put("EndTime", endTime);
		parameters.put("PageNum", pageNum);
		parameters.put("PageSize", pageSize);
		parameters.put("StockTransferNo", stockTransferNo);
		parameters.put("StockTransferType", stockTransferType);
		parameters.put("StockTransferStatus", stockTransferStatus);
		return parameters;
	}

	@Override
	public Class<StockTransferQueryResponse> getResponseClass() {
		return StockTransferQueryResponse.class;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public String getStockTransferNo() {
		return stockTransferNo;
	}

	public void setStockTransferNo(String stockTransferNo) {
		this.stockTransferNo = stockTransferNo;
	}

	public Integer getStockTransferType() {
		return stockTransferType;
	}

	public void setStockTransferType(Integer stockTransferType) {
		this.stockTransferType = stockTransferType;
	}

	public Integer getStockTransferStatus() {
		return stockTransferStatus;
	}

	public void setStockTransferStatus(Integer stockTransferStatus) {
		this.stockTransferStatus = stockTransferStatus;
	}

}
