package com.yiran.api.vo;

import java.io.Serializable;

public class ResultReturnVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1060967669267084551L;
	/**
	 * 交易结果代码
	 */
	private String ret_code;
	/**
	 * 交易结果描述
	 */
	private String ret_msg;
	public String getRet_code() {
		return ret_code;
	}
	public void setRet_code(String ret_code) {
		this.ret_code = ret_code;
	}
	public String getRet_msg() {
		return ret_msg;
	}
	public void setRet_msg(String ret_msg) {
		this.ret_msg = ret_msg;
	}
	
	
}
