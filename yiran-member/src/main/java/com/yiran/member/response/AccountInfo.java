/**
 * 
 */
package com.yiran.member.response;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * <p>账户关系信息</p>
 */
public class AccountInfo implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 7508973193526296773L;

    /**
     * 会员编号
     */
    private String            memberId;

    /**
     * 账户类型
     */
    private Long              accountType;

    /**
     * 账户编号
     */
    private String            accountId;

    /**
     * 账户别名
     */
    private String            alias;

    /**
     * 账户属性
     */
    private Long              accountAttribute;

    /**
     * 扩展信息
     */
    private String            extention;

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public Long getAccountType() {
        return accountType;
    }

    public void setAccountType(Long accountType) {
        this.accountType = accountType;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public Long getAccountAttribute() {
        return accountAttribute;
    }

    public void setAccountAttribute(Long accountAttribute) {
        this.accountAttribute = accountAttribute;
    }

    public String getExtention() {
        return extention;
    }

    public void setExtention(String extention) {
        this.extention = extention;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
