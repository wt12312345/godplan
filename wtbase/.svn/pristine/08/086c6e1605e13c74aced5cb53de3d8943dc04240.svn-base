package com.hq.base.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.hq.web.AbstractClass;

public class HttpNewUtil extends AbstractClass {

	public String getNew(String url) {
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

	public String doPost(String url, Map<String, String> params,
			String charset, boolean pretty) {
		HttpClient client = new HttpClient();
		try {
			// 设置代理服务器地址和端口
			// client.getHostConfiguration().setProxy("proxy_host_addr",proxy_port);
			// 使用GET方法，如果服务器需要通过HTTPS连接，那只需要将下面URL中的http换成https
			// HttpMethod method = new
			// GetMethod("http://10.1.14.20:8088/workflowController/service/todo/addTask");
			// 使用POST方法
			PostMethod method = new PostMethod(url);
			for (Map.Entry<String, String> entry : params.entrySet()) {
				((PostMethod) method).addParameter(entry.getKey(),
						entry.getValue());
			}
			HttpMethodParams param = method.getParams();
			param.setContentCharset("UTF-8");

			client.executeMethod(method);
			// 打印服务器返回的状态
			// System.out.println(method.getStatusLine());
			// 打印返回的信息
			InputStream stream = method.getResponseBodyAsStream();

			BufferedReader br = new BufferedReader(new InputStreamReader(
					stream, "UTF-8"));
			StringBuffer buf = new StringBuffer();
			String line;
			while (null != (line = br.readLine())) {
				buf.append(line).append("\n");
			}
			// System.out.println(buf.toString());
			// 释放连接
			method.releaseConnection();
			return buf.toString();
		} catch (IOException e) {
			logger.error("执行HTTP Post请求 " + url + " 时，发生异常！" + e.getMessage());
		}
		return null;
	}

	public static String doPost(String url, List<String> listKey,
			List<String> listValue) {
		HttpClient client = new HttpClient();
		try {
			PostMethod method = new PostMethod(url);
			if (listKey != null) {
				for (int i = 0; i < listKey.size(); i++) {
					((PostMethod) method).addParameter(listKey.get(i),
							listValue.get(i));
				}
			}
			HttpMethodParams param = method.getParams();
			param.setContentCharset("UTF-8");

			client.executeMethod(method);
			InputStream stream = method.getResponseBodyAsStream();

			BufferedReader br = new BufferedReader(new InputStreamReader(
					stream, "UTF-8"));
			StringBuffer buf = new StringBuffer();
			String line;
			while (null != (line = br.readLine())) {
				buf.append(line).append("\n");
			}
			method.releaseConnection();
			String result = buf.toString();
			return result;
		} catch (IOException e) {
			System.out.println("执行HTTP Post请求 " + url + " 时，发生异常！"
					+ e.getMessage());
		}
		return null;
	}

	public static String sendPost(String url, Map<String, String> params) {
		OutputStreamWriter out = null;
		BufferedReader in = null;
		StringBuilder result = new StringBuilder();
		try {
			URL realUrl = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) realUrl
					.openConnection();
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// POST方法
			conn.setRequestMethod("POST");
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			conn.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			conn.connect();
			// 获取URLConnection对象对应的输出流
			out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
			// 发送请求参数
			if (params != null) {
				StringBuilder param = new StringBuilder();
				for (Map.Entry<String, String> entry : params.entrySet()) {
					if (param.length() > 0) {
						param.append("&");
					}
					param.append(entry.getKey());
					param.append("=");
					param.append(entry.getValue());
					System.out.println(entry.getKey() + ":" + entry.getValue());
				}
				System.out.println("param:" + param.toString());
				out.write(param.toString());
			}
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(
					conn.getInputStream(), "UTF-8"));
			String line;
			while ((line = in.readLine()) != null) {
				result.append(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result.toString();
	}
}
