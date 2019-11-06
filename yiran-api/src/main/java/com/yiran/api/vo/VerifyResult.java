package com.yiran.api.vo;
/**
 * 验证结果码
 * @author pandaa
 *
 */
public class VerifyResult {

	/**
	 * 错误编码
	 */
	private String code;
	/**
	 * 错误信息
	 */
	private String msg;
	/**
	 * 是否成功
	 */
	private boolean success;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	
}
