package com.yiran.payorder.rms;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * RMS请求参数,需要转为jason串传输
 *
 */
public class RmsRequest {
    
    
	/** 检查点编号 */  
	private String checkPoint;
	/** 接口版本号  */
	private Integer version;
	/** 商户接入渠道  */
	private String payChannel;
	/** MAS订单号  */
	private String masOrderNo;
	/** 网关订单号  */
	private String customerOrderNo;
	/** 订单创建时间  */
	private String orderTime;
	/** 应付金额  */
	private String payableAmount;
	/** 付款机构 InstCode  */
	private String payOrg;
	/** 网银支付完成后从用户浏览器跳转回时的IP  */
	private String payReturnIP;
	/** 实际支付时间  */
	private String payTime;
	/** 产品码  */
	private String productCode;
	/** 支付码  */
	private String paymentCode;
	/** 支付状态（0:成功, 1:失败） */
	private String paymentStatus;
	/** 银行返回码  */
	private String bankReturnCode;
	/** 银行返回信息  */
	private String bankReturnMsg;
	/** 风险订单标记：0 - 非风险订单，1 - 风险订单 */
	private Integer riskOrderFlag;
	/** PE支付流水号  */
	private String paymentSeqNo;
	/** 渠道订单号，CMF生成的机构订单号 */
	private String instOrderNo;
	/** 货币符号，CNY：人民币  */
	private String currency;
	/** 订单类型
	 * OT001 - B2C订单 
	   OT002 - C2C订单 
       OT003 - B2B订单 
       OT004 - 代扣订单 
       OT005 - 代发订单 
       OT006 - POS订单 
	 */
	private String orderType;
	/** 渠道编码  */
	private String channelCode;
	/** 会员号  */
	private String payeeId;
	/** 备注  */
	private String memo;
	/** 扩展字段 */
	private String publicMapExpand;
	/** 付款IP */
	private String realPayIp;
	/** 用户付款的URL */
	private String realPayWebdomain;
	/** 用户下单的IP */
	private String orderIp;
	/** 银行内部流水号 */
	private String bankSerialNo;
	/** 支付订单号 */
	private String paymentOrderNo;
	/** 付款批次号 */
	private String fundInBatchNo;
	/** 出款批次号 */
	private String fundOutBatchNo;
	/** 入款订单号(退款时使用) */
	private String fundInOrderNo;
	/** 风险处理结果      代码：
		000 - 通过
		001 - 拒绝
		002 - 滞留（转人工）
		003 - 预警
		004 - 监控
		999 - 发生异常  */
	private String riskResult;
	/** 风险处理结果描述 */
	private String riskMsg;
	/** 风险值 */
	private String riskValue;
	/** 是否银企直连 */
	private boolean isBdc;
	/** 渠道返回信息 */
	private String channelReturnMsg;
	/** 渠道返回编码 */
	private String channelReturnCode;
	/** cmf 订单号 */
	private String paySeqNo;

	
	private String bizType;
	private String payMode;
	
	private String payerMemberId;
	private String bankCode;
	private String bankName;
	private String bankArea;
	private String bankLineNo;
	private String bankAccountNumber;
	private String bankAccountName;
	
	private String tradeVoucherNos;

    private String memberId;



	public String getPayMode() {
		return payMode;
	}

	public void setPayMode(String payMode) {
		this.payMode = payMode;
	}

	public String getCheckPoint() {
		return checkPoint;
	}

	public void setCheckPoint(String checkPoint) {
		this.checkPoint = checkPoint;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getPayChannel() {
		return payChannel;
	}

	public void setPayChannel(String payChannel) {
		this.payChannel = payChannel;
	}

	public String getMasOrderNo() {
		return masOrderNo;
	}

	public void setMasOrderNo(String masOrderNo) {
		this.masOrderNo = masOrderNo;
	}

	public String getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}

	public String getPayableAmount() {
		return payableAmount;
	}

	public String getPaySeqNo() {
        return paySeqNo;
    }

    public void setPaySeqNo(String paySeqNo) {
        this.paySeqNo = paySeqNo;
    }

    public void setPayableAmount(String payableAmount) {
		this.payableAmount = payableAmount;
	}

	public String getPayOrg() {
		return payOrg;
	}

	public void setPayOrg(String payOrg) {
		this.payOrg = payOrg;
	}

	public String getPayReturnIP() {
		return payReturnIP;
	}

	public void setPayReturnIP(String payReturnIP) {
		this.payReturnIP = payReturnIP;
	}

	public String getPayTime() {
		return payTime;
	}

	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getPaymentCode() {
		return paymentCode;
	}

