package com.yiran.paychannel.utils;

import com.netfinworks.common.lang.StringUtil;
import com.yiran.paychannel.constants.BasicConstant;

/**
 * <p>通用工具类</p>
 */
public class CommonUtil implements BasicConstant {
    /** 参数连接符 */
    public static final String AND_TAG   = "&";
    /** 参数等于 */
    public static final String EQUAL_TAG = "=";

    /**
     * 获取文件内容属性
     * @param context
     * @param key
     * @return
     */
    public static String getFileProperty(String context, String key) {
        String rawValue = StringUtil.substringAfter(context, key + EQUAL_TAG);
        return StringUtil.substringBefore(rawValue, AND_TAG);
    }

    /**
     * 根据原子信息组装键值
     * @param factors
     * @return
     */
    public static String key(String... factors) {
        StringBuilder sb = new StringBuilder();
        for (String factor : factors) {
            sb.append(factor).append(SPLIT_TAG);
        }

        return sb.substring(0, sb.length() - 1);
    }
}
