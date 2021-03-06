package com.iwilley.b1ec2.api.request;

import java.util.Map;

import com.iwilley.b1ec2.api.B1EC2Request;
import com.iwilley.b1ec2.api.response.ExpressQueryResponse;

public class ExpressQueryRequest implements B1EC2Request<ExpressQueryResponse>{

	@Override
	public String getApiMethodName() {
        return "B1EC2.Express.Query";
	}

	@Override
	public Map<String, String> GetParameters() {
		return null;
	}

	@Override
	public Class<ExpressQueryResponse> getResponseClass() {
		return ExpressQueryResponse.class;
	}
}
