package com.iwilley.b1ec2.api.internal.mapping;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;

import com.iwilley.b1ec2.api.ApiException;
import com.iwilley.b1ec2.api.B1EC2Response;
import com.iwilley.b1ec2.api.Constants;
import com.iwilley.b1ec2.api.internal.util.StringUtils;



/**
 * 转换工具类。
 * 
 */
public class Converters {

	/**
	 * 是否对JSON返回的数据类型进行校验，默认不校验。给内部测试JSON返回时用的开关。
	 * 规则：返回的"基本"类型只有String,Long,Boolean,Date,采取严格校验方式，如果类型不匹配，报错
	 */
	public static boolean isCheckJsonType = false;

	private static final Set<String> baseFields = new HashSet<String>();
	private static final Map<String, Field> fieldCache = new ConcurrentHashMap<String, Field>();

	static {
		baseFields.add("errorCode");
		baseFields.add("errorMsg");
		baseFields.add("body");
	}

	private Converters() {
	}

	/**
	 * 使用指定 的读取器去转换字符串为对象。
	 * 
	 * @param <T> 领域泛型
	 * @param clazz 领域类型
	 * @param reader 读取器
	 * @return 领域对象
	 * @throws ApiException
	 */
	public static <T> T convert(Class<T> clazz, Reader reader) throws ApiException {
		T rsp = null;

		try {
			rsp = clazz.newInstance();
			BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
			PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();

			for (PropertyDescriptor pd : pds) {
				Method method = pd.getWriteMethod();
				if (method == null) { // ignore read-only fields
					continue;
				}

				String itemName = pd.getName();
				String listName = null;

				Field field;
				if (baseFields.contains(itemName) && B1EC2Response.class.isAssignableFrom(clazz)) {
					field = getField(B1EC2Response.class, pd);
				} else {
					field = getField(clazz, pd);
				}

				ApiField jsonField = field.getAnnotation(ApiField.class);
				if (jsonField != null) {
					itemName = jsonField.value();
				}
				ApiListField jsonListField = field.getAnnotation(ApiListField.class);
				if (jsonListField != null) {
					listName = jsonListField.value();
				}

				if (!reader.hasReturnField(itemName)) {
					if (listName == null || !reader.hasReturnField(listName)) {
						continue; // ignore non-return field
					}
				}

				Class<?> typeClass = field.getType();
				// 目前
				if (String.class.isAssignableFrom(typeClass)) {
					Object value = reader.getPrimitiveObject(itemName);
					if (value instanceof String) {
						method.invoke(rsp, value.toString());
					} else {
						if (isCheckJsonType && value != null) {
							throw new ApiException(itemName + " is not a String");
						}
						if (value != null) {
							method.invoke(rsp, value.toString());
						} else {
							method.invoke(rsp, "");
						}
					}
				} else if (long.class.isAssignableFrom(typeClass) || Long.class.isAssignableFrom(typeClass)) {
					Object value = reader.getPrimitiveObject(itemName);
					if (value instanceof Long) {
						method.invoke(rsp, (Long) value);
					} else {
						if (isCheckJsonType && value != null) {
							throw new ApiException(itemName + " is not a Number(Long)");
						}
						if (StringUtils.isNumeric(value)) {
							method.invoke(rsp, Long.valueOf(value.toString()));
						}
					}
				} else if (int.class.isAssignableFrom(typeClass) || Integer.class.isAssignableFrom(typeClass)) {
					Object value = reader.getPrimitiveObject(itemName);
					if (value instanceof Integer) {
						method.invoke(rsp, (Integer) value);
					} else {
						if (isCheckJsonType && value != null) {
							throw new ApiException(itemName + " is not a Number(Integer)");
						}
						if (StringUtils.isNumeric(value)) {
							method.invoke(rsp, Integer.valueOf(value.toString()));
						}
					}
				} else if (boolean.class.isAssignableFrom(typeClass) || Boolean.class.isAssignableFrom(typeClass)) {
					Object value = reader.getPrimitiveObject(itemName);
					if (value instanceof Boolean) {
						method.invoke(rsp, (Boolean) value);
					} else {
						if (isCheckJsonType && value != null) {
							throw new ApiException(itemName + " is not a Boolean");
						}
						if (value != null) {
							method.invoke(rsp, Boolean.valueOf(value.toString()));
						}
					}
				} else if (double.class.isAssignableFrom(typeClass) || Double.class.isAssignableFrom(typeClass)) {
					Object value = reader.getPrimitiveObject(itemName);
					if (value instanceof Double) {
						method.invoke(rsp, (Double) value);
					} else {
						if (isCheckJsonType && value != null) {
							throw new ApiException(itemName + " is not a Double");
						}
					}
				} else if (Number.class.isAssignableFrom(typeClass)) {
					Object value = reader.getPrimitiveObject(itemName);
					if (value instanceof Number) {
						method.invoke(rsp, (Number) value);
					} else {
						if (isCheckJsonType && value != null) {
							throw new ApiException(itemName + " is not a Number");
						}
					}
				} else if (Date.class.isAssignableFrom(typeClass)) {
					Object value = reader.getPrimitiveObject(itemName);
					if (value instanceof String) {
						Date dateValue;
						try{
							DateFormat format = new SimpleDateFormat(Constants.DATE_TIME_FORMAT);
							format.setTimeZone(TimeZone.getTimeZone(Constants.DATE_TIMEZONE));
							dateValue = format.parse(value.toString());
						}catch(ParseException e){
							DateFormat format = new SimpleDateFormat(Constants.DATE_TIME_FORMAT2);
							format.setTimeZone(TimeZone.getTimeZone(Constants.DATE_TIMEZONE));
							dateValue = format.parse(value.toString());
						}
						method.invoke(rsp, dateValue);
					}
				} else if (List.class.isAssignableFrom(typeClass)) {
					Type fieldType = field.getGenericType();
					if (fieldType instanceof ParameterizedType) {
						ParameterizedType paramType = (ParameterizedType) fieldType;
						Type[] genericTypes = paramType.getActualTypeArguments();
						if (genericTypes != null && genericTypes.length > 0) {
							if (genericTypes[0] instanceof Class<?>) {
								Class<?> subType = (Class<?>) genericTypes[0];
								List<?> listObjs = reader.getListObjects(listName, itemName, subType);
								if (listObjs != null) {
									method.invoke(rsp, listObjs);
								}
							}
						}
					}
				} else {
					Object obj = reader.getObject(itemName, typeClass);
					if (obj != null) {
						method.invoke(rsp, obj);
					}
				}
			}
		} catch (Exception e) {
			throw new ApiException(e);
		}

		return rsp;
	}

	private static Field getField(Class<?> clazz, PropertyDescriptor pd) throws Exception {
		String key = clazz.getName() + "_" + pd.getName();
		Field field = fieldCache.get(key);
		if (field == null) {// 这个方法不加锁，初始化并发也没关系，无非多put几次
			field = clazz.getDeclaredField(pd.getName());
			fieldCache.put(key, field);
		}
		return field;
	}
}