package com.yiran.payorder.domain;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.yiran.paychannel.enums.YesNo;

/**
 * <p>
 * CMF机构订单--VO
 * </p>
 */
public class InstOrderVO {

    private String     instOrderNo;       // 提现流水号(机构订单号)
    private String     instCode;          // 目标银行代码(代收银行)
    private String     instName;          // 目标银行名称(代收银行)
    private String     fundInstCode;      // 收款银行代码
    private String     fundInstName;      // 收款银行名称
    private String     fundInstBranchCode; // 收款支银行代码
    private String     fundInstBranchName; // 收款支银行名称
    private String     accountNo;         // 收款人银行卡号
    private String     accountName;       // 收款人姓名
    private String     purpose;           // 出款原因
    private Long       archiveBatchNo;    // 归档批次号
    private String     archiveStatus;     // 归档状态；参见InstOrderArchiveStatus.java，针对批量的才有值。
    private String     memberId;          // 通行证(付款方)
    private BigDecimal amount;            // 金额
    private Date       gmtCreate;         // 创建时间
    private Date       gmtBookingSubmit;  // 文件生成时间
    private String     instStatus;        // 订单状态 (I: 处理中， S:成功， F:失败）
    private String     paymentCode;       // 支付码
    private String     productCode;       // 产品码
    private boolean    canManualChange;   // 是否支持手工更改状态，1. 直连出款的不允许操作, 2.
    // 手工出款的若复核通过后不允许操作
    private String     fundChannelCode;   // 渠道编号
    private String     fundChannelApi;    // 渠道API
    private Integer    instOrderId;       // 主键
    private String     memo;              // 订单处理情况说明
    private Long       archiveTemplateId; // 归档模板
    private YesNo      checkFlag;         // 是否复核通过
    private String     gateOrderNo;       //网关订单号
    private String     paymentSeqNo;      //PE流水号

    public String getInstOrderNo() {
        return instOrderNo;
    }

    public void setInstOrderNo(String instOrderNo) {
        this.instOrderNo = instOrderNo;
    }

    public String getFundInstCode() {
        return fundInstCode;
    }

    public void setFundInstCode(String fundInstCode) {
        this.fundInstCode = fundInstCode;
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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public String getInstStatus() {
        return instStatus;
    }

    public void setInstStatus(String instStatus) {
        this.instStatus = instStatus;
    }

    public String getFundInstName() {
        return fundInstName;
    }

    public void setFundInstName(String fundInstName) {
        this.fundInstName = fundInstName;
    }

    public Long getArchiveBatchNo() {
        return archiveBatchNo;
    }

    public void setArchiveBatchNo(Long archiveBatchNo) {
        this.archiveBatchNo = archiveBatchNo;
    }

    public String getArchiveStatus() {
        return archiveStatus;
    }

    public void setArchiveStatus(String archiveStatus) {
        this.archiveStatus = archiveStatus;
    }

    public String getInstCode() {
        return instCode;
    }

    public void setInstCode(String instCode) {
        this.instCode = instCode;
    }

    public String getPaymentCode() {
        return paymentCode;
    }

    public void setPaymentCode(String paymentCode) {
        this.paymentCode = paymentCode;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public boolean isCanManualChange() {
        return canManualChange;
    }

    public void setCanManualChange(boolean canManualChange) {
        this.canManualChange = canManualChange;
    }

    public String getInstName() {
        return instName;
    }

    public void setInstName(String instName) {
        this.instName = instName;
    }

    public String getFundChannelCode() {
        return fundChannelCode;
    }

    public void setFundChannelCode(String fundChannelCode) {
        this.fundChannelCode = fundChannelCode;
    }

    public String getFundChannelApi() {
        return fundChannelApi;
    }

    public void setFundChannelApi(String fundChannelApi) {
        this.fundChannelApi = fundChannelApi;
    }

    public Long getArchiveTemplateId() {
        return archiveTemplateId;
    }

    public void setArchiveTemplateId(Long archiveTemplateId) {
        this.archiveTemplateId = archiveTemplateId;
    }

    public YesNo getCheckFlag() {
        return checkFlag;
    }

    public void setCheckFlag(YesNo checkFlag) {
        this.checkFlag = checkFlag;
    }

    public void setInstOrderId(Integer instOrderId) {
        this.instOrderId = instOrderId;
    }

    public Integer getInstOrderId() {
        return instOrderId;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getMemo() {
        return memo;
    }

    public String getFundInstBranchCode() {
        return fundInstBranchCode;
    }

    public void setFundInstBranchCode(String fundInstBranchCode) {
        this.fundInstBranchCode = fundInstBranchCode;
    }

    public String getFundInstBranchName() {
        return fundInstBranchName;
    }

    public void setFundInstBranchName(String fundInstBranchName) {
        this.fundInstBranchName = fundInstBranchName;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getGateOrderNo() {
        return gateOrderNo;
    }

    public void setGateOrderNo(String gateOrderNo) {
        this.gateOrderNo = gateOrderNo;
    }

    public String getPaymentSeqNo() {
        return paymentSeqNo;
    }

    public void setPaymentSeqNo(String paymentSeqNo) {
        this.paymentSeqNo = paymentSeqNo;
    }

    public Date getGmtBookingSubmit() {
        return gmtBookingSubmit;
    }

    public void setGmtBookingSubmit(Date gmtBookingSubmit) {
        this.gmtBookingSubmit = gmtBookingSubmit;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
