package com.hq.base.webconf;

import java.util.HashMap;
import java.util.Map;

public class Conf {

	private Map<String, String> config;
	
	private String host;

	private String context;

	private String memcacheLocation;
	public String getConfig(String key) {
		return config.get(key);
	}
	
	public void setConfig(String key, String value) {
		if (config == null) {
			config = new HashMap<String, String>();
		}
		config.put(key, value);
	}


	public String getMemcacheLocation() {
		return memcacheLocation;
	}

	public void setMemcacheLocation(String memcacheLocation) {
		this.memcacheLocation = memcacheLocation;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

}
