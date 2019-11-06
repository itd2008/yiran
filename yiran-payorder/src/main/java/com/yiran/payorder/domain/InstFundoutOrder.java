package com.yiran.payorder.domain;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.yiran.paychannel.enums.CardType;
import com.yiran.paychannel.enums.CompanyOrPersonal;


/**
 * <p>
 * 出款订单
 * </p>
 */
public class InstFundoutOrder extends PayInstOrder {
    /** 银行代码 */
    private String            bankCode;
    /** 银行名称 */
    private String            bankName;
    /** 开户行支行名 */
    private String            bankBranch;
    /** 电子联行号 */
    private String            bankBranchCode;
    /** 银行所属省份 */
    private String            bankProvince;
    /** 银行所属城市 */
    private String            bankCity;
    /** 地区代码 */
    private String            areaCode;
    /** 账户类型 */
    private CompanyOrPersonal companyOrPersonal;
    /** 开户名称 */
    private String            accountName;
    /** 出款帐号 */
    private String            accountNo;
    /** 卡类型 */
    private CardType          cardType;
    /** 协议号 */
    private String            agreementNo;
    /** 期望到帐时间 */
    private Date              expectTime;
    /** 用途 */
    private String            purpose;
    /** 通行证 */
    private String            ptId;

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankBranch() {
        return bankBranch;
    }

    public void setBankBranch(String bankBranch) {
        this.bankBranch = bankBranch;
    }

    public String getBankBranchCode() {
        return bankBranchCode;
    }

    public void setBankBranchCode(String bankBranchCode) {
        this.bankBranchCode = bankBranchCode;
    }

    public String getBankProvince() {
        return bankProvince;
    }

    public void setBankProvince(String bankProvince) {
        this.bankProvince = bankProvince;
    }

    public String getBankCity() {
        return bankCity;
    }

    public void setBankCity(String bankCity) {
        this.bankCity = bankCity;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public CompanyOrPersonal getCompanyOrPersonal() {
        return companyOrPersonal;
    }

    public void setCompanyOrPersonal(CompanyOrPersonal companyOrPersonal) {
        this.companyOrPersonal = companyOrPersonal;
    }

    public void setCompanyOrPersonal(String companyOrPersonalStr) {
        this.companyOrPersonal = CompanyOrPersonal.getByCode(companyOrPersonalStr);
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public CardType getCardType() {
        return cardType;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }

    public void setCardType(String cardTypeStr) {
        this.cardType = CardType.getByCode(cardTypeStr);
    }

    public String getAgreementNo() {
        return agreementNo;
    }

    public void setAgreementNo(String agreementNo) {
        this.agreementNo = agreementNo;
    }

    public Date getExpectTime() {
        return expectTime;
    }

    public void setExpectTime(Date expectTime) {
        this.expectTime = expectTime;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getPtId() {
        return ptId;
    }

    public void setPtId(String ptId) {
        this.ptId = ptId;
    }

    /**
     * 克隆
     */
    public InstFundoutOrder clone() throws CloneNotSupportedException {
        return (InstFundoutOrder) super.clone();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
