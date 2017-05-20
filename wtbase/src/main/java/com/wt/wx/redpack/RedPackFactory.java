package com.wt.wx.redpack;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.stereotype.Service;

import com.wt.base.util.MD5Util;
import com.wt.base.util.NonceStr;
import com.wt.base.util.TimeUtil;
import com.wt.base.util.TypeUtil;
import com.wt.wx.constant.WxUrl;
import com.wt.wx.entity.RedPack;
import com.wt.wx.pay.ClientCustomSSL;
import com.wt.wx.pay.XMLUtil;
import com.wt.wx.redpack.vo.RedPackReturn;

@Service("redPackFactory")
public class RedPackFactory {

	private Logger logger = Logger.getLogger(this.getClass());

	/**
	 * 
	 * @param nonce_str
	 *            随机字符串
	 * @param remark
	 *            描述
	 * @param wishing
	 *            祝福语
	 * @param send_name
	 *            商户名
	 * @param IP
	 *            调用接口的机器的IP
	 * @param re_openid
	 *            接受红包的用户的openid
	 * @param total_amount
	 *            红包金额
	 * @return
	 */
	public String getRedPack(RedPack redPack) {

		String number = "";
		/**
		 * 商户号
		 */
		String mchId = redPack.getMchId();
		/**
		 * 公众账号appid
		 */
		String appId = redPack.getAppId();
		/**
		 * 红包名称
		 */
		String act_name = redPack.getAct_name();
		/**
		 * 商户支付秘匙
		 */
		String key = redPack.getWxKey();
		/**
		 * 随机字符串
		 */
		String nonce_str = NonceStr.getNonceStr();
		/**
		 * ip
		 */
		String ip = redPack.getWxIp();
		/**
		 * 用户openid
		 */
		String openId = redPack.getRe_openid();
		/**
		 * 描述
		 */
		String mark = redPack.getWxRemark();
		/**
		 * 红包名称
		 */
		String send_name = redPack.getSend_name();
		/**
		 * 金额
		 */
		int total_amount = redPack.getTotal_amount();
		/**
		 * 祝福语
		 */
		String wishing = redPack.getWishing();
		String msg = null;
		try {
			int a[] = new int[10];
			for (int i = 0; i < a.length; i++) {
				a[i] = (int) (10 * (Math.random()));
				number += TypeUtil.toString(a[i]);
			}
			String time = TimeUtil.getDateNowStr();
			/**
			 * 商户订单号(由商户号+当前时间（yyyymmdd格式）+10位一天内不能重复的数字。)
			 */
			String mch_billno = redPack.getMchId() + time + number;
			// 按ASCII从小到大排序
			String signa = "act_name=" + act_name + "&client_ip=" + ip
					+ "&mch_billno=" + mch_billno + "&mch_id=" + mchId
					+ "&nonce_str=" + nonce_str + "&re_openid=" + openId
					+ "&remark=" + mark + "&send_name=" + send_name
					+ "&total_amount=" + total_amount + "&total_num=1"
					+ "&wishing=" + wishing + "&wxappid=" + appId;
			String sign = signa + "&key=" + key;
			sign = new String(sign.getBytes(), "ISO-8859-1");
			/**
			 * 取得签名
			 */
			String signature = MD5Util.string2MD5(sign).toUpperCase();
			System.out.println("获取的sign=" + signature);
			RedPackReturn redPackReturn = getInstance(nonce_str, mark,
					mch_billno, wishing, mchId, signature, send_name, ip,
					openId, total_amount, act_name, appId);
			String paySignXML = XMLUtil.convertToXml(redPackReturn);

			// RedPackReturn preReturn =
			System.out.println("发送的XML:" + paySignXML);
			msg = redPackReturn(paySignXML, redPackReturn.getMch_id(),
					redPack.getMchPath());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}

	private String redPackReturn(String xml, String mchId, String mchPath) {
		String wxCode = "";
		try {
			ClientCustomSSL clientCustom = new ClientCustomSSL();
			CloseableHttpClient httpclient = clientCustom.getHttpclient(
					mchPath, mchId);
			// 调用接口地址
			String url = WxUrl.SendRedPack;
			HttpPost httpPost = new HttpPost(url);
			xml = new String(xml.getBytes(), "ISO-8859-1");
			StringEntity myEntity = new StringEntity(xml);

			System.out.println("发送xml:" + xml);
			httpPost.setEntity(myEntity);
			try {
				CloseableHttpResponse response = httpclient.execute(httpPost);
				try {
					HttpEntity result = response.getEntity();
					String resultStr = EntityUtils.toString(result, "UTF-8");
					System.out.println("调用红包接口返回信息：" + resultStr);
					if (result != null) {
						Document document = DocumentHelper.parseText(resultStr);
						Element root = document.getRootElement();
						Iterator<?> iter = root.elementIterator();
						// 返回结果 SUCCESS/FAIL
						while (iter.hasNext()) {
							Element ele = (Element) iter.next();
							if (ele.getName().equals("return_code")) {
								wxCode = ele.getText();
							} else if (ele.getName().equals("err_code")) {
								logger.info("错误信息："+ele.getText());
							} else if (ele.getName().equals("err_code_des")) {
								logger.info("错误提示："+ele.getName().equals("err_code_des"));
							}
						}
						XMLUtil.convertXmlStrToObject(RedPackReturn.class,
								resultStr);
					}
				} finally {
					response.close();
				}
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				// 关闭连接,释放资源
				try {
					httpclient.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			// logger.error("红包接口异常：" + e.getMessage(), e);
		}
		return wxCode;
	}

	private RedPackReturn getInstance(String nonce_str, String remark,
			String mch_billno, String wishing, String mchId, String signature,
			String send_name, String IP, String re_openid, int total_amount,
			String act_name, String appId) {
		RedPackReturn redPack = new RedPackReturn();
		redPack.setAct_name(act_name);
		redPack.setClient_ip(IP);
		redPack.setMch_billno(mch_billno);
		redPack.setMch_id(mchId);
		redPack.setNonce_str(nonce_str);
		redPack.setRe_openid(re_openid);
		redPack.setRemark(remark);
		redPack.setSend_name(send_name);
		redPack.setSign(signature);
		redPack.setTotal_amount(total_amount);
		redPack.setTotal_num(1);
		redPack.setWishing(wishing);
		redPack.setWxappid(appId);
		return redPack;
	}

}
