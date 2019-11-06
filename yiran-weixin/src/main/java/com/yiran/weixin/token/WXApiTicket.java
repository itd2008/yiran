package com.yiran.weixin.token;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
/**
 * 微信卡券ticket
 * @author pandaa
 *
 */
public class WXApiTicket implements java.io.Serializable {
	private static final long serialVersionUID = 6811728829784621761L;
	private int errcode;
	private String errmsg;
	private String ticket;
	private String expires_in;
 
	public WXApiTicket() {
 
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
