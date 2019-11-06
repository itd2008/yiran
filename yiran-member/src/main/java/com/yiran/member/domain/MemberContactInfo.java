package com.yiran.member.domain;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * <p>联系人账户信息对外信息</p>
 */
public class MemberContactInfo implements Serializable,Comparable<MemberContactInfo> {

    private static final long serialVersionUID = 4814948159382665437L;

    private String            contactId;                               // 联系人id
    private String            memberId;                               // 所属会员ID
    private String            name;                               // 联系人名称
    private String            mobile;                               // 手机号
    private String            contactType;                               // 联系人类型
    private String            contactMemberId;                               // 会员ID
    private String            contactIdentity;                               // 会员标识

    
    private String            bankName;                               // 银行名称
    private String            pinyin;                               // PINYIN
    private String            bankCode;                               // 银行代码
    private String            province;                               // 省份
    private String            city;                                   // 城市
    private String            bankBranch;                             // 支行名称
    private String            accountName;                            // 银行账户开户名
    private String            accountNo;                              // 账号
    private String            accountNoSummary;                       // 银行卡号的掩码****尾四位
    private Integer           cardType;                               // 卡类型(1借记卡 0其它)
    private Integer           cardAttribute;                          // 卡属性(0对公 1对私)
    
    private Integer           status;                                 // 状态(0正常 1注销)
    private String            memo;                                    //备注
    private String            extention;                              // 扩展信息


    public String getContactId() {
        return contactId;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getContactType() {
        return contactType;
    }

    public void setContactType(String contactType) {
        this.contactType = contactType;
    }

    public String getContactMemberId() {
        return contactMemberId;
    }

    public void setContactMemberId(String contactMemberId) {
        this.contactMemberId = contactMemberId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getBankBranch() {
        return bankBranch;
    }

    public void setBankBranch(String bankBranch) {
        this.bankBranch = bankBranch;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getExtention() {
        return extention;
    }

    public void setExtention(String extention) {
        this.extention = extention;
    }
    
    public String getAccountNoSummary() {
        return accountNoSummary;
    }

    public void setAccountNoSummary(String accountNoSummary) {
        this.accountNoSummary = accountNoSummary;
    }

    
    public void setContactIdentity(String contactIdentity) {
        this.contactIdentity = contactIdentity;
    }

    public String getContactIdentity() {
        return contactIdentity;
    }

    
    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }
    
    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    @Override
    public int compareTo(MemberContactInfo o) {
        if(null == o || o.getPinyin() == null){
            //比较对象为空
            return 1;
        }
        if(this.getPinyin() == null){
            return -1;
        }
        return this.getPinyin().compareTo(o.getPinyin());
    }
}
