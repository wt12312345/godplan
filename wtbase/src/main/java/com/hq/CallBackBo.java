package com.hq;

public class CallBackBo {
	private boolean result = false;
	private String msg = "";
	private String err = "";
	private String content = "";

	public boolean getResult() {
		return result;
	}

	public String getMsg() {
		return msg;
	}

	public String getContent() {
		return content;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getErr() {
		return err;
	}

	public void setErr(String err) {
		this.err = err;
	}
}