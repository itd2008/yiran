package com.yiran.payorder.domain;

import com.netfinworks.common.util.money.Money;
import com.yiran.paychannel.enums.BizType;
import com.yiran.paychannel.enums.InstOrderStatus;
import com.yiran.paychannel.enums.PayMode;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 机构订单信息
 * </p>
 */
public class InstOrderInfo implements Serializable {

    private static final long serialVersionUID = 01235702576775724051265L;

    /** 提交机构订单号 */
    protected String          instOrderNo;
    /** 目标机构代码 */
    protected String          instCode;
    /** 产品编码 */
    protected String          productCode;
    /** 支付编码 */
    protected String          paymentCode;
    /** 资金渠道代码 */
    protected String          fundChannelCode;
    /** 机构订单ID */
    protected Integer            instOrderId;
    /** 订单类型 */
    protected BizType         bizType;
    /** 支付方式 **/
    protected PayMode         payMode;
    /** 金额 */
    protected Money           amount           = new Money("0.00");
    /** 状态 */
    protected InstOrderStatus status;
    /** 扩展信息 */
    protected String          extension;
    /** 创建时间 */
    protected Date            gmtCreate;
    /** 最后修改时间 */
    protected Date            gmtModified;

    public String getInstOrderNo() {
        return instOrderNo;
    }

    public void setInstOrderNo(String instOrderNo) {
        this.instOrderNo = instOrderNo;
    }

    public String getInstCode() {
        return instCode;
    }

    public void setInstCode(String instCode) {
        this.instCode = instCode;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getFundChannelCode() {
        return fundChannelCode;
    }

    public void setFundChannelCode(String fundChannelCode) {
        this.fundChannelCode = fundChannelCode;
    }

    public String getPaymentCode() {
        return paymentCode;
    }

    public void setPaymentCode(String paymentCode) {
        this.paymentCode = paymentCode;
    }

    public Integer getInstOrderId() {
        return instOrderId;
    }

    public void setInstOrderId(Integer instOrderId) {
        this.instOrderId = instOrderId;
    }

    public BizType getBizType() {
        return bizType;
    }

    public void setBizType(BizType bizType) {
        this.bizType = bizType;
    }

    public PayMode getPayMode() {
        return payMode;
    }

    public void setPayMode(PayMode payMode) {
        this.payMode = payMode;
    }

    public Money getAmount() {
        return amount;
    }

    public void setAmount(Money amount) {
        this.amount = amount;
    }

    public InstOrderStatus getStatus() {
        return status;
    }

    public void setStatus(InstOrderStatus status) {
        this.status = status;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
