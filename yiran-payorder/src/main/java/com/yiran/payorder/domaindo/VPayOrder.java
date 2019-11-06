package com.yiran.payorder.domaindo;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 查询v_pay_order视图
 * @author pandaa
 *
 */
public class VPayOrder {
	/**
	 * 渠道流水号
	 */
	private String paySeqNo;
	/**
	 * 支付流水号
	 */
	private String paymentSeqNo;
	/**
	 * 机构订单ID
	 */
	private String instOrderId;
	/**
	 * 支付模式
	 */
	private String payMode;
	/**
	 * 目标机构
	 */
	private String instCode;
	/**
	 * 支付通知状态
	 */
	private String paymentNotifyStatus;
	/**
	 * 状态
	 */
	private String status;
	/**
	 * 复核状态
	 */
	private String confirmStatus;
	/**
	 * 创建时间
	 */
	private Date gmtCreate;
	/**
	 * 最后修改时间
	 */
	private Date gmtModified;
	
	
	public String getPaySeqNo() {
		return paySeqNo;
	}
	public void setPaySeqNo(String paySeqNo) {
		this.paySeqNo = paySeqNo;
	}
	public String getPaymentSeqNo() {
		return paymentSeqNo;
	}
	public void setPaymentSeqNo(String paymentSeqNo) {
		this.paymentSeqNo = paymentSeqNo;
	}
	public String getInstOrderId() {
		return instOrderId;
	}
	public void setInstOrderId(String instOrderId) {
		this.instOrderId = instOrderId;
	}
	public String getPayMode() {
		return payMode;
	}
	public void setPayMode(String payMode) {
		this.payMode = payMode;
	}
	public String getInstCode() {
		return instCode;
	}
	public void setInstCode(String instCode) {
		this.instCode = instCode;
	}
	public String getPaymentNotifyStatus() {
		return paymentNotifyStatus;
	}
	public void setPaymentNotifyStatus(String paymentNotifyStatus) {
		this.paymentNotifyStatus = paymentNotifyStatus;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getConfirmStatus() {
		return confirmStatus;
	}
	public void setConfirmStatus(String confirmStatus) {
		this.confirmStatus = confirmStatus;
	}
	public Date getGmtCreate() {
		return gmtCreate;
	}
	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}
	public Date getGmtModified() {
		return gmtModified;
	}
	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}
	
	public String toString(){
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }


}
