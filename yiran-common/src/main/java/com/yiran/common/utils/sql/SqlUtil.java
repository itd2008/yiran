package com.yiran.common.utils.sql;

import com.yiran.common.utils.StringUtils;

/**
 * sql操作工具类
 * 
 * @author yiran
 */
public class SqlUtil
{
    /**
     * 防止sql注入 替换危险字符
     */
    public static String escapeSql(String value)
    {
        if (StringUtils.isNotEmpty(value))
        {
            value = value.replaceAll("\\(", "");
            value = value.replaceAll("\\)", "");
        }
        return value;
    }
}
