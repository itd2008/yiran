package com.yiran.member.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.yiran.common.base.BaseEntity;
import java.util.Date;

/**
 * 配置表 member_td_config
 * 
 * @author yiran
 * @date 2019-03-30
 */
public class MemberTdConfig extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 主键ID */
	private Integer id;
	/** 配置类型 */
	private String type;
	/** 配置子类型 */
	private String subType;
	/** 配置值 */
	private String value;
	/** 状态(0不可用 1可用) */
	private Integer status;
	/** 类型描述 */
	private String typeDesc;
	/** 值描述 */
	private String valueDesc;
	/** 顺序号 */
	private Integer orderNo;
	/** 建立时间 */
	private Date createTime;
	/** 更新时间 */
	private Date updateTime;
	/** 建立人 */
	private String createUser;
	/** 更新人 */
	private String updateUser;
	/** 备注信息 */
	private String memo;
	/** 扩展字段1 */
	private String ext1;
	/** 扩展字段2 */
	private String ext2;
	/** 扩展字段3 */
	private String ext3;

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setType(String type) 
	{
		this.type = type;
	}

	public String getType() 
	{
		return type;
	}
	public void setSubType(String subType) 
	{
		this.subType = subType;
	}

	public String getSubType() 
	{
		return subType;
	}
	public void setValue(String value) 
	{
		this.value = value;
	}

	public String getValue() 
	{
		return value;
	}
	public void setStatus(Integer status) 
	{
		this.status = status;
	}

	public Integer getStatus() 
	{
		return status;
	}
	public void setTypeDesc(String typeDesc) 
	{
		this.typeDesc = typeDesc;
	}

	public String getTypeDesc() 
	{
		return typeDesc;
	}
	public void setValueDesc(String valueDesc) 
	{
		this.valueDesc = valueDesc;
	}

	public String getValueDesc() 
	{
		return valueDesc;
	}
	public void setOrderNo(Integer orderNo) 
	{
		this.orderNo = orderNo;
	}

	public Integer getOrderNo() 
	{
		return orderNo;
	}
	public void setCreateTime(Date createTime) 
	{
		this.createTime = createTime;
	}

	public Date getCreateTime() 
	{
		return createTime;
	}
	public void setUpdateTime(Date updateTime) 
	{
		this.updateTime = updateTime;
	}

	public Date getUpdateTime() 
	{
		return updateTime;
	}
	public void setCreateUser(String createUser) 
	{
		this.createUser = createUser;
	}

	public String getCreateUser() 
	{
		return createUser;
	}
	public void setUpdateUser(String updateUser) 
	{
		this.updateUser = updateUser;
	}

	public String getUpdateUser() 
	{
		return updateUser;
	}
	public void setMemo(String memo) 
	{
		this.memo = memo;
	}

	public String getMemo() 
	{
		return memo;
	}
	public void setExt1(String ext1) 
	{
		this.ext1 = ext1;
	}

	public String getExt1() 
	{
		return ext1;
	}
	public void setExt2(String ext2) 
	{
		this.ext2 = ext2;
	}

	public String getExt2() 
	{
		return ext2;
	}
	public void setExt3(String ext3) 
	{
		this.ext3 = ext3;
	}

	public String getExt3() 
	{
		return ext3;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("type", getType())
            .append("subType", getSubType())
            .append("value", getValue())
            .append("status", getStatus())
            .append("typeDesc", getTypeDesc())
            .append("valueDesc", getValueDesc())
            .append("orderNo", getOrderNo())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("createUser", getCreateUser())
            .append("updateUser", getUpdateUser())
            .append("memo", getMemo())
            .append("ext1", getExt1())
            .append("ext2", getExt2())
            .append("ext3", getExt3())
            .toString();
    }
}
