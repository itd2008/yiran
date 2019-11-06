/**
 * 
 */
package com.yiran.member.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.yiran.member.base.Request;


/**
 * <p>集成创建虚拟商户请求对象</p>
 */
public class CreateVirtualMerchantRequest extends Request {

    /** 描述 */
	private static final long serialVersionUID = -7374379651556532479L;
	
	/** 主商户ID */
	private String parentMemberId;
	
    /**
     * 虚拟商户标识
     */
    private String            identify;

    /**
     * 虚拟商户标识类型: 1(邮箱), 2(手机号), 3(普通文字)
     */
    private Integer           identifyType = 3;

    /**虚拟商户标识平台类型 */
    private String            identifyPlatformType="1";

    /**
     * 平台类型
     */
    private String            platformType = "1";

    private String registerSource;

	public String getParentMemberId() {
		return parentMemberId;
	}



	public void setParentMemberId(String parentMemberId) {
		this.parentMemberId = parentMemberId;
	}



	public String getIdentify() {
		return identify;
	}



	public void setIdentify(String identify) {
		this.identify = identify;
	}



	public Integer getIdentifyType() {
		return identifyType;
	}



	public void setIdentifyType(Integer identifyType) {
		this.identifyType = identifyType;
	}



	public String getIdentifyPlatformType() {
		return identifyPlatformType;
	}



	public void setIdentifyPlatformType(String identifyPlatformType) {
		this.identifyPlatformType = identifyPlatformType;
	}



	public String getPlatformType() {
		return platformType;
	}



	public void setPlatformType(String platformType) {
		this.platformType = platformType;
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
