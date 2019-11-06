/**
 * 
 */
package com.yiran.member.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.yiran.member.base.Request;

/**
 * <p>查询认证成功的认证信息</p>
 */
public class VerifyBindQueryRequest extends Request {
    private static final long serialVersionUID = -7250636718030174171L;
    private Integer           verifyType;                              //认证实体值
    private String            verifyEntity;                            //认证类型

    public Integer getVerifyType() {
        return verifyType;
    }

    public void setVerifyType(Integer verifyType) {
        this.verifyType = verifyType;
    }

    public String getVerifyEntity() {
        return verifyEntity;
    }

    public void setVerifyEntity(String verifyEntity) {
        this.verifyEntity = verifyEntity;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
