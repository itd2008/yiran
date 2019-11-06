package com.yiran.wechat.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.yiran.common.base.BaseEntity;
import java.util.Date;

/**
 * 收货地址表 wechat_receiving_address
 * 
 * @author yiran
 * @date 2019-06-13
 */
public class WechatReceivingAddress extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** id */
	private Integer id;
	/** 用户编号 */
	private String userId;
	/** 收件人姓名 */
	private String realname;
	/** 联系电话 */
	private String telephone;
	/** 备用联系电话 */
	private String telphone2;
	/** 是否是默认地址，0表示否，1表示是 */
	private Integer isDefaultAddress;
	/** 省 */
	private String province;
	
	private String provinceCode;
	
	private String cityCode;
	
	private String areaCode;
	/** 市 */
	private String city;
	/** 区 */
	private String area;
	/** 详细地址 */
	private String detailedAddress;
	/** 邮政编码 */
	private String zip;
	/** 创建者 */
	private String createBy;
	/** 创建时间 */
	private Date createTime;
	/** 更新者 */
	private String updateBy;
	/** 更新时间 */
	private Date updateTime;

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setUserId(String userId) 
	{
		this.userId = userId;
	}

	public String getUserId() 
	{
		return userId;
	}
	public void setRealname(String realname) 
	{
		this.realname = realname;
	}

	public String getRealname() 
	{
		return realname;
	}
	public void setTelephone(String telephone) 
	{
		this.telephone = telephone;
	}

	public String getTelephone() 
	{
		return telephone;
	}
	public void setTelphone2(String telphone2) 
	{
		this.telphone2 = telphone2;
	}

	public String getTelphone2() 
	{
		return telphone2;
	}
	public void setIsDefaultAddress(Integer isDefaultAddress) 
	{
		this.isDefaultAddress = isDefaultAddress;
	}

	public Integer getIsDefaultAddress() 
	{
		return isDefaultAddress;
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
	public void setArea(String area) 
	{
		this.area = area;
	}

	public String getArea() 
	{
		return area;
	}
	public void setDetailedAddress(String detailedAddress) 
	{
		this.detailedAddress = detailedAddress;
	}

	public String getDetailedAddress() 
	{
		return detailedAddress;
	}
	public void setZip(String zip) 
	{
		this.zip = zip;
	}

	public String getZip() 
	{
		return zip;
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

    public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("realname", getRealname())
            .append("telephone", getTelephone())
            .append("telphone2", getTelphone2())
            .append("isDefaultAddress", getIsDefaultAddress())
            .append("province", getProvince())
            .append("city", getCity())
            .append("area", getArea())
            .append("detailedAddress", getDetailedAddress())
            .append("zip", getZip())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
