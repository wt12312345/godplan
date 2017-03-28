package com.iwilley.b1ec2.api.request;

import java.util.Map;
import java.util.Date;

import com.iwilley.b1ec2.api.B1EC2Request;
import com.iwilley.b1ec2.api.internal.util.B1EC2HashMap;
import com.iwilley.b1ec2.api.response.SalesOrderDeliverResponse;

public class SalesOrderDeliverRequest implements B1EC2Request<SalesOrderDeliverResponse>{

    //订单ID
    public Integer orderId;
    
    //订单号
    public String orderNo;

    //快递公司ID(参考ExpressQuery接口)
    //如果为空, 则表示无需物流发货
    public Integer expressId;

    //快递单号
    public String expressTrackNo;

    //包裹重量
    public Double weight;
    
    //拦截原因
    public Integer reasonId;

    //拦截备注
    public String holdingMemo;

    /// 序列号,包含: 订单LineNum：序列号;
    /// 范例如下: 0:XXXXXX;1:ZZZZZ;
    public String serialNumberList;

    /// 发票号码
    public String invoiceNo;
    
    /// 开票日期
    public Date invoiceDate;
    
    /// 系统备注
    public String orderMemo;

    /// 自定义字段1
    public String userDefinedField1;

    /// 自定义字段2
    public String userDefinedField2;

    /// 自定义字段3
    public String userDefinedField3;

    /// 自定义字段4
    public String userDefinedField4;

    /// 自定义字段5
    public String userDefinedField5;

    /// 自定义字段6
    public Double userDefinedField6;

    /// 自定义字段7
    public Double userDefinedField7;

    /// 自定义字段8
    public Date userDefinedField8;
    
	@Override
	public String getApiMethodName() {
        return "B1EC2.SalesOrder.Deliver";
	}

	@Override
	public Map<String, String> GetParameters() {
		B1EC2HashMap parameters = new B1EC2HashMap();
		parameters.put("OrderId", orderId);
		parameters.put("OrderNo", orderNo);
		parameters.put("ExpressId", expressId);
		parameters.put("ExpressTrackNo", expressTrackNo);
		parameters.put("Weight", weight);
		parameters.put("InvoiceNo", invoiceNo);
		parameters.put("InvoiceDate", invoiceDate);
		parameters.put("OrderMemo", orderMemo);
		parameters.put("ReasonId", reasonId);
		parameters.put("HoldingMemo", holdingMemo);
		parameters.put("SerialNumberList", serialNumberList);
		parameters.put("UserDefinedField1", userDefinedField1);
		parameters.put("UserDefinedField2", userDefinedField2);
		parameters.put("UserDefinedField3", userDefinedField3);
		parameters.put("UserDefinedField4", userDefinedField4);
		parameters.put("UserDefinedField5", userDefinedField5);
		parameters.put("UserDefinedField6", userDefinedField6);
		parameters.put("UserDefinedField7", userDefinedField7);
		parameters.put("UserDefinedField8", userDefinedField8);
		return parameters;
	}

	@Override
	public Class<SalesOrderDeliverResponse> getResponseClass() {
		return SalesOrderDeliverResponse.class;
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

	public Integer getExpressId() {
		return expressId;
	}

	public void setExpressId(Integer expressId) {
		this.expressId = expressId;
	}

	public String getExpressTrackNo() {
		return expressTrackNo;
	}

	public void setExpressTrackNo(String expressTrackNo) {
		this.expressTrackNo = expressTrackNo;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}
	
	public String getSerialNumberList() {
		return serialNumberList;
	}

	public void setSerialNumberList(String serialNumberList) {
		this.serialNumberList = serialNumberList;
	}
	
	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	
	public String getHoldingMemo() {
		return holdingMemo;
	}

	public void setHoldingMemo(String holdingMemo) {
		this.holdingMemo = holdingMemo;
	}

	public Integer getReasonId() {
		return reasonId;
	}

	public void setReasonId(Integer reasonId) {
		this.reasonId = reasonId;
	}
	
	public String getOrderMemo() {
		return orderMemo;
	}

	public void setOrderMemo(String orderMemo) {
		this.orderMemo = orderMemo;
	}
	
	public String getUserDefinedField1() {
		return userDefinedField1;
	}

	public void setUserDefinedField1(String userDefinedField1) {
		this.userDefinedField1 = userDefinedField1;
	}

	public String getUserDefinedField2() {
		return userDefinedField2;
	}

	public void setUserDefinedField2(String userDefinedField2) {
		this.userDefinedField2 = userDefinedField2;
	}

	public String getUserDefinedField3() {
		return userDefinedField3;
	}

	public void setUserDefinedField3(String userDefinedField3) {
		this.userDefinedField3 = userDefinedField3;
	}

	public String getUserDefinedField4() {
		return userDefinedField4;
	}

	public void setUserDefinedField4(String userDefinedField4) {
		this.userDefinedField4 = userDefinedField4;
	}

	public String getUserDefinedField5() {
		return userDefinedField5;
	}

	public void setUserDefinedField5(String userDefinedField5) {
		this.userDefinedField5 = userDefinedField5;
	}

	public Double getUserDefinedField6() {
		return userDefinedField6;
	}

	public void setUserDefinedField6(Double userDefinedField6) {
		this.userDefinedField6 = userDefinedField6;
	}

	public Double getUserDefinedField7() {
		return userDefinedField7;
	}

	public void setUserDefinedField7(Double userDefinedField7) {
		this.userDefinedField7 = userDefinedField7;
	}

	public Date getUserDefinedField8() {
		return userDefinedField8;
	}

	public void setUserDefinedField8(Date userDefinedField8) {
		this.userDefinedField8 = userDefinedField8;
	}

	public Date getInvoiceDate() {
		return invoiceDate;
	}


	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
	
	
}
