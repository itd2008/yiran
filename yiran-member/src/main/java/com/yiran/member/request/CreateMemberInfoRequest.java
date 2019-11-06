/**
 * 
 */
package com.yiran.member.request;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.yiran.member.base.Request;

/**
 * <p>创建会员请求对象</p>
 */
public class CreateMemberInfoRequest extends Request {

    /**
     * 
     */
    private static final long serialVersionUID = 9130311226477404692L;

    /**
     * 会员类型（1：个人会员，2：企业会员，3：特约商户）
     */
    private Integer           memberType;

    /**
     * 登录名， 非空
     */
    private String            loginName;

    /**
     * 登录名类型: 1(邮箱), 2(手机号), 3(普通文字)
     */
    private Integer           loginNameType;

    /**登录名平台类型 */
    private String            loginNamePlatformType;

    /**
     *  登录密码
     */
    private String            loginPassword;

    /**
     * 外部平台用户标识
     */
    private String            platformUserId;

    /**
     * 平台类型
     */
    private String            platformType;

    /**
     * 会员名称
     */
    private String            memberName;
    /**
     * 注册来源
     */
    private String registerSource;


    /**
     * @return the memberType
     */
    public Integer getMemberType() {
        return memberType;
    }

    /**
     * @param memberType the memberType to set
     */
    public void setMemberType(Integer memberType) {
        this.memberType = memberType;
    }

    /**
     * @return the loginName
     */
    public String getLoginName() {
        return loginName;
    }

    /**
     * @param loginName the loginName to set
     */
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    /**
     * @return the loginNameType
     */
    public Integer getLoginNameType() {
        return loginNameType;
    }

    /**
     * @param loginNameType the loginNameType to set
     */
    public void setLoginNameType(Integer loginNameType) {
        this.loginNameType = loginNameType;
    }

    /**
     * 
     * @return
     */
    public String getLoginNamePlatformType() {
        return loginNamePlatformType;
    }

    /**
     * 
     * @param loginNamePlatformType
     */
    public void setLoginNamePlatformType(String loginNamePlatformType) {
        this.loginNamePlatformType = loginNamePlatformType;
    }

    /**
     * @return the loginPassword
     */
    public String getLoginPassword() {
        return loginPassword;
    }

    /**
     * @param loginPassword the loginPassword to set
     */
    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }

    /**
     * @return the platformUserId
     */
    public String getPlatformUserId() {
        return platformUserId;
    }

    /**
     * @param platformUserId the platformUserId to set
     */
    public void setPlatformUserId(String platformUserId) {
        this.platformUserId = platformUserId;
    }

    /**
     * @return the platformType
     */
    public String getPlatformType() {
        return platformType;
    }

    /**
     * @param platformType the platformType to set
     */
    public void setPlatformType(String platformType) {
        this.platformType = platformType;
    }

    /**
     * @return the memberName
     */
    public String getMemberName() {
        return memberName;
    }

    /**
     * @param memberName the memberName to set
     */
    public void setMemberName(String memberName) {
        this.memberName = memberName;
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
