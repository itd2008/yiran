package com.yiran.payorder.domain;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.netfinworks.common.util.money.Money;
import com.yiran.paychannel.domain.TmFundChannel;
import com.yiran.paychannel.enums.InstOrderStatus;
import com.yiran.payorder.constant.BasicConstant;

/**
 * <p>提交机构基础订单</p>
 */
public class InstBaseOrder implements Cloneable, BasicConstant {

    /** 提交机构订单号 */
    protected String              instOrderNo;
    /** 目标机构代码 */
    protected String              instCode;
    /** 产品编码 */
    protected String              productCode;
    /** 资金渠道 */
    protected TmFundChannel         fundChannel;
    /** 资金渠道代码 */
    protected String              fundChannelCode;
    /** 金额 */
    protected Money               amount    = new Money(ZERO_MONEY_STRING);
    /** 状态 */
    protected InstOrderStatus     status;
    /** 扩展信息 */
    protected Map<String, String> extension = new HashMap<String, String>();
    /** 创建时间 */
    protected Date                gmtCreate;
    /** 最后修改时间 */
    protected Date                gmtModified;
    /** 备注 */
    protected String              memo;

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

    public TmFundChannel getFundChannel() {
        return fundChannel;
    }

    public void setFundChannel(TmFundChannel fundChannel) {
        this.fundChannel = fundChannel;
    }

    public String getFundChannelCode() {
        return fundChannelCode;
    }

    public void setFundChannelCode(String fundChannelCode) {
        this.fundChannelCode = fundChannelCode;
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

    public Map<String, String> getExtension() {
        return extension;
    }

    public void setExtension(Map<String, String> extension) {
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

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#clone()
     */
    public InstBaseOrder clone() throws CloneNotSupportedException {
        return (InstBaseOrder) super.clone();
    }
}
