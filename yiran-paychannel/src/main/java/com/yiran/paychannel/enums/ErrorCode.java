package com.yiran.paychannel.enums;

import java.text.MessageFormat;

/**
 *
 * <p>Error Code 枚举类</p>
 */
public enum ErrorCode {

    //状态机异常
    SMC_TRANSITION_UNDEFINED("B01101", "不合法的跃迁函数,ID[{0}]、当前状态编码[{1}]、跃迁动作[{2}]、其他信息[{3}]"),
    SMC_STATE_UNDEFINED("B01102", "未定义的跃迁状态,ID[{0}]、当前状态编码[{1}]、跃迁动作[{2}]"),


    EXCEPTION("100","runtime exception"),
    RUNTIME_EXCEPTION("101","runtime exception"),

    COMMUNICATE_ERROR("200","communicate failed"),
    VALIDATE_ERROR("300","validate exists exception"),

    OPERAT_ERROR("400","operate exists exception"),
    ASSESSMENT_REQUIRED("401", "订单需要经过审核"),
    CHANNEL_UNAVAILABLE("402", "当前渠道不可用"),
    FUND_VERIFICATION_ERROR("403", "渠道返回信息与原机构订单信息不匹配"),
    NO_ORIGIN_ORDER("404", "原始订单不存在"),
    NO_VALID_CHANNEL("405", "找不到匹配的渠道"),
    ORDER_EXISTED("406", "订单正在处理中,请稍后再查"),
    OUTBOUND_ERROR("407","调用外部渠道时发送系统异常"),
    JMS_SEND_ERROR("408","通过MQ发送消息失败"),
    INVALID_PARRAMETER("409","传入参数不正确"),
    NO_VALID_COMPARATOR("410","没有合适的比较器"),
    PARSE_ERROR("411","解析文件报错"),
    GROOVY_EXECUTE_ERROR("412","groovy脚本执行出错"),
    ROUTE_ERROR_NOT_SUPPORT_REFUND("413","路由异常,资金源不支持退款,需要提交出款流程用于冲退."),
    ROUTE_ERROR_PARAM_NEED_PAY_MODE("414","路由异常,支付方式参数不能为空"),
    ROUTE_ERROR_PARAM_NEED_BIZ_TYPE("415","路由异常,业务类型不能为空"),
    ROUTE_ERROR_PARAM_NEED_TARGET_INST("422","路由异常,目标机构不能为空"),
    ROUTE_ERROR_PARAM_NEED_CHANNEL_CODE("416","路由异常,冲退路由时,渠道编码不能为空"),
    ROUTE_ERROR_NO_VALID_ROUTE_RULE("417","路由异常,没有匹配路由规则"),
    ROUTE_ERROR_NO_VALID_CHANNEL("418","路由异常,没有匹配资金源"),
    ROUTE_ERROR_FILTER_SCRIPT_ERROR("419","路由异常,过滤器执行脚本出错"),
    ROUTE_ERROR_RATER_SCRIPT_ERROR("423","路由异常,算分器执行脚本出错"),
    ROUTE_ERROR_REFRESH_VERSION_ERROR("420","路由异常,刷新版本出错"),
    ROUTE_ERROR_EXT_CONFIG_ERROR("421","路由异常,扩展信息配置出错"),
    
    NON_EXIST_INSTORDER_ERROR("422","机构订单号不存在"),

    BATCH_ERROR_STATUS("501","机构批量出错,状态不对"),
    BATCH_ERROR_RESULT_NULL("502","结果订单没找到"),


    WRONG_STATE_EXCEPTION("600","状态不一致异常"),

    SYSTEM_ERROR("999", "system error");


    private String errorCode;
    private String errorMessage;

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getFormatErrorMessage(String arg) {
        return getFormatErrorMessage(new String[]{arg});
    }

    public String getFormatErrorMessage(String[] args) {
        MessageFormat form = new MessageFormat(errorMessage);
        return form.format(args);
    }

    ErrorCode(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    /**
     * 工厂方法,从指定errorcode值获取对应的枚举
     * @param errorCode
     * @return
     */
    public static ErrorCode getFromErrorCode(String errorCode){
        for (ErrorCode e : ErrorCode.values()) {
            if (e.getErrorCode().equals(errorCode)) {
                return e;
            }
        }
        return null;
    }
}
