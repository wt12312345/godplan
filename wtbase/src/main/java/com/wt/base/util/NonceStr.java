package com.wt.base.util;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

/** 随机字符串生成方式 */
public class NonceStr {
	public static String getNonceStr() {
		String strSource = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		int maxPos = strSource.length();
		String noceStr = "";
		for (int i = 0; i < 32; i++) {
			noceStr += strSource.charAt((int) (Math.random() * maxPos));
		}
		return noceStr;
	}

	/** 产生一个随机的字符串 */
	public static String randomString(int length) {
		String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		Random random = new Random();
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int num = random.nextInt(62);
			buf.append(str.charAt(num));
		}
		return buf.toString();
	}

	/** 产生一个随机的字符串，适用于JDK 1.7 */
	public static String random(int length) {
		StringBuilder builder = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			builder.append((char) (ThreadLocalRandom.current().nextInt(33, 128)));
		}
		return builder.toString();
	}

	/**
	 * 获取32位String类型的唯一id
	 * 
	 * @return
	 */
	public static String getUUID() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString().trim().replace("-", "");
	}

	// public static void main(String[] args) {
	// String strSource =
	// "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
	// for (int i = 1; i <= 10; i++) {
	// // System.out.println((int)(Math.random()*62));
	// System.out.println(NonceStr.getNonceStr());
	// }
	// }
}
