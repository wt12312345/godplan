package com.wt.wx;

@SuppressWarnings("serial")
public class ParserException extends Exception {
	public static final String CODE_UNSUPPORTED_PARSE_TYPE="001";
	
	private Exception sourceException;
	private String code;

	public ParserException() {
	}

	public ParserException(String code) {

		this.code = code;
	}

	public ParserException(Exception ex, String code) {
		this.sourceException = ex;
		this.code=code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Exception getException() {
		return sourceException;
	}
}
