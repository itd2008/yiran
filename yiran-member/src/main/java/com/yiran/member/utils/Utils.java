package com.yiran.member.utils;

import java.io.UnsupportedEncodingException;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 
 * <p>公用小方法/p>
 */
public class Utils {

    private Utils() {

    }

    public static int getByteLen(String s) {
        if (s == null) {
            return 0;
        }
        try {
            return s.getBytes("GBK").length;
        } catch (UnsupportedEncodingException e) {
            return s.getBytes().length;
        }
    }
    
    /**
     * 打印java 对象
     * @param obj
     * @return
     */
    public static String toString(Object obj) {
        return ToStringBuilder.reflectionToString(obj, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
