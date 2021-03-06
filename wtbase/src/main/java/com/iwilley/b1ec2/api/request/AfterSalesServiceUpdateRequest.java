package com.iwilley.b1ec2.api.request;

import java.util.Map;

import com.iwilley.b1ec2.api.B1EC2Request;
import com.iwilley.b1ec2.api.internal.util.B1EC2HashMap;
import com.iwilley.b1ec2.api.response.AfterSalesServiceUpdateResponse;

public class AfterSalesServiceUpdateRequest implements B1EC2Request<AfterSalesServiceUpdateResponse>{

    //订单ID
    public Integer afterSaleServiceId;
    
    //订单号
    public String afterSaleServiceNo;

    //备注
    public String refundMemo;
    
    //自定义字段1
    public String userDefinedField1;
    
    //自定义字段2
    public String userDefinedField2;
    
    //自定义字段3
    public String userDefinedField3;
    
    //自定义字段4
    public String userDefinedField4;
    
    
	@Override
	public String getApiMethodName() {
        return "B1EC2.AfterSalesService.Update";
	}

	@Override
	public Map<String, String> GetParameters() {
		B1EC2HashMap parameters = new B1EC2HashMap();
		parameters.put("AfterSaleServiceId", afterSaleServiceId);
		parameters.put("AfterSaleServiceNo", afterSaleServiceNo);
		parameters.put("RefundMemo", refundMemo);
		parameters.put("UserDefinedField1", userDefinedField1);
		parameters.put("UserDefinedField2", userDefinedField2);
		parameters.put("UserDefinedField3", userDefinedField3);
		parameters.put("UserDefinedField4", userDefinedField4);
		return parameters;
	}

	@Override
	public Class<AfterSalesServiceUpdateResponse> getResponseClass() {
		return AfterSalesServiceUpdateResponse.class;
	}

	public Integer getAfterSaleServiceId() {
		return afterSaleServiceId;
	}

	public void setAfterSaleServiceId(Integer afterSaleServiceId) {
		this.afterSaleServiceId = afterSaleServiceId;
	}

	public String getAfterSaleServiceNo() {
		return afterSaleServiceNo;
	}

	public void setAfterSaleServiceNo(String afterSaleServiceNo) {
		this.afterSaleServiceNo = afterSaleServiceNo;
	}

	public String getRefundMemo() {
		return refundMemo;
	}

	public void setRefundMemo(String refundMemo) {
		this.refundMemo = refundMemo;
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

	
}
