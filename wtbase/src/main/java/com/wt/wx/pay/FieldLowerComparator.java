package com.wt.wx.pay;

import java.lang.reflect.Field;
import java.util.Comparator;

/**反射属性Filed 小写按字典排序比较*/
public class FieldLowerComparator implements Comparator<Field>{

	public int compare(Field o1, Field o2) {
		return o1.getName().toLowerCase().compareTo(o2.getName().toLowerCase());
	} 


}
