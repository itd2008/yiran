package com.yiran.payorder.request;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.netfinworks.common.domain.Extension;
import com.netfinworks.common.domain.Kvp;
import com.yiran.paychannel.enums.BizType;
import com.yiran.paychannel.enums.PayMode;
import com.yiran.paychannel.utils.ExtensionKeyUtil;

/**
 * 渠道订单请求参数
 * @author pandaa
 *
 */
public class PayOrderRequest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 712393683348005576L;

	/** 请求批次号，批量提交时使用。可空 */
    protected String          requestBatchNo;
    /** 支付流水号。非空 */
    protected String          paymentSeqNo;
    /** 清结算ID */
    protected String          settlementId;
    /** 产品码。非空 */
    protected String          productCode;
    /** 支付编码。非空 */
    protected String          paymentCode;
    /** 支付方式 **/
    protected PayMode         payMode;
    /** 业务类型 **/
    protected BizType         bizType;
    /** 机构编码 目标机构 */
    private String            instCode;
    /** 会员ID。非空 */
    protected String          memberId;
    /** 金额。非空 */
    protected BigDecimal      amount;
    /** 币种。非空 */
    protected String          currencyCode;
    /** 业务发起时间 */
    protected Date            bizTime;
    /** 资金渠道，兼容用 */
    protected String          fundsChannel;
    /** 操作员 */
    protected String          operator;
    /** 扩展信息 */
    protected Extension       extension        = new Extension();

    public String getRequestBatchNo() {
        return requestBatchNo;
    }

    public void setRequestBatchNo(String requestBatchNo) {
        this.requestBatchNo = requestBatchNo;
    }

    public String getPaymentSeqNo() {
        return paymentSeqNo;
    }

    public void setPaymentSeqNo(String paymentSeqNo) {
        this.paymentSeqNo = paymentSeqNo;
    }

    public String getSettlementId() {
        return settlementId;
    }

    public void setSettlementId(String settlementId) {
        this.settlementId = settlementId;
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

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public Date getBizTime() {
        return bizTime;
    }

    public void setBizTime(Date bizTime) {
        this.bizTime = bizTime;
    }

    public String getFundsChannel() {
        return fundsChannel;
    }

    public void setFundsChannel(String fundsChannel) {
        this.fundsChannel = fundsChannel;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Extension getExtension() {
        return extension;
    }

    public void setExtension(Extension extension) {
        this.extension = extension;
    }

    public PayMode getPayMode() {
        return payMode;
    }

    public void setPayMode(PayMode payMode) {
        this.payMode = payMode;
    }

    public BizType getBizType() {
        return bizType;
    }

    public void setBizType(BizType bizType) {
        this.bizType = bizType;
    }

    public String getInstCode() {
        return instCode;
    }

    public void setInstCode(String instCode) {
        this.instCode = instCode;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE)
               + ",extension:" + toStringExtension();
    }

    public String toStringExtension() {
        if (extension == null || extension.getEntryList() == null) {
            return null;
        }
        StringBuilder builder = new StringBuilder();
        for (Kvp kvp : extension.getEntryList()) {
            if (!ExtensionKeyUtil.isLogged(kvp.getKey())) {
                continue;
            }
            builder.append(kvp.getKey() + "=" + kvp.getValue() + ",");
        }
        return builder.toString();
    }
}
