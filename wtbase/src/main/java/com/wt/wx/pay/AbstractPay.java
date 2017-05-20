package com.wt.wx.pay;

/**定义一些公用的方法*/
public class AbstractPay {
	/**根据属性获取get方法*/
	protected String getGetMethod(String fieldName){
		if(fieldName.length()==1){
			return "get"+fieldName.toUpperCase();
		}else{
			return "get"+fieldName.substring(0, 1).toUpperCase()+fieldName.substring(1);
		}
	}
}
