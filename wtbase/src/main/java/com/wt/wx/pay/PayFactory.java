package com.wt.wx.pay;

import java.util.Date;

import com.wt.base.util.NonceStr;
import com.wt.wx.pay.vo.JsapiPaySign;
import com.wt.wx.pay.vo.MchPaySign;
import com.wt.wx.pay.vo.Pay;
import com.wt.wx.pay.vo.PrepayReturn;

/** 支付对象工厂，用于根据菜单生成用于支付的package */
public class PayFactory {
	/**
	 * 生成用于支付的对象
	 * 
	 * @param orderNo
	 *            订单号
	 * @param description
	 *            订单描述
	 * @param price
	 *            钱
	 * @param orderCreateTime
	 *            订单时间
	 * @param ip
	 * @param openId
	 * @param notify_url
	 * @return
	 */
	public static Pay getMchPay(String appId, String mchId, String payKey,
			String payPath, String orderNo, String description, double price,
			Date orderCreateTime, String ip, String openId, String notify_url) {
		System.out.println("getMchPay***************************");
		Pay pay = new Pay();
		pay.setAppId(appId);
		pay.setNonceStr(NonceStr.getNonceStr());
		pay.setTimeStamp(orderCreateTime.getTime() / 1000 + "");
		pay.setSignType("MD5");

		// 取得与支付ID的签名
		MchPaySign paySign = MchPaySignFactory.getInstance(appId, mchId,
				payKey, orderNo, description, price, pay.getNonceStr(), ip,
				openId, "JSAPI", notify_url);
		System.out.println("获取支付签名成功");

		// 统 一 支 付 接 口 返 回 的prepay_id 参数值
		MchPayController mchPay = new MchPayController();
		String paySignXML = XMLUtil.convertToXml(paySign);
		PrepayReturn preReturn = mchPay.prepay(paySignXML, payPath,
				paySign.getMch_id());
		// System.out.println("____________" + preReturn.getErr_code());
		// System.out.println("____________" + preReturn.getErr_code_des());
		// System.out.println("____________" + preReturn.getResult_code());
		// System.out.println("____________" + preReturn.getReturn_code());
		// System.out.println("____________" + preReturn.getReturn_msg());
		// System.out.println("prepay_id：" + preReturn.getPrepay_id());
		pay.setPackageStr("prepay_id=" + preReturn.getPrepay_id());

		// JSAPI签名
		JsapiPaySign jsapiPaySign = PaySignFactory.getMchInstance(pay,
				orderCreateTime.getTime());
		pay.setPaySign(jsapiPaySign.generatePaySign(payKey));
		return pay;
	}
}
