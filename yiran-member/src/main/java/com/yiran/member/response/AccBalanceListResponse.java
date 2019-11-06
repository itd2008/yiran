/**
 * 
 */
package com.yiran.member.response;

import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.netfinworks.common.util.money.Money;
import com.yiran.member.base.Response;

/**
 * <p>查询账户余额收支明细响应信息</p>
 */
public class AccBalanceListResponse extends Response {
    private static final long            serialVersionUID = 1921224074862976985L;
    private List<AccountBalanceListResp> balanceListResp;                        //账户收支列表
    private Integer                      totalCount;                             //总记录数
    private Integer                      pageCount;                              //页数
    /**
     * 总收入 只有汇总时才有值
     */
    private Money                        totalIncome;
    /**
     * 总支出 只有汇总时才有值
     */
    private Money                        totalPayout;

    public List<AccountBalanceListResp> getBalanceListResp() {
        return balanceListResp;
    }

    public void setBalanceListResp(List<AccountBalanceListResp> balanceListResp) {
        this.balanceListResp = balanceListResp;
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

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
