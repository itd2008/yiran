package com.yiran.paychannel.utils;

import org.springframework.util.CollectionUtils;

import com.netfinworks.common.lang.StringUtil;
import com.yiran.paychannel.constants.BasicConstant;

/**
 * <p>表达式工具类</p>
 */
public class ExpressionUtil implements BasicConstant {

    /**
     * 判断素组是否包含
     * @param more
     * @param less
     * @return
     */
    public static boolean contains(String[] more, String[] less) {
        if (more == null) {
            return less == null ? true : false;
        }

        if (less == null) {
            return false;
        }

        for (String value : less) {
            if (!contains(more, value)) {
                return false;
            }
        }

        return true;
    }

    /**
     * 判断数组是否包含值
     * @param array
     * @param value
     * @return
     */
    public static boolean contains(String[] array, String value) {
        if (StringUtil.isBlank(value)) {
            return false;
        }

        if (array == null) {
            return false;
        }

        return CollectionUtils.arrayToList(array).contains(value);
    }

    /**
     * 判断数组与素组字符串是否一致
     * 默认以“,”作为分隔符
     * @param array
     * @param arrayStr
     * @return
     */
    public static boolean match(String[] array, String arrayStr) {
        return match(array, arrayStr, null);
    }

    /**
     * 判断数组与素组字符串是否一致
     * @param array
     * @param arrayStr
     * @param spiltTag
     * @return
     */
    public static boolean match(String[] array, String arrayStr, String spiltTag) {
        if (StringUtil.isBlank(arrayStr)) {
            return false;
        }

        String[] valueArray = arrayStr.split(StringUtil.isBlank(spiltTag) ? CHAR_COMMA : spiltTag);
        return contains(array, valueArray) && contains(valueArray, array);
    }
    
    /**
     * 判断数组与值是否包含
     * @param r
     * @param set
     * @return
     */
    public boolean in(String r, String[] set) {
        if(r==null || set==null)return false;
        
        for(String l : set){
            if(r.equals(l))return true;
        }
        return false;
    }
    
    /**
     * 判断数组与值手否一致,后续不会使用
     * @param set
     * @param value
     * @return
     * @deprecated
     */
    public boolean is(String[] set, String value){
        return match(set, value);
    }
}
