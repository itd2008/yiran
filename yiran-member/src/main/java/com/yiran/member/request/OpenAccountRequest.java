/**
 * 
 */
package com.yiran.member.request;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.yiran.member.base.Request;

/**
 * <p>开账户请求参数</p>
 */
public class OpenAccountRequest extends Request {

    /**
     * 
     */
    private static final long serialVersionUID = 5215087947705857534L;

    /**
     * 会员编号
     */
    private String            memberId;
    /**
    * 账户类型 101 对私基本户  201 对公基本户  301 特约基本户	
    */
    private Long              accountType;

    /**
     * 账户别名
     */
    private String            alias;

    /**
     * 账户激活状态：1 激活，0 未激活
     */
    private Integer           activateStatus;

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

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public Integer getActivateStatus() {
        return activateStatus;
    }

    public void setActivateStatus(Integer activateStatus) {
        this.activateStatus = activateStatus;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
