package com.yiran.payorder.domain;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.netfinworks.common.util.money.Money;

/**
 * <p>退款订单</p>
 */
public class InstRefundOrder extends PayInstOrder {
    /** 入款机构订单号 */
    private String fundinOrderNo;
    /** 入款机构返回流水号 */
    private String fundinInstSeqNo;
    /** 入款实收金额  */
    private Money  fundinRealAmount = new Money(ZERO_MONEY_STRING);
    /** 入款时间 */
    private String fundinDate;

    public String getFundinOrderNo() {
        return fundinOrderNo;
    }

    public void setFundinOrderNo(String fundinOrderNo) {
        this.fundinOrderNo = fundinOrderNo;
    }

    public String getFundinInstSeqNo() {
        return fundinInstSeqNo;
    }

    public void setFundinInstSeqNo(String fundinInstSeqNo) {
        this.fundinInstSeqNo = fundinInstSeqNo;
    }

    public Money getFundinRealAmount() {
        return fundinRealAmount;
    }

    public void setFundinRealAmount(Money fundinRealAmount) {
        if (fundinRealAmount == null) {
            this.fundinRealAmount = new Money(ZERO_MONEY_STRING);
        } else {
            this.fundinRealAmount = fundinRealAmount;
        }

    }

    public String getFundinDate() {
        return fundinDate;
    }

    public void setFundinDate(String fundinDate) {
        this.fundinDate = fundinDate;
    }
    
    /**
     * 克隆
     */
    public InstRefundOrder clone() throws CloneNotSupportedException {
        return (InstRefundOrder) super.clone();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
