package com.yiran.wechat.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.yiran.common.base.BaseEntity;

/**
 * 商品统计表 wechat_product_statistics
 * 
 * @author yiran
 * @date 2019-06-13
 */
public class WechatProductStatistics extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 商品编号 */
	private Integer productId;
	/** 浏览次数 */
	private String visitCount;
	/** 评论次数 */
	private String replyCount;
	/** 销售总量 */
	private String saleQuantity;
	/** 销售总额 */
	private String saleAmount;
	/** 进货总量 */
	private String purchaseQuantity;
	/** 进货总额 */
	private String purchaseAmount;
	/** 成本均价 */
	private String costPrice;
	/** 毛利润金额 */
	private String grossProfit;

	public void setProductId(Integer productId) 
	{
		this.productId = productId;
	}

	public Integer getProductId() 
	{
		return productId;
	}
	public void setVisitCount(String visitCount) 
	{
		this.visitCount = visitCount;
	}

	public String getVisitCount() 
	{
		return visitCount;
	}
	public void setReplyCount(String replyCount) 
	{
		this.replyCount = replyCount;
	}

	public String getReplyCount() 
	{
		return replyCount;
	}
	public void setSaleQuantity(String saleQuantity) 
	{
		this.saleQuantity = saleQuantity;
	}

	public String getSaleQuantity() 
	{
		return saleQuantity;
	}
	public void setSaleAmount(String saleAmount) 
	{
		this.saleAmount = saleAmount;
	}

	public String getSaleAmount() 
	{
		return saleAmount;
	}
	public void setPurchaseQuantity(String purchaseQuantity) 
	{
		this.purchaseQuantity = purchaseQuantity;
	}

	public String getPurchaseQuantity() 
	{
		return purchaseQuantity;
	}
	public void setPurchaseAmount(String purchaseAmount) 
	{
		this.purchaseAmount = purchaseAmount;
	}

	public String getPurchaseAmount() 
	{
		return purchaseAmount;
	}
	public void setCostPrice(String costPrice) 
	{
		this.costPrice = costPrice;
	}

	public String getCostPrice() 
	{
		return costPrice;
	}
	public void setGrossProfit(String grossProfit) 
	{
		this.grossProfit = grossProfit;
	}

	public String getGrossProfit() 
	{
		return grossProfit;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("productId", getProductId())
            .append("visitCount", getVisitCount())
            .append("replyCount", getReplyCount())
            .append("saleQuantity", getSaleQuantity())
            .append("saleAmount", getSaleAmount())
            .append("purchaseQuantity", getPurchaseQuantity())
            .append("purchaseAmount", getPurchaseAmount())
            .append("costPrice", getCostPrice())
            .append("grossProfit", getGrossProfit())
            .toString();
    }
}
