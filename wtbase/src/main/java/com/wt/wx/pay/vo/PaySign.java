package com.wt.wx.pay.vo;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

import com.wt.wx.EncoderHandler;
import com.wt.wx.pay.FieldLowerComparator;

/**
 * paySign 字段是对本次发起 JS API 的行为进行鉴权，只有通过了 paySign 鉴权，才能继 续对 package
 * 鉴权并生成预支付单。这里将定义 paySign 的生成规则。
 */
public class PaySign {

	/**
	 * 是 字段名称：公众号 id；字段来源：商户注册具有支付权限的公 众号成功后即可获得；传入方式：由商户直接传入。 参数类型：字符串类型
	 */
	private String appid;

	/**
	 * 是 字段名称：时间戳；字段来源：商户生成从 1970 年 1 月 1 日 00：00：00 至今的秒数，即当前的时间，且最终需要转换为字
	 * 符串形式；由商户生成后传入。 参数类型：字符串类型；参数长度：32 个字节以下。
	 */
	private String timestamp;

	/**
	 * 是 字段名称：随机字符串；字段来源：商户生成的随机字符串。 由商户生成后传入。 参数类型：字符串类型；参数长度：32 个字节以下。
	 */
	private String noncestr;

	/**
	 * 是 字段名称：扩展字符串；参数类型：字符串类型；字段来源： 商户将订单信息组成该字符串，具体组成方案参见接口使用说 明中 package
	 * 组包帮助；由商户按照规范拼接后传入。 参数类型：字符串类型；参数长度：4096 个字节以下。
	 */
	private String packagestr;

	// private String appkey;
	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getNoncestr() {
		return noncestr;
	}

	public void setNoncestr(String noncestr) {
		this.noncestr = noncestr;
	}

	public String getPackagestr() {
		return packagestr;
	}

	public void setPackagestr(String packagestr) {
		this.packagestr = packagestr;
	}

	public String generatePaySign() {
		String paySign = "";
		try {
			String string1 = getSortASCIIString();
			paySign = EncoderHandler.encode("SHA1", string1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return paySign;
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
			Method method = this.getClass().getMethod(
					getGetMethod(field.getName()));
			if (i != 1) {
				sbf.append("&");
			}
			if (field.getName().equals("packagestr")) {
				sbf.append("package").append("=").append(method.invoke(this));
			} else {
				sbf.append(field.getName().toLowerCase()).append("=")
						.append(method.invoke(this));
			}
			i++;
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

	/** 获取测试的paySign */
	public PaySign getTestPaySign() {
		PaySign p = new PaySign();
		p.setAppid("wxf8b4f85f3a794e77");
		p.setTimestamp("189026618");
		p.setNoncestr("adssdasssd13d");
		p.setPackagestr("bank_type=WX&body=XXX&fee_type=1&input_charset=GBK&notify_url=http%3a"
				+ "%2f%2fwww.qq.com&out_trade_no=16642817866003386000&partner=1900000109&spbill_cre"
				+ "ate_ip=127.0.0.1&total_fee=1&sign=BEEF37AD19575D92E191C1E4B1474CA9");
		return p;
	}

	public static void main(String[] args) {
		PaySign p = new PaySign();
		p.setAppid("wxf8b4f85f3a794e77");
		p.setTimestamp("189026618");
		p.setNoncestr("adssdasssd13d");
		p.setPackagestr("bank_type=WX&body=XXX&fee_type=1&input_charset=GBK&notify_url=http%3a"
				+ "%2f%2fwww.qq.com&out_trade_no=16642817866003386000&partner=1900000109&spbill_cre"
				+ "ate_ip=127.0.0.1&total_fee=1&sign=BEEF37AD19575D92E191C1E4B1474CA9");
		System.out.println(p.generatePaySign());
	}
}
