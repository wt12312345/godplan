package com.wt.wx.pay;

import com.wt.base.util.NonceStr;
import com.wt.wx.pay.vo.MchPaySign;

/** 根据订单生成用于微信支付的paySign(证书版) */
public class MchPaySignFactory {

	/**
	 * 根据订单生成用于微信支付的实例
	 * 
	 * @param wxAccount
	 *            微信账号信息
	 * @param order
	 *            订单信息
	 * @param nonceStr
	 *            随机字符串
	 * @param mch_id
	 *            微信支付分配的商户号
	 * @param ip
	 *            订单生成的机器 IP
	 * @param key
	 *            商户支付密钥
	 * @param openId
	 *            用户标识
	 * @param tradeType
	 *            交易类型(JSAPI 、 NATIVE 、 APP)
	 * @param notify_url
	 *            接收微信支付成功通知
	 * @return
	 */
	public static MchPaySign getInstance(String appId, String mchId,
			String payKey, String orderNo, String description, double price,
			String nonceStr, String ip, String openId, String tradeType,
			String notify_url) {
		MchPaySign m = new MchPaySign();
		m.setAppid(appId);
		m.setBody(description);
		m.setDevice_info(null);
		m.setMch_id(mchId);
		m.setAttach(null);
		m.setTime_start(null);
		m.setTime_expire(null);
		m.setGoods_tag(null);
		m.setOpenid(null);
		m.setGoods_tag(null);
		m.setNotify_url(notify_url);
		m.setTrade_type(tradeType);
		m.setNonce_str(NonceStr.getNonceStr());
		m.setOut_trade_no(orderNo);
		m.setSpbill_create_ip(ip);
		m.setTotal_fee((int) (price * 100) + "");
		m.setOpenid(openId);
		String sign = m.generatePaySign(payKey);
		System.out.println("微信支付回调接口：" + notify_url);
		m.setSign(sign);
		return m;
	}
}
