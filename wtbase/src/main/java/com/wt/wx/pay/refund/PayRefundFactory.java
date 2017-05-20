package com.wt.wx.pay.refund;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.wt.base.util.MD5Util;
import com.wt.base.util.NonceStr;
import com.wt.wx.constant.WxUrl;
import com.wt.wx.pay.ClientCustomSSL;
import com.wt.wx.pay.XMLUtil;
import com.wt.wx.pay.vo.PayRefund;
import com.wt.wx.pay.vo.PayRefundReturn;

public class PayRefundFactory {

	/**
	 * @param remark
	 * @param wishing
	 * @param send_name
	 * @param IP
	 * @param re_openid
	 * @param total_amount
	 */
	public Element MoenyBacak(PayRefund payRefund) {
		/**
		 * 商户号
		 */
		String mchId = payRefund.getMcn_id();
		/**
		 * 公众账号appid
		 */
		String appId = payRefund.getAppid();
		/**
		 * 随机字符串
		 */
		String nonce_str = NonceStr.getNonceStr();
		/**
		 * 商户侧传个微信的订单号
		 */
		String tradeNo = payRefund.getTradeNo();
		/**
		 * 商户退款单号 商户系统内部的退款单号，商户系统内部唯一，同一退款单号多次请求只退一笔
		 */
		String out_refund_no = payRefund.getOut_refund_no();
		/**
		 * 总金额(单位为分)
		 */
		int total_fee = payRefund.getTotal_fee();
		/**
		 * 退款金额
		 */
		int refund_fee = payRefund.getRefund_fee();
		/**
		 * 商户支付秘匙
		 */
		// String key = "yangxiaotao82hbgK9jKsEns2njsdhes";
		String key = payRefund.getKey();
		// 按ASCII从小到大排序
		String signa = "appid=" + appId + "&mch_id=" + mchId + "&nonce_str="
				+ nonce_str + "&op_user_id=" + mchId + "&out_refund_no="
				+ out_refund_no + "&out_trade_no=" + tradeNo + "&refund_fee="
				+ refund_fee + "&total_fee=" + total_fee;
		String sign = signa + "&key=" + key;
		System.out.println("获取的sign原始数据=" + signa);
		/**
		 * 取得签名
		 */
		String signature = MD5Util.string2MD5(sign).toUpperCase();
		System.out.println("获取的sign=" + signature);
		PayRefundReturn moneyBackReturn = getInstance(appId, mchId, nonce_str,
				out_refund_no, tradeNo, refund_fee, total_fee, signature);
		String paySignXML = XMLUtil.convertToXml(moneyBackReturn);
		Element msg = redPackReturn(paySignXML, moneyBackReturn.getMch_id(),payRefund.getMchPath());
		return msg;
	}

	public Element redPackReturn(String xml, String mchId, String mchPath) {
		Element root = null;
		try {
			ClientCustomSSL clientCustom = new ClientCustomSSL();
			//String mchPath = WxUrl.PayMchPath;
			System.out.println("mchPath=" + mchPath);

			CloseableHttpClient httpclient = clientCustom.getHttpclient(
					mchPath, mchId);
			// 调用接口地址
			String url = WxUrl.URL_MONEYBACK;
			HttpPost httpPost = new HttpPost(url);
			StringEntity myEntity = new StringEntity(xml, "UTF-8");
			System.out.println("发送xml:" + xml);
			httpPost.setEntity(myEntity);
			try {
				CloseableHttpResponse response = httpclient.execute(httpPost);
				try {
					HttpEntity result = response.getEntity();
					String resultStr = EntityUtils.toString(result, "UTF-8");
					//获取接收到的XML的节点
					Document document = DocumentHelper.parseText(resultStr);
					root = document.getRootElement();
					
					System.out.println("调用退款接口返回信息：" + resultStr);
					if (result != null) {
						// PayRefundReturn moneyBackReturn = (PayRefundReturn)
						XMLUtil.convertXmlStrToObject(PayRefundReturn.class,
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
		return root;
	}

	public PayRefundReturn getInstance(String appId, String mchId,
			String nonce_str, String out_refund_no, String tradeNo,
			int refund_fee, int total_fee, String signature) {
		PayRefundReturn moneyBackReturn = new PayRefundReturn();
		moneyBackReturn.setAppid(appId);
		moneyBackReturn.setMch_id(mchId);
		moneyBackReturn.setNonce_str(nonce_str);
		moneyBackReturn.setOp_user_id(mchId);
		moneyBackReturn.setOut_refund_no(out_refund_no);
		moneyBackReturn.setOut_trade_no(tradeNo);
		moneyBackReturn.setRefund_fee(refund_fee);
		moneyBackReturn.setSign(signature);
		moneyBackReturn.setTotal_fee(total_fee);
		moneyBackReturn.setTransaction_id("");
		return moneyBackReturn;
	}

}
