/**
 * 
 */
package com.yiran.member.request;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * <p>查询账户余额明细入参</p>
 */
public class GetBalanceListRequestParam implements Serializable {
    private static final long serialVersionUID = -8554826033798855972L;

    private Integer           pageSize;                                //页大小
    private Integer           pageId;                                  //页码（首页为1）
    private Integer           txnType;                                 //类型(1 所有 2 支出 3 收入)
    private Integer           orderMode;                               //排序方式 (1升序 2倒序)
    private Date              startTime;                               //开始时间
    private Date              endTime;                                 //结束时间
    private String            extention;                               //扩展字段
    private Boolean           needSummary;                             //是否需要汇总

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

    public Integer getTxnType() {
        return txnType;
    }

    public void setTxnType(Integer txnType) {
        this.txnType = txnType;
    }

    public Integer getOrderMode() {
        return orderMode;
    }

    public void setOrderMode(Integer orderMode) {
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

    public String getExtention() {
        return extention;
    }

    public void setExtention(String extention) {
        this.extention = extention;
    }
    
    public Boolean getNeedSummary() {
        return needSummary;
    }

    public void setNeedSummary(Boolean needSummary) {
        this.needSummary = needSummary;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
