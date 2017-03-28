package com.iwilley.b1ec2.api.request;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.iwilley.b1ec2.api.B1EC2Request;
import com.iwilley.b1ec2.api.domain.ShopSkuPushLine;
import com.iwilley.b1ec2.api.internal.util.B1EC2HashMap;
import com.iwilley.b1ec2.api.response.ShopItemPushResponse;

// 宝贝推送
public class ShopItemPushRequest implements B1EC2Request<ShopItemPushResponse> {

	// 宝贝编码
	public String shopItemCode;

	// 店铺Id
	public Integer shopId;

	// 宝贝名称
	public String shopItemName;

	// 是否虚拟商品
	public Boolean isVirtual;

	// 主图Url
	public String pictureUrl;

	// 商家编码
	public String outerId;

	// 商品数量
	public Integer quantity;

	// 商品价格
	public Double price;

	// 体积(立方米）
	public Double size;

	// 重量（千克）
	public Double weight;

	// 在售中 在库中
	public String status;

	// 备注
	public String memo;

	// 创建时间
	public Date createdTime;

	// 修改时间
	public Date updateTime;

	// 退货行信息
	private List<ShopSkuPushLine> skusInfo;

	@Override
	public String getApiMethodName() {
		return "B1EC2.ShopItem.Push";
	}

	@Override
	public Map<String, String> GetParameters() {
		B1EC2HashMap parameters = new B1EC2HashMap();
		parameters.put("ShopItemCode", shopItemCode);
		parameters.put("ShopId", shopId);
		parameters.put("ShopItemName", shopItemName);
		parameters.put("IsVirtual", isVirtual);
		parameters.put("PictureUrl", pictureUrl);
		parameters.put("OuterId", outerId);
		parameters.put("Quantity", quantity);
		parameters.put("Price", price);
		parameters.put("Size", size);
		parameters.put("Weight", weight);
		parameters.put("Status", status);
		parameters.put("Memo", memo);
		parameters.put("CreatedTime", createdTime);
		parameters.put("UpdateTime", updateTime);
		if (skusInfo != null && skusInfo.size() > 0) {
			StringBuffer lineInfo = new StringBuffer();
			for (ShopSkuPushLine sku : skusInfo) {
				lineInfo.append(sku.getShopSkuCode());
				lineInfo.append(":");
				lineInfo.append(sku.getOuterId());
				lineInfo.append(":");
				lineInfo.append(sku.getProperty1());
				lineInfo.append(":");
				lineInfo.append(sku.getProperty2());
				lineInfo.append(":");
				lineInfo.append(sku.getPrice());
				lineInfo.append(":");
				lineInfo.append(sku.getSize());
				lineInfo.append(":");
				lineInfo.append(sku.getWeight());
				lineInfo.append(":");
				lineInfo.append(sku.getQuantity());
				lineInfo.append(";");
			}
			parameters.put("SkusInfo", lineInfo.toString());
		}
		return parameters;
	}

	@Override
	public Class<ShopItemPushResponse> getResponseClass() {
		return ShopItemPushResponse.class;
	}

	public String getShopItemCode() {
		return shopItemCode;
	}

	public Integer getShopId() {
		return shopId;
	}

	public String getShopItemName() {
		return shopItemName;
	}

	public Boolean getIsVirtual() {
		return isVirtual;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}

	public String getOuterId() {
		return outerId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public Double getPrice() {
		return price;
	}

	public Double getSize() {
		return size;
	}

	public Double getWeight() {
		return weight;
	}

	public String getStatus() {
		return status;
	}

	public String getMemo() {
		return memo;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public List<ShopSkuPushLine> getSkusInfo() {
		return skusInfo;
	}

	public void setShopItemCode(String shopItemCode) {
		this.shopItemCode = shopItemCode;
	}

	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}

	public void setShopItemName(String shopItemName) {
		this.shopItemName = shopItemName;
	}

	public void setIsVirtual(Boolean isVirtual) {
		this.isVirtual = isVirtual;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	public void setOuterId(String outerId) {
		this.outerId = outerId;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public void setSize(Double size) {
		this.size = size;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public void setSkusInfo(List<ShopSkuPushLine> skusInfo) {
		this.skusInfo = skusInfo;
	}

}
