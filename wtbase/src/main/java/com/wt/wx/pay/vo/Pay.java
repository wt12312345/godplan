package com.wt.wx.pay.vo;

/**Pay 支付对象
 * err_msg get_brand_wcpay_request:ok 支付成功*/
public class Pay {
	/**是
	字段名称：公众号 id；字段来源：商户注册具有支付权限的公
	众号成功后即可获得；传入方式：由商户直接传入。
	参数类型：字符串类型*/
	private String appId; 
	
	/**是
	字段名称：时间戳；字段来源：商户生成从 1970 年 1 月 1 日
	00：00：00 至今的秒数，即当前的时间，且最终需要转换为字
	符串形式；由商户生成后传入。
	参数类型：字符串类型；参数长度：32 个字节以下。*/
	private String timeStamp; 
	
	/**是
	字段名称：随机字符串；字段来源：商户生成的随机字符串。
	由商户生成后传入。
	参数类型：字符串类型；参数长度：32 个字节以下。*/
	private String nonceStr; 
	
	/**
	 是
	字段名称：扩展字符串；参数类型：字符串类型；字段来源：
	商户将订单信息组成该字符串，具体组成方案参见接口使用说
	明中 package 组包帮助；由商户按照规范拼接后传入。
	参数类型：字符串类型；参数长度：4096 个字节以下。*/
	private String packageStr;
	
	public String getPackageStr() {
		return packageStr;
	}

	public void setPackageStr(String packageStr) {
		this.packageStr = packageStr;
	}

	/**是
	字段名称：签名方式；参数类型：字符串类型；字段来源：按
	照文档中所示填入，目前仅支持 SHA1；
	参数类型：字符串类型；参数取值："SHA1"。*/
	private String signType; 
	
	/**是
	字段名称：签名；字段来源：商户将接口列表中的参数按照指
	定方式进行签名，签名方式使用 signType 中标示的签名方式，微信公众号支付接
	具体签名方案参见接口使用说明中签名帮助；由商户按照规范
	签名后传入。
	参数类型：字符串类型；参数长度：40 个字符。*/
	private String paySign;

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getNonceStr() {
		return nonceStr;
	}

	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}

	public String getSignType() {
		return signType;
	}

	public void setSignType(String signType) {
		this.signType = signType;
	}

	public String getPaySign() {
		return paySign;
	}

	public void setPaySign(String paySign) {
		this.paySign = paySign;
	} 
	
	
}
