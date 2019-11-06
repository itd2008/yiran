package com.yiran.member.domain;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.yiran.member.enums.BasicAccountType;
import com.yiran.member.enums.CareerEnum;
import com.yiran.member.enums.GenderEnum;
import com.yiran.member.enums.PositionEnum;


/**
 * <p>个人会员对象</p>
 */
public class PersonalMember extends MemberTmMember {

    /**
     * 
     */
    private static final long serialVersionUID = 7451922150161881798L;

    /** 默认登录名 */
    private String            defaultLoginName;

    /** 真实姓名 */
    private String            trueName;
    // 真实姓名的ticket
    private String            trueNameTicket;

    /** 性别 */
    private GenderEnum        gender;

    /** 职位 */
    private PositionEnum      position;

    /** 职业 */
    private CareerEnum        career;

    /** 生日 */
    private String            birthDay;

    public String getDefaultLoginName() {
        return defaultLoginName;
    }

    public void setDefaultLoginName(String defaultLoginName) {
        this.defaultLoginName = defaultLoginName;
    }

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

    public String getTrueNameTicket() {
        return trueNameTicket;
    }

    public void setTrueNameTicket(String trueNameTicket) {
        this.trueNameTicket = trueNameTicket;
    }

    public GenderEnum getGender() {
        return gender;
    }

    public void setGender(GenderEnum gender) {
        this.gender = gender;
    }

    public PositionEnum getPosition() {
        return position;
    }

    public void setPosition(PositionEnum position) {
        this.position = position;
    }

    public CareerEnum getCareer() {
        return career;
    }

    public void setCareer(CareerEnum career) {
        this.career = career;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    @Override
    public BasicAccountType getBaseAccountType() {
        return BasicAccountType.PERSONAL_BASIC_ACCOUNT;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
