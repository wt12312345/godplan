package com.iwilley.b1ec2.api.request;

import java.util.Map;

import com.iwilley.b1ec2.api.B1EC2Request;
import com.iwilley.b1ec2.api.response.RefundReasonQueryResponse;

public class RefundReasonQueryRequest implements B1EC2Request<RefundReasonQueryResponse>{

	@Override
	public String getApiMethodName() {
        return "B1EC2.RefundReason.Query";
	}

	@Override
	public Map<String, String> GetParameters() {
		return null;
	}

	@Override
	public Class<RefundReasonQueryResponse> getResponseClass() {
		return RefundReasonQueryResponse.class;
	}
}
