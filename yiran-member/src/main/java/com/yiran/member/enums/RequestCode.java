package com.yiran.member.enums;

/**
 * <p>接口编码定义</p>
 */
public enum RequestCode {
    CR_COM_MBR("CR_COM_MBR", "开立会员-企业会员"),
    CR_PER_MBR("CR_PER_MBR", "开立会员-个人会员"),
    CR_INST_MBR("CR_INST_MBR", "开立会员-非企业机构会员"),
    GET_COM_MBR("GET_COM_MBR", "查询企业会员"),
    GET_PER_MBR("GET_PER_MBR", "查询个人会员"),
    GET_INST_MBR("GET_INST_MBR", "查询非企业机构会员"),
    CAN_MBR("CAN_MBR","注销会员"),
    ACT_MBR("ACT_MBR","激活会员"),
    SLP_MBR("SLP_MBR","休眠会员"),
    AWK_MBR("AWK_MBR","唤醒会员"),
    LOCK_MBR("LOCK_MBR","锁定会员"),
    UNLOCK_MBR("UNLOCK_MBR","解锁会员"),
    UP_COM_MBR("UP_COM_MBR", "更新会员-企业会员"),
    UP_PER_MBR("UP_PER_MBR", "更新会员-个人会员"),
    UP_INST_MBR("UP_INST_MBR", "更新会员-非企业机构会员"),    
    CR_CONT_INFO("CR_CONT_INFO","创建会员联系信息"),
    UP_CONT_INFO("UP_CONT_INFO","更新会员联系信息"),
    GET_CONT_INFO("GET_CONT_INFO","查询会员联系信息"),
    ADD_MBR_CONT("ADD_MBR_CONT","新增会员联系人"),
    RMV_MBR_CONT("RMV_MBR_CONT","移除会员联系人"),
    GET_MBR_CONT("GET_MBR_CONT","查询会员联系人"),
    CR_ACCT("CR_ACCT","创建会员账户"),
    GET_ACCT("GET_ACCT","查询会员账户"),
    
    CR_OP("CR_OP","开立操作员"),
    GET_OP("GET_OP","根据登录名查询操作员"),
    UP_OP("UP_OP","更新操作员"),
    CAN_OP("CAN_OP","注销操作员"),
    ACT_OP("ACT_OP","激活操作员"),
    LOCK_OP("LOCK_OP","锁定操作员"),
    UNLOCK_OP("UNLOCK_OP","解锁操作员"),
    ADD_LOGIN("ADD_LOGIN","新增登录方式"),
    RMV_LOGIN("RMV_LOGIN","移除登录方式"),
    ADD_OP_CONT("ADD_OP_CONT","新增操作员联系人"),
    RMV_OP_CONT("RMV_OP_CONT","移除操作员联系人"),
    GET_OP_CONT("GET_OP_CONT","查询操作员联系人"),
    SET_PSW("SET_PSW","设置支付密码"),
    CHK_PSW("CHK_PSW","校验支付密码"),
    
    CR_MER("CR_MER", "创建商户"),
    ACT_MER("ACT_MER", "激活商户"),
    CAN_MER("CAN_MER", "注销商户"),
    GET_MER_INFO("GET_MER_INFO", "查询商户信息"),
    ADD_MER_CONT("ADD_MER_CONT", "添加商户联系人"),
    RMV_MER_CONT("RMV_MER_CONT", "移除商户联系人"),
    GET_MER_CONT("GET_MER_CONT", "查询商户联系人"),
    
    
    CR_ORG("CR_ORG", "开立集团"),
    GET_ORG("GET_ORG","查询集团"),
    CAN_ORG("CAN_ORG", "注销集团"),
    UP_ORG("UP_ORG", "更新集团"),
    ADD_ORG_NODE("ADD_ORG_NODE", "新增集团节点"),
    RMV_ORG_NODE("RMV_ORG_NODE", "移除集团节点"),
    RMV_ORG_TREE("RMV_ORG_TREE", "移除集团树"),
    ADD_ORG_CONT("ADD_ORG_CONT", "添加集团联系人"),
    RMV_ORG_CONT("RMV_ORG_CONT", "删除集团联系人"),
    GET_ORG_CONT("GET_ORG_CONT", "查询集团联系人"),
    GET_ORG_NODES_TREE("GET_ORG_NODES_TREE", "查询集团节点树"),
    MOVE_ORG_NODE("MOVE_ORG_NODE", "变更集团节点"),
    
    ADD_VERIFY("ADD_VERIFY", "新增验证信息"),
    GET_VERIFY("GET_VERIFY", "查询验证信息"),
    UP_VERIFY("UP_VERIFY", "更新验证信息"),
    
    ADD_COMPANY_BANK_ACCT("ADD_COM_BANK_ACCT", "增加企业银行账号"),
    ADD_PERSON_BANK_ACCT("ADD_PER_BANK_ACCT", "增加个人银行账号"),
    DISABLE_BANK_ACCT("DISABLE_BANK_ACCT", "禁用银行账号"),
    LOCK_BANK_ACCT("LOCK_BANK_ACCT", "锁定银行账号"),
    UNLOCK_BANK_ACCT("UNLOCK_BANK_ACCT", "解锁银行账号"),
    GET_COMPANY_BANK_ACCT("GET_COMPANY_BANK_ACCT", "查询企业银行账户"),
    GET_PERSON_BANK_ACCT("GET_PERSON_BANK_ACCT", "查询个人银行账户"),
    
    GET_SUB_DISTRICTS("GET_SUB_DISTRICTS", "查询下一层地区信息"),
    
    XXX("XXX", "XXX", true);

    /** 代码 */
    private final String  code;
    /** 信息 */
    private final String  message;
    /** 接口是否异步返回 */
    private final boolean isAsyn;

    RequestCode(String code, String message, boolean isAsyn) {
        this.code = code;
        this.message = message;
        this.isAsyn = isAsyn;
    }

    RequestCode(String code, String message) {
        this.code = code;
        this.message = message;
        this.isAsyn = false;
    }

    /**
     * 通过代码获取枚举项
     * @param code
     * @return
     */
    public static RequestCode getByCode(String code) {
        if (code == null) {
            return null;
        }

        for (RequestCode requestCode : RequestCode.values()) {
            if (requestCode.getCode().equals(code)) {
                return requestCode;
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

    public boolean isAsyn() {
        return isAsyn;
    }

}
