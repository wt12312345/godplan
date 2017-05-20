package com.wt.wx.pay.vo;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

import com.wt.wx.EncoderHandler;
import com.wt.wx.pay.AbstractPay;
import com.wt.wx.pay.FieldLowerComparator;

/** 回复微信package xml对象 */
public class PackageReturn extends AbstractPay {
	private String appId;
	private String packageStr;
	private String timeStamp;
	private String nonceStr;
	private String retCode;
	private String retErrMsg;
	private String appSignature;
	private String signMethod;
	private String appKey;

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getPackageStr() {
		return packageStr;
	}

	public void setPackageStr(String packageStr) {
		this.packageStr = packageStr;
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

	public String getRetCode() {
		return retCode;
	}

	public void setRetCode(String retCode) {
		this.retCode = retCode;
	}

	public String getRetErrMsg() {
		return retErrMsg;
	}

	public void setRetErrMsg(String retErrMsg) {
		this.retErrMsg = retErrMsg;
	}

	public String getAppSignature() {
		return appSignature;
	}

	public void setAppSignature(String appSignature) {
		this.appSignature = appSignature;
	}

	public String getSignMethod() {
		return signMethod;
	}

	public void setSignMethod(String signMethod) {
		this.signMethod = signMethod;
	}

	/** 根据微信支付文档的算法，生成appSignature字符串 */
	public String generateAppSignature() {
		String result = "";
		try {
			String string1 = getSortASCIIString();
			result = EncoderHandler.encode("SHA1", string1);
			this.appSignature = result;
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
		Arrays.sort(fields, new FieldLowerComparator());
		int i = 1;
		for (Field field : fields) {
			if ("appId".equals(field.getName())
					|| "appKey".equals(field.getName())
					|| "packageStr".equals(field.getName())
					|| "timeStamp".equals(field.getName())
					|| "nonceStr".equals(field.getName())
					|| "retCode".equals(field.getName())
					|| "retErrMsg".equals(field.getName())) {
				Method method = this.getClass().getMethod(
						getGetMethod(field.getName()));
				if (i != 1) {
					sbf.append("&");
				}
				if ("packageStr".equals(field.getName())) {
					sbf.append("package").append("=")
							.append(method.invoke(this));
				} else {
					sbf.append(field.getName().toLowerCase()).append("=")
							.append(method.invoke(this));
				}
				i++;
			}

		}

		return sbf.toString();
	}

	/** 将packageReturn 对象转换为xml */
	public String toXMLString() {
		StringBuffer xml = new StringBuffer();
		xml.append("<xml>");
		xml.append("<TimeStamp><![CDATA[").append(timeStamp)
				.append("]]></TimeStamp>");
		xml.append("<RetErrMsg><![CDATA[").append(retErrMsg)
				.append("]]></RetErrMsg>");
		xml.append("<NonceStr><![CDATA[").append(nonceStr)
				.append("]]></NonceStr>");
		xml.append("<RetCode><![CDATA[").append(retCode)
				.append("]]></RetCode>");
		xml.append("<AppId><![CDATA[").append(appId).append("]]></AppId>");
		xml.append("<AppSignature><![CDATA[").append(appSignature)
				.append("]]></AppSignature>");
		xml.append("<SignMethod><![CDATA[").append(signMethod)
				.append("]]></SignMethod>");
		xml.append("<Package><![CDATA[").append(packageStr)
				.append("]]></Package>");
		xml.append("</xml>");
		return xml.toString();
	}

	public static void main(String[] args) {
		PackageReturn re = new PackageReturn();
		re.setAppId("wxf8b4f85f3a794e77");
		re.setPackageStr("bank_type=WX&body=test&fee_type=1&input_charset=GBK&notify_url=htttp%3A%2F%2Fwww.baidu.com&out_trade_no=CRMmdOZoMjkTTBZs&partner=1900000109&spbill_create_ip=127.0.0.1&total_fee=1&sign=001FF198C481C7799988809989BC80A5");
		re.setTimeStamp("1402658232");
		re.setNonceStr("xxgfWFW4TTxwAad8");
		re.setRetCode("0");
		re.setRetErrMsg("ok");
		re.setSignMethod("sha1");
		re.setAppKey("2Wozy2aksie1puXUBpWD8oZxiD1DfQuEaiC7KcRATv1Ino3mdopKaPGQQ7TtkNySuAmCaDCrw4xhPY5qKTBl7Fzm0RgR3c0WaVYIXZARsxzHV2x7iwPPzOz94dnwPWSn");
		System.out.println(re.generateAppSignature());
	}
}
