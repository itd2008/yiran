package com.yiran.payorder.constant;

import com.netfinworks.common.util.money.Money;

/**
 * <p>基础常量定义</p>
 * @author sean won
 * @version $Id: BasicConstant.java, v 0.1 2010-12-22 下午04:39:41 sean won Exp $
 */
public interface BasicConstant {
    /** 编码 */
    public static final String  ENCODER                    = "UTF-8";
    /** 文件相关编码 */
    public static final String  FILE_ENCODE                = "GBK";
    /** 零金额字符串 */
    public static final String  ZERO_MONEY_STRING          = "0.0";
    /** 零金额 */
    public static final Money   ZERO_MONEY                 = new Money(ZERO_MONEY_STRING);
    /** 真标记 */
    public static final String  TRUE_STRING                = "Y";
    /** 假标记 */
    public static final String  FALSE_STRING               = "N";
    /** 占位符前缀 */
    public static final String  PLACEHOLDER_PREFIX         = "$";
    /** 分隔符 */
    public static final String  SPLIT_TAG                  = "-";
    /** 逗号 */
    public static final String  CHAR_COMMA                 = ",";
    /** 分号 */
    public static final String  CHAR_SEMICOLON             = ";";

    /**统一缓存过滤规则key*/
    public static final String  MEMCACHED_FILTER_RULE_KEY  = "com.netfinworks.cmf.filterrule.";

    /** 后台任务触发监听器名称 */
    public static final String  DAEMON_TRIGGER_LISTENER    = "daemonTriggerListener";
    /** 后台任务触发器名称前缀 */
    public static final String  DAEMON_TRIGGER_NAME_PREFIX = "DAEMON_";

    /** 信息目录名称 */
    public static final String  META_INFO                  = "META-INF";
    /** 未知主机信息 */
    public static final String  UNKNOW_HOST                = "UNKNOW_HOST";
    /** 渠道API, 否需要校验通知结果*/
    public final static String  NEED_RISK_CHECK            = "needRiskCheck";
    /** API's URI is MULE (means that it's old API) */
    public final static String  API_URL_MULE               = "MULE";
    /** 查询API, 时间起始点.*/
    public final static String  QUERY_TIME_FENCE           = "queryTimeFence";
    /** 查询API, 查询时间段长度*/
    public final static String  QUERY_DURATION             = "queryDuration";
    /** 查询API, 开始时间   40*/
    public final static int     QUERY_START_TIME           = 40;
    /** 查询API, 结束时间   10 */
    public final static int     QUERY_END_TIME             = 10;
    /** 返回码， 0表示成功 */
    public static final Integer SUCCESS                    = 0;
    /** 返回码， -1表示失败 */
    public static final Integer FAILURE                    = -1;
    /** 请求地址前缀 */
    public static final String  PREFIX_API_URI             = "http://";

    /** 中间分隔符 */
    public static final String  INTER_SLIPTER              = "/";

    public static final String  POSFIX_HTML                = ".htm";

    /** 渠道通讯异常信息*/
    public static final String  ERROR_COMM                 = "发送渠道通讯异常";
    /** 银行回调页面地址 */
    public static final String  PAGE_URL                   = "pageUrl";
    /** 银行回调后台地址 */
    public static final String  SERVER_URL                 = "serverUrl";
    
    /** 自动退款转人工退款，延迟时间*/
    public final static String  TANSFER_DELAY_TIME             = "tansferDelayTime";
}