	public void setPaymentCode(String paymentCode) {
		this.paymentCode = paymentCode;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public String getBankReturnCode() {
		return bankReturnCode;
	}

	public void setBankReturnCode(String bankReturnCode) {
		this.bankReturnCode = bankReturnCode;
	}

	public String getBankReturnMsg() {
		return bankReturnMsg;
	}

	public void setBankReturnMsg(String bankReturnMsg) {
		this.bankReturnMsg = bankReturnMsg;
	}

	public Integer getRiskOrderFlag() {
		return riskOrderFlag;
	}

	public void setRiskOrderFlag(Integer riskOrderFlag) {
		this.riskOrderFlag = riskOrderFlag;
	}

	public String getPaymentSeqNo() {
		return paymentSeqNo;
	}

	public void setPaymentSeqNo(String paymentSeqNo) {
		this.paymentSeqNo = paymentSeqNo;
	}

	public String getInstOrderNo() {
		return instOrderNo;
	}

	public void setInstOrderNo(String instOrderNo) {
		this.instOrderNo = instOrderNo;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getChannelCode() {
		return channelCode;
	}

	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getPublicMapExpand() {
		return publicMapExpand;
	}

	public void setPublicMapExpand(String publicMapExpand) {
		this.publicMapExpand = publicMapExpand;
	}

	public String getRealPayIp() {
		return realPayIp;
	}

	public void setRealPayIp(String realPayIp) {
		this.realPayIp = realPayIp;
	}

	public String getRealPayWebdomain() {
		return realPayWebdomain;
	}

	public void setRealPayWebdomain(String realPayWebdomain) {
		this.realPayWebdomain = realPayWebdomain;
	}

	public String getOrderIp() {
		return orderIp;
	}

	public void setOrderIp(String orderIp) {
		this.orderIp = orderIp;
	}

	public String getBankSerialNo() {
		return bankSerialNo;
	}

	public void setBankSerialNo(String bankSerialNo) {
		this.bankSerialNo = bankSerialNo;
	}

    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

    public String getPayerMemberId() {
        return payerMemberId;
    }

    public void setPayerMemberId(String payerMemberId) {
        this.payerMemberId = payerMemberId;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankArea() {
        return bankArea;
    }

    public void setBankArea(String bankArea) {
        this.bankArea = bankArea;
    }

    public String getBankLineNo() {
        return bankLineNo;
    }

    public void setBankLineNo(String bankLineNo) {
        this.bankLineNo = bankLineNo;
    }

    public String getBankAccountNumber() {
        return bankAccountNumber;
    }

    public void setBankAccountNumber(String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }

    public String getBankAccountName() {
        return bankAccountName;
    }

    public void setBankAccountName(String bankAccountName) {
        this.bankAccountName = bankAccountName;
    }

	public String getPaymentOrderNo() {
		return paymentOrderNo;
	}

	public void setPaymentOrderNo(String paymentOrderNo) {
		this.paymentOrderNo = paymentOrderNo;
	}

	public String getFundInBatchNo() {
		return fundInBatchNo;
	}

	public void setFundInBatchNo(String fundInBatchNo) {
		this.fundInBatchNo = fundInBatchNo;
	}

	public String getFundOutBatchNo() {
		return fundOutBatchNo;
	}

	public void setFundOutBatchNo(String fundOutBatchNo) {
		this.fundOutBatchNo = fundOutBatchNo;
	}

	public String getFundInOrderNo() {
		return fundInOrderNo;
	}

	public void setFundInOrderNo(String fundInOrderNo) {
		this.fundInOrderNo = fundInOrderNo;
	}

	public String getRiskResult() {
		return riskResult;
	}

	public void setRiskResult(String riskResult) {
		this.riskResult = riskResult;
	}

	public String getRiskMsg() {
		return riskMsg;
	}

	public void setRiskMsg(String riskMsg) {
		this.riskMsg = riskMsg;
	}

	public String getRiskValue() {
		return riskValue;
	}

	public void setRiskValue(String riskValue) {
		this.riskValue = riskValue;
	}

	public boolean isBdc() {
		return isBdc;
	}
	
	public void setBdc(boolean isBdc) {
		this.isBdc = isBdc;
	}

	public String getChannelReturnMsg() {
		return channelReturnMsg;
	}

	public void setChannelReturnMsg(String channelReturnMsg) {
		this.channelReturnMsg = channelReturnMsg;
	}

	public String getChannelReturnCode() {
		return channelReturnCode;
	}

	public void setChannelReturnCode(String channelReturnCode) {
		this.channelReturnCode = channelReturnCode;
	}
	
	public String getCustomerOrderNo() {
        return customerOrderNo;
    }

    public void setCustomerOrderNo(String customerOrderNo) {
        this.customerOrderNo = customerOrderNo;
    }

    public String getPayeeId() {
        return payeeId;
    }

    public void setPayeeId(String payeeId) {
        this.payeeId = payeeId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getTradeVoucherNos() {
        return tradeVoucherNos;
    }

    public void setTradeVoucherNos(String tradeVoucherNos) {
        this.tradeVoucherNos = tradeVoucherNos;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
