package com.yiran.member.response;

import java.util.List;


import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.yiran.member.base.Response;


/**
 * <p>会员账户关系响应结果 </p>
 */
public class AccRelationResponse extends Response {

    /**
     * 
     */
    private static final long serialVersionUID = 6774143351659928666L;

    /**
     * 会员账户关系列表
     */
    private List<AccountInfo> accountRelations;

    public List<AccountInfo> getAccountRelations() {
        return accountRelations;
    }

    public void setAccountRelations(List<AccountInfo> accountRelations) {
        this.accountRelations = accountRelations;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
