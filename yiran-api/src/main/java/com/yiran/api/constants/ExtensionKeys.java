package com.yiran.api.constants;

public class ExtensionKeys {


    /**
     * 银行返回信息JSON序列话后扩展
     */
    public static final String BANK_EXT_INFO             = "bankExtInfo";

    /**
     * 机构返回一级状态码
     */
    public static final String INST_RETURN_CODE          = "INST_RETURN_CODE";

    /**
     * 机构返回一级状态码描述
     */
    public static final String INST_RETURN_MSG           = "INST_RETURN_MSG";

    /**
     * 机构返回二级状态码
     */
    public static final String INST_RETURN_SUB_CODE      = "INST_RETURN_SUB_CODE";

    /**
     * 机构返回二级状态码描述
     */
    public static final String INST_RETURN_SUB_MSG       = "INST_RETURN_SUB_MSG";

    /**
     * API TYPE
     */
    public static final String CHANNEL_API_TYPE          = "CHANNEL_API_TYPE";

    /**
     * 返回机构的数据
     */
    public static final String RETURN_TO_INST_INFO       = "RETURN_TO_INST_INFO";

    /**
     * 返回机构的方式，某些机构需要我们返回给他们特定处理过的数据 数据保存在RETURN_TO_INST_INFO键对应的值中
     */
    public static final String RESPONSE_TO_INST_TYPE     = "RESPONSE_TO_INST_TYPE";

    public static final String WEIBO_PAY_USER_PAY_IP     = "weiboPayUserPayIp";

    public static final String WEIBO_PAY_USER_PAY_DOMAIN = "weiboPayUserPayDomain";

    public static final String INST_ORDER_NO             = "instOrderNo";

    public static final String AMOUNT                    = "instAmount";

    /**
     * 对账文件查询日期
     */
    public static final String QUERY_DATE                = "bizDate";

    /**
     * 对账文件查询结束日期（此扩展参数为空，默认查询结束日期跟bizDate相同）
     */
    public static final String QUERY_END_DATE            = "endDate";

    /**
     * 对账文件上传路径
     */
    public static final String UPLOAD_PATH               = "uploadPath";

    /**
     * 交易码
     */
    public static final String TRANS_CODE                = "transCode";
    
    /**
     * 业务类型：I-入款，B-退款，O-出款
     */
    public static final String BIZ_TYPE                = "bizType";


}
