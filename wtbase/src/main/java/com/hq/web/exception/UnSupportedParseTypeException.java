package com.hq.web.exception;

public class UnSupportedParseTypeException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3013699021067433680L;
	
	public UnSupportedParseTypeException(String type){
		super("ReceiveXMLParser无法解析Type为 "+type+" 的信息或事件XML");
	}

}
