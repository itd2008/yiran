package com.yiran.reconciliation.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.yiran.common.base.BaseEntity;
import java.util.Date;

/**
 * 对账渠道设置表 reconciliation_channel_setting
 * 
 * @author yiran
 * @date 2019-09-20
 */
public class ReconciliationChannelSetting extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 编号 */
	private Integer id;
	/** 支付渠道名称 */
	private String channelName;
	/** 渠道编号 */
	private String fundChannelCode;
	/**
	 * 机构编码
	 */
	private String instCode;
	/** 描述 */
	private String reconcileDesc;
	/** 是否有效 */
	private String status;
	/** 对账周期 */
	private Integer billDay;
	/** 扩展信息 */
	private String extend;
	/** 创建者 */
	private String createBy;
	/** 创建时间 */
	private Date createTime;
	/** 更新者 */
	private String updateBy;
	/** 更新时间 */
	private Date updateTime;
	/** 备注 */
	private String remark;

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setChannelName(String channelName) 
	{
		this.channelName = channelName;
	}

	public String getChannelName() 
	{
		return channelName;
	}
	public void setFundChannelCode(String fundChannelCode) 
	{
		this.fundChannelCode = fundChannelCode;
	}

	public String getFundChannelCode() 
	{
		return fundChannelCode;
	}
	public void setReconcileDesc(String reconcileDesc) 
	{
		this.reconcileDesc = reconcileDesc;
	}

	public String getReconcileDesc() 
	{
		return reconcileDesc;
	}
	public void setStatus(String status) 
	{
		this.status = status;
	}

	public String getStatus() 
	{
		return status;
	}
	public void setBillDay(Integer billDay) 
	{
		this.billDay = billDay;
	}

	public Integer getBillDay() 
	{
		return billDay;
	}
	public void setExtend(String extend) 
	{
		this.extend = extend;
	}

	public String getExtend() 
	{
		return extend;
	}
	public void setCreateBy(String createBy) 
	{
		this.createBy = createBy;
	}

	public String getCreateBy() 
	{
		return createBy;
	}
	public void setCreateTime(Date createTime) 
	{
		this.createTime = createTime;
	}

	public Date getCreateTime() 
	{
		return createTime;
	}
	public void setUpdateBy(String updateBy) 
	{
		this.updateBy = updateBy;
	}

	public String getUpdateBy() 
	{
		return updateBy;
	}
	public void setUpdateTime(Date updateTime) 
	{
		this.updateTime = updateTime;
	}

	public Date getUpdateTime() 
	{
		return updateTime;
	}
	public void setRemark(String remark) 
	{
		this.remark = remark;
	}

	public String getRemark() 
	{
		return remark;
	}

	
    public String getInstCode() {
		return instCode;
	}

	public void setInstCode(String instCode) {
		this.instCode = instCode;
	}

	public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("channelName", getChannelName())
            .append("fundChannelCode", getFundChannelCode())
            .append("reconcileDesc", getReconcileDesc())
            .append("status", getStatus())
            .append("billDay", getBillDay())
            .append("extend", getExtend())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
