package com.wt.web.domain;

/**用于nice valiedator 的json格式
 * ok 表示验证成功的赋值
 * error 表示验证失败时要显示的信息
 * 两个只需要赋值一个*/
public class JsonValidator {

	private String ok;
	private String error;
	public String getOk() {
		return ok;
	}
	public void setOk(String ok) {
		this.ok = ok;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
}
