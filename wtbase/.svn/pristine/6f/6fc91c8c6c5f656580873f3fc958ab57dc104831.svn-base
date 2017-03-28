package com.iwilley.b1ec2.api.request;

import java.util.Date;
import java.util.Map;

import com.iwilley.b1ec2.api.B1EC2Request;
import com.iwilley.b1ec2.api.internal.util.B1EC2HashMap;
import com.iwilley.b1ec2.api.response.SupplierQueryResponse;;

public class SupplierQueryRequest implements B1EC2Request<SupplierQueryResponse>{

	//修改开始时间
    public Date startTime;

    //修改结束时间
    public Date endTime;
	
	
	
	@Override
	public String getApiMethodName() {
        return "B1EC2.Supplier.Query";
	}

	@Override
	public Map<String, String> GetParameters() {
		B1EC2HashMap parameters = new B1EC2HashMap();
		parameters.put("StartTime", startTime);
		parameters.put("EndTime", endTime);
		return parameters;
	}

	@Override
	public Class<SupplierQueryResponse> getResponseClass() {
		return SupplierQueryResponse.class;
	}
	
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
}
