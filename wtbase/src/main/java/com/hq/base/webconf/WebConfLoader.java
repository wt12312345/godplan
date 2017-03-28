package com.hq.base.webconf;

import java.io.InputStream;
import java.util.Properties;

public class WebConfLoader {
	private WebConfLoader() {
	}

	private static Conf config;
	static {
		try {
			config = loadConfig();
		} catch (Exception ex) {
			ex.printStackTrace();
			System.exit(-1);
		}
	}

	public static Conf getConfig() {
		return config;
	}

	private static Conf loadConfig() throws Exception {
		InputStream in = null;
		try {
			in = WebConfLoader.class.getClassLoader().getResourceAsStream(
					"conf.properties");
			Properties p = new Properties();
			p.load(in);
			Conf con = new Conf();
			for (Object k : p.keySet()) {
				String key = (String) k;
				con.setConfig(key, p.getProperty(key));
				System.out.println("Config:    Key:[" + key + "]" + "  Value:["
						+ p.getProperty(key) + "].");
			}
			return con;
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				in.close();
			} catch (Exception ex) {
				ex.printStackTrace();
				System.exit(-1);
			}
		}
	}
}
