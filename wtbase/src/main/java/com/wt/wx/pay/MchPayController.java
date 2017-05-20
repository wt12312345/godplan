package com.wt.wx.pay;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.wt.wx.constant.WxUrl;
import com.wt.wx.pay.vo.PrepayReturn;

/** 统一支付接口（证书版） */
public class MchPayController {

	private Logger logger = Logger.getLogger(this.getClass());

	/** 统一支付接口（证书版） */
	public PrepayReturn prepay(String xml, String machPath, String mchId) {
		PrepayReturn prepayReturn = null;
		try {
			ClientCustomSSL clientCustom = new ClientCustomSSL();
			CloseableHttpClient httpclient = clientCustom.getHttpclient(
					machPath, mchId);
			String url = WxUrl.URL_PAY_POST;
			logger.info("调用支付接口：" + url);
			logger.info("调用支付接口传送XML参数：" + xml);
			HttpPost httpPost = new HttpPost(url);
			StringEntity myEntity = new StringEntity(xml, "UTF-8");
			httpPost.setEntity(myEntity);
			try {
				CloseableHttpResponse response = httpclient.execute(httpPost);
				try {
					HttpEntity result = response.getEntity();
					String resultStr = EntityUtils.toString(result, "UTF-8");
					logger.info("调用支付接口返回信息：" + resultStr);
					if (result != null) {
						prepayReturn = (PrepayReturn) XMLUtil
								.convertXmlStrToObject(PrepayReturn.class,
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
			logger.error("统一支付接口异常：" + e.getMessage(), e);
		}
		return prepayReturn;
	}
}
