package com.iwilley.b1ec2.api.request;

import java.util.Map;

import com.iwilley.b1ec2.api.B1EC2Request;
import com.iwilley.b1ec2.api.internal.util.B1EC2HashMap;
import com.iwilley.b1ec2.api.response.SalesOrderCancelResponse;

// 订单关闭接口
public class SalesOrderCancelRequest implements
		B1EC2Request<SalesOrderCancelResponse> {

	// / 订单ID
	public Integer orderId;

	// 订单系统编码
	public String orderNo;

	// 平台订单号
	public String shopOrderNo;

	@Override
	public String getApiMethodName() {
		return "B1EC2.SalesOrder.Cancel";
	}

	@Override
	public Map<String, String> GetParameters() {
		B1EC2HashMap parameters = new B1EC2HashMap();
		parameters.put("OrderId", orderId);
		parameters.put("OrderNo", orderNo);
		parameters.put("ShopOrderNo", shopOrderNo);
		return parameters;
	}

	@Override
	public Class<SalesOrderCancelResponse> getResponseClass() {
		return SalesOrderCancelResponse.class;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getShopOrderNo() {
		return shopOrderNo;
	}

	public void setShopOrderNo(String shopOrderNo) {
		this.shopOrderNo = shopOrderNo;
	}

}
