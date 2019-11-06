package com.yiran.paychannel.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.netfinworks.common.lang.StringUtil;

/**
 * <p>JSON字符串转换工具</p>
 */
public class MapUtil {
    /**
     * JSON字符串转成MAP
     * @param ext
     * @return
     */
    public static Map<String, String> jsonToMap(String ext) {
        Map<String, String> extMap = StringUtil.isBlank(ext) ? new HashMap<String, String>() : JSON
            .parseObject(ext, new TypeReference<Map<String, String>>() {
            });
        return extMap;
    }

    /**
     * MAP转换成JSON
     * @param map
     * @return
     */
    public static String mapToJson(Map<String, String> map) {
        if (CollectionUtils.isEmpty(map)) {
            return null;
        }
        return JSON.toJSONString(map, SerializerFeature.UseISO8601DateFormat);
    }

    /**
     * MAP新增VALUE
     * @param source
     * @param key
     * @param value
     * @return
     */
    public static String addValue(String source, String key, String value) {
        Map<String, String> map = MapUtil.jsonToMap(source);
        map.put(key, value);
        return mapToJson(map);
    }

    public static String addValue(String source, Map<String, String> map) {
        Map<String, String> sourceMap = MapUtil.jsonToMap(source);
        if (!CollectionUtils.isEmpty(map)) {
            sourceMap.putAll(map);
        }
        return mapToJson(sourceMap);
    }
}
