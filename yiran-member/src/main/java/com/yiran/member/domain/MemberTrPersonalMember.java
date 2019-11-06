package com.yiran.member.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.yiran.common.base.BaseEntity;
import java.util.Date;

/**
 * 个人会员表 member_tr_personal_member
 * 
 * @author yiran
 * @date 2019-03-30
 */
public class MemberTrPersonalMember extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 会员ID */
	private String memberId;
	/** 默认登录名 */
	private String defaultLoginName;
	/** 真实姓名 */
	private String trueName;
	/** 证件类型 */
	private Integer certType;
	/** 证件号 */
	private String idNo;
	/** 性别(0保密 1男 2女) */
	private Integer gender;
	/** 职位 */
	private Integer postition;
	/** 职业 */
	private Integer career;
	/** 生日 */
	private String birthday;
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

	public void setMemberId(String memberId) 
	{
		this.memberId = memberId;
	}

	public String getMemberId() 
	{
		return memberId;
	}
	public void setDefaultLoginName(String defaultLoginName) 
	{
		this.defaultLoginName = defaultLoginName;
	}

	public String getDefaultLoginName() 
	{
		return defaultLoginName;
	}
	public void setTrueName(String trueName) 
	{
		this.trueName = trueName;
	}

	public String getTrueName() 
	{
		return trueName;
	}
	public void setCertType(Integer certType) 
	{
		this.certType = certType;
	}

	public Integer getCertType() 
	{
		return certType;
	}
	public void setIdNo(String idNo) 
	{
		this.idNo = idNo;
	}

	public String getIdNo() 
	{
		return idNo;
	}
	public void setGender(Integer gender) 
	{
		this.gender = gender;
	}

	public Integer getGender() 
	{
		return gender;
	}
	public void setPostition(Integer postition) 
	{
		this.postition = postition;
	}

	public Integer getPostition() 
	{
		return postition;
	}
	public void setCareer(Integer career) 
	{
		this.career = career;
	}

	public Integer getCareer() 
	{
		return career;
	}
	public void setBirthday(String birthday) 
	{
		this.birthday = birthday;
	}

	public String getBirthday() 
	{
		return birthday;
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

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("memberId", getMemberId())
            .append("defaultLoginName", getDefaultLoginName())
            .append("trueName", getTrueName())
            .append("certType", getCertType())
            .append("idNo", getIdNo())
            .append("gender", getGender())
            .append("postition", getPostition())
            .append("career", getCareer())
            .append("birthday", getBirthday())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("createUser", getCreateUser())
            .append("updateUser", getUpdateUser())
            .append("memo", getMemo())
            .toString();
    }
}
