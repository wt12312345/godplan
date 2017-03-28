package com.iwilley.b1ec2.api.request;

import java.util.Date;
import java.util.Map;

import com.iwilley.b1ec2.api.B1EC2Request;
import com.iwilley.b1ec2.api.internal.util.B1EC2HashMap;
import com.iwilley.b1ec2.api.response.BomQueryResponse;

// 查询组合商品接口
public class BomQueryRequest implements B1EC2Request<BomQueryResponse> {
	
    // 修改开始时间
    public Date startTime;

    // 修改结束时间
    public Date endTime;

    // 页码。取值范围:大于零的整数;默认值:1。
    public Integer pageNum;

    // 每页条数。取值范围：1~100，默认值：50
    public Integer pageSize;
	
	@Override
	public String getApiMethodName() {
		return "B1EC2.Bom.Query";
	}

	@Override
	public Map<String, String> GetParameters() {
		B1EC2HashMap parameters = new B1EC2HashMap();
		parameters.put("StartTime", startTime);
		parameters.put("EndTime", endTime);
		parameters.put("PageNum", pageNum);
		parameters.put("PageSize", pageSize);
		return parameters;
	}

	@Override
	public Class<BomQueryResponse> getResponseClass() {
		return BomQueryResponse.class;
	}

	public Date getStartTime() {
		return startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

}
