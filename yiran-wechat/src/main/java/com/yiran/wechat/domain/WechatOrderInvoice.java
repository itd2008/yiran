package com.yiran.wechat.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.yiran.common.base.BaseEntity;
import java.util.Date;

/**
 * 订单发票表 wechat_order_invoice
 * 
 * @author yiran
 * @date 2019-06-13
 */
public class WechatOrderInvoice extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 自动编号 */
	private Integer invoiceId;
	/** 订单编号 */
	private Integer orderId;
	/** 是否增值税发票:0普通发票,1增值发票 */
	private Integer isVat;
	/** 发票抬头名称 */
	private String invoiceTitle;
	/** 发票抬头内容 */
	private String invoiceContent;
	/** 发票金额 */
	private String invoiceAmount;
	/** 发票税号 */
	private String invoiceTaxNo;
	/** 开票税金 */
	private String invoiceTax;
	/** 公司名称[增值税] */
	private String vatCompanyName;
	/** 公司地址[增值税] */
	private String vatCompanyAddress;
	/** 联系电话[增值税] */
	private String vatTelphone;
	/** 开户银行[增值税] */
	private String vatBankName;
	/** 银行帐号[增值税] */
	private String vatBankAccount;
	/** 订单结算时间 */
	private String orderSettlementTime;
	/** 创建者 */
	private String createBy;
	/** 创建时间 */
	private Date createTime;
	/** 更新者 */
	private String updateBy;
	/** 更新时间 */
	private Date updateTime;

	public void setInvoiceId(Integer invoiceId) 
	{
		this.invoiceId = invoiceId;
	}

	public Integer getInvoiceId() 
	{
		return invoiceId;
	}
	public void setOrderId(Integer orderId) 
	{
		this.orderId = orderId;
	}

	public Integer getOrderId() 
	{
		return orderId;
	}
	public void setIsVat(Integer isVat) 
	{
		this.isVat = isVat;
	}

	public Integer getIsVat() 
	{
		return isVat;
	}
	public void setInvoiceTitle(String invoiceTitle) 
	{
		this.invoiceTitle = invoiceTitle;
	}

	public String getInvoiceTitle() 
	{
		return invoiceTitle;
	}
	public void setInvoiceContent(String invoiceContent) 
	{
		this.invoiceContent = invoiceContent;
	}

	public String getInvoiceContent() 
	{
		return invoiceContent;
	}
	public void setInvoiceAmount(String invoiceAmount) 
	{
		this.invoiceAmount = invoiceAmount;
	}

	public String getInvoiceAmount() 
	{
		return invoiceAmount;
	}
	public void setInvoiceTaxNo(String invoiceTaxNo) 
	{
		this.invoiceTaxNo = invoiceTaxNo;
	}

	public String getInvoiceTaxNo() 
	{
		return invoiceTaxNo;
	}
	public void setInvoiceTax(String invoiceTax) 
	{
		this.invoiceTax = invoiceTax;
	}

	public String getInvoiceTax() 
	{
		return invoiceTax;
	}
	public void setVatCompanyName(String vatCompanyName) 
	{
		this.vatCompanyName = vatCompanyName;
	}

	public String getVatCompanyName() 
	{
		return vatCompanyName;
	}
	public void setVatCompanyAddress(String vatCompanyAddress) 
	{
		this.vatCompanyAddress = vatCompanyAddress;
	}

	public String getVatCompanyAddress() 
	{
		return vatCompanyAddress;
	}
	public void setVatTelphone(String vatTelphone) 
	{
		this.vatTelphone = vatTelphone;
	}

	public String getVatTelphone() 
	{
		return vatTelphone;
	}
	public void setVatBankName(String vatBankName) 
	{
		this.vatBankName = vatBankName;
	}

	public String getVatBankName() 
	{
		return vatBankName;
	}
	public void setVatBankAccount(String vatBankAccount) 
	{
		this.vatBankAccount = vatBankAccount;
	}

	public String getVatBankAccount() 
	{
		return vatBankAccount;
	}
	public void setOrderSettlementTime(String orderSettlementTime) 
	{
		this.orderSettlementTime = orderSettlementTime;
	}

	public String getOrderSettlementTime() 
	{
		return orderSettlementTime;
	}
	public void setCreateBy(String createBy) 
	{
		this.createBy = createBy;
	}

	public String getCreateBy() 
	{
		return createBy;
	}
	public void setCreateTime(Date createTime) 
	{
		this.createTime = createTime;
	}

	public Date getCreateTime() 
	{
		return createTime;
	}
	public void setUpdateBy(String updateBy) 
	{
		this.updateBy = updateBy;
	}

	public String getUpdateBy() 
	{
		return updateBy;
	}
	public void setUpdateTime(Date updateTime) 
	{
		this.updateTime = updateTime;
	}

	public Date getUpdateTime() 
	{
		return updateTime;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("invoiceId", getInvoiceId())
            .append("orderId", getOrderId())
            .append("isVat", getIsVat())
            .append("invoiceTitle", getInvoiceTitle())
            .append("invoiceContent", getInvoiceContent())
            .append("invoiceAmount", getInvoiceAmount())
            .append("invoiceTaxNo", getInvoiceTaxNo())
            .append("invoiceTax", getInvoiceTax())
            .append("vatCompanyName", getVatCompanyName())
            .append("vatCompanyAddress", getVatCompanyAddress())
            .append("vatTelphone", getVatTelphone())
            .append("vatBankName", getVatBankName())
            .append("vatBankAccount", getVatBankAccount())
            .append("orderSettlementTime", getOrderSettlementTime())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
