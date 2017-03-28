package com.iwilley.b1ec2.api.request;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.iwilley.b1ec2.api.B1EC2Request;
import com.iwilley.b1ec2.api.domain.SalesOrderCreateLine;
import com.iwilley.b1ec2.api.internal.util.B1EC2HashMap;
import com.iwilley.b1ec2.api.response.SalesOrderCreateResponse;

public class SalesOrderCreateRequest implements
		B1EC2Request<SalesOrderCreateResponse> {

	// / 网店ID
	public Integer shopId;

	// 快递公司ID
	public Integer expressId;

	// 仓库ID
	public Integer whsId;

	// 客户名称
	public String customerName;

	// 平台订单编号
	public String shopOrderNo;

	// 物流费用
	public Double actPostFee;

	// 订单备注
	public String sellerMemo;

	// 是否货单付款 默认false
	public Boolean isCod;

	// 收货人姓名
	public String receiverName;

	// 收货人手机
	public String receiverMobile;

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

	// 发票抬头
	public String invoiceName;

	// 开票类型
	// 0:无需发票;10:普通发票;20:增值税普通发票;25:增值税专用发票;30:收据;
	// 默认为0
	public Integer invoiceType;

	// 开票金额
	public Double invoiceTotal;

	// 发票快递号
	public String invoiceExpressNo;

	// 开票备注
	public String invoiceMemo;

	// 发票号码
	public String invoiceNo;

	// 自定义字段1
	public String userDefinedField1;

	// 自定义字段2
	public String userDefinedField2;

	// 自定义字段3
	public String userDefinedField3;

	// 自定义字段4
	public String userDefinedField4;

	// 自定义字段5
	public String userDefinedField5;

	// 自定义字段6
	public Double userDefinedField6;

	// 自定义字段7
	public Double userDefinedField7;

	// 自定义字段8
	public Date userDefinedField8;

	// 自定义字段9
	public String userDefinedField9;

	// 自定义字段10
	public String userDefinedField10;

	// 自定义字段11
	public String userDefinedField11;

	// 折扣金额
	public Double discountFee;

	// 应付金额
	public Double orderTotal;

	// 平台订单状态
	public String shopOrderStatus;

	// 网站付款时间
	public Date shopPayTime;
	
	// 调整金额
	public String adjustFee;

    // 商品总额
	public String goodsTotal;
	
	//税金
    public double customTax;
    //身份证号码
	public String customIdNo;
	   

	private List<SalesOrderCreateLine> itemLines;

	@Override
	public String getApiMethodName() {
		return "B1EC2.SalesOrder.Create";
	}

	@Override
	public Map<String, String> GetParameters() {
		B1EC2HashMap parameters = new B1EC2HashMap();
		parameters.put("ShopId", shopId);
		parameters.put("ExpressId", expressId);
		parameters.put("WhsId", whsId);
		parameters.put("CustomerName", customerName);
		parameters.put("ShopOrderNo", shopOrderNo);
		parameters.put("ActPostFee", actPostFee);
		parameters.put("SellerMemo", sellerMemo);
		parameters.put("IsCod", isCod);
		parameters.put("ReceiverName", receiverName);
		parameters.put("ReceiverMobile", receiverMobile);
		parameters.put("ReceiverState", receiverState);
		parameters.put("ReceiverCity", receiverCity);
		parameters.put("ReceiverDistrict", receiverDistrict);
		parameters.put("ReceiverAddress", receiverAddress);
		parameters.put("ReceiverZip", receiverZip);
		parameters.put("InvoiceName", invoiceName);
		parameters.put("InvoiceType", invoiceType);
		parameters.put("InvoiceTotal", invoiceTotal);
		parameters.put("InvoiceExpressNo", invoiceExpressNo);
		parameters.put("InvoiceMemo", invoiceMemo);
		parameters.put("InvoiceNo", invoiceNo);
		parameters.put("UserDefinedField1", userDefinedField1);
		parameters.put("UserDefinedField2", userDefinedField2);
		parameters.put("UserDefinedField3", userDefinedField3);
		parameters.put("UserDefinedField4", userDefinedField4);
		parameters.put("UserDefinedField5", userDefinedField5);
		parameters.put("UserDefinedField6", userDefinedField6);
		parameters.put("UserDefinedField7", userDefinedField7);
		parameters.put("UserDefinedField8", userDefinedField8);
		parameters.put("UserDefinedField9", userDefinedField9);
		parameters.put("UserDefinedField10", userDefinedField10);
		parameters.put("UserDefinedField11", userDefinedField11);
		parameters.put("DiscountFee", discountFee);
		parameters.put("OrderTotal", orderTotal);
		parameters.put("ShopOrderStatus", shopOrderStatus);
		parameters.put("ShopPayTime", shopPayTime);
		parameters.put("AdjustFee", adjustFee);
		parameters.put("GoodsTotal", goodsTotal);
		parameters.put("CustomTax", customTax);
		parameters.put("CustomIdNo", customIdNo);
		if (itemLines != null && itemLines.size() > 0) {
			StringBuffer lineInfo = new StringBuffer();
			for (SalesOrderCreateLine itemLine : itemLines) {
				lineInfo.append(itemLine.getSkuCode());
				lineInfo.append("~");
				lineInfo.append(itemLine.getQuantity());
				lineInfo.append("~");
				lineInfo.append(itemLine.getPrice());
				lineInfo.append("~");
				lineInfo.append(itemLine.getLineMemo());
				lineInfo.append("~");
				lineInfo.append(itemLine.getLineUdf1());
				lineInfo.append("~");
				lineInfo.append(itemLine.getLineUdf2());
				lineInfo.append("~");
				lineInfo.append(itemLine.getIsVirtual());
				lineInfo.append("~");
				lineInfo.append(itemLine.getParentSku());
				lineInfo.append("^");
			}
			parameters.put("ItemLineInfo", lineInfo.toString());
		}
		return parameters;
	}

	@Override
	public Class<SalesOrderCreateResponse> getResponseClass() {
		return SalesOrderCreateResponse.class;
	}

	public Integer getShopId() {
		return shopId;
	}

	public Integer getExpressId() {
		return expressId;
	}

	public Integer getWhsId() {
		return whsId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public String getShopOrderNo() {
		return shopOrderNo;
	}

	public Double getActPostFee() {
		return actPostFee;
	}

	public String getSellerMemo() {
		return sellerMemo;
	}

	public Boolean getIsCod() {
		return isCod;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public String getReceiverMobile() {
		return receiverMobile;
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

	public String getInvoiceName() {
		return invoiceName;
	}

	public Integer getInvoiceType() {
		return invoiceType;
	}

	public Double getInvoiceTotal() {
		return invoiceTotal;
	}

	public String getInvoiceExpressNo() {
		return invoiceExpressNo;
	}

	public String getInvoiceMemo() {
		return invoiceMemo;
	}

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public String getUserDefinedField1() {
		return userDefinedField1;
	}

	public String getUserDefinedField2() {
		return userDefinedField2;
	}

	public String getUserDefinedField3() {
		return userDefinedField3;
	}

	public String getUserDefinedField4() {
		return userDefinedField4;
	}

	public String getUserDefinedField5() {
		return userDefinedField5;
	}

	public Double getUserDefinedField6() {
		return userDefinedField6;
	}

	public Double getUserDefinedField7() {
		return userDefinedField7;
	}

	public Date getUserDefinedField8() {
		return userDefinedField8;
	}

	public String getUserDefinedField9() {
		return userDefinedField9;
	}

	public String getUserDefinedField10() {
		return userDefinedField10;
	}

	public String getUserDefinedField11() {
		return userDefinedField11;
	}

	public Double getDiscountFee() {
		return discountFee;
	}

	public Double getOrderTotal() {
		return orderTotal;
	}

	public String getShopOrderStatus() {
		return shopOrderStatus;
	}

	public Date getShopPayTime() {
		return shopPayTime;
	}
	
	public String getAdjustFee() {
		return adjustFee;
	}

	public String getGoodsTotal() {
		return goodsTotal;
	}

	public List<SalesOrderCreateLine> getItemLines() {
		return itemLines;
	}

	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}

	public void setExpressId(Integer expressId) {
		this.expressId = expressId;
	}

	public void setWhsId(Integer whsId) {
		this.whsId = whsId;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public void setShopOrderNo(String shopOrderNo) {
		this.shopOrderNo = shopOrderNo;
	}

	public void setActPostFee(Double actPostFee) {
		this.actPostFee = actPostFee;
	}

	public void setSellerMemo(String sellerMemo) {
		this.sellerMemo = sellerMemo;
	}

	public void setIsCod(Boolean isCod) {
		this.isCod = isCod;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public void setReceiverMobile(String receiverMobile) {
		this.receiverMobile = receiverMobile;
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

	public void setInvoiceName(String invoiceName) {
		this.invoiceName = invoiceName;
	}

	public void setInvoiceType(Integer invoiceType) {
		this.invoiceType = invoiceType;
	}

	public void setInvoiceTotal(Double invoiceTotal) {
		this.invoiceTotal = invoiceTotal;
	}

	public void setInvoiceExpressNo(String invoiceExpressNo) {
		this.invoiceExpressNo = invoiceExpressNo;
	}

	public void setInvoiceMemo(String invoiceMemo) {
		this.invoiceMemo = invoiceMemo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public void setUserDefinedField1(String userDefinedField1) {
		this.userDefinedField1 = userDefinedField1;
	}

	public void setUserDefinedField2(String userDefinedField2) {
		this.userDefinedField2 = userDefinedField2;
	}

	public void setUserDefinedField3(String userDefinedField3) {
		this.userDefinedField3 = userDefinedField3;
	}

	public void setUserDefinedField4(String userDefinedField4) {
		this.userDefinedField4 = userDefinedField4;
	}

	public void setUserDefinedField5(String userDefinedField5) {
		this.userDefinedField5 = userDefinedField5;
	}

	public void setUserDefinedField6(Double userDefinedField6) {
		this.userDefinedField6 = userDefinedField6;
	}

	public void setUserDefinedField7(Double userDefinedField7) {
		this.userDefinedField7 = userDefinedField7;
	}

	public void setUserDefinedField8(Date userDefinedField8) {
		this.userDefinedField8 = userDefinedField8;
	}

	public void setUserDefinedField9(String userDefinedField9) {
		this.userDefinedField9 = userDefinedField9;
	}

	public void setUserDefinedField10(String userDefinedField10) {
		this.userDefinedField10 = userDefinedField10;
	}

	public void setUserDefinedField11(String userDefinedField11) {
		this.userDefinedField11 = userDefinedField11;
	}

	public void setDiscountFee(Double discountFee) {
		this.discountFee = discountFee;
	}

	public void setOrderTotal(Double orderTotal) {
		this.orderTotal = orderTotal;
	}

	public void setShopOrderStatus(String shopOrderStatus) {
		this.shopOrderStatus = shopOrderStatus;
	}

	public void setShopPayTime(Date shopPayTime) {
		this.shopPayTime = shopPayTime;
	}
	
	public void setAdjustFee(String adjustFee) {
		this.adjustFee = adjustFee;
	}
	public void setGoodsTotal(String goodsTotal) {
		this.goodsTotal = goodsTotal;
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

	public void setCustomTax(int customTax) {
		this.customTax = customTax;
	}
	

	public void setItemLines(List<SalesOrderCreateLine> itemLines) {
		this.itemLines = itemLines;
	}
	

}
