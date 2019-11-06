package com.yiran.api.vo;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.yiran.paychannel.enums.CardType;


public class BankCardInfo implements Serializable {

    private static final long serialVersionUID = -961252575683018753L;
    /** 卡类型 */
    private CardType          cardType;
    /** 卡号    */
    private String            cardNo;
    /** 名称    */
    private String            name;
    /** CVV2 */
    private String            cvv2;
    /** 有效期  */
    private String            validDate;

    public CardType getCardType() {
        return this.cardType;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }

    public String getCardNo() {
        return this.cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCvv2() {
        return this.cvv2;
    }

    public void setCvv2(String cvv2) {
        this.cvv2 = cvv2;
    }

    public String getValidDate() {
        return this.validDate;
    }

    public void setValidDate(String validDate) {
        this.validDate = validDate;
    }
    
    @Override
    public String toString(){
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
