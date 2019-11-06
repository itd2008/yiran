package com.yiran.weixin.token;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * jsapi_ticket是公众号用于调用微信JS接口的临时票据。正常情况下，jsapi_ticket的有效期为7200秒，通过access_token来获取。
 * @author pandaa
 *
 */
public class JsApiTicket implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6811728829784621761L;
	private int errcode;
	private String errmsg;
	private String ticket;
	private String expires_in;
 
	public JsApiTicket() {
 
	}
 
	public String getTicket() {
		return ticket;
	}
 
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public int getErrcode() {
		return errcode;
	}

	public void setErrcode(int errcode) {
		this.errcode = errcode;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

	public String getExpires_in() {
		return expires_in;
	}

	public void setExpires_in(String expires_in) {
		this.expires_in = expires_in;
	}
 
	@Override
	public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("errcode", getErrcode())
            .append("errmsg", getErrmsg())
            .append("ticket", getTicket())
            .append("expires_in", getExpires_in())
            .toString();
    }
 
}
