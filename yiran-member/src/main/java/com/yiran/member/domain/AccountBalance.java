/**
 * 
 */
package com.yiran.member.domain;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.netfinworks.common.util.money.Money;

/**
 * <p>账户余额明细</p>
 */
public class AccountBalance implements Serializable {

    private static final long serialVersionUID = -2225477766415530370L;

    private String            productCode;                             //PE产品编码
    private String            payCode;                                 //PE支付编码（支付类型）
    private String            sysTraceNo;                              //外部订单号
    private Money             txnAmt;                                  //金额
    private Date              txnTime;                                 //交易时间
    private Integer           txnType;                                 //交易类型(2 支出 3 收入)
    private Money             afterAvailAmt;                           //交易后可用余额
    private Money             afterAmt;                                //交易后总余额
    private String            summary;                                 //摘要
    private String            remark;                                  //备注

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getPayCode() {
        return payCode;
    }

    public void setPayCode(String payCode) {
        this.payCode = payCode;
    }

    public String getSysTraceNo() {
        return sysTraceNo;
    }

    public void setSysTraceNo(String sysTraceNo) {
        this.sysTraceNo = sysTraceNo;
    }

    public Money getTxnAmt() {
        return txnAmt;
    }

    public void setTxnAmt(Money txnAmt) {
        this.txnAmt = txnAmt;
    }

    public Date getTxnTime() {
        return txnTime;
    }

    public void setTxnTime(Date txnTime) {
        this.txnTime = txnTime;
    }

    public Integer getTxnType() {
        return txnType;
    }

    public void setTxnType(Integer txnType) {
        this.txnType = txnType;
    }

    public Money getAfterAvailAmt() {
        return afterAvailAmt;
    }

    public void setAfterAvailAmt(Money afterAvailAmt) {
        this.afterAvailAmt = afterAvailAmt;
    }

    public Money getAfterAmt() {
        return afterAmt;
    }

    public void setAfterAmt(Money afterAmt) {
        this.afterAmt = afterAmt;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
    
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    } 

}
