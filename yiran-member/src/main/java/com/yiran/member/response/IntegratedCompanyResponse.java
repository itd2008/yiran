/**
 *
 */
package com.yiran.member.response;


import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.yiran.member.base.Response;


/**
 * <p>创建会员响应</p>
 */
public class IntegratedCompanyResponse extends Response {

    /**
     *
     */
    private static final long serialVersionUID = 6405210553542745298L;

    public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}


	private String            memberId;

    private String            operatorId;

    private String            accountId;
    /*
     * 商户Id
     */
    private String            merchantId;
    /**
     * @return the memberId
     */
    public String getMemberId() {
        return memberId;
    }

    /**
     * @param memberId the memberId to set
     */
    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    /**
     * @return the operatorId
     */
    public String getOperatorId() {
        return operatorId;
    }

    /**
     * @param operatorId the operatorId to set
     */
    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }


    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
