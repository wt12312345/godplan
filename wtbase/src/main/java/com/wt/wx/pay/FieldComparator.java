package com.wt.wx.pay;

import java.lang.reflect.Field;
import java.util.Comparator;

/**反射属性Filed 按字典排序比较*/
public class FieldComparator implements Comparator<Field>{

	public int compare(Field o1, Field o2) {
		return o1.getName().compareTo(o2.getName());
	}
 

}
