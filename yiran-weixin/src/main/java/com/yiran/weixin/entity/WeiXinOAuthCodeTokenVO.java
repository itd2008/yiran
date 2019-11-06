package com.yiran.weixin.entity;

import java.io.Serializable;

/**
 * 通过code换取access_token以及OpenID
 * @author pandaa
 *
 */
public class WeiXinOAuthCodeTokenVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -468854490830025276L;
	/**
	 * 参数                                                                              描述
		
		access_token                网页授权接口调用凭证,注意：此access_token与基础支持的access_token不同
		
		expires_in					access_token接口调用凭证超时时间，单位（秒）
		
		refresh_token				用户刷新access_token
		
		openid						用户唯一标识，请注意，在未关注公众号时，用户访问公众号的网页，也会产生一个用户和公众号唯一的OpenID
		
		scope						用户授权的作用域，使用逗号（,）分隔
		
		{"access_token":"9_7B5izqM9JTM7KNOhZh9rzZOIxHBbHd8Awym2X6lGEy0oF_GgiK43hyPW9YAFhNgAXorcuTzmmhic99HdiKHcBTPbTjiWTfsef0jb9ZKs1jE","expires_in":7200,"refresh_token":"9_9-oQNVUy6v--NQWDac-_T4Te3wKxjxZdh4--3aSEk5i7htATbL1iI6TdeGNNiwUkdi51d5ZAqTFT_Ak-4R2FVsVUITPeqWczmryYM3IeWdw","openid":"orLpEtxDSPI5KFioqiBZLfmj-cas","scope":"snsapi_userinfo","unionid":"ocId11YXSEJXbe5mp-bXJ77lmrM4"}
	 */
	private String access_token;
	private String expires_in;	
	private String refresh_token;
	private String openid;
	private String scope;
	private String unionid;
	
	public String getUnionid() {
		return unionid;
	}
	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}
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
	public String getRefresh_token() {
		return refresh_token;
	}
	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	@Override
	public String toString() {
		return "WeiXinOAuthCodeTokenVO [access_token=" + access_token + ", expires_in=" + expires_in
				+ ", refresh_token=" + refresh_token + ", openid=" + openid + ", scope=" + scope + "]";
	}
	

}
