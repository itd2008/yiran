package com.yiran.wechat.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.yiran.common.base.BaseEntity;
import java.util.Date;

/**
 * 商品进销存表 wechat_product_inventory
 * 
 * @author yiran
 * @date 2019-06-25
 */
public class WechatProductInventory extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 自动编号 */
	private Integer productInventoryId;
	/** 商品编号 */
	private Integer productId;
	/** 出入库数量 */
	private String quantity;
	/** 单价  */
	private String price;
	/** 小计金额 */
	private String subtotal;
	/** 旧库存 */
	private String stockOld;
	/** 新库存 */
	private String stockNew;
	/** 出入库方向 1:出库;2:入库 */
	private String direction;
	/** 出入库类型：SELL-RETURN=销售退货(入库),PURCHASE=采购入库,SALE=销售出库,PURCHASE-RETURN=采购退货(出库),INTERNAL=内部出库,INVENTORY=盘点调仓 */
	private String operateType;
	/** 订单编号 */
	private String orderId;
	/** 供应商编号 */
	private String providerId;
	/**  备注信息 */
	private String remark;
	/** 经手人用户编号:内部出库经手人、销售卖家、退货入库仓管员、进货入库采购员或仓管员、盘点调仓财务员或仓管员 */
	private String userId;
	/** 审批人员用户编号 */
	private String auditorUid;
	/** 出入库结果 */
	private String directionResult;
	/** 出入库时间 */
	private Date createTime;

	public void setProductInventoryId(Integer productInventoryId) 
	{
		this.productInventoryId = productInventoryId;
	}

	public Integer getProductInventoryId() 
	{
		return productInventoryId;
	}
	public void setProductId(Integer productId) 
	{
		this.productId = productId;
	}

	public Integer getProductId() 
	{
		return productId;
	}
	public void setQuantity(String quantity) 
	{
		this.quantity = quantity;
	}

	public String getQuantity() 
	{
		return quantity;
	}
	public void setPrice(String price) 
	{
		this.price = price;
	}

	public String getPrice() 
	{
		return price;
	}
	public void setSubtotal(String subtotal) 
	{
		this.subtotal = subtotal;
	}

	public String getSubtotal() 
	{
		return subtotal;
	}
	public void setStockOld(String stockOld) 
	{
		this.stockOld = stockOld;
	}

	public String getStockOld() 
	{
		return stockOld;
	}
	public void setStockNew(String stockNew) 
	{
		this.stockNew = stockNew;
	}

	public String getStockNew() 
	{
		return stockNew;
	}
	public void setDirection(String direction) 
	{
		this.direction = direction;
	}

	public String getDirection() 
	{
		return direction;
	}
	public void setOperateType(String operateType) 
	{
		this.operateType = operateType;
	}

	public String getOperateType() 
	{
		return operateType;
	}
	public void setOrderId(String orderId) 
	{
		this.orderId = orderId;
	}

	public String getOrderId() 
	{
		return orderId;
	}
	public void setProviderId(String providerId) 
	{
		this.providerId = providerId;
	}

	public String getProviderId() 
	{
		return providerId;
	}
	public void setRemark(String remark) 
	{
		this.remark = remark;
	}

	public String getRemark() 
	{
		return remark;
	}
	public void setUserId(String userId) 
	{
		this.userId = userId;
	}

	public String getUserId() 
	{
		return userId;
	}
	public void setAuditorUid(String auditorUid) 
	{
		this.auditorUid = auditorUid;
	}

	public String getAuditorUid() 
	{
		return auditorUid;
	}
	public void setDirectionResult(String directionResult) 
	{
		this.directionResult = directionResult;
	}

	public String getDirectionResult() 
	{
		return directionResult;
	}
	public void setCreateTime(Date createTime) 
	{
		this.createTime = createTime;
	}

	public Date getCreateTime() 
	{
		return createTime;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("productInventoryId", getProductInventoryId())
            .append("productId", getProductId())
            .append("quantity", getQuantity())
            .append("price", getPrice())
            .append("subtotal", getSubtotal())
            .append("stockOld", getStockOld())
            .append("stockNew", getStockNew())
            .append("direction", getDirection())
            .append("operateType", getOperateType())
            .append("orderId", getOrderId())
            .append("providerId", getProviderId())
            .append("remark", getRemark())
            .append("userId", getUserId())
            .append("auditorUid", getAuditorUid())
            .append("directionResult", getDirectionResult())
            .append("createTime", getCreateTime())
            .toString();
    }
}
