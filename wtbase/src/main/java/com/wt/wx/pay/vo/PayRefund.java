package com.wt.wx.pay.vo;


public class PayRefund {
	/**
	 * 公共账号ID
	 */
	private String appid="";
	/**
	 * 商户号
	 */
	private String mcn_id="";
	/**
	 * 商户侧传个微信的订单号
	 */
	private String tradeNo="";
	/**
	 * 商户退款单号  商户系统内部的退款单号，商户系统内部唯一，同一退款单号多次请求只退一笔
	 */
	private String out_refund_no ="";
	/**
	 * 总金额(单位为分)
	 */
	private int total_fee;
	/**
	 * 退款金额
	 */
	private int refund_fee;
	/**
	 * 商户支付秘匙
	 */
	private String key = "";
	/**
	 * mchPath
	 */
	private String mchPath = "";
	
	public String getMchPath() {
		return mchPath;
	}
	public void setMchPath(String mchPath) {
		this.mchPath = mchPath;
	}
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getMcn_id() {
		return mcn_id;
	}
	public void setMcn_id(String mcn_id) {
		this.mcn_id = mcn_id;
	}
	public String getTradeNo() {
		return tradeNo;
	}
	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}
	public String getOut_refund_no() {
		return out_refund_no;
	}
	public void setOut_refund_no(String out_refund_no) {
		this.out_refund_no = out_refund_no;
	}
	public int getTotal_fee() {
		return total_fee;
	}
	public void setTotal_fee(int total_fee) {
		this.total_fee = total_fee;
	}
	public int getRefund_fee() {
		return refund_fee;
	}
	public void setRefund_fee(int refund_fee) {
		this.refund_fee = refund_fee;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	
}
