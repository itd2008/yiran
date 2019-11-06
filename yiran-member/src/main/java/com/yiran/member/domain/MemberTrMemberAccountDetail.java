package com.yiran.member.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.yiran.common.base.BaseEntity;

/**
 * 会员账户明细表 member_tr_member_account_detail
 * 
 * @author yiran
 * @date 2019-04-04
 */
public class MemberTrMemberAccountDetail extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 编号 */
	private Integer id;
	/** 会员账户ID */
	private Integer memberAccId;
	/** 账户余额 */
	private String balance;
	/** 账户可用余额 */
	private String availableBalance;
	/** 账户冻结余额 */
	private String frozenBalance;
	/** 可提现金额，信用账户现金额度 */
	private String withdrawBalance;
	/** 币种 */
	private String currencyCode;
	/** 扩展字段 */
	private String extention;

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setMemberAccId(Integer memberAccId) 
	{
		this.memberAccId = memberAccId;
	}

	public Integer getMemberAccId() 
	{
		return memberAccId;
	}
	public void setBalance(String balance) 
	{
		this.balance = balance;
	}

	public String getBalance() 
	{
		return balance;
	}
	public void setAvailableBalance(String availableBalance) 
	{
		this.availableBalance = availableBalance;
	}

	public String getAvailableBalance() 
	{
		return availableBalance;
	}
	public void setFrozenBalance(String frozenBalance) 
	{
		this.frozenBalance = frozenBalance;
	}

	public String getFrozenBalance() 
	{
		return frozenBalance;
	}
	public void setWithdrawBalance(String withdrawBalance) 
	{
		this.withdrawBalance = withdrawBalance;
	}

	public String getWithdrawBalance() 
	{
		return withdrawBalance;
	}
	public void setCurrencyCode(String currencyCode) 
	{
		this.currencyCode = currencyCode;
	}

	public String getCurrencyCode() 
	{
		return currencyCode;
	}
	public void setExtention(String extention) 
	{
		this.extention = extention;
	}

	public String getExtention() 
	{
		return extention;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("memberAccId", getMemberAccId())
            .append("balance", getBalance())
            .append("availableBalance", getAvailableBalance())
            .append("frozenBalance", getFrozenBalance())
            .append("withdrawBalance", getWithdrawBalance())
            .append("currencyCode", getCurrencyCode())
            .append("extention", getExtention())
            .toString();
    }
}
