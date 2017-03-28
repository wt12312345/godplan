package com.hq.base.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.hq.web.AbstractClass;

public class HttpUtil extends AbstractClass {

	public static String DEFAULT_CHARACTER = "UTF-8";

	public static HttpUtil httpUtil = new HttpUtil();

	/** http访问模拟客户端 */
	public static HttpClient httpClient = new HttpClient();

	// singlton
	private HttpUtil() {
	};

	public static String doGetNew(String url) {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			// 创建httpget.
			HttpGet httpget = new HttpGet(url);
			// System.out.println("executing request " + httpget.getURI());
			// httpget.addHeader("Content-Type", "text/html; charset=UTF-8");

			// 执行get请求.
			CloseableHttpResponse response = httpclient.execute(httpget);
			// response.addHeader("Content-Type", "text/html; charset=UTF-8");
			// httpclient.addRequestHeader("Content-Type",
			// "text/html; charset=UTF-8");
			try {
				// 获取响应实体
				HttpEntity entity = response.getEntity();
				// System.out.println("--------------------------------------");
				// 打印响应状态
				// System.out.println(response.getStatusLine());
				if (entity != null) {
					// 打印响应内容长度
					// System.out.println("Response content length: "
					// + entity.getContentLength());
					// 打印响应内容
					String result = EntityUtils.toString(entity);
					result = new String(result.getBytes("ISO-8859-1"), "UTF-8");
					// System.out.println("Response content: " + result);
					return result;
				}
				// System.out.println("------------------------------------");
			} finally {
				// response.close();
				EntityUtils.consumeQuietly(response.getEntity());
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
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
		return "";
	}

	/**
	 * Send a request to a address and get the return content.
	 * */
	public static String doGet(String url) {
		return httpUtil.DoGet(url, 2, DEFAULT_CHARACTER);
	}

	public static String doGet222(String url) {
		return httpUtil.DoGet222(url, 2, DEFAULT_CHARACTER);
	}

	public static String doGet(String url, int retry) {
		return httpUtil.DoGet(url, retry, DEFAULT_CHARACTER);
	}

	/** do Get 方法 */
	public static String doGet(String url, int retry, String character) {
		return httpUtil.DoGet(url, retry, character);
	}

	public String DoGet(String url, int leftTime, String character) {
		System.setProperty("jsse.enableSNIExtension", "false");
		GetMethod get = new GetMethod(url);
		get.addRequestHeader("Content-Type", "text/html; charset=" + character);
		get.addRequestHeader("User-Agent","Mozilla/5.0 (Linux; U; Android 4.2.2; zh-cn; GT-N7108D Build/JDQ39) AppleWebKit/533.1 (KHTML, like Gecko)Version/4.0 MQQBrowser/5.4 TBS/025483 Mobile Safari/533.1 MicroMessenger/6.2.0.54_r1169949.561 NetType/WIFI Language/zh_CN");
		try {
			int status = httpClient.executeMethod(get);
			if (status == HttpStatus.SC_OK) {
				// String b=get.getResponseBodyAsString();
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(get.getResponseBodyAsStream(),
								"ISO-8859-1"));
				String tmp = null;
				String htmlRet = "";
				while ((tmp = reader.readLine()) != null) {
					htmlRet += tmp + "\r\n";
				}
				htmlRet = new String(htmlRet.getBytes("ISO-8859-1"), character);
				return htmlRet;
			}
		} catch (Exception e) {
			logger.error("http do get exception:" + e.getMessage(), e);
		} finally {
			get.releaseConnection();
		}
		return null;
	}

