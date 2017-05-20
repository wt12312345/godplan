package com.wt.wx.pay.vo;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.wt.wx.EncoderHandler;
import com.wt.wx.pay.FieldLowerComparator;

/** 统一支付接口(证书版) 参数类 */
@XmlAccessorType(XmlAccessType.FIELD)
// XML文件中的根标识
@XmlRootElement(name = "xml")
// 控制JAXB 绑定类中属性和字段的排序
@XmlType(propOrder = { "appid", "body", "mch_id", "nonce_str",
		"spbill_create_ip", "total_fee", "device_info", "attach", "time_start",
		"out_trade_no", "time_expire", "sign", "goods_tag", "openid",
		"product_id", "notify_url", "trade_type" })
public class MchPaySign {

	private String appid;

	private String body;

	private String mch_id;

	private String nonce_str;

	private String out_trade_no;

	private String spbill_create_ip;

	private String total_fee;

	private String device_info;

	private String sign;

	private String attach;

	private String time_start;

	private String time_expire;

	private String goods_tag;

	private String openid;

	private String product_id;

	private String notify_url;

	private String trade_type;

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getMch_id() {
		return mch_id;
	}

	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}

	public String getNonce_str() {
		return nonce_str;
	}

	public void setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getSpbill_create_ip() {
		return spbill_create_ip;
	}

	public void setSpbill_create_ip(String spbill_create_ip) {
		this.spbill_create_ip = spbill_create_ip;
	}

	public String getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(String total_fee) {
		this.total_fee = total_fee;
	}

	public String getDevice_info() {
		return device_info;
	}

	public void setDevice_info(String device_info) {
		this.device_info = device_info;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getAttach() {
		return attach;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}

	public String getTime_start() {
		return time_start;
	}

	public void setTime_start(String time_start) {
		this.time_start = time_start;
	}

	public String getTime_expire() {
		return time_expire;
	}

	public void setTime_expire(String time_expire) {
		this.time_expire = time_expire;
	}

	public String getGoods_tag() {
		return goods_tag;
	}

	public void setGoods_tag(String goods_tag) {
		this.goods_tag = goods_tag;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getProduct_id() {
		return product_id;
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}

	public String getNotify_url() {
		return notify_url;
	}

	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}

	public String getTrade_type() {
		return trade_type;
	}

	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}

	public String generatePaySign(String key) {
		String paySign = "";
		String paySignUpperCase = "";
		try {
			String string1 = getSortASCIIString();
			string1 += "&key=" + key;
			System.out.println(string1);
			paySign = EncoderHandler.encodeByMD5(string1);
			paySignUpperCase = paySign.toUpperCase();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return paySignUpperCase;
	}

	/**
	 * 获取根据ASCII码编译的字符串
	 * 
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	private String getSortASCIIString() throws NoSuchMethodException,
			SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		StringBuffer sbf = new StringBuffer();
		Field[] fields = this.getClass().getDeclaredFields();
		Arrays.sort(fields, new FieldLowerComparator());
		int i = 1;
		for (Field field : fields) {
			if (!field.getName().equals("sign")) {
				Method method = this.getClass().getMethod(
						getGetMethod(field.getName()));
				Object value = method.invoke(this);
				if (value != null && !"".equals(value.toString())) {
					if (i != 1) {
						sbf.append("&");
					}
					sbf.append(field.getName().toLowerCase()).append("=")
							.append(value);
					i++;
				}
			}
		}
		return sbf.toString();
	}

	/** 根据属性获取get方法 */
	private String getGetMethod(String fieldName) {
		if (fieldName.length() == 1) {
			return "get" + fieldName.toUpperCase();
		} else {
			return "get" + fieldName.substring(0, 1).toUpperCase()
					+ fieldName.substring(1);
		}
	}
}
