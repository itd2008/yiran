package com.yiran.api.request;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.yiran.api.vo.BankCardInfo;
import com.yiran.paychannel.enums.CertType;
import com.yiran.payorder.request.ChannelRequest;

public class AuthenticateRequest extends ChannelRequest {

    private static final long serialVersionUID = 1589332562770963532L;
    /** 卡信息 */
    private BankCardInfo      bankCardInfo;
    /** 证件号 */
    private String            certNo;
    /** 证件类型 */
    private CertType          certType;
    /** 手机号 */
    private String            mobilePhoneNo;

    public String getFundChannelCode() {
        return this.fundChannelCode;
    }

    public void setFundChannelCode(String fundChannelCode) {
        this.fundChannelCode = fundChannelCode;
    }

    public BankCardInfo getBankCardInfo() {
        return this.bankCardInfo;
    }

    public void setBankCardInfo(BankCardInfo bankCardInfo) {
        this.bankCardInfo = bankCardInfo;
    }

    public String getCertNo() {
        return this.certNo;
    }

    public void setCertNo(String certNo) {
        this.certNo = certNo;
    }

    public CertType getCertType() {
        return this.certType;
    }

    public void setCertType(CertType certType) {
        this.certType = certType;
    }

    public String getMobilePhoneNo() {
        return this.mobilePhoneNo;
    }

    public void setMobilePhoneNo(String mobilePhoneNo) {
        this.mobilePhoneNo = mobilePhoneNo;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}

