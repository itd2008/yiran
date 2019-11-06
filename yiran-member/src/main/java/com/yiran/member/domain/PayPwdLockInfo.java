/**
 * 
 */
package com.yiran.member.domain;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * <p>支付密码锁定信息</p>
 */
public class PayPwdLockInfo {

	/**
	 * 是否被锁定
	 */
	private boolean locked;
	
	/**
	 * 锁定开始时间
	 */
	private Date lockBeginTime;
	
	/**
	 * 锁定结束时间
	 */
	private Date lockEndTime;
	
	
	public PayPwdLockInfo() {
		super();
	}

	public PayPwdLockInfo(boolean locked) {
		super();
		this.locked = locked;
	}
	
	public PayPwdLockInfo(boolean locked,Date lockBeginTime, Date lockEndTime) {
		super();
		this.locked = locked;
		this.lockBeginTime = lockBeginTime;
		this.lockEndTime = lockEndTime;
	}

	/**
	 * 
	 * @param locked
	 * @param lockBeginTime
	 * @param lockSpan 锁定时长(单位分钟)
	 */
	public PayPwdLockInfo(boolean locked, Date lockBeginTime, long lockSpan) {
		super();
		this.locked = locked;
		this.lockBeginTime = lockBeginTime;
		this.lockEndTime = new Date(lockBeginTime.getTime() + (lockSpan * 60 * 1000));
	}

	/**
	 * @return the locked
	 */
	public boolean isLocked() {
		return locked;
	}

	/**
	 * @param locked the locked to set
	 */
	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	/**
	 * @return the lockBeginTime
	 */
	public Date getLockBeginTime() {
		return lockBeginTime;
	}

	/**
	 * @param lockBeginTime the lockBeginTime to set
	 */
	public void setLockBeginTime(Date lockBeginTime) {
		this.lockBeginTime = lockBeginTime;
	}

	/**
	 * @return the lockEndTime
	 */
	public Date getLockEndTime() {
		return lockEndTime;
	}

	/**
	 * @param lockEndTime the lockEndTime to set
	 */
	public void setLockEndTime(Date lockEndTime) {
		this.lockEndTime = lockEndTime;
	}

	/**
	 * 剩余时间(单位分钟),不足一分钟按一分钟计算
	 * @return the remainTime
	 */
	public long getRemainTime() {
		long remainTime = 0;
		if(locked && lockEndTime != null){
			Date now = new Date();
			if(now.before(lockEndTime)){
				long n = lockEndTime.getTime() - now.getTime();
				BigDecimal num = new BigDecimal(n);
				BigDecimal val =num.divide(new BigDecimal(1000 * 60),0,BigDecimal.ROUND_UP);
				
				remainTime = val.longValue();
			}
		}
		return remainTime;
	}	
	

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
