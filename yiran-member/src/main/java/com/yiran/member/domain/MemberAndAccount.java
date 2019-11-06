package com.yiran.member.domain;


/**
 * 
 * <p>会员账户关系</p>
 */
public class MemberAndAccount {

    private String memberId;
    
    private String operatorId;

    private String accountId;
    
    private String merchantId;//商户ID
    private String hryId;
    private MemberTmOperator operator;
    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }
	

	public MemberTmOperator getOperator() {
		return operator;
	}

	public void setOperator(MemberTmOperator operator) {
		this.operator = operator;
	}

	public String getHryId() {
		return hryId;
	}

	public void setHryId(String hryId) {
		this.hryId = hryId;
	}
    
}
