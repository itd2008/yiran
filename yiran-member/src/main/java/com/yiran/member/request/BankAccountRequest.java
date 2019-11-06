package com.yiran.member.request;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.yiran.member.base.Request;

/**
 * <p>查询会员银行绑定请求参数</p>
 */
public class BankAccountRequest extends Request {

    /**
     * 
     */
    private static final long serialVersionUID = -369276445033529893L;

    private String            memberId;                               //会员ID
    private Integer           cardType;                               //卡类型(1借记卡 2信用卡,3存折,4其它) 
    private Integer           cardAttribute;                          //卡属性(0对公 1对私)
    private Integer           isVerified;                             //认证状态(0未认证 1已认证)
    private Integer           status;                                 //卡状态(0失效  1正常 2锁定 3未激活)
    private String            isSigning;                              //是否签约（Y是 N否）
    private String            payAttribute;                           //qpay：快捷支付，bsqpay：小快捷，umppay：联动优势，normal：(普通卡)无验证卡,certpay:打卡认证，trust_collect：大额代扣，name_cardnum_cert：两要素验证卡(姓名和卡号)
    private String            bankAccountNum;                         //卡号

    private String 			  financialCard;                          //是否是理财卡 Y是 否则不是

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }
    
    

    public String getFinancialCard() {
		return financialCard;
	}

	public void setFinancialCard(String financialCard) {
		this.financialCard = financialCard;
	}

	public Integer getCardType() {
        return cardType;
    }

    public void setCardType(Integer cardType) {
        this.cardType = cardType;
    }

    public Integer getCardAttribute() {
        return cardAttribute;
    }

    public void setCardAttribute(Integer cardAttribute) {
        this.cardAttribute = cardAttribute;
    }

    public Integer getIsVerified() {
        return isVerified;
    }

    public void setIsVerified(Integer isVerified) {
        this.isVerified = isVerified;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getIsSigning() {
        return isSigning;
    }

    public void setIsSigning(String isSigning) {
        this.isSigning = isSigning;
    }
    
    public String getPayAttribute() {
        return payAttribute;
    }
    
    public void setPayAttribute(String payAttribute) {
        this.payAttribute = payAttribute;
    }
    
    public String getBankAccountNum() {
        return bankAccountNum;
    }

    public void setBankAccountNum(String bankAccountNum) {
        this.bankAccountNum = bankAccountNum;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
