package com.yiran.payorder.response;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.yiran.paychannel.utils.ExtensionKeyUtil;
import com.yiran.payorder.enums.PayFundResultCode;
import com.netfinworks.biz.common.util.BaseResult;
import com.netfinworks.common.domain.Extension;
import com.netfinworks.common.domain.Kvp;

/**
 * 支付处理结果
 * @author pandaa
 *
 */
public class PayFundResult extends BaseResult{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1581146691109719439L;
	
	 /** 结果代码 */
    private PayFundResultCode resultCode;
    /** 扩展信息 */
    private Extension         extension;
    /** 资金源id*/
    private String            fundsChannel;
    /** 渠道支付编号 */
    private String            channelPayNo;
    /** cmf机构订单号 */
    private String            instOrderNo;
    /** 渠道支付时间 */
    private Date              instPayTime;
    /** 实际支付金额 */
    protected BigDecimal      amount;
    /** 实际支付币种 */
    protected String          currencyCode;

    private String formHtml;
    /**
     * 默认构造
     */
    public PayFundResult() {
    }

    
    public String getFormHtml() {
		return formHtml;
	}


	public void setFormHtml(String formHtml) {
		this.formHtml = formHtml;
	}


	/**
     * 根据结果代码构造
     * @param resultCode
     */
    public PayFundResult(PayFundResultCode resultCode) {
        this.setResultCode(resultCode);
    }

    public PayFundResultCode getResultCode() {
        return resultCode;
    }

    public void setResultCode(PayFundResultCode resultCode) {
        this.success = resultCode == PayFundResultCode.SUCCESS;
        this.resultCode = resultCode;
    }

    public Extension getExtension() {
        return extension;
    }

    public void setExtension(Extension extension) {
        this.extension = extension;
    }

    public String getFundsChannel() {
        return fundsChannel;
    }

    public void setFundsChannel(String fundsChannel) {
        this.fundsChannel = fundsChannel;
    }

    public String getChannelPayNo() {
        return channelPayNo;
    }

    public void setChannelPayNo(String channelPayNo) {
        this.channelPayNo = channelPayNo;
    }

    public String getInstOrderNo() {
        return instOrderNo;
    }

    public void setInstOrderNo(String instOrderNo) {
        this.instOrderNo = instOrderNo;
    }

    public Date getInstPayTime() {
        return instPayTime;
    }

    public void setInstPayTime(Date instPayTime) {
        this.instPayTime = instPayTime;
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
