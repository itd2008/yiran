package com.yiran.payorder.domaindo;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import com.yiran.common.base.BaseEntity;
import java.util.Date;

/**
 * 签约表 pay_sign_info
 * 
 * @author yiran
 * @date 2019-07-13
 */
public class PaySignInfoDO extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 主键 */
	private Integer signReqId;
	/** 签约渠道编号 */
	private String fundChannelCode;
	/** 卡号 */
	private String cardNo;
	/** 银行代码 */
	private String targetInstCode;
	/** 银行卡信息表主键 */
	private String bankCardId;
	/** 签约协议号 */
	private String channelSignNo;
	/** 签约时间 */
	private Date channelSignTime;
	/** 渠道签约有效期 */
	private Date channelSignValid;
	/** 状态：A(待签约推进)，S(签约完成),F(签约失败),E(签约情况)，C(取消签约),T(解约) */
	private String status;
	/** 签约短信最终发送方：MERCHANT(商户)，PLATFORM(永达)，BANK(银行) */
	private String signSmsSender;
	/** 签约发起方：MAS,SYSTEM(系统) */
	private String signSource;
	/** 支付方式 */
	private String payMode;
	/** 访问方式 */
	private String accessChannel;
	/** 签约扩展信息 */
	private String sourceExtInfo;
	/** 会员id */
	private String memberId;
	/** 来源请求号 */
	private String sourceSeqNo;
	/** cmf流水号 */
	private String cmfSeqNo;
	/** 签约扩展参数 */
	private String channelExtInfo;
	/** 失败原因 */
	private String failReason;
	/** 创建时间 */
	private Date gmtCreate;
	/** 最后修改时间 */
	private Date gmtModified;

	public void setSignReqId(Integer signReqId) 
	{
		this.signReqId = signReqId;
	}

	public Integer getSignReqId() 
	{
		return signReqId;
	}
	public void setFundChannelCode(String fundChannelCode) 
	{
		this.fundChannelCode = fundChannelCode;
	}

	public String getFundChannelCode() 
	{
		return fundChannelCode;
	}
	public void setCardNo(String cardNo) 
	{
		this.cardNo = cardNo;
	}

	public String getCardNo() 
	{
		return cardNo;
	}
	public void setTargetInstCode(String targetInstCode) 
	{
		this.targetInstCode = targetInstCode;
	}

	public String getTargetInstCode() 
	{
		return targetInstCode;
	}
	public void setBankCardId(String bankCardId) 
	{
		this.bankCardId = bankCardId;
	}

	public String getBankCardId() 
	{
		return bankCardId;
	}
	public void setChannelSignNo(String channelSignNo) 
	{
		this.channelSignNo = channelSignNo;
	}

	public String getChannelSignNo() 
	{
		return channelSignNo;
	}
	public void setChannelSignTime(Date channelSignTime) 
	{
		this.channelSignTime = channelSignTime;
	}

	public Date getChannelSignTime() 
	{
		return channelSignTime;
	}
	public void setChannelSignValid(Date channelSignValid) 
	{
		this.channelSignValid = channelSignValid;
	}

	public Date getChannelSignValid() 
	{
		return channelSignValid;
	}
	public void setStatus(String status) 
	{
		this.status = status;
	}

	public String getStatus() 
	{
		return status;
	}
	public void setSignSmsSender(String signSmsSender) 
	{
		this.signSmsSender = signSmsSender;
	}

	public String getSignSmsSender() 
	{
		return signSmsSender;
	}
	public void setSignSource(String signSource) 
	{
		this.signSource = signSource;
	}

	public String getSignSource() 
	{
		return signSource;
	}
	public void setPayMode(String payMode) 
	{
		this.payMode = payMode;
	}

	public String getPayMode() 
	{
		return payMode;
	}
	public void setAccessChannel(String accessChannel) 
	{
		this.accessChannel = accessChannel;
	}

	public String getAccessChannel() 
	{
		return accessChannel;
	}
	public void setSourceExtInfo(String sourceExtInfo) 
	{
		this.sourceExtInfo = sourceExtInfo;
	}

	public String getSourceExtInfo() 
	{
		return sourceExtInfo;
	}
	public void setMemberId(String memberId) 
	{
		this.memberId = memberId;
	}

	public String getMemberId() 
	{
		return memberId;
	}
	public void setSourceSeqNo(String sourceSeqNo) 
	{
		this.sourceSeqNo = sourceSeqNo;
	}

	public String getSourceSeqNo() 
	{
		return sourceSeqNo;
	}
	public void setCmfSeqNo(String cmfSeqNo) 
	{
		this.cmfSeqNo = cmfSeqNo;
	}

	public String getCmfSeqNo() 
	{
		return cmfSeqNo;
	}
	public void setChannelExtInfo(String channelExtInfo) 
	{
		this.channelExtInfo = channelExtInfo;
	}

	public String getChannelExtInfo() 
	{
		return channelExtInfo;
	}
	public void setFailReason(String failReason) 
	{
		this.failReason = failReason;
	}

	public String getFailReason() 
	{
		return failReason;
	}
	public void setGmtCreate(Date gmtCreate) 
	{
		this.gmtCreate = gmtCreate;
	}

	public Date getGmtCreate() 
	{
		return gmtCreate;
	}
	public void setGmtModified(Date gmtModified) 
	{
		this.gmtModified = gmtModified;
	}

	public Date getGmtModified() 
	{
		return gmtModified;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("signReqId", getSignReqId())
            .append("fundChannelCode", getFundChannelCode())
            .append("cardNo", getCardNo())
            .append("targetInstCode", getTargetInstCode())
            .append("bankCardId", getBankCardId())
            .append("channelSignNo", getChannelSignNo())
            .append("channelSignTime", getChannelSignTime())
            .append("channelSignValid", getChannelSignValid())
            .append("status", getStatus())
            .append("signSmsSender", getSignSmsSender())
            .append("signSource", getSignSource())
            .append("payMode", getPayMode())
            .append("accessChannel", getAccessChannel())
            .append("sourceExtInfo", getSourceExtInfo())
            .append("memberId", getMemberId())
            .append("sourceSeqNo", getSourceSeqNo())
            .append("cmfSeqNo", getCmfSeqNo())
            .append("channelExtInfo", getChannelExtInfo())
            .append("failReason", getFailReason())
            .append("gmtCreate", getGmtCreate())
            .append("gmtModified", getGmtModified())
            .toString();
    }
}
