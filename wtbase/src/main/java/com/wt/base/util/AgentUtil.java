package com.wt.base.util;

import javax.servlet.http.HttpServletRequest;

public class AgentUtil {
	public static boolean isWechat(HttpServletRequest request) {
		String userAgent = request.getHeader("user-agent");
		return isWechat(userAgent);

	}

	public static boolean isWechat(String userAgent) {
		if (userAgent.toLowerCase().contains("micromessenger")) {
			return true;
		}
		return false;

	}

	public static boolean isMobile(HttpServletRequest request) {
		// String userAgent = request.getHeader("user-agent");
		// if (userAgent.contains("MicroMessenger")) {
		// return true;
		// }
		return false;
	}

	public static boolean isMobile(String userAgent) {
		// if (userAgent.contains("MicroMessenger")) {
		// return true;
		// }
		return false;
	}

	public static boolean isPad(HttpServletRequest request) {
		// String userAgent = request.getHeader("user-agent");
		// if (userAgent.contains("MicroMessenger")) {
		// return true;
		// }
		return false;
	}

	public static boolean isPad(String userAgent) {
		// if (userAgent.contains("MicroMessenger")) {
		// return true;
		// }
		return false;
	}

}
