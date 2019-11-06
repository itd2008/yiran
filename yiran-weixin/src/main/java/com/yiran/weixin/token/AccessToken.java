package com.yiran.weixin.token;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * token 票据
 * @author pandaa
 *
 */
public class AccessToken implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5527857686135643971L;
	private String access_token;
	private String expires_in;
	
	public String getAccess_token() {
		return access_token;
	}
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
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
            .append("access_token", getAccess_token())
            .append("expires_in", getExpires_in())
            .toString();
    }
	
}
