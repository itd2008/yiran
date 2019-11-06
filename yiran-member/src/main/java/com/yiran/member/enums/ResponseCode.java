package com.yiran.member.enums;

/**
 * <p>应答码定义</p>
 */
public enum ResponseCode {
    //000~199全局
    SUCCESS("0", "处理成功"),
    NO_QUERY_RESULT("001", "无查询结果"),
    ARGUMENT_ERROR("002", "参数错误"),
    SIGN_CHECK_FAILURE("003", "参数签名错误"),
    TRANS_AUTH_FAILURE("004", "接口权限错误"),
    INTERFACE_COMPATIBALE_ERROR("005", "接口兼容错误"),
    OPERATION_FAILURE("006", "处理失败"),
    EXCEED_COUNT_LIMIT("007", "超过允许的最大个数"),
    DUPLICATE_RECORD("008", "重复记录"),
    NO_EXIST_RECORD("009", "不存在的记录"),



    //100~899会员-逻辑
    MEMBER_CREATE_FAIL("101", "会员开立失败"),
    MEMBER_EXIST("102", "会员已存在"),
    MEMBER_NOT_EXIST("103", "会员不存在"),
    MEMBER_IDENTITY_EXIST("104","会员标识已存在"),
    MEMBER_NOT_SAME("105","信息不属于同一个会员"),
    MEMBER_CREATE_IDENTITY_FAIL("106", "创建会员标识失败"),
    MEMBER_CANCEL_FAIL("108","注销会员失败"),
    MEMBER_ACTIVE_FAIL("110","激活会员失败"),
    MEMBER_SLEEP_FAIL("112","休眠会员失败"),
    MEMBER_AWAKE_FAIL("114","唤醒会员失败"),
    MEMBER_LOCK_FAIL("115","锁定会员失败"),
    MEMBER_UNLOCK_FAIL("116","解锁会员失败"),
    MEMBER_UPDATE_FAIL("117","会员信息更新失败"),
    MEMBER_CANCELLED("118", "会员已注销"),
    MEMBER_TYPE_FAIL("119", "会员类型不一致"),
    MEMBER_DUPLICATE_CANCELLED("120", "同简称会员一天内不能重复注销"),
    MEMBER_LOCKED("121", "该会员被锁定"),
    MEMBER_ALREADY_ACTIVE("122","该会员已经激活"),
    EXTERNAL_USER_ALEREADY_BIND("124","外部用户已绑定"),
    OPERATIONS_NOT_ALLOWED_ON_TEMP_MEMBER("125","临时会员不支持该操作"),
    TEMP_MEMBER_BIND_FAIL("126","绑定临时会员失败"),
    TEMP_MEMBER_UPGRADE_FAIL("127","升级临时会员失败"),
    MEMBER_IDENTITY_NOT_EXIST("128", "会员标识不存在"),
    ACCOUNT_ACTIVE_FAIL("129","账户激活失败"),
    MEMBER_UNACTIVE("130","会员未激活"),
    MEMBER_SLEEP("132","会员已休眠"),
    MEMBER_STATUS_UPDATE_FAIL("133","会员状态更新失败"),
    ACCOUNT_ID_NOT_EXIST("134","账户不存在"),
    OPERATOR_ACCOUNT_ID_NOT_MATCH("135","操作员与账户对应的会员不匹配"),
    MEMBER_UPDATE_VERIFYLEVEL_FAIL("136","会员更新认证等级失败"),



