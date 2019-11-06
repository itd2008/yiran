package com.yiran.wechat.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.yiran.common.base.BaseEntity;
import java.util.Date;

/**
 * 商品表 wechat_product
 * 
 * @author yiran
 * @date 2019-06-13
 */
public class WechatProduct 
{
	private static final long serialVersionUID = 1L;
	/** 父ID */
    private Integer pid;
	/** 自动编号 */
	private Integer productId;
	/** 商品名称 */
	private String productName;
	/** 商品型号 */
	private String marque;
	/** 仓库条码 */
	private String barcode;
	/** 类型编号 */
	private String typeId;
	/** 类别编号 */
	private String categoryId;
	/** 品牌编号 */
	private String brandId;
	/** 商品价格 */
	private String price;
	/** 市场价格 */
	private String marketPrice;
	/** 成本价格: 进货价格 */
	private String costPrice;
	/**  库存量 */
	private String stock;
	/** 告警库存 */
	private String warningStock;
	/** 商品积分 */
	private String integral;
	/** 商品图片url */
	private String pictureUrl;
	/** 状态:0下架,1上架,2预售 */
	private String status;
	/** 创建时间 */
	private Date createTime;
	private Date updateTime;
	/**
	 * 属性
	 */
	private String attrs;

	/**
	 * 类目
	 */
	private WechatShopProductCategory productCategory;
	/**
	 * 商品明细
	 */
	private WechatProductDescription wechatProductDescription;
	/**
	 * 详情
	 */
	private String description;
	/**
	 * 规格和数量 价格
	 */
	private String specificationPriceAndQuantity;
	/**
	 * 商品类型
	 */
	private String productType;
	/**
	 * 是否预售
	 */
	private String isAdvanceSale;
	/**
	 * 发货时间承诺
	 */
	private String deliveryTime;
	/**
	 * 运费模板ID
	 */
	private String freightTemplateId;
	/**
	 * 7天无理由退换货
	 */
	private String free7days;
	/**
	 * 假一赔十
	 */
	private String oneFalsePaysTen;
	/**
	 * 折扣
	 */
	private String discount;
	/**
	 * 折扣价
	 */
	private String discountPrice;
	/**
	 * 商品数量
	 */
	private String inventory;
	/**
	 * 规格IDs
	 */
	private String specificationIds;
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 品牌名
	 */
	private String brandName;
	
	private String p1;
	private String p2;
	private String p3;
	private String p4;
	private String p5;
	private String p6;
	private String p7;
	private String p8;
	private String p9;
	private String p10;
	
	public WechatShopProductCategory getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(WechatShopProductCategory productCategory) {
		this.productCategory = productCategory;
	}

	public void setProductId(Integer productId) 
	{
		this.productId = productId;
	}

	public Integer getProductId() 
	{
		return productId;
	}
	public void setProductName(String productName) 
	{
		this.productName = productName;
	}

	public String getProductName() 
	{
		return productName;
	}
	public void setMarque(String marque) 
	{
		this.marque = marque;
	}

	public String getMarque() 
	{
		return marque;
	}
	public void setBarcode(String barcode) 
	{
		this.barcode = barcode;
	}

	public String getBarcode() 
	{
		return barcode;
	}
	public void setTypeId(String typeId) 
	{
		this.typeId = typeId;
	}

	public String getTypeId() 
	{
		return typeId;
	}
	public void setCategoryId(String categoryId) 
	{
		this.categoryId = categoryId;
	}

	public String getCategoryId() 
	{
		return categoryId;
	}
	public void setBrandId(String brandId) 
	{
		this.brandId = brandId;
	}

	public String getBrandId() 
	{
		return brandId;
	}
	public void setPrice(String price) 
	{
		this.price = price;
	}

	public String getPrice() 
	{
		return price;
	}
	public void setMarketPrice(String marketPrice) 
	{
		this.marketPrice = marketPrice;
	}

	public String getMarketPrice() 
	{
		return marketPrice;
	}
	public void setCostPrice(String costPrice) 
	{
		this.costPrice = costPrice;
	}

	public String getCostPrice() 
	{
		return costPrice;
	}
	public void setStock(String stock) 
	{
		this.stock = stock;
	}

	public String getStock() 
	{
		return stock;
	}
	public void setWarningStock(String warningStock) 
	{
		this.warningStock = warningStock;
	}

	public String getWarningStock() 
	{
		return warningStock;
	}
	public void setIntegral(String integral) 
	{
		this.integral = integral;
	}

	public String getIntegral() 
	{
		return integral;
	}
	public void setPictureUrl(String pictureUrl) 
	{
		this.pictureUrl = pictureUrl;
	}

	public String getPictureUrl() 
	{
		return pictureUrl;
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

    public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}
	

