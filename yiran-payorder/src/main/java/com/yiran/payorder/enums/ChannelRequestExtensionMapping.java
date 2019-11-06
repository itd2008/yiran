package com.yiran.payorder.enums;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;

import com.yiran.payorder.common.PropertyExtensionMapping;
import com.yiran.payorder.domain.FundoutRequest;
import com.yiran.payorder.request.ChannelFundRequest;


/**
 * <p>渠道请求对象属性特殊指定扩展信息</p>
 *
 */
public enum ChannelRequestExtensionMapping implements PropertyExtensionMapping {

    //出款部分
    ID_CARD("idCard", "identityNo", FundoutRequest.class),
    ID_TYPE("idType", "identityType", FundoutRequest.class),

    //退款部分
    ;

    /** 扩展键值 */
    private final String                                                                         extensionKey;
    /** 属性名称 */
    private final String                                                                         propertyName;
    /** 对象类 */
    private final Class<? extends ChannelFundRequest>                                            objectClass;

    /** 缓存扩展键值队 */
    private static Set<String>                                                                   cachedKeySet;
    /** 缓存映射列表 */
    private static Map<Class<? extends ChannelFundRequest>, Set<ChannelRequestExtensionMapping>> cachedMapping = new HashMap<Class<? extends ChannelFundRequest>, Set<ChannelRequestExtensionMapping>>();

    /**
     * 构造
     * @param extensionKey
     * @param propertyName
     * @param objectClass
     */
    ChannelRequestExtensionMapping(String extensionKey, String propertyName,
                                   Class<? extends ChannelFundRequest> objectClass) {
        this.extensionKey = extensionKey;
        this.propertyName = propertyName;
        this.objectClass = objectClass;
    }

    /**
     * 获取键值队
     * @return
     */
    public static Set<String> getKeySet() {
        if (!CollectionUtils.isEmpty(cachedKeySet)) {
            return cachedKeySet;
        }

        synchronized (ChannelRequestExtensionMapping.class) {
            if (!CollectionUtils.isEmpty(cachedKeySet)) {
                return cachedKeySet;
            }

            Set<String> tempSet = new HashSet<String>();
            for (ChannelRequestExtensionMapping extension : ChannelRequestExtensionMapping.values()) {
                tempSet.add(extension.getExtensionKey());
            }

            cachedKeySet = tempSet;
            return cachedKeySet;
        }
    }

    /**
     * 获取键值队
     * @return
     */
    public static Set<ChannelRequestExtensionMapping> getMappingSet(Class<? extends ChannelFundRequest> instOrderClass) {
        if (cachedMapping.containsKey(instOrderClass)) {
            return cachedMapping.get(instOrderClass);
        }

        synchronized (ChannelRequestExtensionMapping.class) {
            if (cachedMapping.containsKey(instOrderClass)) {
                return cachedMapping.get(instOrderClass);
            }

            Set<ChannelRequestExtensionMapping> tempSet = new HashSet<ChannelRequestExtensionMapping>();
            for (ChannelRequestExtensionMapping extension : ChannelRequestExtensionMapping.values()) {
                if (instOrderClass.equals(extension.getObjectClass())) {
                    tempSet.add(extension);
                }
            }

            cachedMapping.put(instOrderClass, tempSet);
            return tempSet;
        }
    }

    @Override
    public String getExtensionKey() {
        return extensionKey;
    }

    @Override
    public String getPropertyName() {
        return propertyName;
    }

    @Override
    public Class<? extends ChannelFundRequest> getObjectClass() {
        return objectClass;
    }
}