    OPERATOR_NOT_EXIST("201", "操作员不存在"),
    OPERATOR_CREATE_FAIL("202", "操作员创建失败"),
    OPERATOR_CREATE_FAIL_MEMBER_STATUS("203","会员状态不正常不能开立操作员"),
    OPERATOR_NOT_SAME("204","信息不属于同一个操作员"),
    OPERATOR_UPDATE_FAIL("205","更新操作员失败"),
    OPERATOR_CANCEL_FAIL("206","操作员注销失败"),
    OPERATOR_ACTIVE_FAIL("207","操作员激活失败"),
    OPERATOR_LOCK_FAIL("208","操作员锁定失败"),
    OPERATOR_UNLOCK_FAIL("209","操作员解锁失败"),
    OPERATOR_STATUS_NOT_MATCH("210","操作员当前状态不允许执行指定操作"),
    OPERATOR_NOT_IN_MEMBER("211","操作员和会员不匹配"),
    OPERATOR_LOCKED("212","操作员被锁定"),
    OPERATOR_DEFAULT_EXIST("213","已存在默认操作员"),
    OPERATOR_LOGIN_NAME_NOT_MATCH("214", "操作员与登录名不匹配"),
    OPERATOR_DUPLICATE_LINK("215", "操作员重复关联"),
    OPERATOR_CREATE_FAIL_MEMBER_TYPE("216","非企业会员不能开产操作员"),

    OPERATOR_UNACTIVE("230","操作员未激活"),
    OPERATOR_CANCEL("233","操作员已注销"),

    LOGIN_NAME_ADD_FAIL("249","操作员添加登录信息失败"),
    LOGIN_NAME_NOT_EXIST("250", "操作员登录信息不存在"),
    LOGIN_NAME_EXIST("251", "操作员登录信息已存在"),
    LOGIN_NAME_LAST_ONE("252","最后一项登录名不允许删除"),
    LOGIN_NAME_NOT_ADD("253", "非企业操作员不能增加登录方式"),
    LOGIN_NAME_REMOVE_FAIL("254","移除登录类型失败"),

    PASSWORD_SET_FAIL("255","设置支付密码失败"),
    PASSWORD_CHECK_FAIL("256","校验支付密码失败"),
    PASSWORD_NOT_EXISTS("257","尚未设置支付密码"),
    MPASSWORD_SET_FAIL("258","设置app端支付密码失败"),
    PASSWORD_TOO_SIMPLE("259","支付密码强度太弱"),
    PASSWORD_TOO_SHORT("260","支付密码长度太小"),
    PASSWORD_TOO_LONG("261","支付密码长度太大"),
    PASSWORD_ILLEGAL_CHAR("262","支付密码包含非法字符"),
    PASSWORD_LOCKED("264","支付密码被锁定"),
    PASSWORD_EQUAL_LOGIN_PASSWORD("263","支付密码不能和登录密码相同"),

    LOGIN_PASSWORD_UPDATE_FAIL("265","登录密码更新失败"),
    LOGIN_PASSWORD_CHECK_FAIL("266","校验登录密码失败"),
    LOGIN_PASSWORD_NOT_EXISTS("267","尚未设置登录密码"),
    
    MPASSWORD_NOT_EXISTS("357","尚未设置app端支付密码"),
    MPASSWORD_LOCKED("364","app端支付密码被锁定"),
    MPASSWORD_EQUAL_LOGIN_PASSWORD("363","app端支付密码不能和登录密码相同"),
    MPASSWORD_CHECK_FAIL("356","校验app端支付密码失败"),
    
    LOGIN_PASSWORD_LOCKED("274","登录密码被锁定"),
    PASSWORD_FLAG_NULL("275","密码类型为空"),
    LOGIN_PASSWORD_EQUAL_PAY("276","登录密码不能和支付密码相同"),

    CONTACT_EXIST("280", "联系信息已存在"),
    CONTACT_NOT_EXIST("281", "联系信息不存在"),
    CONTACT_SAVE_FAIL("282", "保存联系信息失败"),
    CONTACT_UPDATE_FAIL("283", "更新联系信息失败"),
    CONTACT_DELETE_FAIL("284", "删除联系信息失败"),

