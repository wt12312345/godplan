package com.iwilley.b1ec2.api.request;

import java.util.List;
import java.util.Map;

import com.iwilley.b1ec2.api.B1EC2Request;
import com.iwilley.b1ec2.api.domain.BomCreateLine;
import com.iwilley.b1ec2.api.internal.util.B1EC2HashMap;
import com.iwilley.b1ec2.api.response.BomCreateResponse;

// 组合商品创建
public class BomCreateRequest implements B1EC2Request<BomCreateResponse> {

	// SkuCode 必须是虚拟商品
	public String skuCode;

	// 退货行信息
	public List<BomCreateLine> bomLines;

	@Override
	public String getApiMethodName() {
		return "B1EC2.Bom.Create";
	}

	@Override
	public Map<String, String> GetParameters() {
		B1EC2HashMap parameters = new B1EC2HashMap();
		parameters.put("SkuCode", skuCode);
		if (bomLines != null && bomLines.size() > 0) {
			StringBuffer lineInfo = new StringBuffer();
			for (BomCreateLine bomLine : bomLines) {
				lineInfo.append(bomLine.getSkuCode());
				lineInfo.append(":");
				lineInfo.append(bomLine.getQuantity());
				lineInfo.append(";");
			}
			parameters.put("BomLineInfo", lineInfo.toString());
		}
		return parameters;
	}

	@Override
	public Class<BomCreateResponse> getResponseClass() {
		return BomCreateResponse.class;
	}

	public String getSkuCode() {
		return skuCode;
	}

	public List<BomCreateLine> getBomLines() {
		return bomLines;
	}

	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}

	public void setBomLines(List<BomCreateLine> bomLines) {
		this.bomLines = bomLines;
	}

}
