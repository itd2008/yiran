/**
 * 
 */
package com.yiran.member.request;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.yiran.member.base.Request;

/**
 * <p>设置个人会员信息请求信息</p>
 */
public class PersonalMemberInfoRequest extends Request {
    private static final long serialVersionUID = 7647982120050489483L;
    
    /**会员id */
    private String memberId;
    /**会员名称*/
    private String memberName;
    /**真实姓名*/
    private String realName;
    /**性别（0:未知；1：男；2：女）*/
    private Long   gender;
    /**职位（1：默认）*/
    private Long   position;
    /**职业（1：默认）*/
    private Long   career;
    /**生日*/
    private String birthDay;
    /**邀请码*/
    private String invitCode;

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

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Long getGender() {
        return gender;
    }

    public void setGender(Long gender) {
        this.gender = gender;
    }

    public Long getPosition() {
        return position;
    }

    public void setPosition(Long position) {
        this.position = position;
    }

    public Long getCareer() {
        return career;
    }

    public void setCareer(Long career) {
        this.career = career;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public String getInvitCode() {
		return invitCode;
	}

	public void setInvitCode(String invitCode) {
		this.invitCode = invitCode;
	}

	public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
