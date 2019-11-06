package com.yiran.member.domain;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.yiran.member.enums.PassWordStatusEnum;


/**
 * <p>支付密码</p>
 */
public class PayPassWord implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 1486949422609242635L;
    
    /**
     * 会员号
     */
    private String accountId;
    /**
     * 支付密码
     */
    private String passWord;
    
    /**
     * app端支付密码
     */
    private String mpassWord;
    
    /** 支付密码状态 */
    private PassWordStatusEnum	 pwdStatus;
    

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
    
    public PassWordStatusEnum getPwdStatus() {
		return pwdStatus;
	}

	public void setPwdStatus(PassWordStatusEnum pwdStatus) {
		this.pwdStatus = pwdStatus;
	}

	public String getMpassWord() {
		return mpassWord;
	}

	public void setMpassWord(String mpassWord) {
		this.mpassWord = mpassWord;
	}

	@Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
