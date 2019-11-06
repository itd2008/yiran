package com.yiran.weixin.utils;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class WeiXinJsonUtil {
	private static WeiXinJsonUtil ju;
	private static JsonFactory jf;
	private static ObjectMapper mapper;
	private WeiXinJsonUtil(){}
	
	public static WeiXinJsonUtil getInstance() {
		if(ju==null) ju = new WeiXinJsonUtil();
		return ju;
	}
	
	public static ObjectMapper getMapper() {
		if(mapper==null) {
			mapper = new ObjectMapper();
		}
		return mapper;
	}
	
	public static JsonFactory getFactory() {
		if(jf==null) jf = new JsonFactory();
		return jf;
	}
	
	public String obj2json(Object obj) {
		JsonGenerator jg = null;
		try {
			jf = getFactory();
			mapper = getMapper();
			StringWriter out = new StringWriter();
			jg = jf.createJsonGenerator(out);
			mapper.writeValue(jg, obj);
			return out.toString();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(jg!=null) jg.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public Object json2obj(String json,Class<?> clz) {
		try {
			mapper = getMapper();
			return mapper.readValue(json,clz);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
     * JSON字符串转成MAP
     * @param ext
     * @return
     */
    public static Map<String, String> jsonToMap(String ext) {
        Map<String, String> extMap = StringUtils.isBlank(ext) ? new HashMap<String, String>() : JSON
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
        Map<String, String> map = WeiXinJsonUtil.jsonToMap(source);
        map.put(key, value);
        return mapToJson(map);
    }

    public static String addValue(String source, Map<String, String> map) {
        Map<String, String> sourceMap = WeiXinJsonUtil.jsonToMap(source);
        if (!CollectionUtils.isEmpty(map)) {
            sourceMap.putAll(map);
        }
        return mapToJson(sourceMap);
    }
}
