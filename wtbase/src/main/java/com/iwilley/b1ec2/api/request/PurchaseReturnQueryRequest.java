package com.iwilley.b1ec2.api.request;

import java.util.Date;
import java.util.Map;
import com.iwilley.b1ec2.api.B1EC2Request;
import com.iwilley.b1ec2.api.internal.util.B1EC2HashMap;
import com.iwilley.b1ec2.api.response.PurchaseReturnQueryResponse;

public class PurchaseReturnQueryRequest implements B1EC2Request<PurchaseReturnQueryResponse>{

	//仓库ID，默认查询所有仓库的数据，除了默认值外每次只能查询一个仓库。 
    public Integer whsId;
    
	//订单修改开始时间
    public Date startTime;

    //订单修改结束时间
    public Date endTime;

    // 页码。取值范围:大于零的整数;默认值:1。
    // 注：必须采用倒序的分页方式（从最后一页往回取）才能避免漏单问题。
    public Integer pageNum;

    //每页条数。取值范围：1~100，默认值：50
    public Integer pageSize;
    
	@Override
	public String getApiMethodName() {
        return "B1EC2.PurchaseReturn.Query";
	}

	@Override
	public Map<String, String> GetParameters() {
		B1EC2HashMap parameters = new B1EC2HashMap();
		parameters.put("WhsId", whsId);
		parameters.put("StartTime", startTime);
		parameters.put("EndTime", endTime);
		parameters.put("PageNum", pageNum);
		parameters.put("PageSize", pageSize);
		return parameters;
	}

	@Override
	public Class<PurchaseReturnQueryResponse> getResponseClass() {
		return PurchaseReturnQueryResponse.class;
	}

	public Integer getWhsId() {
		return whsId;
	}

	public void setWhsId(Integer whsId) {
		this.whsId = whsId;
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

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
}
