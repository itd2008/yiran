package com.yiran.payorder.domain;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.yiran.paychannel.enums.CardType;

/**
 * <p>入款订单</p>
 */
public class InstFundinOrder extends PayInstOrder {
    /** 卡类型 */
    private CardType cardType;
    /** 付款机构 */
    private String   payerInstCode;
    /** 协议号 */
    private String   contractNo;

    public CardType getCardType() {
        return cardType;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }

    public void setCardType(String cardTypeStr) {
        this.cardType = CardType.getByCode(cardTypeStr);
    }

    public String getPayerInstCode() {
        return payerInstCode;
    }

    public void setPayerInstCode(String payerInstCode) {
        this.payerInstCode = payerInstCode;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    /**
     * 克隆
     */
    public InstFundinOrder clone() throws CloneNotSupportedException {
        return (InstFundinOrder) super.clone();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
