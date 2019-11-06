package com.yiran.member.request;


import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.yiran.member.base.Request;


/**
 * <p>设置登录密码请求参数</p>
 */
public class LoginPwdRequest extends Request {
    private static final long serialVersionUID = 3833879581329241925L;
    
    private String            operatorId;                             //操作员编号
    private String            password;                               //   新的登录密码: SHA-256(密码明文
    private Long			  status = 1L;								// 登陆密码状态 0.初始  1.正常 

    /**
     *默认构造子
     */
    public LoginPwdRequest() {
        super();
    }

    /**
     *默认构造子
     */
    public LoginPwdRequest(String operatorId, String password) {
        super();
        this.operatorId = operatorId;
        this.password = password;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	@Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
