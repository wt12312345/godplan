package com.iwilley.b1ec2.api.request;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.iwilley.b1ec2.api.B1EC2Request;
import com.iwilley.b1ec2.api.domain.ShopOrderCreateDiscount;
import com.iwilley.b1ec2.api.domain.ShopOrderCreateLine;
import com.iwilley.b1ec2.api.domain.ShopOrderCreatePayment;
import com.iwilley.b1ec2.api.internal.util.B1EC2HashMap;
import com.iwilley.b1ec2.api.response.ShopOrderCreateResponse;

// 平台订单创建
public class ShopOrderCreateRequest implements
		B1EC2Request<ShopOrderCreateResponse> {

	// 平台订单编号
	public String shopOrderNo;

	// 网店ID
	public Integer shopId;

	// 网店订单状态
	// 0:草稿 10：未发货 20：已发货 30：已完结 40：已关闭 50：已取消
	public Integer orderStatus;

	// 平台订单状态描述，如已付款，等待发货等等。都为中文描述，仅备注作用
	// 每次订单下载时，更新本字段
	// 平台订单状态
	public String orderStatusName;

	// 货到付款订单
	public Boolean isCod;

	// 是否分销
	public Boolean isDistribution;

	// 是否聚划算
	public Boolean isJhs;

	// 是否预售
	public Boolean isPresale;

	// 是否手机订单
	public Boolean isMobile;

	// 会员昵称
	public String memberNick;

	// 折扣金额
	public Double discountFee;

	// 邮费
	public Double postFee;

	// 调整金额
	public Double adjustFee;

	// 商品总额
	public Double goodsTotal;

	// 应付金额
	public Double orderTotal;

	// 实际收款
	public Double receivedTotal;

	// 网站下单时间
	public Date shopCreatedTime;

	// 网站付款时间
	public Date shopPayTime;

	// 买家留言
	public String buyerMemo;

	// 卖家备注
	public String sellerMemo;

	// 合法值为: blue,green,orange,pink,purple,red,yellow
	// 卖家旗帜
	public String shopFlag;

	// 发票抬头
	public String invoiceName;

	// 开票备注
	public String invoiceMemo;

	// 0:无需发票;10:普通发票;20:增值税普通发票;25:增值税专用发票;30:收据;
	// 发票类型
	public Integer invoiceType;

	// 快递公司
	public String expressName;

	// 快递单号
	public String expressTrackNo;

	// 买家支付宝
	public String buyerAlipayNo;

	// 买家邮箱
	public String buyerEmail;

	// 支付宝付款编号
	public String alipayOrderNo;

	// 收货人姓名
	public String receiverName;

	// 收货人省份
	public String receiverState;

	// 收货人城市
	public String receiverCity;

	// 收货人地区
	public String receiverDistrict;

	// 收货人地址
	public String receiverAddress;

	// 收货人邮编
	public String receiverZip;

	// 收货人手机
	public String receiverMobile;

	// 收货人电话
	public String receiverPhone;

	// 发货时间
	public Date reliveryTime;

	// 交易完成时间
	public Date endTime;

	// 自定义字段1
	public String userDefinedField1;

	// 自定义字段2
	public String userDefinedField2;

	// 自定义字段3
	public String userDefinedField3;

	// 税金
	public double customTax;
	// 身份证号码
	public String customIdNo;

	// 税金
	public String customPaymentName;
	// 身份证号码
	public String customPaymentNo;

	private List<ShopOrderCreateLine> itemLineInfo;

	private List<ShopOrderCreateDiscount> discountLines;

	private List<ShopOrderCreatePayment> paymentLines;

	@Override
	public String getApiMethodName() {
		return "B1EC2.ShopOrder.Push";
	}

	@Override
	public Map<String, String> GetParameters() {
		B1EC2HashMap parameters = new B1EC2HashMap();
		parameters.put("ShopOrderNo", shopOrderNo);
		parameters.put("ShopId", shopId);
		parameters.put("OrderStatus", orderStatus);
		parameters.put("OrderStatusName", orderStatusName);
		parameters.put("IsCod", isCod);
		parameters.put("IsDistribution", isDistribution);
		parameters.put("IsJhs", isJhs);
		parameters.put("IsPresale", isPresale);
		parameters.put("IsMobile", isMobile);
		parameters.put("MemberNick", memberNick);
		parameters.put("DiscountFee", discountFee);
		parameters.put("PostFee", postFee);
		parameters.put("AdjustFee", adjustFee);
		parameters.put("GoodsTotal", goodsTotal);
		parameters.put("OrderTotal", orderTotal);
		parameters.put("ReceivedTotal", receivedTotal);
		parameters.put("ShopCreatedTime", shopCreatedTime);
		parameters.put("ShopPayTime", shopPayTime);
		parameters.put("BuyerMemo", buyerMemo);
		parameters.put("SellerMemo", sellerMemo);
		parameters.put("ShopFlag", shopFlag);
		parameters.put("InvoiceName", invoiceName);
		parameters.put("InvoiceMemo", invoiceMemo);
		parameters.put("InvoiceType", invoiceType);
		parameters.put("ExpressName", expressName);
		parameters.put("ExpressTrackNo", expressTrackNo);
		parameters.put("BuyerAlipayNo", buyerAlipayNo);
		parameters.put("BuyerEmail", buyerEmail);
		parameters.put("AlipayOrderNo", alipayOrderNo);
		parameters.put("ReceiverName", receiverName);
		parameters.put("ReceiverState", receiverState);
		parameters.put("ReceiverCity", receiverCity);
		parameters.put("ReceiverDistrict", receiverDistrict);
		parameters.put("ReceiverAddress", receiverAddress);
		parameters.put("ReceiverZip", receiverZip);
		parameters.put("ReceiverMobile", receiverMobile);
		parameters.put("ReceiverPhone", receiverPhone);
		parameters.put("ReliveryTime", reliveryTime);
		parameters.put("EndTime", endTime);
		parameters.put("UserDefinedField1", userDefinedField1);
		parameters.put("UserDefinedField2", userDefinedField2);
		parameters.put("UserDefinedField3", userDefinedField3);
		parameters.put("CustomTax", customTax);
		parameters.put("CustomIdNo", customIdNo);
		parameters.put("CustomPaymentName", customPaymentName);
		parameters.put("CustomPaymentNo", customPaymentNo);
		if (itemLineInfo != null && itemLineInfo.size() > 0) {
			StringBuffer lineInfo = new StringBuffer();
			for (ShopOrderCreateLine itemLine : itemLineInfo) {
				lineInfo.append(itemLine.getShopLineNo());
				lineInfo.append(":");
				lineInfo.append(itemLine.getOuterId());
				lineInfo.append(":");
				lineInfo.append(itemLine.getQuantity());
				lineInfo.append(":");
				lineInfo.append(itemLine.getPrice());
				lineInfo.append(":");
				lineInfo.append(itemLine.getLineUdf1());
				lineInfo.append(":");
				lineInfo.append(itemLine.getLineUdf2());
				lineInfo.append(":");
				lineInfo.append(itemLine.getitemName());
				lineInfo.append(":");
				lineInfo.append(itemLine.getskuName());
				lineInfo.append(":");
				lineInfo.append(itemLine.getLineTotal());
				lineInfo.append(":");
				lineInfo.append(itemLine.getLineCustomTax());
				lineInfo.append(":");
				lineInfo.append(itemLine.getLineCustomTotal());
				lineInfo.append(";");

			}
			parameters.put("ItemLineInfo", lineInfo.toString());
		}
		if (discountLines != null && discountLines.size() > 0) {
			StringBuffer lineInfo = new StringBuffer();
			for (ShopOrderCreateDiscount itemLine : discountLines) {
				lineInfo.append(itemLine.getDiscountName());
				lineInfo.append(":");
				lineInfo.append(itemLine.getDiscountFee());
				lineInfo.append(";");
			}
			parameters.put("DiscountLineInfo", lineInfo.toString());
		}
		if (paymentLines != null && paymentLines.size() > 0) {
			StringBuffer lineInfo = new StringBuffer();
			for (ShopOrderCreatePayment itemLine : paymentLines) {
				lineInfo.append(itemLine.getPaymentId());
				lineInfo.append(":");
				lineInfo.append(itemLine.getPaymentTotal());
				lineInfo.append(":");
				lineInfo.append(itemLine.getPaymentNo());
				lineInfo.append(";");
			}
			parameters.put("PaymentLineInfo", lineInfo.toString());
		}
		return parameters;
	}

	@Override
	public Class<ShopOrderCreateResponse> getResponseClass() {
		return ShopOrderCreateResponse.class;
	}

	public String getShopOrderNo() {
		return shopOrderNo;
	}

	public Integer getShopId() {
		return shopId;
	}

	public Integer getOrderStatus() {
		return orderStatus;
	}

	public String getOrderStatusName() {
		return orderStatusName;
	}

	public Boolean getIsCod() {
		return isCod;
	}

	public Boolean getIsDistribution() {
		return isDistribution;
	}

	public Boolean getIsJhs() {
		return isJhs;
	}

	public Boolean getIsPresale() {
		return isPresale;
	}

	public Boolean getIsMobile() {
		return isMobile;
	}

	public String getMemberNick() {
		return memberNick;
	}

	public Double getDiscountFee() {
		return discountFee;
	}

	public Double getPostFee() {
		return postFee;
	}

	public Double getAdjustFee() {
		return adjustFee;
	}

	public Double getGoodsTotal() {
		return goodsTotal;
	}

	public Double getOrderTotal() {
		return orderTotal;
	}

	public Double getReceivedTotal() {
		return receivedTotal;
	}

	public Date getShopCreatedTime() {
		return shopCreatedTime;
	}

	public Date getShopPayTime() {
		return shopPayTime;
	}

	public String getBuyerMemo() {
		return buyerMemo;
	}

	public String getSellerMemo() {
		return sellerMemo;
	}

	public String getShopFlag() {
		return shopFlag;
	}

	public String getInvoiceName() {
		return invoiceName;
	}

	public String getInvoiceMemo() {
		return invoiceMemo;
	}

	public Integer getInvoiceType() {
		return invoiceType;
	}

	public String getExpressName() {
		return expressName;
	}

	public String getExpressTrackNo() {
		return expressTrackNo;
	}

	public String getBuyerAlipayNo() {
		return buyerAlipayNo;
	}

	public String getBuyerEmail() {
		return buyerEmail;
	}

	public String getAlipayOrderNo() {
		return alipayOrderNo;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public String getReceiverState() {
		return receiverState;
	}

	public String getReceiverCity() {
		return receiverCity;
	}

	public String getReceiverDistrict() {
		return receiverDistrict;
	}

	public String getReceiverAddress() {
		return receiverAddress;
	}

	public String getReceiverZip() {
		return receiverZip;
	}

	public String getReceiverMobile() {
		return receiverMobile;
	}

	public String getReceiverPhone() {
		return receiverPhone;
	}

	public Date getReliveryTime() {
		return reliveryTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public String getUserDefinedField1() {
		return userDefinedField1;
	}

	public String getUserDefinedField2() {
		return userDefinedField2;
	}

	public List<ShopOrderCreateLine> getItemLineInfo() {
		return itemLineInfo;
	}

	public List<ShopOrderCreateDiscount> getDiscountLines() {
		return discountLines;
	}

	public List<ShopOrderCreatePayment> getPaymentLines() {
		return paymentLines;
	}

	public void setShopOrderNo(String shopOrderNo) {
		this.shopOrderNo = shopOrderNo;
	}

	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	public void setOrderStatusName(String orderStatusName) {
		this.orderStatusName = orderStatusName;
	}

	public void setIsCod(Boolean isCod) {
		this.isCod = isCod;
	}

	public void setIsDistribution(Boolean isDistribution) {
		this.isDistribution = isDistribution;
	}

	public void setIsJhs(Boolean isJhs) {
		this.isJhs = isJhs;
	}

	public void setIsPresale(Boolean isPresale) {
		this.isPresale = isPresale;
	}

	public void setIsMobile(Boolean isMobile) {
		this.isMobile = isMobile;
	}

	public void setMemberNick(String memberNick) {
		this.memberNick = memberNick;
	}

	public void setDiscountFee(Double discountFee) {
		this.discountFee = discountFee;
	}

	public void setPostFee(Double postFee) {
		this.postFee = postFee;
	}

	public void setAdjustFee(Double adjustFee) {
		this.adjustFee = adjustFee;
	}

	public void setGoodsTotal(Double goodsTotal) {
		this.goodsTotal = goodsTotal;
	}

	public void setOrderTotal(Double orderTotal) {
		this.orderTotal = orderTotal;
	}

	public void setReceivedTotal(Double receivedTotal) {
		this.receivedTotal = receivedTotal;
	}

	public void setShopCreatedTime(Date shopCreatedTime) {
		this.shopCreatedTime = shopCreatedTime;
	}

	public void setShopPayTime(Date shopPayTime) {
		this.shopPayTime = shopPayTime;
	}

	public void setBuyerMemo(String buyerMemo) {
		this.buyerMemo = buyerMemo;
	}

	public void setSellerMemo(String sellerMemo) {
		this.sellerMemo = sellerMemo;
	}

	public void setShopFlag(String shopFlag) {
		this.shopFlag = shopFlag;
	}

	public void setInvoiceName(String invoiceName) {
		this.invoiceName = invoiceName;
	}

	public void setInvoiceMemo(String invoiceMemo) {
		this.invoiceMemo = invoiceMemo;
	}

	public void setInvoiceType(Integer invoiceType) {
		this.invoiceType = invoiceType;
	}

	public void setExpressName(String expressName) {
		this.expressName = expressName;
	}

	public void setExpressTrackNo(String expressTrackNo) {
		this.expressTrackNo = expressTrackNo;
	}

	public void setBuyerAlipayNo(String buyerAlipayNo) {
		this.buyerAlipayNo = buyerAlipayNo;
	}

	public void setBuyerEmail(String buyerEmail) {
		this.buyerEmail = buyerEmail;
	}

	public void setAlipayOrderNo(String alipayOrderNo) {
		this.alipayOrderNo = alipayOrderNo;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public void setReceiverState(String receiverState) {
		this.receiverState = receiverState;
	}

	public void setReceiverCity(String receiverCity) {
		this.receiverCity = receiverCity;
	}

	public void setReceiverDistrict(String receiverDistrict) {
		this.receiverDistrict = receiverDistrict;
	}

	public void setReceiverAddress(String receiverAddress) {
		this.receiverAddress = receiverAddress;
	}

	public void setReceiverZip(String receiverZip) {
		this.receiverZip = receiverZip;
	}

	public void setReceiverMobile(String receiverMobile) {
		this.receiverMobile = receiverMobile;
	}

	public void setReceiverPhone(String receiverPhone) {
		this.receiverPhone = receiverPhone;
	}

	public void setReliveryTime(Date reliveryTime) {
		this.reliveryTime = reliveryTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public void setUserDefinedField1(String userDefinedField1) {
		this.userDefinedField1 = userDefinedField1;
	}

	public void setUserDefinedField2(String userDefinedField2) {
		this.userDefinedField2 = userDefinedField2;
	}

	public void setItemLineInfo(List<ShopOrderCreateLine> itemLineInfo) {
		this.itemLineInfo = itemLineInfo;
	}

	public void setDiscountLines(List<ShopOrderCreateDiscount> discountLines) {
		this.discountLines = discountLines;
	}

	public void setPaymentLines(List<ShopOrderCreatePayment> paymentLines) {
		this.paymentLines = paymentLines;
	}

	public String getCustomIdNo() {
		return customIdNo;
	}

	public void setCustomIdNo(String customIdNo) {
		this.customIdNo = customIdNo;
	}

	public double getCustomTax() {
		return customTax;
	}

	public void setCustomTax(double customTax) {
		this.customTax = customTax;
	}

	public String getCustomPaymentName() {
		return customPaymentName;
	}

	public void setCustomPaymentName(String customPaymentName) {
		this.customPaymentName = customPaymentName;
	}

	public String getCustomPaymentNo() {
		return customPaymentNo;
	}

	public void setCustomPaymentNo(String customPaymentNo) {
		this.customPaymentNo = customPaymentNo;
	}

	public String getUserDefinedField3() {
		return userDefinedField3;
	}

	public void setUserDefinedField3(String userDefinedField3) {
		this.userDefinedField3 = userDefinedField3;
	}

}
