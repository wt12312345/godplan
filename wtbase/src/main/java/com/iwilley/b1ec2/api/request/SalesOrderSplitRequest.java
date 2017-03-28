package com.iwilley.b1ec2.api.request;

import java.util.Map;

import com.iwilley.b1ec2.api.B1EC2Request;
import com.iwilley.b1ec2.api.internal.util.B1EC2HashMap;
import com.iwilley.b1ec2.api.response.SalesOrderSplitResponse;

public class SalesOrderSplitRequest implements B1EC2Request<SalesOrderSplitResponse>{

    //订单ID
    public Integer orderId;
    
    //订单号
    public String orderNo;

    //行号 多行已逗号分隔
    public String lineNums;

	@Override
	public String getApiMethodName() {
        return "B1EC2.SalesOrder.Split";
	}

	@Override
	public Map<String, String> GetParameters() {
		B1EC2HashMap parameters = new B1EC2HashMap();
		parameters.put("OrderId", orderId);
		parameters.put("OrderNo", orderNo);
		parameters.put("LineNums", lineNums);
		return parameters;
	}

	@Override
	public Class<SalesOrderSplitResponse> getResponseClass() {
		return SalesOrderSplitResponse.class;
	}

	

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getLineNums() {
		return lineNums;
	}

	public void setLineNums(String lineNums) {
		this.lineNums = lineNums;
	}


	
}
