package com.yiran.wechat.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.yiran.common.base.BaseEntity;
import java.util.Date;

/**
 * 省份设置表 bs_province
 * 
 * @author yiran
 * @date 2019-07-06
 */
public class BsProvince extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 自增列 */
	private Integer provinceId;
	/** 省份代码 */
	private String provinceCode;
	/** 省份名称 */
	private String provinceName;
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

	public void setProvinceId(Integer provinceId) 
	{
		this.provinceId = provinceId;
	}

	public Integer getProvinceId() 
	{
		return provinceId;
	}
	public void setProvinceCode(String provinceCode) 
	{
		this.provinceCode = provinceCode;
	}

	public String getProvinceCode() 
	{
		return provinceCode;
	}
	public void setProvinceName(String provinceName) 
	{
		this.provinceName = provinceName;
	}

	public String getProvinceName() 
	{
		return provinceName;
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
            .append("provinceId", getProvinceId())
            .append("provinceCode", getProvinceCode())
            .append("provinceName", getProvinceName())
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
