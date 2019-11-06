package com.yiran.paychannel.enums;

import org.apache.commons.lang.StringUtils;

/**
 *
 * <p>接口类型：SP（单笔支付），BP（批量支付），SQ（单笔查询），BQ（批量查询），SR（单笔退款），BR（批量退款）</p>
 */
public enum FundChannelApiType {
    SINGLE_PAY("SP", "单笔支付"),          //一期 出款
    BATCH_PAY("BP", "批量支付"),
    BATCH_FILE_PAY("BFP", "批量文件支付"),
    SINGLE_QUERY("SQ", "单笔查询"),
    BATCH_QUERY("BQ", "批量查询"),
    QUERY_BALANCE("QB", "查询账户余额"),
    SINGLE_REFUND("SR", "单笔退款"),       //一期 自动充退
    BATCH_REFUND("BR", "批量退款"),
    BATCH_FILE_REFUND("BFR", "批量文件退款"),
    MANUAL_REFUND("MR", "人工充退"),       //一期 人工充退
    DEBIT("DB","扣款"),                    //一点充，积分，预付费卡，银联快捷，手机
    DEBIT_ADVANCE("DBA", "扣款推进"),
    DEBIT_ADVANCE_QUERY("DBAQ", "扣款推进查询"),
    DEBIT_UNDO("DBU","扣款撤销"),
    DEBIT_REVERSAL("DBR","扣款冲正"),
    DEBIT_UNDO_REVERSAL("DBUR","扣款撤销冲正"),
    SIGN("SG","网银B2C跳转"),              //一期 网银
    VERIFY_SIGN("VS", "网银验签"),
    CHECK("CK", "检查参数"),
    RENDER("RD", "获取展现"),
    SINGLE_REFUND_QUERY("SRQ", "单退款查询"),
    BATCH_REFUND_QUERY("BRQ", "批量退款查询"),
    CODED("CODED","流转接口"),
    PRE_AUTH("PA", "预授权"),
    PRE_AUTH_UNDO("PAU", "预授权撤销"),
    PRE_AUTH_REVERSAL("PAR", "预授权冲正"),
    PRE_AUTH_UNDO_REVERSAL("PAUR", "预授权撤销冲正"),
    PRE_AUTH_DONE("PAD", "预授权完成"),
    PRE_AUTH_DONE_UNDO("PADU", "预授权完成撤销"),
    PRE_AUTH_DONE_REVERSAL("PADR", "预授权完成冲正"),
    PRE_AUTH_DONE_UNDO_REVERSAL("PADUR", "预授权完成撤销冲正"),
    CHECK_IN("CI", "签到"),
    CHECK_OUT("CO", "签退"),
    BATCH_SETTLE("BS", "批结"),
    BATCH_DETAIL_SUBMIT("BDS", "批上送"),
    AUTHENTICATE("AUTH", "鉴权"),
    AUTHENTICATE_QUERY("AQ", "鉴权查询"),
    AUTHENTICATE_VERIFY("AV", "鉴权验证"),
    TERMINATE("TERM","解约"),
    TERMINATE_VERIFY("TV","解约验证"),
    TERMINATE_QUERY("TQ","解约查询"),
    AUTH_ADVANCE("AUTHA", "签约推进"),
    SEND_MESSAGE("SM","发送短信"),
    NOTIFY("NT","通知"),
    FILE_REQUEST("FR","请求银行生成文件"),
    FILE_REQUEST_QUERY("FRQ","查询银行生成文件"),
    FILE_DOWN("FD","下载银行生成文件"),
    REFUND_NOTIFY("RNT","退款通知"),
    UPDATE_REPAYMENT_PLAN("URP","更新还款计划")
    ;

    /** 代码 */
    private final String code;
    /** 信息 */
    private final String message;


    /**
     * 接口是否为文件方式
     * @param type
     * @return
     */
    public static boolean isFile(FundChannelApiType type){
        return BATCH_FILE_PAY.equals(type)
            || BATCH_FILE_REFUND.equals(type);
    }

    /**
     * 接口是否为批量
     * @param type
     * @return
     */
    public static boolean isBatch(FundChannelApiType type){
        return BATCH_PAY.equals(type)
                    || BATCH_QUERY.equals(type)
                    || BATCH_REFUND.equals(type)||BATCH_FILE_PAY.equals(type)||BATCH_FILE_REFUND.equals(type);
    }

    /**
     * 接口是否为单笔操作
     * @param type
     * @return
     */
    public static boolean isSingle(FundChannelApiType type){
        return SINGLE_PAY.equals(type)
                    || SINGLE_QUERY.equals(type)
                    || SINGLE_REFUND.equals(type)
                    || DEBIT.equals(type)
                    || SIGN.equals(type)
                    || MANUAL_REFUND.equals(type)
                    || PRE_AUTH_DONE.equals(type);
    }

    public static boolean isMatchBizType(FundChannelApiType type,BizType bizType){
        if(bizType.equals(BizType.FUNDIN))
            return isFundin(type);
        if(bizType.equals(BizType.FUNDOUT))
            return isFundout(type);
        if(bizType.equals(BizType.REFUND))
            return isRefund(type);
        return false;
    }

    /**
     * 接口是否为入款操作
     * @param type
     * @return
     */
    public static boolean isFundin(FundChannelApiType type){
        return SIGN.equals(type)
                    || VERIFY_SIGN.equals(type)
                    || DEBIT.equals(type)
                    || DEBIT_REVERSAL.equals(type)
                    || PRE_AUTH.equals(type)
                    || PRE_AUTH_DONE.equals(type);
    }

    /**
     * 接口是否为出款操作
     * @param type
     * @return
     */
    public static boolean isFundout(FundChannelApiType type){
        return SINGLE_PAY.equals(type)
                    || BATCH_PAY.equals(type)
                    || BATCH_FILE_PAY.equals(type)
                    || SINGLE_QUERY.equals(type);
    }

    /**
     * 判断是否自动出款(API方式)；对于自动出款的请求，CMF须返回PE渠道编号等信息.
     *
     * @param type
     * @return
     */
    public static boolean isAutoFundout(FundChannelApiType type) {
        return SINGLE_PAY.equals(type)
            || BATCH_PAY.equals(type);
    }

    public static boolean isQuery(FundChannelApiType type){
    	return SINGLE_QUERY.equals(type)
    				|| BATCH_QUERY.equals(type)
    				|| SINGLE_REFUND_QUERY.equals(type)
    				|| BATCH_REFUND_QUERY.equals(type);
    }

    /**
     * 接口是否为冲退操作
     * @param type
     * @return
     */
    public static boolean isRefund(FundChannelApiType type){
        return BATCH_FILE_REFUND.equals(type)
                    || MANUAL_REFUND.equals(type)
                    || BATCH_REFUND.equals(type)
                    || SINGLE_REFUND.equals(type);
    }

    /**
     * 构造
     * @param code
     * @param message
     */
    FundChannelApiType(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 通过代码获取
     * @param code
     * @return
     */
    public static FundChannelApiType getByCode(String code) {
        if (StringUtils.isBlank(code)) {
            return null;
        }

        for (FundChannelApiType type : FundChannelApiType.values()) {
            if (type.getCode().equals(code)) {
                return type;
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

    public static boolean isInstNoRequired(FundChannelApiType apiType) {
        return isRefund(apiType) || isFundout(apiType) || SIGN.equals(apiType) || DEBIT.equals(apiType);
    }
}
