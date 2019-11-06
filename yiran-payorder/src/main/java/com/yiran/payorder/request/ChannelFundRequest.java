package com.yiran.payorder.request;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.netfinworks.common.util.money.Money;
import com.yiran.paychannel.enums.FundChannelApiType;

/**
 * <p>渠道支付类请求基类</p>
 * 根据各个业务大类继承此基类实现进行扩展
 */
public class ChannelFundRequest extends ChannelRequest {
    private static final long serialVersionUID = 3566102968597941518L;

    /** 目标机构 */
    protected String          targetInstCode;
    /** 金额 */
    protected Money          amount;
    /** 机构订单提交时间 */
    private Date instOrderSubmitTime;

    /**
     * 默认构造
     */
    public ChannelFundRequest() {
    }

    /**
     * 根据API类型构造
     * @param fundChannelCode
     * @param apiType
     */
    public ChannelFundRequest(String fundChannelCode, FundChannelApiType apiType) {
        super(fundChannelCode, apiType);
    }

    public String getTargetInstCode() {
        return targetInstCode;
    }

    public void setTargetInstCode(String targetInstCode) {
        this.targetInstCode = targetInstCode;
    }

    public Money getAmount() {
        return amount;
    }

    public void setAmount(Money amount) {
        this.amount = amount;
    }
    
    public Date getInstOrderSubmitTime() {
        return instOrderSubmitTime;
    }

    public void setInstOrderSubmitTime(Date instOrderSubmitTime) {
        this.instOrderSubmitTime = instOrderSubmitTime;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
