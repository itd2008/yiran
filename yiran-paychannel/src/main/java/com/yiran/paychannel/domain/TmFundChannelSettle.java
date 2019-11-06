package com.yiran.paychannel.domain;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import com.yiran.common.base.BaseEntity;
import java.util.Date;

/**
 * 资金渠道算表 tm_fund_channel_settle
 * 
 * @author yiran
 * @date 2019-04-19
 */
public class TmFundChannelSettle extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 结算ID */
	private Integer settleId;
	/** 资金源编码 */
	private String fundChannelCode;
	/** 合同号 */
	private String contractNo;
	/** 商户号 */
	private String merchantNo;
	/** 结算账号 */
	private String accountNo;
	/** 结算帐户名称 */
	private String accountName;
	/** 结算方式：S（单笔），B（批量） */
	private String settleMode;
	/** 结算周期，以天为单位的数字 */
	private Integer settleCycle;
	/** 清算文件获取支持模式：N（不支持），F（FTP），O（线上），U（线下） */
	private String clearingFileMode;
	/** 是否可用：Y（可用），N（不可用） */
	private String enable;
	/** 创建时间 */
	private Date gmtCreate;
	/** 最后修改时间 */
	private Date gmtModified;
	/** 备注 */
	private String memo;
	/** 扩展信息 */
	private String extension;

	public void setSettleId(Integer settleId) 
	{
		this.settleId = settleId;
	}

	public Integer getSettleId() 
	{
		return settleId;
	}
	public void setFundChannelCode(String fundChannelCode) 
	{
		this.fundChannelCode = fundChannelCode;
	}

	public String getFundChannelCode() 
	{
		return fundChannelCode;
	}
	public void setContractNo(String contractNo) 
	{
		this.contractNo = contractNo;
	}

	public String getContractNo() 
	{
		return contractNo;
	}
	public void setMerchantNo(String merchantNo) 
	{
		this.merchantNo = merchantNo;
	}

	public String getMerchantNo() 
	{
		return merchantNo;
	}
	public void setAccountNo(String accountNo) 
	{
		this.accountNo = accountNo;
	}

	public String getAccountNo() 
	{
		return accountNo;
	}
	public void setAccountName(String accountName) 
	{
		this.accountName = accountName;
	}

	public String getAccountName() 
	{
		return accountName;
	}
	public void setSettleMode(String settleMode) 
	{
		this.settleMode = settleMode;
	}

	public String getSettleMode() 
	{
		return settleMode;
	}
	public void setSettleCycle(Integer settleCycle) 
	{
		this.settleCycle = settleCycle;
	}

	public Integer getSettleCycle() 
	{
		return settleCycle;
	}
	public void setClearingFileMode(String clearingFileMode) 
	{
		this.clearingFileMode = clearingFileMode;
	}

	public String getClearingFileMode() 
	{
		return clearingFileMode;
	}
	public void setEnable(String enable) 
	{
		this.enable = enable;
	}

	public String getEnable() 
	{
		return enable;
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
	public void setMemo(String memo) 
	{
		this.memo = memo;
	}

	public String getMemo() 
	{
		return memo;
	}
	public void setExtension(String extension) 
	{
		this.extension = extension;
	}

	public String getExtension() 
	{
		return extension;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("settleId", getSettleId())
            .append("fundChannelCode", getFundChannelCode())
            .append("contractNo", getContractNo())
            .append("merchantNo", getMerchantNo())
            .append("accountNo", getAccountNo())
            .append("accountName", getAccountName())
            .append("settleMode", getSettleMode())
            .append("settleCycle", getSettleCycle())
            .append("clearingFileMode", getClearingFileMode())
            .append("enable", getEnable())
            .append("gmtCreate", getGmtCreate())
            .append("gmtModified", getGmtModified())
            .append("memo", getMemo())
            .append("extension", getExtension())
            .toString();
    }
}
