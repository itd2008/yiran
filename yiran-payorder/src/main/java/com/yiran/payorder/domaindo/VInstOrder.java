package com.yiran.payorder.domaindo;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 查询v_inst_order视图
 * @author pandaa
 *
 */
public class VInstOrder {
	/**
	 * 机构订单ID
	 */
	private String instOrderId;
	/**
	 * 提交机构订单号
	 */
	private String instOrderNo;
	/**
	 * 资金渠道
	 */
	private String fundChannel;
	/**
	 * 订单类型
	 */
	private String orderType;
	/**
	 * 金额
	 */
	private String amount;
	/**
	 * 状态
	 */
	private String status;
	/**
	 * 交互类型
	 */
	private String communicateType;
	/**
	 * 交互状态
	 */
	private String communicateStatus;
	/**
	 * 归档批次号
	 */
	private String archiveBatchId;
	/**
	 * 预计提交时间
	 */
	private Date gmtBookingSubmit;
	/**
	 * 创建时间
	 */
	private Date gmtCreate;
	/**
	 * 最后修改时间
	 */
	private Date gmtModified;
	
	public String getInstOrderId() {
		return instOrderId;
	}
	public void setInstOrderId(String instOrderId) {
		this.instOrderId = instOrderId;
	}
	public String getInstOrderNo() {
		return instOrderNo;
	}
	public void setInstOrderNo(String instOrderNo) {
		this.instOrderNo = instOrderNo;
	}
	public String getFundChannel() {
		return fundChannel;
	}
	public void setFundChannel(String fundChannel) {
		this.fundChannel = fundChannel;
	}
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCommunicateType() {
		return communicateType;
	}
	public void setCommunicateType(String communicateType) {
		this.communicateType = communicateType;
	}
	public String getCommunicateStatus() {
		return communicateStatus;
	}
	public void setCommunicateStatus(String communicateStatus) {
		this.communicateStatus = communicateStatus;
	}
	public String getArchiveBatchId() {
		return archiveBatchId;
	}
	public void setArchiveBatchId(String archiveBatchId) {
		this.archiveBatchId = archiveBatchId;
	}
	public Date getGmtBookingSubmit() {
		return gmtBookingSubmit;
	}
	public void setGmtBookingSubmit(Date gmtBookingSubmit) {
		this.gmtBookingSubmit = gmtBookingSubmit;
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
