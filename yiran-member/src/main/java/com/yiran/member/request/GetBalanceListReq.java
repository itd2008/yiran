/**
 * 
 */
package com.yiran.member.request;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.yiran.member.enums.OrderModeEnum;
import com.yiran.member.enums.TxnTypeEnum;
/**
 * <p>账户余额明细请求对象</p>
 */
public class GetBalanceListReq implements Serializable {

    private static final long serialVersionUID = 4388697933439155402L;

    /**
     * 会员编号
     */
    private String            memberId;

    /**
     * 账户类型
     */
    private Long              accountType;

    /**
     * 账户号
     */
    private String            accountId;

    private Integer           pageSize;                               //页大小
    private Integer           pageId;                                 //首页为1
    private TxnTypeEnum       txnType;                                //类型(1 所有 2 支出 3 收入)
    private OrderModeEnum     orderMode;                              //排序方式 (1顺序 2倒序)默认按时间倒序
    private Date              startTime;                              //开始时间
    private Date              endTime;                                //结束时间
    private String            requestOperator;                        //请求操作者
    private boolean           needSummary = false;                    //是否需要汇总
    
    
    public boolean isNeedSummary() {
        return needSummary;
    }

    public void setNeedSummary(boolean needSummary) {
        this.needSummary = needSummary;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public Long getAccountType() {
        return accountType;
    }

    public void setAccountType(Long accountType) {
        this.accountType = accountType;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageId() {
        return pageId;
    }

    public void setPageId(Integer pageId) {
        this.pageId = pageId;
    }

    public TxnTypeEnum getTxnType() {
        return txnType;
    }

    public void setTxnType(TxnTypeEnum txnType) {
        this.txnType = txnType;
    }

    public OrderModeEnum getOrderMode() {
        return orderMode;
    }

    public void setOrderMode(OrderModeEnum orderMode) {
        this.orderMode = orderMode;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getRequestOperator() {
        return requestOperator;
    }

    public void setRequestOperator(String requestOperator) {
        this.requestOperator = requestOperator;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
