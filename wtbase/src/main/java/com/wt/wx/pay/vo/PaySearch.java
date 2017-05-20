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

/** 查询是否支付成功 */
@XmlAccessorType(XmlAccessType.FIELD)
// XML文件中的根标识
@XmlRootElement(name = "xml")
// 控制JAXB 绑定类中属性和字段的排序
@XmlType(propOrder = { "appid", "mch_id", "out_trade_no", "nonce_str", "sign" })
public class PaySearch {

	private String appid;
	private String mch_id;
	private String out_trade_no;
	private String nonce_str;
	private String sign;

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getMch_id() {
		return mch_id;
	}

	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getNonce_str() {
		return nonce_str;
	}

	public void setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String generatePaySign(String payKey) {
		String paySign = "";
		String paySignUpperCase = "";
		try {
			String string1 = getSortASCIIString();
			paySign = EncoderHandler.encodeByMD5(string1 + "&key=" + payKey);
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
