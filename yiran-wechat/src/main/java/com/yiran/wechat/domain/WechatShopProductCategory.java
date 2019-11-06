package com.yiran.wechat.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.yiran.common.base.BaseEntity;
import java.util.Date;

/**
 * 商品类目表 wechat_shop_product_category
 * 
 * @author yiran
 * @date 2019-06-13
 */
public class WechatShopProductCategory extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 商品类目主键 */
	private Integer id;
	/** 商品父类目id */
	private Integer pid;
	/** 商品类目名称 */
	private String name;
	/** 栏目的序号 */
	private Integer orders;
	/** 栏目的状态，0表示启用，1表示停止 */
	private Integer status;
	/** 栏目的类型 */
	private Integer type;
	
	/** 父名称 */
    private String parentName;

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setPid(Integer pid) 
	{
		this.pid = pid;
	}

	public Integer getPid() 
	{
		return pid;
	}
	public void setName(String name) 
	{
		this.name = name;
	}

	public String getName() 
	{
		return name;
	}
	public void setOrders(Integer orders) 
	{
		this.orders = orders;
	}

	public Integer getOrders() 
	{
		return orders;
	}
	public void setStatus(Integer status) 
	{
		this.status = status;
	}

	public Integer getStatus() 
	{
		return status;
	}
	public void setType(Integer type) 
	{
		this.type = type;
	}

	public Integer getType() 
	{
		return type;
	}

    public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("pid", getPid())
            .append("name", getName())
            .append("orders", getOrders())
            .append("status", getStatus())
            .append("type", getType())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
