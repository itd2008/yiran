package com.yiran.payorder.enums;
/**
 * 支付订单处理结果代码
 * @author pandaa
 *
 */
public enum PayFundResultCode {
	SUCCESS("0000", "处理成功"),
    REQUEST_SUCCESS("0001", "提交CMF成功"), //已提交到机构，等待结果返回
    IN_PROCESS("0002", "处理中"), //可能出现提交异常，结果待确认 返回调用方 机构支付已提交
    FAILED("0003","处理失败"),//机构支付明确失败
    REFUND_MANUAL("0004","人工退单处理中"),//仅充退使用.
    SUBMIT_INST("0005","提交银行成功"),//用于出款的提交银行成功，该code下一定要返回fundChannelCode个PE

    CHANNEL_NOT_ACCESS("E001", "没有可用通道,可能订单金额超过该银行支付限额"),
    PARAMETER_INVALID("E002", "请求参数不正确"),

    ORDER_NOT_EXIST("E003", "订单不存在"), //仅查询时使用.
    ORDER_QUERY_ERROR("E004", "订单查询异常"), //仅查询时使用.

    UNKNOW_EXCEPTION("E999", "未知异常")
    ;

    /** 代码 */
    private final String code;

    /** 描述信息 */
    private final String message;

    PayFundResultCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }
}
