package com.hq.web.interceptor;

import com.hq.base.util.NonceStr;

public class TokenProsessor {
	/** 随机生成一个token */
	public static String generateToken() {
		return System.currentTimeMillis() + NonceStr.random(8);
	}

	public static void main(String[] args) {
		System.out.println(TokenProsessor.generateToken());
	}
}