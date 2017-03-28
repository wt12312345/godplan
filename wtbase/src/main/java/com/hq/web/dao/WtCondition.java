package com.hq.web.dao;

import java.util.ArrayList;
import java.util.List;

public class WtCondition {
	/**
	 * 等于
	 */
	public static String EQ = "eq";
	/**
	 * 不等于
	 */
	public static String NE = "ne";
	/**
	 * 大于
	 */
	public static String GT = "gt";
	/**
	 * 大于等于
	 */
	public static String GE = "ge";
	/**
	 * 小于
	 */
	public static String LT = "lt";
	/**
	 * 小于等于
	 */
	public static String LE = "le";
	/**
	 * 字符串匹配
	 */
	public static String LIKE = "like";
	/**
	 * 降序
	 */
	public static String DESC = "desc";
	/**
	 * 升序
	 */
	public static String ASC = "asc";
	/**
	 * 等于列表中的值
	 */
	public static String IN = "in";
	/**
	 * 等于列表中的值
	 */
	public static String NOTIN = "notin";
	/**
	 * 两者之间
	 */
	public static String BETWEEN = "between";
	/**
	 * 两者之间
	 */
	public static String OR = "or";

	/**
	 * 用于设置一般查询条件
	 * 
	 * @param mode
	 *            eq、ne、lt、like
	 * @param name
	 *            字段名
	 * @param value
	 *            用于eq、ne、lt、like的值
	 */
	public WtCondition(String mode, String name, Object value) {
		conditionMode = mode;
		fieldName = name;
		fieldValue = value;
	}

	/**
	 * 用于设置一般查询条件
	 * 
	 * @param mode
	 *            eq、ne、lt、like、in
	 * @param name
	 *            字段名
	 * @param value
	 *            用于eq、ne、lt、like的值
	 * @param values
	 *            用于in的值
	 */
	public WtCondition(String mode, String name, Object value, List<Object> values) {
		conditionMode = mode;
		fieldName = name;
		fieldValue = value;
		fieldValues = values;
	}

	/**
	 * 专用于IN查询
	 * 
	 * @param name
	 * @param values
	 */
	public WtCondition(String name, List<Object> values) {
		conditionMode = WtCondition.IN;
		fieldName = name;
		fieldValues = values;
	}

	public WtCondition(String name, List<Object> values, boolean isIn) {
		if (!isIn) {
			conditionMode = WtCondition.NOTIN;
		} else {
			conditionMode = WtCondition.IN;
		}
		fieldName = name;
		fieldValues = values;
	}

	/**
	 * 专用于between
	 * 
	 * @param name
	 * @param valueStart
	 * @param valueOver
	 */
	public WtCondition(String name, Object valueStart, Object valueOver) {
		conditionMode = WtCondition.BETWEEN;
		fieldName = name;
		this.valueStart = valueStart;
		this.valueOver = valueOver;
	}

	public WtCondition(WtCondition... arrCdt) {
		conditionMode = WtCondition.OR;
		for (int i = 0; i < arrCdt.length; i++) {
			this.listForOr.add(arrCdt[i]);
		}
	}

	/**
	 * 一般用于设置排序
	 * 
	 * @param mode
	 *            desc、asc
	 * @param name
	 */
	public WtCondition(String mode, String name) {
		conditionMode = mode;
		fieldName = name;
	}

	public String conditionMode;
	/**
	 * 字段名
	 */
	public String fieldName;
	/**
	 * 要设置条件的字段的对应值
	 */
	public Object fieldValue;
	public Object valueStart;
	public Object valueOver;
	/**
	 * 要设置条件的字段的对应值集合，针对Restrictions.in等
	 */
	public List<Object> fieldValues;
	/**
	 * 针对OR
	 */
	public List<WtCondition> listForOr = new ArrayList<WtCondition>();
}
