package com.iwilley.b1ec2.api.request;

import java.util.Map;

import com.iwilley.b1ec2.api.B1EC2Request;
import com.iwilley.b1ec2.api.response.CategoryQueryResponse;

// 类目查询接口
public class CategoryQueryRequest implements B1EC2Request<CategoryQueryResponse> {

	@Override
	public String getApiMethodName() {
		return "B1EC2.Category.Query";
	}

	@Override
	public Map<String, String> GetParameters() {
		return null;
	}

	@Override
	public Class<CategoryQueryResponse> getResponseClass() {
		return CategoryQueryResponse.class;
	}

}