	public WechatProductDescription getWechatProductDescription() {
		return wechatProductDescription;
	}

	public void setWechatProductDescription(WechatProductDescription wechatProductDescription) {
		this.wechatProductDescription = wechatProductDescription;
	}
	

	public String getAttrs() {
		return attrs;
	}

	public void setAttrs(String attrs) {
		this.attrs = attrs;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	

	public String getSpecificationPriceAndQuantity() {
		return specificationPriceAndQuantity;
	}

	public void setSpecificationPriceAndQuantity(String specificationPriceAndQuantity) {
		this.specificationPriceAndQuantity = specificationPriceAndQuantity;
	}
	
	

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getIsAdvanceSale() {
		return isAdvanceSale;
	}

	public void setIsAdvanceSale(String isAdvanceSale) {
		this.isAdvanceSale = isAdvanceSale;
	}

	public String getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(String deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public String getFreightTemplateId() {
		return freightTemplateId;
	}

	public void setFreightTemplateId(String freightTemplateId) {
		this.freightTemplateId = freightTemplateId;
	}

	public String getFree7days() {
		return free7days;
	}

	public void setFree7days(String free7days) {
		this.free7days = free7days;
	}

	public String getOneFalsePaysTen() {
		return oneFalsePaysTen;
	}

	public void setOneFalsePaysTen(String oneFalsePaysTen) {
		this.oneFalsePaysTen = oneFalsePaysTen;
	}

	
	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public String getDiscountPrice() {
		return discountPrice;
	}

	public void setDiscountPrice(String discountPrice) {
		this.discountPrice = discountPrice;
	}

	public String getInventory() {
		return inventory;
	}

	public void setInventory(String inventory) {
		this.inventory = inventory;
	}

	
	public String getSpecificationIds() {
		return specificationIds;
	}

	public void setSpecificationIds(String specificationIds) {
		this.specificationIds = specificationIds;
	}

	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	
	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	

	public String getP1() {
		return p1;
	}

	public void setP1(String p1) {
		this.p1 = p1;
	}

	public String getP2() {
		return p2;
	}

	public void setP2(String p2) {
		this.p2 = p2;
	}

	public String getP3() {
		return p3;
	}

	public void setP3(String p3) {
		this.p3 = p3;
	}

	public String getP4() {
		return p4;
	}

	public void setP4(String p4) {
		this.p4 = p4;
	}

	public String getP5() {
		return p5;
	}

	public void setP5(String p5) {
		this.p5 = p5;
	}

	public String getP6() {
		return p6;
	}

	public void setP6(String p6) {
		this.p6 = p6;
	}

	public String getP7() {
		return p7;
	}

	public void setP7(String p7) {
		this.p7 = p7;
	}

	public String getP8() {
		return p8;
	}

	public void setP8(String p8) {
		this.p8 = p8;
	}

	public String getP9() {
		return p9;
	}

	public void setP9(String p9) {
		this.p9 = p9;
	}

	public String getP10() {
		return p10;
	}

	public void setP10(String p10) {
		this.p10 = p10;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("productId", getProductId())
            .append("productName", getProductName())
            .append("title", getTitle())
            .append("marque", getMarque())
            .append("barcode", getBarcode())
            .append("typeId", getTypeId())
            .append("categoryId", getCategoryId())
            .append("attrs", getAttrs())
            .append("brandId", getBrandId())
            .append("price", getPrice())
            .append("marketPrice", getMarketPrice())
            .append("costPrice", getCostPrice())
            .append("stock", getStock())
            .append("warningStock", getWarningStock())
            .append("integral", getIntegral())
            .append("pictureUrl", getPictureUrl())
            .append("status", getStatus())
            .append("createTime", getCreateTime())
            .append("pid", getPid())
            .append("productCategory", getProductCategory())
            .append("wechatProductDescription", getWechatProductDescription())
            .append("description", getDescription())
            .append("specificationPriceAndQuantity", getSpecificationPriceAndQuantity())
            .append("productType", getProductType())
            .append("isAdvanceSale", getIsAdvanceSale())
            .append("deliveryTime", getDeliveryTime())
            .append("freightTemplateId", getFreightTemplateId())
            .append("free7days", getFree7days())
            .append("oneFalsePaysTen", getOneFalsePaysTen())
            .append("discount", getDiscount())
            .append("discountPrice", getDiscountPrice())
            .append("inventory", getInventory())
            .append("specificationIds", getSpecificationIds())
            .append("brandName", getBrandName())
            .append("p1", getP1())
            .append("p2", getP2())
            .append("p3", getP3())
            .append("p4", getP4())
            .append("p5", getP5())
            .append("p6", getP6())
            .append("p7", getP7())
            .append("p8", getP8())
            .append("p9", getP9())
            .append("p10", getP10())
            .toString();
    }
}
