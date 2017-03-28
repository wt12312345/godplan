package com.iwilley.b1ec2.api.request;

import java.util.List;
import java.util.Map;

import com.iwilley.b1ec2.api.B1EC2Request;
import com.iwilley.b1ec2.api.domain.UnknownPackageItemLine;
import com.iwilley.b1ec2.api.internal.util.B1EC2HashMap;
import com.iwilley.b1ec2.api.response.UnknownPackageCreateResponse;

// 售后单创建
public class UnknownPackageCreateRequest implements
		B1EC2Request<UnknownPackageCreateResponse> {

	// / 退货仓库
	public Integer returnWhsId;

	// / 退回快递公司
	public Integer returnExpressId;

	// / 退货快递单号

	public String expressTrackNo;

	// / 自定义字段1
	public String userDefinedField1;

	// / 自定义字段2
	public String userDefinedField2;

	// / 自定义字段3
	public String userDefinedField3;

	// / 自定义字段4
	public String userDefinedField4;

	// 退货行信息
	private List<UnknownPackageItemLine> itemLines;

	@Override
	public String getApiMethodName() {
		return "B1EC2.UnknownPackage.Create";
	}

	@Override
	public Map<String, String> GetParameters() {
		B1EC2HashMap parameters = new B1EC2HashMap();
		parameters.put("ReturnWhsId", returnWhsId);
		parameters.put("ReturnExpressId", returnExpressId);
		parameters.put("ExpressTrackNo", expressTrackNo);
		parameters.put("UserDefinedField1", userDefinedField1);
		parameters.put("UserDefinedField2", userDefinedField2);
		parameters.put("UserDefinedField3", userDefinedField3);
		parameters.put("UserDefinedField4", userDefinedField4);
		if (itemLines != null && itemLines.size() >0) {
			StringBuffer lineInfo = new StringBuffer();
			for (UnknownPackageItemLine returnLine : itemLines) {
				 lineInfo.append(returnLine.getSkuCode());
                 lineInfo.append(":");
                 lineInfo.append(returnLine.getQuantity());
                 lineInfo.append(";");
			}
			parameters.put("ItemLineInfo", lineInfo.toString());
		}
		return parameters;
	}

	@Override
	public Class<UnknownPackageCreateResponse> getResponseClass() {
		return UnknownPackageCreateResponse.class;
	}

	public Integer getReturnWhsId() {
		return returnWhsId;
	}

	public Integer getReturnExpressId() {
		return returnExpressId;
	}

	public String getExpressTrackNo() {
		return expressTrackNo;
	}

	public String getUserDefinedField1() {
		return userDefinedField1;
	}

	public String getUserDefinedField2() {
		return userDefinedField2;
	}

	public String getUserDefinedField3() {
		return userDefinedField3;
	}

	public String getUserDefinedField4() {
		return userDefinedField4;
	}

	public List<UnknownPackageItemLine> getItemLines() {
		return itemLines;
	}

	public void setReturnWhsId(Integer returnWhsId) {
		this.returnWhsId = returnWhsId;
	}

	public void setReturnExpressId(Integer returnExpressId) {
		this.returnExpressId = returnExpressId;
	}

	public void setExpressTrackNo(String expressTrackNo) {
		this.expressTrackNo = expressTrackNo;
	}

	public void setUserDefinedField1(String userDefinedField1) {
		this.userDefinedField1 = userDefinedField1;
	}

	public void setUserDefinedField2(String userDefinedField2) {
		this.userDefinedField2 = userDefinedField2;
	}

	public void setUserDefinedField3(String userDefinedField3) {
		this.userDefinedField3 = userDefinedField3;
	}

	public void setUserDefinedField4(String userDefinedField4) {
		this.userDefinedField4 = userDefinedField4;
	}

	public void setItemLines(List<UnknownPackageItemLine> itemLines) {
		this.itemLines = itemLines;
	}

}
