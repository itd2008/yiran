/**
 * 
 */
package com.yiran.member.request;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.yiran.member.response.IdentityInfo;

/**
 * <p>会员基本信息</p>
 */
public class BaseMemberInfo implements Serializable {
    private static final long  serialVersionUID = -6068139843895334892L;
    /**会员编号*/
    private String             memberId;
    /**会员名称*/
    private String             memberName;
    /**会员状态*/
    private Long               status;
    /**锁定状态（0：未锁定；1：锁定）*/
    private Long               lockStatus;
    /**会员类型 */
    private Long               memberType;
    /**会员激活时间*/
    private Date               activeTime;
    /**会员创建时间*/
    private Date               createTime;
    /**会员标识集合*/
    private List<IdentityInfo> identitys;
    /**接口扩展字段*/
    private String             extention;
    /** 注册来源 */
    private String registerSource;
    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Long getLockStatus() {
        return lockStatus;
    }

    public void setLockStatus(Long lockStatus) {
        this.lockStatus = lockStatus;
    }

    public Long getMemberType() {
        return memberType;
    }

    public void setMemberType(Long memberType) {
        this.memberType = memberType;
    }

    public Date getActiveTime() {
        return activeTime;
    }

    public void setActiveTime(Date activeTime) {
        this.activeTime = activeTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public List<IdentityInfo> getIdentitys() {
        return identitys;
    }

    public void setIdentitys(List<IdentityInfo> identitys) {
        this.identitys = identitys;
    }

    public String getExtention() {
        return extention;
    }

    public void setExtention(String extention) {
        this.extention = extention;
    }

    
    public String getRegisterSource() {
		return registerSource;
	}

	public void setRegisterSource(String registerSource) {
		this.registerSource = registerSource;
	}

	@Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
