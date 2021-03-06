package com.iwilley.b1ec2.api.request;

import java.util.Date;
import java.util.Map;

import com.iwilley.b1ec2.api.B1EC2Request;
import com.iwilley.b1ec2.api.internal.util.B1EC2HashMap;
import com.iwilley.b1ec2.api.response.SalesOrderUpdateResponse;

public class SalesOrderUpdateRequest implements B1EC2Request<SalesOrderUpdateResponse> {

    //订单ID
    public Integer orderId;
    
    //订单号
    public String orderNo;

    //订单备注
    public String orderMemo;
    
   //拦截原因
    public Integer reasonId;

    //拦截备注
    public String holdingMemo;
    
    //自定义字段1
    public String userDefinedField1;
    
    //自定义字段2
    public String userDefinedField2;
    
    //自定义字段3
    public String userDefinedField3;
    
    //自定义字段4
    public String userDefinedField4;
    
    //自定义字段5
    public String userDefinedField5;
    
    //自定义字段6
    public Double userDefinedField6;
    
    //自定义字段7
    public Double userDefinedField7;
    
    //自定义字段8
    public Date userDefinedField8;
    
    //自定义字段9
    public String userDefinedField9;
    
    //自定义字段10
    public String userDefinedField10;
    
    //自定义字段11
    public String userDefinedField11;
    
	@Override
	public String getApiMethodName() {
        return "B1EC2.SalesOrder.Update";
	}

	@Override
	public Map<String, String> GetParameters() {
		B1EC2HashMap parameters = new B1EC2HashMap();
		parameters.put("OrderId", orderId);
		parameters.put("OrderNo", orderNo);
		parameters.put("OrderMemo", orderMemo);
		parameters.put("ReasonId", reasonId);
		parameters.put("HoldingMemo", holdingMemo);
		parameters.put("UserDefinedField1", userDefinedField1);
		parameters.put("UserDefinedField2", userDefinedField2);
		parameters.put("UserDefinedField3", userDefinedField3);
		parameters.put("UserDefinedField4", userDefinedField4);
		parameters.put("UserDefinedField5", userDefinedField5);
		parameters.put("UserDefinedField6", userDefinedField6);
		parameters.put("UserDefinedField7", userDefinedField7);
		parameters.put("UserDefinedField8", userDefinedField8);
		parameters.put("UserDefinedField9", userDefinedField9);
		parameters.put("UserDefinedField10", userDefinedField10);
		parameters.put("UserDefinedField11", userDefinedField11);
		return parameters;
	}

	@Override
	public Class<SalesOrderUpdateResponse> getResponseClass() {
		return SalesOrderUpdateResponse.class;
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

	public String getOrderMemo() {
		return orderMemo;
	}

	public void setOrderMemo(String orderMemo) {
		this.orderMemo = orderMemo;
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

	public String getUserDefinedField9() {
		return userDefinedField9;
	}

	public void setUserDefinedField9(String userDefinedField9) {
		this.userDefinedField9 = userDefinedField9;
	}

	public String getUserDefinedField10() {
		return userDefinedField10;
	}

	public void setUserDefinedField10(String userDefinedField10) {
		this.userDefinedField10 = userDefinedField10;
	}

	public String getUserDefinedField11() {
		return userDefinedField11;
	}

	public void setUserDefinedField11(String userDefinedField11) {
		this.userDefinedField11 = userDefinedField11;
	}
	
}
