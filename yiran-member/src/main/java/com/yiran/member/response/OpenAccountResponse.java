/**
 * 
 */
package com.yiran.member.response;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.yiran.member.base.Response;


/**
 * <p>开户响应信息</p>
 */
public class OpenAccountResponse extends Response {

    /**
     * 
     */
    private static final long serialVersionUID = -2441193039568890096L;

    private String accountId;

    private Date   createTime;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
