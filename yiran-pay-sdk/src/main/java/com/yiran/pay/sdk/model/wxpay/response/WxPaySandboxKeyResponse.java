package com.yiran.pay.sdk.model.wxpay.response;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "xml", strict = false)
public class WxPaySandboxKeyResponse {

    @Element(name = "return_code")
    private String returnCode;

    @Element(name = "return_msg", required = false)
    private String returnMsg;

    @Element(name = "mch_id", required = false)
    private String mchId;

    @Element(name = "sandbox_signkey", required = false)
    private String sandboxSignkey;

	public String getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}

	public String getReturnMsg() {
		return returnMsg;
	}

	public void setReturnMsg(String returnMsg) {
		this.returnMsg = returnMsg;
	}

	public String getMchId() {
		return mchId;
	}

	public void setMchId(String mchId) {
		this.mchId = mchId;
	}

	public String getSandboxSignkey() {
		return sandboxSignkey;
	}

	public void setSandboxSignkey(String sandboxSignkey) {
		this.sandboxSignkey = sandboxSignkey;
	}
    
    
}
