package com.yiran.payorder.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.yiran.common.base.BaseEntity;
import java.util.Date;

/**
 * 分布式锁表 pay_distributed_lock
 * 
 * @author yiran
 * @date 2019-08-11
 */
public class PayDistributedLock extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 锁的唯一区别码 */
	private String lockTicket;
	/** 锁名称 */
	private String lockName;
	/** 锁类型：E排它锁,S同步锁 */
	private String lockType;
	/** 描述 */
	private String lockDesc;
	/** 锁状态,E执行中；F执行结束； */
	private String lockStatus;
	/** 上锁时间 */
	private Date gmtLock;
	/** 加锁机器IP */
	private String machineIp;
	/** 加锁机器HOST */
	private String machineHost;
	/** 最后修改时间 */
	private Date gmtModified;

	public void setLockTicket(String lockTicket) 
	{
		this.lockTicket = lockTicket;
	}

	public String getLockTicket() 
	{
		return lockTicket;
	}
	public void setLockName(String lockName) 
	{
		this.lockName = lockName;
	}

	public String getLockName() 
	{
		return lockName;
	}
	public void setLockType(String lockType) 
	{
		this.lockType = lockType;
	}

	public String getLockType() 
	{
		return lockType;
	}
	public void setLockDesc(String lockDesc) 
	{
		this.lockDesc = lockDesc;
	}

	public String getLockDesc() 
	{
		return lockDesc;
	}
	public void setLockStatus(String lockStatus) 
	{
		this.lockStatus = lockStatus;
	}

	public String getLockStatus() 
	{
		return lockStatus;
	}
	public void setGmtLock(Date gmtLock) 
	{
		this.gmtLock = gmtLock;
	}

	public Date getGmtLock() 
	{
		return gmtLock;
	}
	public void setMachineIp(String machineIp) 
	{
		this.machineIp = machineIp;
	}

	public String getMachineIp() 
	{
		return machineIp;
	}
	public void setMachineHost(String machineHost) 
	{
		this.machineHost = machineHost;
	}

	public String getMachineHost() 
	{
		return machineHost;
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
            .append("lockTicket", getLockTicket())
            .append("lockName", getLockName())
            .append("lockType", getLockType())
            .append("lockDesc", getLockDesc())
            .append("lockStatus", getLockStatus())
            .append("gmtLock", getGmtLock())
            .append("machineIp", getMachineIp())
            .append("machineHost", getMachineHost())
            .append("gmtModified", getGmtModified())
            .toString();
    }
}
