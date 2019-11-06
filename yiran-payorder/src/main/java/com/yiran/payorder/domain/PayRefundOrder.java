package com.yiran.payorder.domain;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import com.yiran.common.base.BaseEntity;
import java.util.Date;

/**
 * 退款订单表 pay_refund_order
 * 
 * @author yiran
 * @date 2019-07-13
 */
public class PayRefundOrder extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 机构订单ID */
	private Integer instOrderId;
	/** 充值订单号 */
	private String fundinOrderNo;
	/** 充值实收金额 */
	private Double fundinRealAmount;
	/** 入款时间 */
	private String fundinDate;
	/** 最后修改时间 */
	private Date gmtModified;

	public void setInstOrderId(Integer instOrderId) 
	{
		this.instOrderId = instOrderId;
	}

	public Integer getInstOrderId() 
	{
		return instOrderId;
	}
	public void setFundinOrderNo(String fundinOrderNo) 
	{
		this.fundinOrderNo = fundinOrderNo;
	}

	public String getFundinOrderNo() 
	{
		return fundinOrderNo;
	}
	public void setFundinRealAmount(Double fundinRealAmount) 
	{
		this.fundinRealAmount = fundinRealAmount;
	}

	public Double getFundinRealAmount() 
	{
		return fundinRealAmount;
	}
	public void setFundinDate(String fundinDate) 
	{
		this.fundinDate = fundinDate;
	}

	public String getFundinDate() 
	{
		return fundinDate;
	}
	public void setGmtModified(Date gmtModified) 
	{
		this.gmtModified = gmtModified;
	}

	public Date getGmtModified() 
	{
		return gmtModified;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("instOrderId", getInstOrderId())
            .append("fundinOrderNo", getFundinOrderNo())
            .append("fundinRealAmount", getFundinRealAmount())
            .append("fundinDate", getFundinDate())
            .append("gmtModified", getGmtModified())
            .toString();
    }
}
