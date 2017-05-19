package com.wt.base.util;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

public class TypeUtil {
	public static double doublePlus(double... arrDbl) {
		double result = 0;
		for (int i = 0; i < arrDbl.length; i++) {
			java.math.BigDecimal d1 = new java.math.BigDecimal(
					String.valueOf(result));
			java.math.BigDecimal d2 = new java.math.BigDecimal(
					String.valueOf(arrDbl[i]));
			result = d1.add(d2).doubleValue();
		}
		return result;
	}

	public static int toInt(Object obj) {
		try {
			return Integer.parseInt(obj.toString().trim());
		} catch (Exception a) {
			return 0;
		}
	}

	public static long toLong(Object obj) {
		try {
			return Long.parseLong(obj.toString());
		} catch (Exception a) {
			return 0;
		}
	}

	public static Double toDouble(Object obj) {
		try {
			return Double.parseDouble(obj.toString());
		} catch (Exception a) {
			return 0.0;
		}
	}

	public static Float toFloat(Object obj) {
		try {
			return Float.parseFloat(obj.toString());
		} catch (Exception a) {
			return (float) 0;
		}
	}

	public static String toString(Object obj) {
		if (obj == null) {
			return "";
		} else {
			return obj.toString().trim();
		}
	}

	/**
	 * 判断是否为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		return str == null || str.length() == 0;
	}

	/**
	 * 相乘进位，2位小数
	 * 
	 * @param arg
	 * @param scale
	 * @return
	 */
	public static double getDouble(int arg, double arg2) {
		BigDecimal d = new BigDecimal(arg); // 存款
		BigDecimal r = new BigDecimal(arg2); // 利息
		BigDecimal i = d.multiply(r).setScale(2, RoundingMode.UP);
		return i.doubleValue();
	}

	public static double getDouble(double arg, double arg2, int scale) {
		BigDecimal d = new BigDecimal(arg);
		BigDecimal r = new BigDecimal(arg2);
		BigDecimal i = d.multiply(r).setScale(scale, RoundingMode.UP);
		return i.doubleValue();
	}

	/**
	 * 进位（分）：19800 * 0.119 = 2357
	 * 
	 * @param arg
	 * @param scale
	 * @return
	 */
	public static int getInt(int arg, double scale) {
		BigDecimal d = new BigDecimal(arg);
		BigDecimal r = new BigDecimal(scale);
		BigDecimal i = d.multiply(r).setScale(0, RoundingMode.UP);
		return i.intValue();
	}

	/**
	 * 12412 - 124.12 | int/long - String
	 * 
	 * @param obj
	 * @return
	 */
	public static String toCoinFormat(Object obj) {
		try {
			while (obj.toString().length() < 4) {
				obj = "0" + obj;
			}
			String objStr = obj.toString();
			int length = objStr.length();
			String yuan = objStr.substring(0, length - 2);
			if (yuan.equals("00")) {
				yuan = "0";
			} else if (yuan.length() > 1 && yuan.substring(0, 1).equals("0")) {
				yuan = yuan.substring(1, yuan.length());
			}
			String fen = objStr.substring(length - 2, length);
			if (fen.equals("00")) {
				fen = "";
			} else if (fen.length() == 2 && fen.substring(1, 2).equals("0")) {
				fen = "." + fen.substring(0, 1);
			} else {
				fen = "." + fen;
			}
			return yuan + fen;
		} catch (Exception a) {
			return "0";
		}
	}

	public static double toCoinFormatDbl(Object obj) {
		try {
			while (obj.toString().length() < 4) {
				obj = "0" + obj;
			}
			String objStr = obj.toString();
			int length = objStr.length();
			String yuan = objStr.substring(0, length - 2);
			if (yuan.equals("00")) {
				yuan = "0";
			} else if (yuan.length() > 1 && yuan.substring(0, 1).equals("0")) {
				yuan = yuan.substring(1, yuan.length());
			}
			String fen = objStr.substring(length - 2, length);
			if (fen.equals("00")) {
				fen = "";
			} else if (fen.length() == 2 && fen.substring(1, 2).equals("0")) {
				fen = "." + fen.substring(0, 1);
			} else {
				fen = "." + fen;
			}
			return toDouble(yuan + fen);
		} catch (Exception a) {
			return 0;
		}
	}

	/**
	 * 还原到分。124.12 - 12412 | String - int
	 * 
	 * @param obj
	 * @return
	 */
	public static int toPriceFormat(Object obj) {
		try {
			String objStr = toString(obj);
			if (objStr.isEmpty()) {
				return 0;
			}
			String[] arrObjStr = objStr.split("\\.");
			String strPrice = "";
			if (arrObjStr.length == 0) {
				strPrice = obj + "00";
			} else if (arrObjStr.length == 1) {
				strPrice = arrObjStr[0] + "00";
			} else if (arrObjStr.length == 2) {
				String temp = arrObjStr[1];
				while (temp.length() < 2) {
					temp += "0";
				}
				temp = temp.substring(0, 2);
				strPrice = arrObjStr[0] + temp;
			}
			return toInt(strPrice);
		} catch (Exception a) {
			return 0;
		}
	}

	/**
	 * 金额：100.5 - 101，四舍五入
	 * 
	 * @param obj
	 * @return
	 */
	public static int priceCarry(double price) {
		int priceInt = (int) price;
		if (price - priceInt >= 0.5) {
			return priceInt + 1;
		}
		return priceInt;
	}

	public static Map convertObjToMap(Object obj) {
		Map<String, Object> reMap = new HashMap<String, Object>();
		if (obj == null)
			return null;
		Field[] fields = obj.getClass().getDeclaredFields();
		try {
			for (int i = 0; i < fields.length; i++) {
				try {
					Field f = obj.getClass().getDeclaredField(
							fields[i].getName());
					f.setAccessible(true);
					Object o = f.get(obj);
					reMap.put(fields[i].getName(), o);
				} catch (NoSuchFieldException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return reMap;
	}

}
