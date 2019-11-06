/**
 *
 */
package com.yiran.payorder.domain;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.netfinworks.biz.common.util.QueryBase;

/**
 * <p>CMF机构订单--查询</p>
 *
 */
@SuppressWarnings("serial")
public class InstOrderQuery extends QueryBase {
    /** 普通-对公-借记卡 */
    public static final String FUNDOUT_TYPE_C_DC = "1";
    /** 普通-对私-借记卡 */
    public static final String FUNDOUT_TYPE_P_DC = "2";
    /** 认证-对私-借记卡 */
    public static final String FUNDOUT_TYPE_P_IC = "3";
    /** 还款-对私-贷记卡 */
    public static final String FUNDOUT_TYPE_P_CC = "4";

    private String             instOrderNo;            //提现流水号(机构订单号)
    private BigDecimal         amountMin;              //最小金额
    private BigDecimal         amountMax;              //最大金额
    private String             instCode;               //目标机构代码(提现收单行)
    private String             fundInstCode;           //收款银行
    private String             instStatus;             //机构订单状态。根据银行错误码汇总为三种状态：I（处理中），S（成功），F（失败）
    private Long               archiveBatchNo;          //归档批次号
    private Date               gmtCreateBegin;         //生成日期
    private Date               gmtCreateEnd;           //生成日期
    private Date               gmtBookingSubmitBegin;  //生成日期
    private Date               gmtBookingSubmitEnd;    //生成日期
    private String             accountNo;              //收款账号
    private String             accountName;            //收款人姓名
    private String             memberId;               //通行证(付款方)
    private String             fundoutType;            //提现类型
    private String             fundChannelCode;        //资金渠道编码

    public String getInstOrderNo() {
        return instOrderNo;
    }

    public void setInstOrderNo(String instOrderNo) {
        this.instOrderNo = instOrderNo;
    }

    public BigDecimal getAmountMin() {
        return amountMin;
    }

    public void setAmountMin(BigDecimal amountMin) {
        this.amountMin = amountMin;
    }

    public BigDecimal getAmountMax() {
        return amountMax;
    }

    public void setAmountMax(BigDecimal amountMax) {
        this.amountMax = amountMax;
    }

    public String getInstCode() {
        return instCode;
    }

    public void setInstCode(String instCode) {
        this.instCode = instCode;
    }

    public String getFundInstCode() {
        return fundInstCode;
    }

    public void setFundInstCode(String fundInstCode) {
        this.fundInstCode = fundInstCode;
    }

    public String getInstStatus() {
        return instStatus;
    }

    public void setInstStatus(String instStatus) {
        this.instStatus = instStatus;
    }

    public Long getArchiveBatchNo() {
        return archiveBatchNo;
    }

    public void setArchiveBatchNo(Long archiveBatchNo) {
        this.archiveBatchNo = archiveBatchNo;
    }

    public Date getGmtCreateBegin() {
        return gmtCreateBegin;
    }

    public void setGmtCreateBegin(Date gmtCreateBegin) {
        this.gmtCreateBegin = gmtCreateBegin;
    }

    public Date getGmtCreateEnd() {
        return gmtCreateEnd;
    }

    public void setGmtCreateEnd(Date gmtCreateEnd) {
        this.gmtCreateEnd = gmtCreateEnd;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getFundoutType() {
        return fundoutType;
    }

    public void setFundoutType(String fundoutType) {
        this.fundoutType = fundoutType;
    }
    

    public Date getGmtBookingSubmitBegin() {
        return gmtBookingSubmitBegin;
    }

    public void setGmtBookingSubmitBegin(Date gmtBookingSubmitBegin) {
        this.gmtBookingSubmitBegin = gmtBookingSubmitBegin;
    }

    public Date getGmtBookingSubmitEnd() {
        return gmtBookingSubmitEnd;
    }

    public void setGmtBookingSubmitEnd(Date gmtBookingSubmitEnd) {
        this.gmtBookingSubmitEnd = gmtBookingSubmitEnd;
    }

    public String getFundChannelCode() {
        return fundChannelCode;
    }

    public void setFundChannelCode(String fundChannelCode) {
        this.fundChannelCode = fundChannelCode;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
