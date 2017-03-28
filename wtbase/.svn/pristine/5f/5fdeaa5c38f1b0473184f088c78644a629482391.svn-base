package com.iwilley.b1ec2.api.request;

import java.util.Date;
import java.util.Map;
import com.iwilley.b1ec2.api.B1EC2Request;
import com.iwilley.b1ec2.api.internal.util.B1EC2HashMap;
import com.iwilley.b1ec2.api.response.ShopItemQueryResponse;

public class ShopItemQueryRequest implements B1EC2Request<ShopItemQueryResponse>{

	//商品修改开始时间
    public Date startTime;

    //商品修改结束时间
    public Date endTime;

    //页码。取值范围:大于零的整数;默认值:1。
    public Integer pageNum;

    //每页条数。取值范围：1~100，默认值：50
    public Integer pageSize;
    
    ///店铺id
    public Integer shopId;

    
	@Override
	public String getApiMethodName() {
        return "B1EC2.ShopItem.Query";
	}

	@Override
	public Map<String, String> GetParameters() {
		B1EC2HashMap parameters = new B1EC2HashMap();
		parameters.put("StartTime", startTime);
		parameters.put("EndTime", endTime);
		parameters.put("PageNum", pageNum);
		parameters.put("PageSize", pageSize);
		parameters.put("ShopId", shopId);
		return parameters;
	}

	@Override
	public Class<ShopItemQueryResponse> getResponseClass() {
		return ShopItemQueryResponse.class;
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

	public Integer getShopId() {
		return shopId;
	}

	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}

}
