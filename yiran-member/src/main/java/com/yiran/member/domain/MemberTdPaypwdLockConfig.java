package com.yiran.member.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.yiran.common.base.BaseEntity;

/**
 * 支付密码锁定配置表 member_td_paypwd_lock_config
 * 
 * @author yiran
 * @date 2019-03-30
 */
public class MemberTdPaypwdLockConfig extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 主键ID */
	private Integer id;
	/** 锁定策略 */
	private String lockStrategy;
	/** 错误输入检测时间段(以分钟记) */
	private Integer inputDetectSpan;
	/** 触发锁定的错误输入次数 */
	private Integer wrongInputCount;
	/** 锁定时间段(以分钟记) */
	private Integer lockSpan;
	/** 状态(0不可用;1可用) */
	private Integer status;
	/** 备注 */
	private String memo;

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setLockStrategy(String lockStrategy) 
	{
		this.lockStrategy = lockStrategy;
	}

	public String getLockStrategy() 
	{
		return lockStrategy;
	}
	public void setInputDetectSpan(Integer inputDetectSpan) 
	{
		this.inputDetectSpan = inputDetectSpan;
	}

	public Integer getInputDetectSpan() 
	{
		return inputDetectSpan;
	}
	public void setWrongInputCount(Integer wrongInputCount) 
	{
		this.wrongInputCount = wrongInputCount;
	}

	public Integer getWrongInputCount() 
	{
		return wrongInputCount;
	}
	public void setLockSpan(Integer lockSpan) 
	{
		this.lockSpan = lockSpan;
	}

	public Integer getLockSpan() 
	{
		return lockSpan;
	}
	public void setStatus(Integer status) 
	{
		this.status = status;
	}

	public Integer getStatus() 
	{
		return status;
	}
	public void setMemo(String memo) 
	{
		this.memo = memo;
	}

	public String getMemo() 
	{
		return memo;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("lockStrategy", getLockStrategy())
            .append("inputDetectSpan", getInputDetectSpan())
            .append("wrongInputCount", getWrongInputCount())
            .append("lockSpan", getLockSpan())
            .append("status", getStatus())
            .append("memo", getMemo())
            .toString();
    }
}
