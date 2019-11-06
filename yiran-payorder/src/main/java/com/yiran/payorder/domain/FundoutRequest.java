package com.yiran.payorder.domain;

import com.yiran.paychannel.enums.CardType;
import com.yiran.paychannel.enums.CompanyOrPersonal;
import com.yiran.payorder.request.ChannelFundRequest;

/**
 * <p>出款同一request</p>
 */
public class FundoutRequest extends ChannelFundRequest {

    private static final long   serialVersionUID = 708846509083955934L;
    /** 证件类型 */
    protected String            identityType;
    /** 证件号 */
    protected String            identityNo;
    /** 是否同行 */
    protected Boolean           innerBank;
    /** 银行编码 */
    protected String            bankCode;
    /** 银行名称 */
    protected String            bankName;
    /** 分行  */
    protected String            bankBranch;
    /** 分行编码 */
    protected String            bankBranchCode;
    /** 省份信息 */
    protected String            bankProvince;
    /** 城市信息 */
    protected String            bankCity;
    /** 邮政编码 */
    protected String            areaCode;
    /** 对公/对私 */
    protected CompanyOrPersonal accountType;
    /** 账户名 */
    protected String            accountName;
    /** 账户卡号 */
    protected String            accountNo;
    /**　卡类型　*/
    protected CardType          cardType;
    /** 协议号 */
    protected String            agreementNo;
    /**　手机号 */
    protected String            mobilePhone;
    /** 出款原因 */
    protected String            purpose;
    /** 产品编码 */
    protected String            productCode;
    /** 支付编码 */
    protected String            paymentCode;

    public String getIdentityType() {
        return identityType;
    }

    public void setIdentityType(String identityType) {
        this.identityType = identityType;
    }

    public String getIdentityNo() {
        return identityNo;
    }

    public void setIdentityNo(String identityNo) {
        this.identityNo = identityNo;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getPaymentCode() {
        return paymentCode;
    }

    public void setPaymentCode(String paymentCode) {
        this.paymentCode = paymentCode;
    }

    public Boolean getInnerBank() {
        return innerBank;
    }

    public void setInnerBank(Boolean innerBank) {
        this.innerBank = innerBank;
    }

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

    public CompanyOrPersonal getAccountType() {
        return accountType;
    }

    public void setAccountType(CompanyOrPersonal accountType) {
        this.accountType = accountType;
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

    public String getAgreementNo() {
        return agreementNo;
    }

    public void setAgreementNo(String agreementNo) {
        this.agreementNo = agreementNo;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

}
