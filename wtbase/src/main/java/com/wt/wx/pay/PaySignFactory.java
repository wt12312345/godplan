package com.wt.wx.pay;

import com.wt.base.util.NonceStr;
import com.wt.wx.pay.vo.JsapiPaySign;
import com.wt.wx.pay.vo.Pay;
import com.wt.wx.pay.vo.PaySearch;
import com.wt.wx.pay.vo.PaySign;

/** 根据订单生成用于微信支付的paySign */
public class PaySignFactory {
	/** 根据订单生成用于微信支付的实例 */
	public static PaySign getInstance(String appId, String packageStr,
			long orderTime, String nonceStr) {
		PaySign paySign = new PaySign();
		paySign.setAppid(appId);
		paySign.setNoncestr(nonceStr);
		paySign.setPackagestr(packageStr);
		paySign.setTimestamp(orderTime / 1000 + "");
		return paySign;
	}

	public static JsapiPaySign getMchInstance(Pay pay, long orderTime) {
		JsapiPaySign paySign = new JsapiPaySign();
		paySign.setAppId(pay.getAppId());
		paySign.setPackagestr(pay.getPackageStr());
		paySign.setNonceStr(pay.getNonceStr());
		paySign.setSignType("MD5");
		paySign.setTimeStamp(orderTime / 1000 + "");
		return paySign;
	}

	public static PaySearch getPaySearch(String orderNo, String appId,
			String mchId, String payKey) {
		PaySearch paySearch = new PaySearch();
		paySearch.setNonce_str(NonceStr.getNonceStr());
		paySearch.setAppid(appId);
		paySearch.setMch_id(mchId);
		paySearch.setOut_trade_no(orderNo);
		paySearch.setSign(paySearch.generatePaySign(payKey));
		return paySearch;
	}
}
