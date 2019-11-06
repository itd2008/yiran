package com.yiran.member.request;


import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.yiran.member.base.Request;


/**
 * <p>验证操作员登陆密码请求信息</p>
 */
public class OperatorLoginPwdByIdRequest extends Request {

    /**
     *
     */
    private static final long serialVersionUID = 7556804780863975016L;


    /**
     * 登录密码
     */
    private String            password;

    /**
     * 盐值
     */
    private String            salt;

    /**
     * 登录ID
     * 20140603追加检索值
     */
    private String            operatorId;
    
    /** 错误时是否累计错误次数  默认统计*/
    private boolean			  countWhenWrong = true;

    public OperatorLoginPwdByIdRequest() {

    }

    public OperatorLoginPwdByIdRequest(String operatorId, String password, String salt) {
        this.password = password;
        this.operatorId = operatorId;
        this.salt = salt;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
    
    public boolean isCountWhenWrong() {
		return countWhenWrong;
	}

	public void setCountWhenWrong(boolean countWhenWrong) {
		this.countWhenWrong = countWhenWrong;
	}

	@Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }


}
