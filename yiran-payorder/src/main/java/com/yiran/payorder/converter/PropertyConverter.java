package com.yiran.payorder.converter;

import java.util.HashMap;
import java.util.Map;

import org.springframework.util.CollectionUtils;

import com.netfinworks.common.domain.Extension;
import com.netfinworks.common.domain.Kvp;
import com.netfinworks.common.lang.StringUtil;

/**
 * <p>参数转换器</p>
 */
public class PropertyConverter {
    private static final String EQUALS = "=";
    private static final String SPLIT  = ";";

    /**
     * 将MAP转换为字符串
     * @param propertyMap
     * @return
     */
    public static String convertFromMap(Map<String, String> propertyMap) {
        if (CollectionUtils.isEmpty(propertyMap)) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : propertyMap.entrySet()) {
            sb.append(entry.getKey()).append(EQUALS).append(entry.getValue()).append(SPLIT);
        }

        return sb.toString();
    }

    /**
     * 将字符串串转换为MAP
     * @param propertyString
     * @return
     */
    public static Map<String, String> convertToMap(String propertyString) {
        Map<String, String> propertyMap = new HashMap<String, String>();
        if (StringUtil.isBlank(propertyString)) {
            return propertyMap;
        }

        String[] propertyArray = propertyString.split(SPLIT);
        for (int i = 0; i < propertyArray.length; i++) {
            String[] keyValue = propertyArray[i].split(EQUALS);
            if (keyValue.length != 2) {
                throw new IllegalArgumentException("参数不合法");
            }

            propertyMap.put(keyValue[0], keyValue[1]);
        }

        return propertyMap;
    }
    
    
    /**
     * 将字符串串转换为Extension
     * @param propertyString
     * @return
     */
    public static Extension convertToExtension(String propertyString) {
    	Extension propertyMap = new Extension();
        if (StringUtil.isBlank(propertyString)) {
            return propertyMap;
        }

        String[] propertyArray = propertyString.split(SPLIT);
        for (int i = 0; i < propertyArray.length; i++) {
            String[] keyValue = propertyArray[i].split(EQUALS);
            if (keyValue.length != 2) {
                throw new IllegalArgumentException("参数不合法");
            }
            propertyMap.add(keyValue[0], keyValue[1]);
        }

        return propertyMap;
    }
    
    
    /**
     * 将字符串串转换为Extension
     * @param propertyString
     * @return
     */
    public static String convertFromExtension(Extension extension) {
    	if(extension==null)return null;
    	
    	Map<String, String> propertyMap = new HashMap<String, String>();
    	
    	for(Kvp kvp : extension.getEntryList()){
    		propertyMap.put(kvp.getKey(), kvp.getValue());
    	}
    	
    	return convertFromMap(propertyMap);
    }
}
