/**
 * 
 */
package com.yiran.member.request;


import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.yiran.member.base.Request;
import com.yiran.member.domain.VerifyInfo;

/**
 * <p>设置认证信息请求参数</p>
 */
public class VerifyInputRequest extends Request {
    private static final long serialVersionUID = -6285142545122395786L;
    private VerifyInfo        verifyInfo;                              //认证信息

    public VerifyInfo getVerifyInfo() {
        return verifyInfo;
    }

    public void setVerifyInfo(VerifyInfo verifyInfo) {
        this.verifyInfo = verifyInfo;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
