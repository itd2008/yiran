package com.yiran.member.enums;
/**
 * 
 * 功能描述：业务产品枚举
 */
public enum  BizProductEnum {
    
    RECHARGE("recharge", "充值"),WITHDRAW("withdraw","提现"),
    TRANS_TO_CARD("trans_to_card","转账到卡"),TRANS_TO_ACCO("trans_to_acco","转账到账户"),
    TIMELY_ACCO("timely_acco","及时到账交易"),TEL_FEE_RECHARGE("tel_fee_recharge","话费充值"),
    FINANCE_INVEST("finance_invest","投资理财"),TRANS_TO_CO_CARD("trans_to_co_card","提现到企业银行卡"),
    TRANS_TO_CO_ACCO("trans_to_co_acco","转账到账户"),FINANCING("financing","融资"),CREDIT_LOAN("credit_loan","信用贷款");

    /** 代码 */
    private final String   code;
    /** 信息 */
    private final String message;

    private BizProductEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 通过代码获取枚举项
     * @param code
     * @return
     */
    public static BizProductEnum getByCode(String code) {
        for (BizProductEnum item : BizProductEnum.values()) {
            if (item.code == code ) {
                return item;
            }
        }

        return null;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
