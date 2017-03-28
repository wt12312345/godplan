package com.hq.sms;

public class SmsAlidayuBo {
	private String mobile = "";
	/**
	 * 短信模版：比如注册、密码找回
	 */
	private String templateCode = "";
	private String extend = "";
	private String url = "";
	private String appKey = "";
	private String appSecret = "";
	/**
	 * 短信签名 ：【${signName}】欢迎使用阿里大于服务。
	 */
	private String signName = "";
	/**
	 * 验证码${code}，您正在进行${product}身份验证，打死不要告诉别人哦！
	 */
	private String code = "";
	/**
	 * 验证码${code}，您正在进行${product}身份验证，打死不要告诉别人哦！
	 */
	private String product = "";

	public String getMobile() {
		return mobile;
	}

	public String getTemplateCode() {
		return templateCode;
	}

	public String getExtend() {
		return extend;
	}

	public String getUrl() {
		return url;
	}

	public String getAppKey() {
		return appKey;
	}

	public String getAppSecret() {
		return appSecret;
	}

	public String getSignName() {
		return signName;
	}

	public String getCode() {
		return code;
	}

	public String getProduct() {
		return product;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
	}

	public void setExtend(String extend) {
		this.extend = extend;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

	public void setSignName(String signName) {
		this.signName = signName;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setProduct(String product) {
		this.product = product;
	}
}
