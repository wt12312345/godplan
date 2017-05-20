package com.wt.wx;

import com.wt.wx.constant.WxReturnCode;

/**
 * this is the exception from the Weixin Server side
 * */
public class WxException extends Exception {
	private static final long serialVersionUID = 0;
	private String _errorCode;
	private String _errorMessage;

	public WxException(String errorCode, String errorMessage) {
		super(errorCode + errorMessage);
		_errorCode = errorCode;
		_errorMessage = errorMessage;
	}

	public WxException(String errorCode) {
		this(errorCode, WxReturnCode.GetErrorDesc(errorCode));
	}

	public String getErrorCode() {
		return _errorCode;
	}

	public String getDescription() {
		return _errorMessage;
	}

}