    ACCOUNT_NOT_EXIST("290","账户信息不存在"),
    ACCOUNT_CREATE_FAIL("292","账户创建失败"),
    ACCOUNT_CHECK_CLEAR_FAIL("293","账户清理检查失败"),
    ACCOUNT_TYPE_NOT_EXIST("294", "账户类型不存在"),
    ACCOUNT_MEMBER_TYPE_NOT_MATCH("295", "账户类型和会员类型不匹配"),
    ACCOUNT_EXCEED_MAX_COUNT("297", "超过最大允许账户数"),
    ACCOUNT_SET_STATUS_FAIL("298", "设置账户状态失败"),
    ACCOUNT_BULK_QUERY_FAIL("299", "批量查询账户失败"),

    MERCHANT_CREATE_FAIL("300", "商户开立失败"),
    MERCHANT_ACT_FAIL("301", "商户激活失败"),
    MERCHANT_CAN_FAIL("302", "商户注销失败"),
    MERCHANT_NOT_EXIST("303", "商户不存在"),
    DUPLICATE_MERCHANT_NAME("304", "商户名重复"),
    DUPLICATE_MERCHANT_ID("305", "商户号重复"),
    MERCHANT_CANCELLED("306", "商户已注销"),
    MERCHANT_DUPLICATE_CANCELLED("307", "同名商户一天内不能重复注销"),
    MEMBER_MERCHANT_EXIST("308", "会员已经添加了商户"),
    MERCHANT_NOT_CREATE("309", "非企业会员不能创建商户"),
    MERCHANT_UPDATE_FAIL("310", "商户更新失败"),
    MERCHANT_UPDATE_STATUS_FAIL("311", "商户状态更新失败"),
    MERCHANT_MEMBER_FAIL("312","商户ID与会员ID不匹配"),


    ORG_CREATE_FAIL("401", "集团开立失败"),
    ORG_CAN_FAIL("402", "集团注销失败"),
    ORG_UPDATE_FAIL("403", "集团更新失败"),
    ORG_ADD_NODE_FAIL("404", "集团节点新增失败"),
    RMV_ORG_NODE_FAIL("405", "集团节点移除失败"),
    ADD_ORG_CONT_FAIL("407", "添加集团联系人失败"),
    ORG_NOT_EXIST("408", "集团不存在"),
    MOVE_ORG_NODE_FAIL("410", "变更集团节点失败"),
    ORG_CANCELLED("412", "集团已注销"),
    ORG_NODE_NOT_EXIST("413", "集团节点不存在"),
    ORG_NODE_UNAVAILABLE("414", "集团节点不可用"),
    MOVE_ORG_NODE_ROOT("417", "集团根节点不能变更"),
    ORG_NODE_NEW_PARENT_CAUSE_CYCLE("419", "新的父节点不能是其自身或其子孙节点"),
    MEMBER_IN_ORG("421", "会员已隶属与某一集团"),
    MEMBER_NOT_IN_ORG("422", "会员不属于任何一个集团节点"),
    MEMBER_NOT_DESCENDANT("423", "会员不是子孙节点"),
    MEMBER_NOT_ROOT("424", "会员不是集团核心成员"),


    ADD_VERIFY_FAIL("451", "添加认证信息失败"),
    GET_VERIFY_FAIL("452", "查询认证信息失败"),
    UPDATE_VERIFY_FAIL("453", "更新认证信息失败"),
    SAVE_VERIFY_IMG_FAIL("454", "保存认证图片失败"),
    DUPLICATE_VERIFY("455", "会员认证信息重复"),
    VERIFY_NOT_EXIST("456", "认证信息不存在"),
    VERIFY_MEMBER_FAIL("457","会员与认证信息不匹配"),
    VERIFY_MOBILE_FAIL("458","会员未绑定手机"),
    VERIFY_NOT_FOUND("467","认证类型不存在"),


    DISTRICT_NOT_EXIST("501", "地区编码不存在"),
    DECIPHERED_TYPE_NOT_EXIST("502", "解密类型不能为空"),

