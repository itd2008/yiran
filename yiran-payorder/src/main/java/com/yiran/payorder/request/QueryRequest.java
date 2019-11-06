package com.yiran.payorder.request;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.netfinworks.common.util.money.Money;

/**
 * <p>查询请求参数</p>
 */
public class QueryRequest extends ChannelRequest {

    private static final long serialVersionUID = -9170420746508623845L;

    /** 金额   */
    private Money             amount;
    /** 机构订单提交时间 */
    private Date              instOrderSubmitTime;
    /** 查询序列号(一些特殊银行需要使用) */
    private String            querySeqNo;
    /** 查询时间(当前时间) */
    private Date              queryTime;
    /** 原始机构订单号(退款时使用) */
    private String            originalInstOrderNo;
    /** 原始订单银行返回订单号(退款时使用) */
    private String            originalInstSeqNo;
    /** 原始机构订单提交时间(退款时使用) */
    private Date              originalInstOrderSubmitTime;
    /** 原始机构订单结算时间(退款时使用) */
    private Date              originalInstOrderSettleTime;
    /** 原始订单金额(退款时使用) */
    private Money             originalInstOrderAmount;

    public Money getAmount() {
        return amount;
    }

    public void setAmount(Money amount) {
        this.amount = amount;
    }

    public Date getInstOrderSubmitTime() {
        return instOrderSubmitTime;
    }

    public void setInstOrderSubmitTime(Date instOrderSubmitTime) {
        this.instOrderSubmitTime = instOrderSubmitTime;
    }

    public String getQuerySeqNo() {
        return querySeqNo;
    }

    public void setQuerySeqNo(String querySeqNo) {
        this.querySeqNo = querySeqNo;
    }

    public Date getQueryTime() {
        return queryTime;
    }

    public void setQueryTime(Date queryTime) {
        this.queryTime = queryTime;
    }

    public String getOriginalInstOrderNo() {
        return originalInstOrderNo;
    }

    public void setOriginalInstOrderNo(String originalInstOrderNo) {
        this.originalInstOrderNo = originalInstOrderNo;
    }

    public String getOriginalInstSeqNo() {
        return originalInstSeqNo;
    }

    public void setOriginalInstSeqNo(String originalInstSeqNo) {
        this.originalInstSeqNo = originalInstSeqNo;
    }

    public Date getOriginalInstOrderSubmitTime() {
        return originalInstOrderSubmitTime;
    }

    public void setOriginalInstOrderSubmitTime(Date originalInstOrderSubmitTime) {
        this.originalInstOrderSubmitTime = originalInstOrderSubmitTime;
    }

    public Money getOriginalInstOrderAmount() {
        return originalInstOrderAmount;
    }

    public void setOriginalInstOrderAmount(Money originalInstOrderAmount) {
        this.originalInstOrderAmount = originalInstOrderAmount;
    }
    
    public Date getOriginalInstOrderSettleTime() {
        return originalInstOrderSettleTime;
    }

    public void setOriginalInstOrderSettleTime(Date originalInstOrderSettleTime) {
        this.originalInstOrderSettleTime = originalInstOrderSettleTime;
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
