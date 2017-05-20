package com.wt.wx.pay.vo;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.Arrays;

import com.wt.wx.EncoderHandler;
import com.wt.wx.pay.AbstractPay;
import com.wt.wx.pay.FieldComparator;

/**
 * 订单详情（package）扩展字符串定义 在商户调起 JS API 时，商户需要此时确定该笔订单详情，并将该订单详情通过一定的 方式进行组合放入
 * package。JS API 调用后，微信将通过 package 的内容生成预支付单
 */
public class Package extends AbstractPay {
	/**
	 * 银行通道类型，由于这里是使用的微信公众号支付，因此 这个字段固定为 WX，注意大写。参数取值："WX"。 必须字段
	 */
	private String bank_type;

	/**
	 * 商品描述。参数长度：128 字节以下 必须字段
	 */
	private String body;

	/**
	 * 附加数据，原样返回。128 字节以下 可选字段
	 */
	private String attach;

	/**
	 * 商户号,即注册时分配的 partnerId。 必选字段
	 */
	private String partner;

	/**
	 * 商户系统内部的订单号,32 个字符内、可包含字母,确保在商 户系统唯一。 参数取值范围：32 字节以下。 必选字段
	 */
	private String out_trade_no;

	/**
	 * 订单总金额，单位为分。 必选字段
	 */
	private String total_fee;

	/**
	 * 现金支付币种,取值：1（人民币）,默认值是 1，暂只支持 1。 必选字段
	 */
	private String fee_type;

	/**
	 * 通知 URL,在支付完成后,接收微信通知支付结果的 URL,需 给 绝 对 路 径 ,255 字 符 内 , 格 式
	 * 如:http://wap.tenpay.com/tenpay.asp。 取值范围：255 字节以内。 必选字段
	 */
	private String notify_url;

	/**
	 * 订单生成的机器 IP，指用户浏览器端 IP，不是商户服务器 IP，格式为 IPV4 整型。 取值范围：15 字节以内。 必选字段
	 */
	private String spbill_create_ip;

	/**
	 * 交 易 起 始 时 间 ， 也 是 订 单 生 成 时 间 ， 格 式 为 yyyyMMddHHmmss，如 2009 年 12 月 25 日 9
	 * 点 10 分 10 秒表示为 20091225091010。时区为 GMT+8 beijing。该时 间取自商户服务器。 取值范围：14 字节。
	 * 可选字段
	 */
	private String time_start;

	/**
	 * 交 易 结 束 时 间 ， 也 是 订 单 失 效 时 间 ， 格 式 为 yyyyMMddHHmmss，如 2009 年 12 月 27 日 9
	 * 点 10 分 10 秒表示为 20091227091010。时区为 GMT+8 beijing。该时 间取自商户服务器。取值范围：14 字节。
	 * 可选字段
	 */
	private String time_expire;

	/**
	 * 物流费用，单位为分。如果有值，必须保证 transport_fee + product_fee=total_fee。 可选字段
	 */
	private String transport_fee;

	/**
	 * 商品费用，单位为分。如果有值，必须保证 transport_fee + product_fee=total_fee。 可选字段
	 */
	private String product_fee;

	/**
	 * 商品标记，优惠券时可能用到。 可选字段
	 */
	private String goods_tag;

	/**
	 * 传入参数字符编码。取值范围："GBK"、"UTF-8"。默认： "GBK" 必选字段
	 */
	private String input_charset;

	/** 对应商户的partnerKey */
	private String key;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getBank_type() {
		return bank_type;
	}

	public void setBank_type(String bank_type) {
		this.bank_type = bank_type;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getAttach() {
		return attach;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}

	public String getPartner() {
		return partner;
	}

	public void setPartner(String partner) {
		this.partner = partner;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(String total_fee) {
		this.total_fee = total_fee;
	}

	public String getFee_type() {
		return fee_type;
	}

	public void setFee_type(String fee_type) {
		this.fee_type = fee_type;
	}

	public String getNotify_url() {
		return notify_url;
	}

	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}

	public String getSpbill_create_ip() {
		return spbill_create_ip;
	}

	public void setSpbill_create_ip(String spbill_create_ip) {
		this.spbill_create_ip = spbill_create_ip;
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

	public String getTransport_fee() {
		return transport_fee;
	}

	public void setTransport_fee(String transport_fee) {
		this.transport_fee = transport_fee;
	}

	public String getProduct_fee() {
		return product_fee;
	}

	public void setProduct_fee(String product_fee) {
		this.product_fee = product_fee;
	}

	public String getGoods_tag() {
		return goods_tag;
	}

	public void setGoods_tag(String goods_tag) {
		this.goods_tag = goods_tag;
	}

	public String getInput_charset() {
		return input_charset;
	}

	public void setInput_charset(String input_charset) {
		this.input_charset = input_charset;
	}

	/** 根据微信支付文档的算法，生成package字符串 */
	public String generatePackage() {
		String result = "";
		try {
			System.getProperties().setProperty("file.encoding", "UTF-8");
			String string1 = getSortASCIIString();
			String signValue = EncoderHandler.encodeByMD5(
					string1 + "&key=" + key).toUpperCase();
			String string2 = getUrlEncodeSortASCIIString();
			result = string2 + "&sign=" + signValue;
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
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
		Arrays.sort(fields, new FieldComparator());
		int i = 1;
		for (Field field : fields) {
			if (!"key".equals(field.getName())) {
				Method method = this.getClass().getMethod(
						getGetMethod(field.getName()));
				Object o = method.invoke(this);
				if (o != null && "" != o) {
					if (i != 1) {
						sbf.append("&");
					}
					sbf.append(field.getName()).append("=").append((String) o);
					i++;
				}
			}

		}
		return sbf.toString();
	}

	/**
	 * 获取根据ASCII码编译的字符串，并且 加密
	 * 
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws UnsupportedEncodingException
	 */
	private String getUrlEncodeSortASCIIString() throws NoSuchMethodException,
			SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException,
			UnsupportedEncodingException {
		StringBuffer sbf = new StringBuffer();

		Field[] fields = this.getClass().getDeclaredFields();
		Arrays.sort(fields, new FieldComparator());
		int i = 1;
		for (Field field : fields) {
			if (!"key".equals(field.getName())) {
				Method method = this.getClass().getMethod(
						getGetMethod(field.getName()));
				Object o = method.invoke(this);
				if (o != null && "" != o) {
					if (i != 1) {
						sbf.append("&");
					}
					sbf.append(field.getName())
							.append("=")
							.append(URLEncoder.encode((String) o, "UTF-8")
									.replace("+", "%20"));
					i++;
				}
			}
		}

		return sbf.toString();
	}

}
