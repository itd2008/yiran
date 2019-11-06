package com.yiran.payorder.domain;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import com.yiran.common.base.BaseEntity;
import java.util.Date;

/**
 * 请求，用于控制唯一表 pay_request
 * 
 * @author yiran
 * @date 2019-07-13
 */
public class PayRequest extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 支付流水号 */
	private String paymentSeqNo;
	/** 是否允许重试：Y（是），N（否） */
	private String canRetry;
	/** 创建时间 */
	private Date gmtCreate;
	/** 最后修改时间 */
	private Date gmtModified;
	/** 结算id */
	private String settlementId;

	public void setPaymentSeqNo(String paymentSeqNo) 
	{
		this.paymentSeqNo = paymentSeqNo;
	}

	public String getPaymentSeqNo() 
	{
		return paymentSeqNo;
	}
	public void setCanRetry(String canRetry) 
	{
		this.canRetry = canRetry;
	}

	public String getCanRetry() 
	{
		return canRetry;
	}
	public void setGmtCreate(Date gmtCreate) 
	{
		this.gmtCreate = gmtCreate;
	}

	public Date getGmtCreate() 
	{
		return gmtCreate;
	}
	public void setGmtModified(Date gmtModified) 
	{
		this.gmtModified = gmtModified;
	}

	public Date getGmtModified() 
	{
		return gmtModified;
	}
	public void setSettlementId(String settlementId) 
	{
		this.settlementId = settlementId;
	}

	public String getSettlementId() 
	{
		return settlementId;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("paymentSeqNo", getPaymentSeqNo())
            .append("canRetry", getCanRetry())
            .append("gmtCreate", getGmtCreate())
            .append("gmtModified", getGmtModified())
            .append("settlementId", getSettlementId())
            .toString();
    }
}