    SET_BANK_ACCOUNT_FAIL("600","修改会员银行账号失败"),
    SET_BANK_ACCOUNT_STATUS_FAIL("601", "设置会员银行账号状态失败"),
    VERIFIE_BANK_ACCOUNT_FAIL("602", "认证会员银行账号失败"),
    BANK_ACCOUNT_NOT_EXISTS("603", "会员绑定银行信息不存在"),
    BANK_ACCOUNT_NO_SUMMARY_CONFLICT("604", "银行卡号存在，不能更新银行卡掩码"),
    MEMBER_BANK_ACCOUNT_NOT_MATCH("605","会员银行卡不匹配"),
    BANK_ACCOUNT_TOO_MANY("607","匹配的银行卡大于1"),
    BANK_ACCOUNT_LOCK("609","银行卡锁定不能新增银行卡"),
    CERT_NO_FILL_FAIL("611","证件号填充失败"),
    BANK_ACCOUNT_PAY_DONT_UPDATE("613","银行卡或支付属性不能修改"),

    QUERY_ACCOUNT_BALANCE_FAIL("701", "查询会员账户余额失败"),
    QUERY_ACCOUNT_BALANCE_LIST_FAIL("702", "查询会员账户余额明细失败"),
    ACTIVATE_ACCOUNT_STATUS_FAIL("703","激活账户状态失败"),
    CHANGE_DENY_ACCOUNT_STATUS_FAIL("704","冻结账户状态失败"),
    CHANGE_CANCELED_ACCOUNT_STATUS_FAIL("705","销户账户状态失败"),
    GET_ACCOUNT_BALANCE_LIST_FAIL("706","查询账户余额明细失败"),
    GET_ACCOUNT_STATUS_LIST_FAIL("707","批量查询账户状态失败"),

    CREATE_CREDIT_FAIL_MOBILE("720","创建信用账户失败：一个手机号只能领用一次信用"),
    
    //新增联系人失败信息提示
    REMOTE_VALIDATE_CARD_FAIL("800","远程调用验证卡Bin校验信息失败"),
    REMOTE_GET_BRANCH_FAIL("801","远程调用获取分行信息失败"),
    REMOTE_VALIDATE_BANK_CODE("802","卡Bin校验卡号和银行编号不匹配"),
    REMOTE_VALIDATE_CARD_ERROR("803","卡Bin校验当前非借记卡"),
    CONTACT_CARD_BIN_FAIL("804","联系人卡号校验失败"),
    GET_BRANCH_BY_RELATE_NAME_FAIL("805","通过提供银行信息无法正常获取分支行号"),
    MEMBER_CONTACT_EXIST("806", "联系人信息已存在"),
    MEMBER_CONTACT_NOT_EXIST("807", "联系人信息不存在"),
    MEMBER_CONTACT_TYPE_CHANGE("808", "联系人类型不允许修改"),
    MEMBER_CONTACT_SAVE_FAIL("809", "保存联系人信息失败"),
    MEMBER_CONTACT_SAVE_BATCH_FAIL("810", "批处理保存联系人未完全成功"),
    MEMBER_CONTACT_TO_EQUAL_MEMBER("811", "双方账户相同,请核对账户信息"),
    MEMBER_CONTACT_NOT_EXIST_NAME("812", "添加账户信息无对应账户名称，请设定联系人名称"),
    
    CREATE_HRY_BIND_FAILURE("6000", "创建海融易绑定关系失败"),
    //900-999全局
    UNKNOWN("999", "异常失败"),
    DB_NO_EFFECTED_ROWS("998","没有更新到数据"),
    DB_MORE_EFFECTED_ROWS("997","更新到太多的数据");

    /** 代码 */
    private final String code;
    /** 信息 */
    private final String message;

    ResponseCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 通过代码获取枚举项
     * @param code
     * @return
     */
    public static ResponseCode getByCode(String code) {
        if (code == null) {
            return null;
        }

        for (ResponseCode responseCode : ResponseCode.values()) {
            if (responseCode.getCode().equals(code)) {
                return responseCode;
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
