/**
 * 
 */
package com.yiran.member.request;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.yiran.member.base.Request;
import com.yiran.member.domain.VerifyInfo;


/**
 * <p>个人会员集成创建</p>
 */
public class IntegratedPersonalRequest extends Request {
    private static final long     serialVersionUID = -4068656419075118498L;

    /**个人会员信息*/
    private PersonalMemberRequest personalRequest;
    /**认证集合信息 */
    private List<VerifyInfo>      verifys;
    /**外部平台用户标识*/
    private String                platformUserId;
    /**平台类型*/
    private String                platformType;
    /**会员状态与账户标志（0默认：会员未激活，且不开储值基本户；1：会员激活，并开未激活储值基本户）*/
    private Integer               memberAccountFlag;
    /** 注册来源 */
    private String registerSource;
    /** 注册来源扩展 json*/
    private String registerSourceExt;
    /**邀请码*/
    private String invitCode;
    public PersonalMemberRequest getPersonalRequest() {
        return personalRequest;
    }

    public void setPersonalRequest(PersonalMemberRequest personalRequest) {
        this.personalRequest = personalRequest;
    }

    public List<VerifyInfo> getVerifys() {
        return verifys;
    }

    public void setVerifys(List<VerifyInfo> verifys) {
        this.verifys = verifys;
    }

    public String getPlatformUserId() {
        return platformUserId;
    }

    public void setPlatformUserId(String platformUserId) {
        this.platformUserId = platformUserId;
    }

    public String getPlatformType() {
        return platformType;
    }

    public void setPlatformType(String platformType) {
        this.platformType = platformType;
    }

    public Integer getMemberAccountFlag() {
        return memberAccountFlag;
    }

    public void setMemberAccountFlag(Integer memberAccountFlag) {
        this.memberAccountFlag = memberAccountFlag;
    }
    
    

    public String getRegisterSource() {
		return registerSource;
	}

	public void setRegisterSource(String registerSource) {
		this.registerSource = registerSource;
	}

	public String getInvitCode() {
		return invitCode;
	}

	public void setInvitCode(String invitCode) {
		this.invitCode = invitCode;
	}
	
	public String getRegisterSourceExt() {
		return registerSourceExt;
	}

	public void setRegisterSourceExt(String registerSourceExt) {
		this.registerSourceExt = registerSourceExt;
	}

	@Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
