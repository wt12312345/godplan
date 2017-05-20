package com.wt.base.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class TimeUtil {
	private static String addZero(Object obj) {
		if (obj == null) {
			return "00";
		} else if (obj.toString().length() == 1) {
			return 0 + obj.toString();
		} else {
			return obj.toString();
		}
	}

	/**
	 * 获取当前日期字符串：20130121(补0)
	 * 
	 * @return 20130121(补0)
	 */
	public static String getDateNowStr() {
		Calendar ca = Calendar.getInstance();
		int year = ca.get(Calendar.YEAR);// 获取年份
		int month = ca.get(Calendar.MONTH) + 1;// 获取月份
		int day = ca.get(Calendar.DATE);// 获取日
		String dateNow = "" + year + addZero(month) + addZero(day);
		return dateNow;
	}

	/**
	 * 获取当前日期字符串：2013121(不补0)
	 * 
	 * @return 2013121(不补0)
	 */
	public static String getDateNowStr2() {
		Calendar ca = Calendar.getInstance();
		int year = ca.get(Calendar.YEAR);// 获取年份
		int month = ca.get(Calendar.MONTH) + 1;// 获取月份
		int day = ca.get(Calendar.DATE);// 获取日
		String dateNow = "" + year + month + day;
		return dateNow;
	}

	/**
	 * 获取当前日期字符串：2013-01-21(补0)
	 * 
	 * @return 2013-01-21(补0)
	 */
	public static String getDateNowStr3() {
		Calendar ca = Calendar.getInstance();
		int year = ca.get(Calendar.YEAR);// 获取年份
		int month = ca.get(Calendar.MONTH) + 1;// 获取月份
		int day = ca.get(Calendar.DATE);// 获取日
		String dateNow = year + "-" + addZero(month) + "-" + addZero(day);
		return dateNow;
	}

	/**
	 * 获取当前日期字符串：2013-1-21(不补0)
	 * 
	 * @return 2013-1-21(不补0)
	 */
	public static String getDateNowStr4() {
		Calendar ca = Calendar.getInstance();
		int year = ca.get(Calendar.YEAR);// 获取年份
		int month = ca.get(Calendar.MONTH) + 1;// 获取月份
		int day = ca.get(Calendar.DATE);// 获取日
		String dateNow = year + "-" + month + "-" + day;
		return dateNow;
	}

	/**
	 * 获取当前时间字符串：20130121023126(补0)
	 * 
	 * @return 20130121023126(补0)
	 */
	public static String getDateTimeNowStr() {
		Calendar ca = Calendar.getInstance();
		int year = ca.get(Calendar.YEAR);// 获取年份
		int month = ca.get(Calendar.MONTH) + 1;// 获取月份
		int day = ca.get(Calendar.DATE);// 获取日
		int minute = ca.get(Calendar.MINUTE);// 分
		int hour = ca.get(Calendar.HOUR_OF_DAY);// 小时
		int second = ca.get(Calendar.SECOND);// 秒
		String dateNow = "" + year + addZero(month) + addZero(day)
				+ addZero(hour) + addZero(minute) + addZero(second);
		return dateNow;
	}

	/**
	 * 获取当前时间字符串： 201312123126(不补0)
	 * 
	 * @return 201312123126(不补0)
	 */
	public static String getDateTimeNowStr2() {
		Calendar ca = Calendar.getInstance();
		int year = ca.get(Calendar.YEAR);// 获取年份
		int month = ca.get(Calendar.MONTH) + 1;// 获取月份
		int day = ca.get(Calendar.DATE);// 获取日
		int minute = ca.get(Calendar.MINUTE);// 分
		int hour = ca.get(Calendar.HOUR_OF_DAY);// 小时
		int second = ca.get(Calendar.SECOND);// 秒
		String dateNow = "" + year + month + day + hour + minute + second;
		return dateNow;
	}

	/**
	 * 获取当前时间字符串：2013-01-21 02:31:26(补0)
	 * 
	 * @return 2013-01-21 02:31:26(补0)
	 */
	public static String getDateTimeNowStr3() {
		Calendar ca = Calendar.getInstance();
		int year = ca.get(Calendar.YEAR);// 获取年份
		int month = ca.get(Calendar.MONTH) + 1;// 获取月份
		int day = ca.get(Calendar.DATE);// 获取日
		int minute = ca.get(Calendar.MINUTE);// 分
		int hour = ca.get(Calendar.HOUR_OF_DAY);// 小时
		int second = ca.get(Calendar.SECOND);// 秒
		String dateNow = year + "-" + addZero(month) + "-" + addZero(day) + " "
				+ addZero(hour) + ":" + addZero(minute) + ":" + addZero(second);
		return dateNow;
	}

	/**
	 * 获取当前时间字符串：2013-1-21 2:31:26(不补0)
	 * 
	 * @return 2013-1-21 2:31:26(不补0)
	 */
	public static String getDateTimeNowStr4() {
		Calendar ca = Calendar.getInstance();
		int year = ca.get(Calendar.YEAR);// 获取年份
		int month = ca.get(Calendar.MONTH) + 1;// 获取月份
		int day = ca.get(Calendar.DATE);// 获取日
		int minute = ca.get(Calendar.MINUTE);// 分
		int hour = ca.get(Calendar.HOUR_OF_DAY);// 小时
		int second = ca.get(Calendar.SECOND);// 秒
		String dateNow = year + "-" + month + "-" + day + " " + hour + ":"
				+ minute + ":" + second;
		return dateNow;
	}

	/**
	 * 获取当前时分字符串：0231(补0)
	 * 
	 * @return 0231(补0)
	 */
	public static String getTimeOnlyStr() {
		Calendar ca = Calendar.getInstance();
		int minute = ca.get(Calendar.MINUTE);// 分
		int hour = ca.get(Calendar.HOUR_OF_DAY);// 小时
		String dateNow = addZero(hour) + "" + addZero(minute);
		return dateNow;
	}

	/**
	 * 获取当前时分字符串：231(不补0)
	 * 
	 * @return 231(不补0)
	 */
	public static String getTimeOnlyStr2() {
		Calendar ca = Calendar.getInstance();
		int minute = ca.get(Calendar.MINUTE);// 分
		int hour = ca.get(Calendar.HOUR_OF_DAY);// 小时
		String dateNow = hour + "" + minute;
		return dateNow;
	}

	/**
	 * 获取当前时分字符串：02:31(补0)
	 * 
	 * @return 02:31(补0)
	 */
	public static String getTimeOnlyStr3() {
		Calendar ca = Calendar.getInstance();
		int minute = ca.get(Calendar.MINUTE);// 分
		int hour = ca.get(Calendar.HOUR_OF_DAY);// 小时
		String dateNow = addZero(hour) + ":" + addZero(minute);
		return dateNow;
	}

	/**
	 * 获取当前时分字符串：02:31(不补0)
	 * 
	 * @return 02:31(不补0)
	 */
	public static String getTimeOnlyStr4() {
		Calendar ca = Calendar.getInstance();
		int minute = ca.get(Calendar.MINUTE);// 分
		int hour = ca.get(Calendar.HOUR_OF_DAY);// 小时
		String dateNow = hour + ":" + minute;
		return dateNow;
	}

	/**
	 * 把日期往后增加一天.整数往后推,负数往前移动
	 * 
	 * @param index
	 * @return
	 */
	public static Date getDate(int index) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(new Date());
		calendar.add(Calendar.DATE, index);// 把日期往后增加一天.整数往后推,负数往前移动
		return calendar.getTime();
	}

	/**
	 * 获取日期：把时、分、秒变0
	 * 
	 * @param date
	 * @return
	 */
	public static Date getDate(Date date) {
		if (date == null) {
			date = new Date();
		}
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 1);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 把日期往后增加一天.整数往后推,负数往前移动
	 * 
	 * @param date
	 * @param index
	 * @return
	 */
	public static Date getDate(Date date, int index) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, index);// 把日期往后增加一天.整数往后推,负数往前移动
		return calendar.getTime();
	}

	public static Date getTime(int hour, int minute, int second) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(new Date());
		calendar.add(Calendar.HOUR_OF_DAY, hour);
		calendar.add(Calendar.MINUTE, minute);
		calendar.add(Calendar.SECOND, second);
		return calendar.getTime();
	}

	public static Date getTime(Date time, int hour, int minute, int second) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(time);
		calendar.add(Calendar.HOUR_OF_DAY, hour);
		calendar.add(Calendar.MINUTE, minute);
		calendar.add(Calendar.SECOND, second);
		return calendar.getTime();
	}

	public static Date getTime(int dayIndex, int hour, int minute, int second) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(new Date());
		calendar.add(Calendar.HOUR_OF_DAY, hour);
		calendar.add(Calendar.MINUTE, minute);
		calendar.add(Calendar.SECOND, second);
		calendar.add(Calendar.DATE, dayIndex);
		return calendar.getTime();
	}

	/**
	 * 字符串日期到时间 yyyy-MM-dd - Date
	 * 
	 * @param str
	 * @return
	 * @throws ParseException
	 */
	public static Date getDateByStr(String str) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = sdf.parse(str);
			return date;
		} catch (ParseException e) {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
				Date date = sdf.parse(str);
				return date;
			} catch (ParseException e2) {
				return null;
			}
		}
	}
	
	public static Date getDateMonthByStr(String str) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
			Date date = sdf.parse(str);
			return date;
		} catch (ParseException e) {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM");
				Date date = sdf.parse(str);
				return date;
			} catch (ParseException e2) {
				return null;
			}
		}
	}

	/**
	 * yyyy-MM-dd HH:mm
	 * 
	 * @param str
	 * @return
	 * @throws ParseException
	 */
	public static Date getTimeByStr(String str) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = sdf.parse(str);
			return date;
		} catch (Exception e) {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				Date date = sdf.parse(str);
				return date;
			} catch (Exception e2) {
				return null;
			}
		}
	}

	/**
	 * yyyy-MM-dd
	 * 
	 * @param date
	 * @return
	 */
	public static String getDateStr(Date date) {
		if (date == null) {
			return "";
		}
		try {
			SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
			String result = df1.format(date);
			return result;
		} catch (Exception e) {
			return "";
		}
	}
	
	/**
	 * yyyy-MM
	 * 
	
	 * @param date
	 * @return
	 */
	public static String getDateMonthStr(Date date) {
		if (date == null) {
			return "";
		}
		try {
			SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM");
			String result = df1.format(date);
			return result;
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * yyyy-MM-dd HH:mm:ss
	 * 
	 * @param date
	 * @return
	 */
	public static String getTimeStr(Date date) {
		if (date == null) {
			return "";
		}
		try {
			SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String result = df1.format(date);
			return result;
		} catch (Exception e) {
			return "";
		}
	}

	public static int getIntervalDays(Date startdate, Date enddate)
			throws ParseException {
		long m_intervalday = enddate.getTime() - startdate.getTime();// 计算所得为微秒数
		m_intervalday = m_intervalday / 1000 / 60 / 60 / 24;// 计算所得的天数
		return (int) m_intervalday;
	}

	/**
	 * 计算两个时间 小时差
	 * 
	 * @param startdate
	 * @param enddate
	 * @return
	 * @throws ParseException
	 */
	public static int getIntervalHours(Date startdate, Date enddate)
			throws ParseException {
		long m_intervalday = enddate.getTime() - startdate.getTime();// 计算所得为微秒数
		m_intervalday = m_intervalday / 1000 / 60 / 60;// 计算所得的天数
		return (int) m_intervalday;
	}

	/**
	 * 计算两个时间 秒差
	 * 
	 * @param startdate
	 * @param enddate
	 * @return
	 * @throws ParseException
	 */
	public static int getIntervalSecond(Date startdate, Date enddate)
			throws ParseException {
		long m_intervalday = enddate.getTime() - startdate.getTime();// 计算所得为毫秒数
		m_intervalday = m_intervalday / 1000;// 计算所得的毫秒数
		return (int) m_intervalday;
	}

	/**
	 * 获取时间差：含天、小时、分钟
	 * 
	 * @param format
	 *            1 - 1天23小时32分钟；2 - 1天23时32分；3 - 1.23.32
	 * @return
	 */
	public static String getIntervalInMinute(Date startDate, Date endDate,
			int format) {
		long m_intervalday = endDate.getTime() - startDate.getTime();// 计算所得为微秒数
		int day = (int) m_intervalday / 1000 / 60 / 60 / 24;// 计算所得的天数
		int hour = (int) m_intervalday / 1000 / 60 / 60 - day * 24;
		int minute = (int) m_intervalday / 1000 / 60 - day * 24 * 60 - hour
				* 60;
		String result = "";
		switch (format) {
		case 1:
			result = day + "天" + hour + "小时" + minute + "分钟";
			break;
		case 2:
			result = day + "天" + hour + "时" + minute + "分";
			break;
		case 3:
			result = day + "." + hour + "." + minute;
			break;
		default:
			result = day + "天" + hour + "小时" + minute + "分钟";
			break;
		}
		return result;
	}

	/**
	 * 获取时间差：含小时、分钟、秒
	 * 
	 * @param startDate
	 * @param endDate
	 * @param format
	 *            1 - 23小时32分钟；2 - 23小时32分钟3秒；3 - 3小时33分；4 - 3小时3分1秒；5 - 1:32；6
	 *            - 3:3:22；7 - 01:32；8 - 01:32:09
	 * @return
	 */
	public static String getIntervalInSecond(Date startDate, Date endDate,
			int format) {
		long m_intervalday = endDate.getTime() - startDate.getTime();// 计算所得为微秒数
		int hour = (int) m_intervalday / 1000 / 60 / 60;
		int minute = (int) m_intervalday / 1000 / 60 - hour * 60;
		int second = (int) m_intervalday / 1000 - hour * 3600 - minute * 60;
		String result = "";
		switch (format) {
		case 1:
			result = hour + "小时" + minute + "分钟";
			break;
		case 2:
			result = hour + "小时" + minute + "分钟" + second + "秒";
			break;
		case 3:
			result = hour + "时" + minute + "分";
			break;
		case 4:
			result = hour + "时" + minute + "分" + second + "秒";
			break;
		case 5:
			result = hour + ":" + minute;
			break;
		case 6:
			result = hour + ":" + minute + ":" + second;
			break;
		case 7:
			result = addZero(hour) + ":" + addZero(minute);
			break;
		case 8:
			result = addZero(hour) + ":" + addZero(minute) + ":"
					+ addZero(second);
			break;
		default:
			result = addZero(hour) + ":" + addZero(minute) + ":"
					+ addZero(second);
			break;
		}
		return result;
	}
}
