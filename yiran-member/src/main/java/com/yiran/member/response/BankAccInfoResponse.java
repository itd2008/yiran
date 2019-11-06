package com.yiran.member.response;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.yiran.member.base.Response;


/**
 * <p>会员银行绑定信息响应结果</p>
 */
public class BankAccInfoResponse extends Response {

    /**
     * 
     */
    private static final long serialVersionUID = 2461936246837067212L;

    private String            bankcardId;                             //绑定银行卡主键Id
    
    public String getBankcardId() {
        return bankcardId;
    }

    public void setBankcardId(String bankcardId) {
        this.bankcardId = bankcardId;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