	public String DoGet222(String url, int leftTime, String character) {
		System.setProperty("jsse.enableSNIExtension", "false");
		GetMethod get = new GetMethod(url);
		get.addRequestHeader("Content-Type", "text/html; charset=" + character);
		get.addRequestHeader("User-Agent",
				"Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; rv:11.0) like Gecko");
		try {
			int status = httpClient.executeMethod(get);
			if (status == HttpStatus.SC_OK) {
				// String b=get.getResponseBodyAsString();
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(get.getResponseBodyAsStream(),
								"ISO-8859-1"));
				String tmp = null;
				String htmlRet = "";
				while ((tmp = reader.readLine()) != null) {
					htmlRet += tmp + "\r\n";
				}
				htmlRet = new String(htmlRet.getBytes("ISO-8859-1"), character);
				return htmlRet;
			}
		} catch (Exception e) {
			logger.error("http do get exception:" + e.getMessage(), e);
		} finally {
			get.releaseConnection();
		}
		return null;
	}

	// set params for the connection
	private static URLConnection getPostConnection(String url)
			throws MalformedURLException, IOException {
		URL realUrl = new URL(url);
		HttpURLConnection connection = (HttpURLConnection) realUrl
				.openConnection();
		connection.setRequestProperty("accept", "*/*");
		connection.setRequestProperty("connection", "Keep-Alive");
		connection.setRequestProperty("user-agent",
				"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
		connection.setRequestProperty("Content-Type",
				"application/x-www-form-urlencoded");
		connection.setDoOutput(true);
		connection.setDoInput(true);
		connection.setRequestMethod("POST");
		connection.setUseCaches(false);
		return connection;
	}

	public static String doPost(String url, String content, int retry) {
		// do post with content
		return httpUtil.doPostWithContent(url, content, retry);
	}

	/**
	 * 默认的post某个请求到某个地址
	 * 
	 * @param url
	 *            地址
	 * @param content
	 *            内容 默認的情況下是有三次重試的
	 * */
	public static String doPost(String url, String content) {
		// default will retry 3 time
		return doPost(url, content, 2);
	}

	/**
	 * send post to server
	 * */
	public String doPostWithContent(String url, String content, int retry) {
		logger.debug("Do Post With Retry:" + retry + ", URL:" + url);
		StringBuffer result = new StringBuffer();
		BufferedReader in = null;
		DataOutputStream out = null;
		URLConnection connection = null;
		try {
			/* 获取连接 */
			connection = getPostConnection(url);
			// connect
			connection.connect();
			out = new DataOutputStream(connection.getOutputStream());
			// send with UTF-8
			logger.debug("Post Content Before Encode:" + content);
			out.write(content.getBytes("utf-8"));
			// get the input Stream
			out.flush();
		} catch (Exception e) {
			if (e instanceof MalformedURLException) {
				/* 如果是URL格式的问题，不用重试了，直接退出 */
				logger.error(e);
				return null;
			}
			/* 针对不是URL格式错误的异常 */
			if (retry > 0) {
				/* 如果有重试的机会，进行重试 */
				logger.info(e);
				return this.doPostWithContent(url, content, retry - 1);
			} else {
				/* 如果已经没有重试机会了 */
				logger.error(e);
				return null;
			}
		} finally {
			try {
				out.close();
			} catch (Exception e) {
				/* 输出流关不掉了。。。 */
				logger.error(e);
			}
		}

		try {
			/* 从服务器读传回的数据 */
			in = new BufferedReader(new InputStreamReader(
					connection.getInputStream(), "utf-8"));
			String line;
			while ((line = in.readLine()) != null) {
				result.append(line);
			}
		} catch (Exception e) {
			/* 这里IO异常，不太好处理。。。不过一般情况下，是消息已经发过去了 */
			logger.error(e);
			// return null
			return null;
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				/* 输入流关不掉了 */
				logger.error(ex);
			}
		}
		logger.debug("Reply Msg From Weinxin:" + result.toString());
		return result.toString();
	}

	public static int doGetStatus(String url) {
		return httpUtil.getStatus(url);
	}

	public int getStatus(String url) {
		GetMethod get = new GetMethod(url);
		get.addRequestHeader("Content-Type", "text/html; charset="
				+ DEFAULT_CHARACTER);
		int status = 404;
		try {
			status = httpClient.executeMethod(get);
		} catch (HttpException e) {
			logger.error("执行httpUtil get方法失败" + e.getMessage(), e);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
		return status;
	}

}
