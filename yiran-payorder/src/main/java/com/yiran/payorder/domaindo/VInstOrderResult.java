package com.yiran.payorder.domaindo;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 查询v_inst_order_result视图
 * @author pandaa
 *
 */
public class VInstOrderResult {
	/**
	 * 结果ID
	 */
	private String resultId;
	/**
	 * 机构订单ID
	 */
	private String instOrderId;
	/**
	 * 机构返回流水号
	 */
	private String instSeqNo;
	/**
	 * 批次类型
	 */
	private String batchType;
	/**
	 * 机构状态
	 */
	private String instStatus;
	/**
	 * 统一结果码
	 */
	private String instResultCode;
	/**
	 * 机构结果码
	 */
	private String apiResultCode;
	/**
	 * 实际金额
	 */
	private String realAmount;
	/**
	 * 卡类型
	 */
	private String cardType;
	/**
	 * 对账状态
	 */
	private String compareStatus;
	/**
	 * 创建时间
	 */
	private String gmtCreate;
	/**
	 * 最后修改时间
	 */
	private String gmtModified;
	/**
	 * 备注
	 */
	private String memo;
	public String getResultId() {
		return resultId;
	}
	public void setResultId(String resultId) {
		this.resultId = resultId;
	}
	public String getInstOrderId() {
		return instOrderId;
	}
	public void setInstOrderId(String instOrderId) {
		this.instOrderId = instOrderId;
	}
	public String getInstSeqNo() {
		return instSeqNo;
	}
	public void setInstSeqNo(String instSeqNo) {
		this.instSeqNo = instSeqNo;
	}
	public String getBatchType() {
		return batchType;
	}
	public void setBatchType(String batchType) {
		this.batchType = batchType;
	}
	public String getInstStatus() {
		return instStatus;
	}
	public void setInstStatus(String instStatus) {
		this.instStatus = instStatus;
	}
	public String getInstResultCode() {
		return instResultCode;
	}
	public void setInstResultCode(String instResultCode) {
		this.instResultCode = instResultCode;
	}
	public String getApiResultCode() {
		return apiResultCode;
	}
	public void setApiResultCode(String apiResultCode) {
		this.apiResultCode = apiResultCode;
	}
	public String getRealAmount() {
		return realAmount;
	}
	public void setRealAmount(String realAmount) {
		this.realAmount = realAmount;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public String getCompareStatus() {
		return compareStatus;
	}
	public void setCompareStatus(String compareStatus) {
		this.compareStatus = compareStatus;
	}
	public String getGmtCreate() {
		return gmtCreate;
	}
	public void setGmtCreate(String gmtCreate) {
		this.gmtCreate = gmtCreate;
	}
	public String getGmtModified() {
		return gmtModified;
	}
	public void setGmtModified(String gmtModified) {
		this.gmtModified = gmtModified;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	public String toString(){
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
