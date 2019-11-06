package com.yiran.api.response;

import java.io.Serializable;


public class SendAuthCodeResponse implements Serializable{

    private static final long serialVersionUID = -8810443372112279475L;
    /**
     * 返回结果码
     */
    private String resultCode;
    /**
     * 返回信息
     */
    private String returnMessage;
    /**
     * 扩展字段 json格式
     */
    private String extension;
    
    public String getVerifyCodeId() {
        return verifyCodeId;
    }

    public void setVerifyCodeId(String verifyCodeId) {
        this.verifyCodeId = verifyCodeId;
    }

    private String verifyCodeId;

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getReturnMessage() {
		return returnMessage;
	}

	public void setReturnMessage(String returnMessage) {
		this.returnMessage = returnMessage;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	
}
