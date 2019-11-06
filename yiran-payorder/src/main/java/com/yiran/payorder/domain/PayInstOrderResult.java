package com.yiran.payorder.domain;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.netfinworks.common.util.money.Money;
import com.yiran.paychannel.enums.BizType;
import com.yiran.paychannel.enums.CardType;
import com.yiran.paychannel.enums.CurrencyType;
import com.yiran.payorder.enums.BatchType;
import com.yiran.payorder.enums.InstOrderCompareStatus;
import com.yiran.payorder.enums.InstOrderGlideStatus;
import com.yiran.payorder.enums.InstResultOperateStatus;
import com.yiran.payorder.enums.NotifyBankOrderStatus;
import com.yiran.payorder.enums.RiskFlag;

import java.util.Date;

/**
 * 机构订单结果表 pay_inst_order_result
 * 
 * @author yiran
 * @date 2019-07-13
 */
public class PayInstOrderResult extends InstBaseResult
{
	private static final long serialVersionUID = 1L;
	 /** 订单结果ID */
    private Integer                    resultId;
	/** 批次结果ID */
    private Integer                    batchResultId;
    /** 归档批次ID */
    private Integer                    archiveBatchId;
    /** 机构订单ID */
    private Integer                    instOrderId;
    /** 机构流水号 */
    private String                  instSeqNo;
    /** 订单类型 */
    private BizType                 orderType;
    /** 实际金额 */
    private Money                   realAmount   = new Money(ZERO_MONEY_STRING);
    /** 实际币种 */
    private CurrencyType            realCurrency = CurrencyType.CNY;
    /** 帐户名称 */
    private String                  accountName;
    /** 帐户帐号 */
    private String                  accountNo;
    /** 卡类型 */
    private CardType                cardType;
    /** 对账状态 */
    private InstOrderCompareStatus  compareStatus;
    /** 流水同步状态 */
    private InstOrderGlideStatus    glideStatus;
    /** 差异信息 */
    private String                  diffMsg;
    /** 资金渠道代码 */
    private String                  fundChannelCode;
    /** 批次类型 */
    private BatchType               batchType;
    /** 该笔订单结果操作状态 */
    private InstResultOperateStatus operateStatus;
    /** 原入款订单号 */
    private String                  fundinOrgiInstOrderNo;
    /** 对无法获取统一编码的结果[instStatus=Question],重新获取编码时设置的标志 */
    private boolean                 isQuestionRetry;
    /** 通知BankOrder状态 */
    private NotifyBankOrderStatus   notifyBankOrderStatus;
    /** 风险标识 */
    private RiskFlag                riskFlag     = RiskFlag.NON_RISK;
    
    private String fromHtml;
    
	public String getFromHtml() {
		return fromHtml;
	}
	public void setFromHtml(String fromHtml) {
		this.fromHtml = fromHtml;
	}
	public Integer getBatchResultId() {
		return batchResultId;
	}
	public void setBatchResultId(Integer batchResultId) {
		this.batchResultId = batchResultId;
	}
	public Integer getArchiveBatchId() {
		return archiveBatchId;
	}
	public void setArchiveBatchId(Integer archiveBatchId) {
		this.archiveBatchId = archiveBatchId;
	}
	public Integer getInstOrderId() {
		return instOrderId;
	}
	public void setInstOrderId(Integer instOrderId) {
		this.instOrderId = instOrderId;
	}
	public String getInstSeqNo() {
		return instSeqNo;
	}
	public void setInstSeqNo(String instSeqNo) {
		this.instSeqNo = instSeqNo;
	}
	public BizType getOrderType() {
		return orderType;
	}
	public void setOrderType(BizType orderType) {
		this.orderType = orderType;
	}
	public Money getRealAmount() {
		return realAmount;
	}
	public void setRealAmount(Money realAmount) {
		this.realAmount = realAmount;
	}
	public CurrencyType getRealCurrency() {
		return realCurrency;
	}
	public void setRealCurrency(CurrencyType realCurrency) {
		this.realCurrency = realCurrency;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public CardType getCardType() {
		return cardType;
	}
	public void setCardType(CardType cardType) {
		this.cardType = cardType;
	}
	public InstOrderCompareStatus getCompareStatus() {
		return compareStatus;
	}
	public void setCompareStatus(InstOrderCompareStatus compareStatus) {
		this.compareStatus = compareStatus;
	}
	public InstOrderGlideStatus getGlideStatus() {
		return glideStatus;
	}
	public void setGlideStatus(InstOrderGlideStatus glideStatus) {
		this.glideStatus = glideStatus;
	}
	public String getDiffMsg() {
		return diffMsg;
	}
	public void setDiffMsg(String diffMsg) {
		this.diffMsg = diffMsg;
	}
	public String getFundChannelCode() {
		return fundChannelCode;
	}
	public void setFundChannelCode(String fundChannelCode) {
		this.fundChannelCode = fundChannelCode;
	}
	public BatchType getBatchType() {
		return batchType;
	}
	public void setBatchType(BatchType batchType) {
		this.batchType = batchType;
	}
	public InstResultOperateStatus getOperateStatus() {
		return operateStatus;
	}
	public void setOperateStatus(InstResultOperateStatus operateStatus) {
		this.operateStatus = operateStatus;
	}
	public String getFundinOrgiInstOrderNo() {
		return fundinOrgiInstOrderNo;
	}
	public void setFundinOrgiInstOrderNo(String fundinOrgiInstOrderNo) {
		this.fundinOrgiInstOrderNo = fundinOrgiInstOrderNo;
	}
	public boolean isQuestionRetry() {
		return isQuestionRetry;
	}
	public void setQuestionRetry(boolean isQuestionRetry) {
		this.isQuestionRetry = isQuestionRetry;
	}
	public NotifyBankOrderStatus getNotifyBankOrderStatus() {
		return notifyBankOrderStatus;
	}
	public void setNotifyBankOrderStatus(NotifyBankOrderStatus notifyBankOrderStatus) {
		this.notifyBankOrderStatus = notifyBankOrderStatus;
	}
	public RiskFlag getRiskFlag() {
		return riskFlag;
	}
	public void setRiskFlag(RiskFlag riskFlag) {
		this.riskFlag = riskFlag;
	}
    
	public Integer getResultId() {
		return resultId;
	}
	public void setResultId(Integer resultId) {
		this.resultId = resultId;
	}
	@Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
