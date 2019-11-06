package com.yiran.message.domain;

import java.io.Serializable;

/**
 * 短信平台发送短息返回信息
 * @author pandaa
 *
 */
public class SendSMSResponse implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8268909437706370368L;

	private String code;
	
	private String msg;
	
	private String status;
	
	private String smsid;
	
	private String bizId;
	
	

	public String getBizId() {
		return bizId;
	}

	public void setBizId(String bizId) {
		this.bizId = bizId;
	}

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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSmsid() {
		return smsid;
	}

	public void setSmsid(String smsid) {
		this.smsid = smsid;
	}
	
	

}
