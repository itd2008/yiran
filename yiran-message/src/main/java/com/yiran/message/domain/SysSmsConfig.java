package com.yiran.message.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.yiran.common.base.BaseEntity;
import java.util.Date;

/**
 * 短信基本设置表 sys_sms_config
 * 
 * @author yiran
 * @date 2019-03-08
 */
public class SysSmsConfig extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	//短信秘钥
	public static String KEY_SMS_APPSECRET="SMS.APPSECRET";
	//短信appID
	public static String KEY_SMS_APPID="SMS.APPID";
	//短信API_URL
	public static String KEY_SMS_API_URL="SMS.API.URL";
	//短信有效时间 单位分钟
	public static String KEY_SMS_EFFECTIVE_TIME ="SMS.EFFECTIVE.TIME";
	//短信限制次数
	public static String KEY_SMS_LIMIT_TIMES ="SMS.LIMIT.TIMES";
	//短信签名
	public static String KEY_SMS_SHORT_SIGNATURE ="SMS.SHORT.SIGNATURE";
	/** 主键 */
	private Integer id;
	/** 名称 */
	private String name;
	/** 键 */
	private String smsKey;
	/** 值 */
	private String smsValue;
	/** 创建者 */
	private String createBy;
	/** 创建时间 */
	private Date createTime;
	/** 更新者 */
	private String updateBy;
	/** 更新时间 */
	private Date updateTime;
	/** 备注 */
	private String remark;
	/** 删除标志（0代表存在 2代表删除） */
	private String delFlag;

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setName(String name) 
	{
		this.name = name;
	}

	public String getName() 
	{
		return name;
	}
	public void setSmsKey(String smsKey) 
	{
		this.smsKey = smsKey;
	}

	public String getSmsKey() 
	{
		return smsKey;
	}
	public void setSmsValue(String smsValue) 
	{
		this.smsValue = smsValue;
	}

	public String getSmsValue() 
	{
		return smsValue;
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
	public void setRemark(String remark) 
	{
		this.remark = remark;
	}

	public String getRemark() 
	{
		return remark;
	}
	public void setDelFlag(String delFlag) 
	{
		this.delFlag = delFlag;
	}

	public String getDelFlag() 
	{
		return delFlag;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("smsKey", getSmsKey())
            .append("smsValue", getSmsValue())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("delFlag", getDelFlag())
            .toString();
    }
}
