package com.yiran.payorder.domain;

import java.util.Date;

import com.netfinworks.common.util.money.Money;
import com.yiran.payorder.request.ChannelFundRequest;

/**
 *  
 * <p>注释</p>
 */
public class RefundRequest extends ChannelFundRequest {
    private static final long serialVersionUID = 8372695725367193904L;

    /** 原始订单金额 */
    protected Money           originalOrderAmount;
    /** 原始银行返回流水号 */
    protected String          orignalInstSeqNo;
    /** 原始机构订单号 */
    protected String          orignalInstOrderNo;
    /** 原始订单提交时间 */
    protected Date            orignalOrderSubmitTime;
    /** 原始订单结算时间 */
    protected Date            orignalOrderSettleTime;

    public Money getOriginalOrderAmount() {
        return originalOrderAmount;
    }

    public void setOriginalOrderAmount(Money originalOrderAmount) {
        this.originalOrderAmount = originalOrderAmount;
    }

    public String getOrignalInstSeqNo() {
        return orignalInstSeqNo;
    }

    public void setOrignalInstSeqNo(String orignalInstSeqNo) {
        this.orignalInstSeqNo = orignalInstSeqNo;
    }

    public String getOrignalInstOrderNo() {
        return orignalInstOrderNo;
    }

    public void setOrignalInstOrderNo(String orignalInstOrderNo) {
        this.orignalInstOrderNo = orignalInstOrderNo;
    }

    public Date getOrignalOrderSubmitTime() {
        return orignalOrderSubmitTime;
    }

    public void setOrignalOrderSubmitTime(Date orignalOrderSubmitTime) {
        this.orignalOrderSubmitTime = orignalOrderSubmitTime;
    }

    public Date getOrignalOrderSettleTime() {
        return orignalOrderSettleTime;
    }

    public void setOrignalOrderSettleTime(Date orignalOrderSettleTime) {
        this.orignalOrderSettleTime = orignalOrderSettleTime;
    }

}
