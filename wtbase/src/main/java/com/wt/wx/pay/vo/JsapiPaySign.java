package com.wt.wx.pay.vo;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

import com.wt.wx.EncoderHandler;
import com.wt.wx.pay.FieldLowerComparator;

public class JsapiPaySign {

	/**
	 * 是 字段名称：公众号 id；字段来源：商户注册具有支付权限的公 众号成功后即可获得；传入方式：由商户直接传入。 参数类型：字符串类型
	 */
	private String appId;

	/**
	 * 是 字段名称：时间戳；字段来源：商户生成从 1970 年 1 月 1 日 00：00：00 至今的秒数，即当前的时间，且最终需要转换为字
	 * 符串形式；由商户生成后传入。 参数类型：字符串类型；参数长度：32 个字节以下。
	 */
	private String timeStamp;

	/**
	 * 是 字段名称：随机字符串；字段来源：商户生成的随机字符串。 由商户生成后传入。 参数类型：字符串类型；参数长度：32 个字节以下。
	 */
	private String nonceStr;

	/**
	 * 是 字段名称：扩展字符串；参数类型：字符串类型；字段来源： 商户将订单信息组成该字符串，具体组成方案参见接口使用说 明中 package
	 * 组包帮助；由商户按照规范拼接后传入。 参数类型：字符串类型；参数长度：4096 个字节以下。
	 */
	private String packagestr;

	private String signType;

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

	public String getPackagestr() {
		return packagestr;
	}

	public void setPackagestr(String packagestr) {
		this.packagestr = packagestr;
	}

	public String generatePaySign(String key) {
		String paySign = "";
		String paySignUpperCase = "";
		try {
			String string1 = getSortASCIIString();
			string1 += "&key=" + key;
			System.out.println("****" + string1);
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
			Method method = this.getClass().getMethod(
					getGetMethod(field.getName()));
			if (i != 1) {
				sbf.append("&");
			}
			if (field.getName().equals("packagestr")) {
				sbf.append("package").append("=").append(method.invoke(this));
			} else {
				sbf.append(field.getName()).append("=")
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
}
