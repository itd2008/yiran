package com.yiran.member.enums;

/**
 * <p>远程返回结果代码</p>
 */
public enum RemoteResultCode {

  //公共start
    SUCCESS(0, "成功"),
    PROCESSING(1, "处理中"),
    Fail(2, "处理失败"), 
    PARAMETERS_AVAILABILITY_ERROR(1022,"参数有效性验证失败"),
    RECORD_NOT_FOUND(1033,"没有找到任何记录"),
    VALUE_TOOLARGE(1062,"值超长"),
    ENUM_NOT_MATCHING(1063,"枚举值不匹配"),
    NONSUPPORT(1066,"暂不支持此功能"),
    //公共end
    UPDATE_BALANCE_ERROR(1000, "更新余额失败"),
    STATUS_MAP_IS_NULL(1001, "账户状态位图不能为空"),
    ACCOUNT_STATUS_NOT_ACTIVATION(1002, "账户状态未激活"),
    ACCOUNT_STATUS_LOCK(1003, "账户状态已锁定"),
    ACCOUNT_STATUS_LOGOUT(1004, "账户已销户"),
    ACCOUNT_STATUS_FROZEN(1005, "账户已冻结"),
    ACCOUNT_STATUS_FUND_IN(1006, "账户已止入"),
    ACCOUNT_STATUS_FUND_OUT(1007, "账户已止出"),
    ACCOUNT_NOT_EXISTS(1008, "账户信息不存在"),
    INSUFFICIENT_ACCOUNT_BALANCE(1009, "余额不足"),
    ACCOUNT_STATUS_INACTIVE_ACCOUNTS(1057, "账户已结转长期不动户"),

    UPDATE_TRANSACTION_STATUS_ERROR(1010, "更新transaction状态失败"),
    UPDATE_ENTRY_STATUS_ERROR(1011, "更新entry状态失败"),
    DUPLICATE_REQUEST_ERROR(1012, "重复请求错误"),
    //账户类start
    ACCOUNT_CREATE_FAIL(1013, "创建帐户失败"),
    NOT_DEFINED_ACCOUNT_TYPE(1014,"账户模板未定义"),
    ACCOUNT_ID_ALREADY_EXISTS(1015,"账户ID已存在"),    
    CANNOTFINDENTRY(1016,"未找到分录"),
    BATCHBOOKING(1017,"批量持久化分录成功"),
    
    QUERY_BATCHACCOUNTINGRESULT_OK(1018,"查询批量入账结果成功"),
    QUERY_BATCHACCOUNTINGRESULT_NOTHING(1019,"没有查询到任何数据"),   
    GET_GZIP_ERROR(1020,"批量数据Gzip转换失败"),
    ACCOUNT_ID_NOT_EXISTS(1021,"账户ID不存在"),
     
    PARAMETERS_BUSINESS_ERROR(1023,"参数业务规则验证失败"),
    ACCOUNT_STATUS_HAS_ACTIVATED(1024,"账户状态已激活"),
    ACCOUNT_STATUS_UNLOCK(1025, "账户状态已解锁"),
    ACCOUNT_STATUS_HAS_UPDATED(1026, "账户状态已更新，不需要重复更新"),
    ACCOUNTNO_GREATERTHAN_MAX(1059, "开户数超过最大值99"),
    INNERACCOUNT_NONSUPPORT(1064, "内部户不支持此功能"),
    OUTERACCOUNT_NONSUPPORT(1065, "外部户不支持此功能"),
    //账户类end    
    //单笔,批量查询start
    TRANSACTION_INIT(1027,"事务状态:初始"),
    TRANSACTION_PROCEEDING(1028,"事务状态:处理中"),
    TRANSACTION_COMPLETE_SUCCESS(1029,"事务状态:入账完成_成功"),
    TRANSACTION_FAIL_PROCEEDING(1030,"事务状态:入账失败_处理中"),
    TRANSACTION_COMPLETE_FAIL(1031,"事务状态:入账完成_失败"),
    TRANSACTION_STATUS_UNKNOW(1032,"事务状态:状态未知"),
    
    //单笔,批量查询end
    
    CREATE_MORE_BASICS_ACCOUNT(1034,"只能创建一个基本户"),
    CREATE_MORE_ONE_HUNDRED_ACCOUNT(1035,"一个用户不能创建超过100个账户"),
    TRANSNO_TXNTYPE_SYSTRACENO_EXIST(1036,"相同事务号,系统跟踪号,入账类型已存在"),
    TRANSACTION_NOT_EXIST(1037,"事务对象不存在"),
    TRANSACTION_CANCELED(1038,"不能重复撤销"),
    PACKAGENO_EXIST(1039,"packageNo已存在"),
    UNIQUENESS_RESTRAIN(1040,"违法唯一性约束(transactionNo,sysTraceNo,txnType)"),
    INVALID_PARAM(1041, "输入参数错误"),
    INACTIVE_ACCOUNTS_ERRORS(1042, "会员账户已转不动户"),
    ACCOUNT_NOT_ACTIVATED(1043, "该会员账户没有激活"),
    UNFORZEN_NOTEQUALS_FROZEN(1044, "解冻金额与冻结金额不一致"),
    UNFORZEN_EXIST(1061, "不能重复解冻"),
    TRANSACTIONNO_EXIST(1045, "transactionNo已存在"),
    VOUCHERNO_EXIST(1046, "voucherNo已存在"),
    OPERATER_ERROR(1060, "操作类型非法"),
    //科目类接口start
    ACCOUNT_TITLE_NO_NOT_EXISTS(1047,"查询科目号不存在"),
    ACCOUNTTITLE_NOT_ALLOW_DELETE(1048,"科目号已创建账户或有子节点,不允许删除!"),
    ACCOUNTTITLE_EXIST(1049,"科目号已存在,不允许重复录入!"),

    ACCOUNTTITLE_TITLERANGE(1050,"子节点科目范围和父节点不一致!"),
    ACCOUNTTITLE_TITLECODE(1051,"子节点科目号不以父节点科目号起始或首数字与科目类型不符!"),
    ACCOUNTTITLE_BALANCEDIRECTION(1052,"子节点余额方向和父节点科不一致!"),
    ACCOUNTTITLE_TYPE(1053,"子节点类型和父节点不一致!"),
    ACCOUNTTITLE_TYPE_BALANCEDIRECTION(1054,"科目类型和余额方向不符合约定规则!"),

    ACCOUNT_TITLE_IS_NOT_LEAF(1055,"该科目不是叶子结点"),
    ACCOUNT_TITLE_INVALID(1056,"该科目状态无效"),
    ACCOUNT_TITLE_IS_NOT_INNER_ACCOUNT(1058,"该科目不是内部账户"),
    


    //科目类接口end
    //公共
    SYS_EXP(9998, "系统异常"),
    DB_EXCEPTION(9999, "数据库异常"),
    
    EXCEPTION(-1, "操作异常");
    
    /** 代码 */
    private final int    code;
    /** 信息 */
    private final String name;

    RemoteResultCode(int code, String name) {
        this.code = code;
        this.name = name;
    }

    /**
     * 根据code获取ENUM
     * @param code
     * @return
     */
    public static RemoteResultCode getByCode(int code) {
        for (RemoteResultCode resultCode : RemoteResultCode.values()) {
            if (resultCode.getCode() == code) {
                return resultCode;
            }
        }
        
        return RemoteResultCode.EXCEPTION;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    
}
