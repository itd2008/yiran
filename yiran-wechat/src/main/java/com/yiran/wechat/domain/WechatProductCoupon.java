package com.yiran.wechat.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.yiran.common.base.BaseEntity;
import java.util.Date;

/**
 * 商品优惠券表 wechat_product_coupon
 * 
 * @author yiran
 * @date 2019-06-13
 */
public class WechatProductCoupon extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 自动编号 */
	private Integer productCouponId;
	/** 序列号 */
	private String serialNo;
	/** 优惠券名称 */
	private String name;
	/** 优惠券面值 */
	private String faceValue;
	/** 优惠券数量 */
	private String quantity;
	/**领取数量*/
	private String receiveNum;
	/**剩余数量*/
	private String residualQuantity;
	/** 使用条件 */
	private String conditionValue;
	/** 使用条件说明 */
	private String conditionDesc;
	/** 生效时间 */
	private Date startTime;
	/** 失效时间 */
	private Date endTime;
	/** 排列次序 */
	private String sort;
	/** 状态:0不可显示，1显示 */
	private String status;
	/** 创建时间 */
	private Date createTime;
	/**
	 * 是否获取 0未获取，1已经获取
	 */
	private String isGet;

	public void setProductCouponId(Integer productCouponId) 
	{
		this.productCouponId = productCouponId;
	}

	public Integer getProductCouponId() 
	{
		return productCouponId;
	}
	public void setSerialNo(String serialNo) 
	{
		this.serialNo = serialNo;
	}

	public String getSerialNo() 
	{
		return serialNo;
	}
	public void setName(String name) 
	{
		this.name = name;
	}

	public String getName() 
	{
		return name;
	}
	public void setFaceValue(String faceValue) 
	{
		this.faceValue = faceValue;
	}

	public String getFaceValue() 
	{
		return faceValue;
	}
	public void setQuantity(String quantity) 
	{
		this.quantity = quantity;
	}

	public String getQuantity() 
	{
		return quantity;
	}
	public void setConditionValue(String conditionValue) 
	{
		this.conditionValue = conditionValue;
	}

	public String getConditionValue() 
	{
		return conditionValue;
	}
	public void setConditionDesc(String conditionDesc) 
	{
		this.conditionDesc = conditionDesc;
	}

	public String getConditionDesc() 
	{
		return conditionDesc;
	}
	public void setStartTime(Date startTime) 
	{
		this.startTime = startTime;
	}

	public Date getStartTime() 
	{
		return startTime;
	}
	public void setEndTime(Date endTime) 
	{
		this.endTime = endTime;
	}

	public Date getEndTime() 
	{
		return endTime;
	}
	public void setSort(String sort) 
	{
		this.sort = sort;
	}

	public String getSort() 
	{
		return sort;
	}
	public void setStatus(String status) 
	{
		this.status = status;
	}

	public String getStatus() 
	{
		return status;
	}
	public void setCreateTime(Date createTime) 
	{
		this.createTime = createTime;
	}

	public Date getCreateTime() 
	{
		return createTime;
	}

    public String getReceiveNum() {
		return receiveNum;
	}

	public void setReceiveNum(String receiveNum) {
		this.receiveNum = receiveNum;
	}

	public String getResidualQuantity() {
		return residualQuantity;
	}

	public void setResidualQuantity(String residualQuantity) {
		this.residualQuantity = residualQuantity;
	}

	public String getIsGet() {
		return isGet;
	}

	public void setIsGet(String isGet) {
		this.isGet = isGet;
	}

	public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("productCouponId", getProductCouponId())
            .append("serialNo", getSerialNo())
            .append("name", getName())
            .append("faceValue", getFaceValue())
            .append("quantity", getQuantity())
            .append("receiveNum", getReceiveNum())
            .append("residualQuantity", getResidualQuantity())
            .append("conditionValue", getConditionValue())
            .append("conditionDesc", getConditionDesc())
            .append("startTime", getStartTime())
            .append("endTime", getEndTime())
            .append("sort", getSort())
            .append("status", getStatus())
            .append("createTime", getCreateTime())
            .toString();
    }
}
