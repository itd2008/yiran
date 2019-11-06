package com.yiran.wechat.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.yiran.common.base.BaseEntity;
import java.util.Date;

/**
 * 街道设置表 bs_street
 * 
 * @author yiran
 * @date 2019-07-06
 */
public class BsStreet extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 自增列 */
	private Integer streetId;
	/** 街道代码 */
	private String streetCode;
	/** 父级区代码 */
	private String areaCode;
	/** 街道名称 */
	private String streetName;
	/** 简称 */
	private String shortName;
	/** 经度 */
	private String lng;
	/** 纬度 */
	private String lat;
	/** 排序 */
	private Integer sort;
	/** 创建时间 */
	private Date gmtCreate;
	/** 修改时间 */
	private Date gmtModified;
	/** 备注 */
	private String memo;
	/** 状态 */
	private Integer dataState;
	/** 租户ID */
	private String tenantCode;

	public void setStreetId(Integer streetId) 
	{
		this.streetId = streetId;
	}

	public Integer getStreetId() 
	{
		return streetId;
	}
	public void setStreetCode(String streetCode) 
	{
		this.streetCode = streetCode;
	}

	public String getStreetCode() 
	{
		return streetCode;
	}
	public void setAreaCode(String areaCode) 
	{
		this.areaCode = areaCode;
	}

	public String getAreaCode() 
	{
		return areaCode;
	}
	public void setStreetName(String streetName) 
	{
		this.streetName = streetName;
	}

	public String getStreetName() 
	{
		return streetName;
	}
	public void setShortName(String shortName) 
	{
		this.shortName = shortName;
	}

	public String getShortName() 
	{
		return shortName;
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
	public void setDataState(Integer dataState) 
	{
		this.dataState = dataState;
	}

	public Integer getDataState() 
	{
		return dataState;
	}
	public void setTenantCode(String tenantCode) 
	{
		this.tenantCode = tenantCode;
	}

	public String getTenantCode() 
	{
		return tenantCode;
	}

    public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("streetId", getStreetId())
            .append("streetCode", getStreetCode())
            .append("areaCode", getAreaCode())
            .append("streetName", getStreetName())
            .append("shortName", getShortName())
            .append("lNG", getLng())
            .append("lAT", getLat())
            .append("sORT", getSort())
            .append("gmtCreate", getGmtCreate())
            .append("gmtModified", getGmtModified())
            .append("mEMO", getMemo())
            .append("dataState", getDataState())
            .append("tenantCode", getTenantCode())
            .toString();
    }
}
