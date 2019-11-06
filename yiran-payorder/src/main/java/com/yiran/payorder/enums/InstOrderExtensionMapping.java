package com.yiran.payorder.enums;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;

import com.yiran.payorder.domain.InstFundinOrder;
import com.yiran.payorder.domain.InstFundoutOrder;
import com.yiran.payorder.domain.PayInstOrder;
import com.yiran.payorder.service.IPropertyExtensionMapping;


/**
 * <p>机构订单属性特殊指定扩展信息</p>
 */
public enum InstOrderExtensionMapping implements IPropertyExtensionMapping {
    // 帐户名称
    ACCOUNT_NAME("accountName", "accountName", InstFundoutOrder.class),
    // 卡号
    CARD_NO("cardNo", "accountNo", InstFundoutOrder.class),
    // 对公对私
    COMPANY_OR_PERSONAL("companyOrPersonal", "companyOrPersonal", InstFundoutOrder.class),
    // 地区代码
    AREA_CODE("areaCode", "areaCode", InstFundoutOrder.class),
    // 分支行
    BANK_BRANCH("bankBranch", "bankBranch", InstFundoutOrder.class),
    // 联行号
    BANK_LINE_NO("bankLineNo", "bankBranchCode", InstFundoutOrder.class),
    // 省份
    BANK_PROVINCE("bankProvince", "bankProvince", InstFundoutOrder.class),
    // 城市
    BANK_CITY("bankCity", "bankCity", InstFundoutOrder.class),
    // 银行代码
    BANK_CODE("bankCode", "bankCode", InstFundoutOrder.class),
    // 银行名称
    BANK_NAME("bankName", "bankName", InstFundoutOrder.class),
    // 出款卡类型
    OUT_CARD_TYPE("cardType", "cardType", InstFundoutOrder.class),
    // 用途
    PURPOSE("purpose", "purpose", InstFundoutOrder.class),
    // 付款机构
    PAYER_INST_CODE("payerInstCode", "payerInstCode", InstFundinOrder.class),
    // 协议号
    CONTRACT_NO("contractNo", "contractNo", InstFundinOrder.class),
    // 入款卡类型
    IN_CARD_TYPE("cardType", "cardType", InstFundinOrder.class),

    ;

    /** 扩展键值 */
    private final String                                                           extensionKey;
    /** 属性名称 */
    private final String                                                           propertyName;
    /** 对象类 */
    private final Class<? extends PayInstOrder>                                       objectClass;

    /** 缓存扩展键值队 */
    private static Map<Class<? extends PayInstOrder>, Set<String>>                    cachedKeySet  = new HashMap<Class<? extends PayInstOrder>, Set<String>>();
    /** 缓存映射列表 */
    private static Map<Class<? extends PayInstOrder>, Set<InstOrderExtensionMapping>> cachedMapping = new HashMap<Class<? extends PayInstOrder>, Set<InstOrderExtensionMapping>>();

    /**
     * 构造
     * @param extensionKey
     * @param propertyName
     * @param objectClass
     */
    InstOrderExtensionMapping(String extensionKey, String propertyName,
                              Class<? extends PayInstOrder> objectClass) {
        this.extensionKey = extensionKey;
        this.propertyName = propertyName;
        this.objectClass = objectClass;
    }

    /**
     * 获取键值队
     * @return
     */
    public static Set<String> getKeySet(Class<? extends PayInstOrder> instOrderClass) {
        if (cachedKeySet.containsKey(instOrderClass)) {
            return cachedKeySet.get(instOrderClass);
        }

        synchronized (InstOrderExtensionMapping.class) {
            if (cachedKeySet.containsKey(instOrderClass)) {
                return cachedKeySet.get(instOrderClass);
            }

            Set<String> tempSet = new HashSet<String>();
            for (InstOrderExtensionMapping extension : InstOrderExtensionMapping.values()) {
                if (instOrderClass.equals(extension.getObjectClass())) {
                    tempSet.add(extension.extensionKey);
                }
            }
            cachedKeySet.put(instOrderClass, tempSet);
            return tempSet;
        }
    }

    /**
     * 获取键值队
     * @return
     */
    public static Set<InstOrderExtensionMapping> getMappingSet(Class<? extends PayInstOrder> instOrderClass) {
        if (cachedMapping.containsKey(instOrderClass)) {
            return cachedMapping.get(instOrderClass);
        }

        synchronized (InstOrderExtensionMapping.class) {
            if (cachedMapping.containsKey(instOrderClass)) {
                return cachedMapping.get(instOrderClass);
            }

            Set<InstOrderExtensionMapping> tempSet = new HashSet<InstOrderExtensionMapping>();
            for (InstOrderExtensionMapping extension : InstOrderExtensionMapping.values()) {
                if (instOrderClass.equals(extension.getObjectClass())) {
                    tempSet.add(extension);
                }
            }
            cachedMapping.put(instOrderClass, tempSet);
            return tempSet;
        }
    }

    public String getExtensionKey() {
        return extensionKey;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public Class<? extends PayInstOrder> getObjectClass() {
        return objectClass;
    }
}
