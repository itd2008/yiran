package com.yiran.payorder.domain;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.yiran.paychannel.enums.InstOrderStatus;

/**
 * 
 * <p>counter</p>
 */
public class QueryOrderResult {

    /** 查询状态 */
    private InstOrderStatus status;

    /** 备注 */
    private String          messag;

    public QueryOrderResult() {

    }

    public QueryOrderResult(InstOrderStatus status, String message) {
        this.status = status;
        this.messag = message;
    }

    public InstOrderStatus getStatus() {
        return status;
    }

    public void setStatus(InstOrderStatus status) {
        this.status = status;
    }

    public String getMessag() {
        return messag;
    }

    public void setMessag(String messag) {
        this.messag = messag;
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
