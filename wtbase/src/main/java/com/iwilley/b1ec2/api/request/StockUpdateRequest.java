package com.iwilley.b1ec2.api.request;

import java.util.Map;
import com.iwilley.b1ec2.api.B1EC2Request;
import com.iwilley.b1ec2.api.internal.util.B1EC2HashMap;
import com.iwilley.b1ec2.api.response.StockUpdateResponse;

public class StockUpdateRequest implements B1EC2Request<StockUpdateResponse> {

	// 库存信息,包含: 库区编码:商品编码:数量:成本;
	// 范例如下: 1010:1201:10:12.5;1010:1202:5:0;
	// 注意:
	// 1. 单次请求最多只能更新100条库存, 超出则报错
	// 2. 如果不希望更新库存成本,则成本字段设置为0;
	public String stock;

	// 是否确认 默认为true
	public Boolean confirmed;

	@Override
	public String getApiMethodName() {
		return "B1EC2.Stock.Update";
	}

	public StockUpdateRequest() {
		confirmed = true;
	}

	@Override
	public Map<String, String> GetParameters() {
		B1EC2HashMap parameters = new B1EC2HashMap();
		parameters.put("Stock", stock);
		parameters.put("Confirmed", confirmed);
		return parameters;
	}

	@Override
	public Class<StockUpdateResponse> getResponseClass() {
		return StockUpdateResponse.class;
	}

	public String getStock() {
		return stock;
	}

	public Boolean getConfirmed() {
		return confirmed;
	}

	public void setStock(String stock) {
		this.stock = stock;
	}

	public void setConfirmed(Boolean confirmed) {
		this.confirmed = confirmed;
	}

}
