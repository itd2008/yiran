package com.yiran.payorder.utils;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.springframework.util.ReflectionUtils;
import com.netfinworks.common.lang.StringUtil;
import com.yiran.payorder.common.PropertyExtensionMapping;
import com.yiran.payorder.enums.ChannelRequestExtensionMapping;
import com.yiran.payorder.request.ChannelFundRequest;
import com.yiran.payorder.service.IPropertyExtensionMapping;

/**
 * <p>对象属性工具类</p>
 */
public class PropertyValueUtil {
    /** 方法对象缓存 */
    private static final Map<String, Method> cacheMethod = new HashMap<String, Method>();

    /**
     * 获取机构订单属性
     * @param obj
     * @param extension
     * @return
     */
    public static Object getValue(Object obj, IPropertyExtensionMapping mapping) {
        Method propertyMethod = getMethod(MethodPrefix.GET, mapping);
        if (propertyMethod == null) {
            throw new IllegalArgumentException("GET方法不存在");
        }

        try {
            return propertyMethod.invoke(obj, new Object[] {});
        } catch (Exception e) {
            throw new IllegalArgumentException("获取值异常", e);
        }
    }

    /**
     * 设值
     * @param obj
     * @param extension
     * @param value
     */
    public static void setValue(Object obj, IPropertyExtensionMapping mapping, Object value) {
        if(value == null){
            return; //值为空,不需要设置
        }
        Method propertyMethod = getMethod(MethodPrefix.SET, mapping,
            new Class[] { value.getClass() });
        if (propertyMethod == null) {
            throw new IllegalArgumentException(mapping.getPropertyName() + "SET方法不存在");
        }

        try {
            propertyMethod.invoke(obj, new Object[] { value });
        } catch (Exception e) {
            throw new IllegalArgumentException("设值异常", e);
        }
    }

    /**
     * 获取方法
     * @param prefix
     * @param extension
     * @param paramTypes
     * @return
     */
    private static Method getMethod(MethodPrefix prefix, IPropertyExtensionMapping mapping,
                                    Class<?>... paramTypes) {
        String propertyName = mapping.getPropertyName();
        String methodName = prefix.getCode()
                            + StringUtil.substring(propertyName, 0, 1).toUpperCase()
                            + StringUtil.substring(propertyName, 1, propertyName.length());
        if (cacheMethod.containsKey(methodName)) {
            return cacheMethod.get(methodName);
        }

        // 反射获取
        Method propertyMethod = ReflectionUtils.findMethod(mapping.getObjectClass(), methodName,
            paramTypes);
        if (propertyMethod != null) {
            cacheMethod.put(methodName, propertyMethod);
        }

        return propertyMethod;
    }

    /** 方法前缀 */
    public enum MethodPrefix {
        GET("get"),

        SET("set");

        /** 代码 */
        private final String code;

        MethodPrefix(String code) {
            this.code = code;
        }

        public String getCode() {
            return code;
        }
    }

	/**
     * 设值
     * @param obj
     * @param extension
     * @param value
     */
    public static void setValue(Object obj, PropertyExtensionMapping mapping, Object value) {
        if(value == null){
            return; //值为空,不需要设置
        }
        Method propertyMethod = getMethod(MethodPrefix.SET, mapping,
            new Class[] { value.getClass() });
        if (propertyMethod == null) {
            throw new IllegalArgumentException(mapping.getPropertyName() + "SET方法不存在");
        }

        try {
            propertyMethod.invoke(obj, new Object[] { value });
        } catch (Exception e) {
            throw new IllegalArgumentException("设值异常", e);
        }
    }
    
    /**
     * 获取方法
     * @param prefix
     * @param extension
     * @param paramTypes
     * @return
     */
    private static Method getMethod(MethodPrefix prefix, PropertyExtensionMapping mapping,
                                    Class<?>... paramTypes) {
        String propertyName = mapping.getPropertyName();
        String methodName = prefix.getCode()
                            + StringUtil.substring(propertyName, 0, 1).toUpperCase()
                            + StringUtil.substring(propertyName, 1, propertyName.length());
        if (cacheMethod.containsKey(methodName)) {
            return cacheMethod.get(methodName);
        }

        // 反射获取
        Method propertyMethod = ReflectionUtils.findMethod(mapping.getObjectClass(), methodName,
            paramTypes);
        if (propertyMethod != null) {
            cacheMethod.put(methodName, propertyMethod);
        }

        return propertyMethod;
    }


}
