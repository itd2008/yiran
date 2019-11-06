package com.yiran.payorder.enums;

/**
 *
 * <p>监控日志--ActionID及警告级别枚举</p>
 *
 * ActionID定义请参见http://monitor.netfinworks.com  业务自检-->自检错误ID (错误上报接口-->错误类型及消息描述)
 */
public enum MonitorItem {
    DUPLIATE_REQUEST_EXCEPTION("67950500", MonitorItem.LEVEL_ERROR, "重复订单请求异常"),
    CHANNEL_ROUTE_EXCEPTION("67950501", MonitorItem.LEVEL_ERROR, "无可用路由"),
    CHANNEL_COMMUNICATE_EXCEPTION("67950502", MonitorItem.LEVEL_ERROR, "渠道通讯异常"),
    STATE_TRASICTION_EXCEPTION("67950503", MonitorItem.LEVEL_ERROR, "状态跃迁异常"),
    NOTIFY_PROCESS("67950504", MonitorItem.LEVEL_ERROR, "通知回调处理异常"),
    OTHERS_EXCEPTION("67950505", MonitorItem.LEVEL_ERROR, "一般系统异常"),
    JMS_SEND_EXCEPTION("67950506", MonitorItem.LEVEL_ERROR, "MQ通知PE异常"),
    RISKORDER_SEND_EXCEPTION("67950507", MonitorItem.LEVEL_ERROR, "风险订单发送异常"),
    PARAMETER_INVALID("PARAMETER_INVALID", MonitorItem.LEVEL_INFO, "PE请求验证不通过"), //暂时没申请ActionID
    NOTIFY_PARAMETER_INVALID("PARAMETER_INVALID2", MonitorItem.LEVEL_INFO, "通知请求验证失败"), //暂时没申请ActionID
    STATUS_NOT_CONSISTENT("67950001", MonitorItem.LEVEL_FATAL, "多次通知状态不一致"), //渠道重复回调CMF，结果状态不一致时，发起监控(比如一次失败，一次成功；或者一次成功，一次风险成功)
    NOTIFY_STATUS_INVALID("67950002", MonitorItem.LEVEL_ERROR, "渠道返回非明确状态"),
    QUESTIONABLE_RISK_ORDER("67950003", MonitorItem.LEVEL_WARN, "可疑、风险订单"),
    STATUS_NOT_CONSISTENT_MULTI("67950004", MonitorItem.LEVEL_FATAL, "拆分多笔订单状态不一致"), //渠道重复回调CMF，结果状态不一致时，发起监控(比如一次失败，一次成功；或者一次成功，一次风险成功)
    REMOVE_FROM_BATCH("67950004", MonitorItem.LEVEL_WARN, "剔除批次订单"),
    CACHE_UNAVAIBLE("67950600", MonitorItem.LEVEL_FATAL, "统一缓存不可用"),
    CMF_UNABAIBLE("67950601", MonitorItem.LEVEL_FATAL, "CMF服务不可用"),
    PROCESS_TIME_OUT("67950602", MonitorItem.LEVEL_WARN, "调用渠道服务超过5秒"),
    INST_ORDER_NON_EXIST("67950603", MonitorItem.LEVEL_WARN, "银行通知返回机构订单号不存在"),
    CHANNEL_PARAMETER_INVALID("CHANNEL_PARAMETER_INVALID", MonitorItem.LEVEL_INFO, "渠道返回结果验证不通过"), //暂时没申请ActionID
    ;

    private String actionId; //ID号，在监控系统--错误日志上报接口定义的
    private String monitorLevel; //报警级别
    private String actionName; //名称

    private static final String LEVEL_FATAL = "fatal";
    private static final String LEVEL_ERROR = "error";
    private static final String LEVEL_WARN  = "warn";
    private static final String LEVEL_INFO  = "info";

    MonitorItem(String actionId, String monitorLevel, String actionName) {
        this.actionId = actionId;
        this.monitorLevel = monitorLevel;
        this.actionName = actionName;
    }

    public String getActionId() {
        return actionId;
    }

    public String getMonitorLevel() {
        return monitorLevel;
    }

    public String getActionName() {
        return actionName;
    }

    public static MonitorItem getByCode(String actionId){
        for (MonitorItem e : MonitorItem.values()) {
            if (e.getActionId().equals(actionId)) {
                return e;
            }
        }
        return null;
    }
}
