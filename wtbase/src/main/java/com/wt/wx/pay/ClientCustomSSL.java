package com.wt.wx.pay;

import java.io.File;
import java.io.FileInputStream;
import java.security.KeyStore;

import javax.net.ssl.SSLContext;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class ClientCustomSSL {

	public CloseableHttpClient getHttpclient(String path, String mchId)
			throws Exception {
		KeyStore keyStore = KeyStore.getInstance("PKCS12");

		String a = new File(this.getClass().getResource("/").getPath())
				.getPath() + path;
		System.out.println("路径=" + a);
		FileInputStream instream = new FileInputStream(a);
		try {
			keyStore.load(instream, mchId.toCharArray());
		} finally {
			instream.close();
		}
		// Trust own CA and all self-signed certs
		SSLContext sslcontext = SSLContexts.custom()
				.loadKeyMaterial(keyStore, mchId.toCharArray()).build();
		// Allow TLSv1 protocol only
		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
				sslcontext, new String[] { "TLSv1" }, null,
				SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
		CloseableHttpClient httpclient = HttpClients.custom()
				.setSSLSocketFactory(sslsf).build();
		return httpclient;
	}

}