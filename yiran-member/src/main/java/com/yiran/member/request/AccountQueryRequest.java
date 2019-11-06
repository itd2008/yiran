/**
 * 
 */
package com.yiran.member.request;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * <p>账户查询条件</p>
 */
public class AccountQueryRequest implements Serializable {
    private static final long serialVersionUID = -2054571602103340635L;

    /**是否需要查询账户信息 */
    private boolean           requireAccountInfos;

    /**要查询的账户类型，当RequireAccounts为true时有效，空代表查询所有账户类型 */
    private List<Long>        accountTypes;

    private String            extention;

    public boolean isRequireAccountInfos() {
        return requireAccountInfos;
    }

    public void setRequireAccountInfos(boolean requireAccountInfos) {
        this.requireAccountInfos = requireAccountInfos;
    }

    public List<Long> getAccountTypes() {
        return accountTypes;
    }

    public void setAccountTypes(List<Long> accountTypes) {
        this.accountTypes = accountTypes;
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
