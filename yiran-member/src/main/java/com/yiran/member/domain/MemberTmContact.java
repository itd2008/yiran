package com.yiran.member.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.yiran.common.base.BaseEntity;
import java.util.Date;

/**
 * 联系人表 member_tm_contact
 * 
 * @author yiran
 * @date 2019-03-30
 */
public class MemberTmContact extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 主键ID */
	private Integer contactId;
	/** 联系信息所属对象ID */
	private String objectId;
	/** 联系人名称 */
	private String contactName;
	/** 联系人类型(0会员联系信息 1其他联系人) */
	private Integer contactType;
	/** 国家,区划码 */
	private String country;
	/** 省份,区划码 */
	private String province;
	/** 城市,区划码 */
	private String city;
	/** 城镇/区/街 */
	private String town;
	/** 地址 */
	private String address;
	/** 邮政编码 */
	private String postcode;
	/** 网站 */
	private String website;
	/** 传真 */
	private String fax;
	/** 手机 */
	private String mobile;
	/** 电话 */
	private String tel;
	/** 邮箱 */
	private String email;
	/** QQ */
	private String qq;
	/** 微信 */
	private String wechart;
	/** 职位 */
	private String position;
	/** 部门 */
	private String dept;
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
	/** 0 无效，1有效 */
	private Integer status;

	public void setContactId(Integer contactId) 
	{
		this.contactId = contactId;
	}

	public Integer getContactId() 
	{
		return contactId;
	}
	public void setObjectId(String objectId) 
	{
		this.objectId = objectId;
	}

	public String getObjectId() 
	{
		return objectId;
	}
	public void setContactName(String contactName) 
	{
		this.contactName = contactName;
	}

	public String getContactName() 
	{
		return contactName;
	}
	public void setContactType(Integer contactType) 
	{
		this.contactType = contactType;
	}

	public Integer getContactType() 
	{
		return contactType;
	}
	public void setCountry(String country) 
	{
		this.country = country;
	}

	public String getCountry() 
	{
		return country;
	}
	public void setProvince(String province) 
	{
		this.province = province;
	}

	public String getProvince() 
	{
		return province;
	}
	public void setCity(String city) 
	{
		this.city = city;
	}

	public String getCity() 
	{
		return city;
	}
	public void setTown(String town) 
	{
		this.town = town;
	}

	public String getTown() 
	{
		return town;
	}
	public void setAddress(String address) 
	{
		this.address = address;
	}

	public String getAddress() 
	{
		return address;
	}
	public void setPostcode(String postcode) 
	{
		this.postcode = postcode;
	}

	public String getPostcode() 
	{
		return postcode;
	}
	public void setWebsite(String website) 
	{
		this.website = website;
	}

	public String getWebsite() 
	{
		return website;
	}
	public void setFax(String fax) 
	{
		this.fax = fax;
	}

	public String getFax() 
	{
		return fax;
	}
	public void setMobile(String mobile) 
	{
		this.mobile = mobile;
	}

	public String getMobile() 
	{
		return mobile;
	}
	public void setTel(String tel) 
	{
		this.tel = tel;
	}

	public String getTel() 
	{
		return tel;
	}
	public void setEmail(String email) 
	{
		this.email = email;
	}

	public String getEmail() 
	{
		return email;
	}
	public void setQq(String qq) 
	{
		this.qq = qq;
	}

	public String getQq() 
	{
		return qq;
	}
	public void setWechart(String wechart) 
	{
		this.wechart = wechart;
	}

	public String getWechart() 
	{
		return wechart;
	}
	public void setPosition(String position) 
	{
		this.position = position;
	}

	public String getPosition() 
	{
		return position;
	}
	public void setDept(String dept) 
	{
		this.dept = dept;
	}

	public String getDept() 
	{
		return dept;
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
	public void setStatus(Integer status) 
	{
		this.status = status;
	}

	public Integer getStatus() 
	{
		return status;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("contactId", getContactId())
            .append("objectId", getObjectId())
            .append("contactName", getContactName())
            .append("contactType", getContactType())
            .append("country", getCountry())
            .append("province", getProvince())
            .append("city", getCity())
            .append("town", getTown())
            .append("address", getAddress())
            .append("postcode", getPostcode())
            .append("website", getWebsite())
            .append("fax", getFax())
            .append("mobile", getMobile())
            .append("tel", getTel())
            .append("email", getEmail())
            .append("qq", getQq())
            .append("wechart", getWechart())
            .append("position", getPosition())
            .append("dept", getDept())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("createUser", getCreateUser())
            .append("updateUser", getUpdateUser())
            .append("memo", getMemo())
            .append("status", getStatus())
            .toString();
    }
}
