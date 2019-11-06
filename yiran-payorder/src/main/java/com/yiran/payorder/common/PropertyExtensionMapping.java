package com.yiran.payorder.common;

/**
 * <p>属性与扩展映射关系</p>
 */
public interface PropertyExtensionMapping {
    /**
     * 获取扩展信息键值
     * @return
     */
    public String getExtensionKey();

    /**
     * 获取属性名称
     * @return
     */
    public String getPropertyName();

    /**
     * 获取对象CLASS
     * @return
     */
    public Class<?> getObjectClass();
}
