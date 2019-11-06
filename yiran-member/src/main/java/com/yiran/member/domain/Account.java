package com.yiran.member.domain;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.netfinworks.common.util.money.Money;

/**
 * <p>账户信息</p>
 */
public class Account implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -2195040637162245157L;

    /**
     * 会员编号
     */
    private String            memberId;

    /**
     * 账户编号
     */
    private String            accountId;

    /**
     * 账户名称
     */
    private String            accountName;

    /**
     * 账户类型
     */
    private Long              accountType;

    /**
     * 账户余额
     */
    private Money             balance;

    /**
     * 账户可用余额
     */
    private Money             availableBalance;

    /**
     * 账户冻结余额
     */
    private Money             frozenBalance;

    /**
     * 可提现金额
     */
    private Money             withdrawBalance;

    /**
     * 激活状态 0:未激活    1:已激活
     */
    private Long              activateStatus;

    /**
     * 冻结状态    0:未冻结    1:止出    2:止入    3:双向冻结(锁定)
     */
    private Long              freezeStatus;

    /**
     * 销户状态 0:未销户    1:已销户    2:已结转长期不动户
     */
    private Long              lifeCycleStatus;

    /**
     * 更后更新时间
     */
    private Date              lastUpdatetime;

    /**
     * 币种
     */
    private String            currencyCode;

    /**
     * 账户属性
     */
    private Long              accountAttribute;

    /**
     * 扩展字段
     */
    private String            extention;

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public Long getAccountType() {
        return accountType;
    }

    public void setAccountType(Long accountType) {
        this.accountType = accountType;
    }

    public Money getBalance() {
        return balance;
    }

    public void setBalance(Money balance) {
        this.balance = balance;
    }

    public Money getAvailableBalance() {
        return availableBalance;
    }

    public void setAvailableBalance(Money availableBalance) {
        this.availableBalance = availableBalance;
    }

    public Money getFrozenBalance() {
        return frozenBalance;
    }

    public void setFrozenBalance(Money frozenBalance) {
        this.frozenBalance = frozenBalance;
    }

    public Money getWithdrawBalance() {
        return withdrawBalance;
    }

    public void setWithdrawBalance(Money withdrawBalance) {
        this.withdrawBalance = withdrawBalance;
    }

    public Long getActivateStatus() {
        return activateStatus;
    }

    public void setActivateStatus(Long activateStatus) {
        this.activateStatus = activateStatus;
    }

    public Long getFreezeStatus() {
        return freezeStatus;
    }

    public void setFreezeStatus(Long freezeStatus) {
        this.freezeStatus = freezeStatus;
    }

    public Long getLifeCycleStatus() {
        return lifeCycleStatus;
    }

    public void setLifeCycleStatus(Long lifeCycleStatus) {
        this.lifeCycleStatus = lifeCycleStatus;
    }

    public Date getLastUpdatetime() {
        return lastUpdatetime;
    }

    public void setLastUpdatetime(Date lastUpdatetime) {
        this.lastUpdatetime = lastUpdatetime;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public Long getAccountAttribute() {
        return accountAttribute;
    }

    public void setAccountAttribute(Long accountAttribute) {
        this.accountAttribute = accountAttribute;
    }

    public String getExtention() {
        return extention;
    }

    public void setExtention(String extention) {
        this.extention = extention;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
