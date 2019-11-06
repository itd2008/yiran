/**
 * 
 */
package com.yiran.member.request;

import java.util.ArrayList;
import java.util.List;

import com.netfinworks.biz.common.util.BaseResult;
import com.netfinworks.common.util.money.Money;
import com.yiran.member.domain.AccountBalance;

/**
 * <p>账户余额明细查询结果集</p>
 */
public class RemoteAccountBalanceListResult extends BaseResult {

    private static final long serialVersionUID = -1087975856929009580L;

    private Integer           totalCount;                              //总记录数
    private Integer           pageCount;                               //页数
    /**
     * 总收入
     */
    private Money             totalIncome;
    /**
     * 总支出
     */
    private Money             totalPayout;

    List<AccountBalance>      balanceList;                             //余额明细

    public RemoteAccountBalanceListResult() {
        super();
    }

    public RemoteAccountBalanceListResult(boolean success) {
        super(success);
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public List<AccountBalance> getBalanceList() {
        return balanceList;
    }

    public void setBalanceList(List<AccountBalance> balanceList) {
        this.balanceList = balanceList;
    }

    public void addBalance(AccountBalance balance) {
        if (this.balanceList == null) {
            this.balanceList = new ArrayList<AccountBalance>();
        }
        this.balanceList.add(balance);
    }

    public Money getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(Money totalIncome) {
        this.totalIncome = totalIncome;
    }

    public Money getTotalPayout() {
        return totalPayout;
    }

    public void setTotalPayout(Money totalPayout) {
        this.totalPayout = totalPayout;
    }
    
}
