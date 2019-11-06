package com.yiran.wechat.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.yiran.common.base.BaseEntity;
import java.util.Date;

/**
 * 地区设置表 bs_area
 * 
 * @author yiran
 * @date 2019-07-06
 */
public class BsArea extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 自增列 */
	private Integer areaId;
	/** 区代码 */
	private String areaCode;
	/** 父级市代码 */
	private String cityCode;
	/** 市名称 */
	private String areaName;
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

	public void setAreaId(Integer areaId) 
	{
		this.areaId = areaId;
	}

	public Integer getAreaId() 
	{
		return areaId;
	}
	public void setAreaCode(String areaCode) 
	{
		this.areaCode = areaCode;
	}

	public String getAreaCode() 
	{
		return areaCode;
	}
	public void setCityCode(String cityCode) 
	{
		this.cityCode = cityCode;
	}

	public String getCityCode() 
	{
		return cityCode;
	}
	public void setAreaName(String areaName) 
	{
		this.areaName = areaName;
	}

	public String getAreaName() 
	{
		return areaName;
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
            .append("areaId", getAreaId())
            .append("areaCode", getAreaCode())
            .append("cityCode", getCityCode())
            .append("areaName", getAreaName())
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
