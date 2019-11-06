package com.yiran.api.utils;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;

public class CheckUtils {
	public static final String COMMON_FIELD = "flowID,initiator,";


	/**
	 * 验证对象是否为NULL,空字符串，空数组，空的Collection或Map(只有空格的字符串也认为是空串)
	 * @param obj 被验证的对象
	 * @param message 异常信息
	 */
	@SuppressWarnings("rawtypes")
	public static void notEmpty(Object obj, String message) {
		if (obj == null){
			throw new IllegalArgumentException(message + " must be specified");
		}
		if (obj instanceof String && obj.toString().trim().length()==0){
			throw new IllegalArgumentException(message + " must be specified");
		}
		if (obj.getClass().isArray() && Array.getLength(obj)==0){
			throw new IllegalArgumentException(message + " must be specified");
		}
		if (obj instanceof Collection && ((Collection)obj).isEmpty()){
			throw new IllegalArgumentException(message + " must be specified");
		}
		if (obj instanceof Map && ((Map)obj).isEmpty()){
			throw new IllegalArgumentException(message + " must be specified");
		}
	}

	/**
	 * 检查响应数据是否包含错误（key为customError、error_code）信息
	 * @param respData
	 * @return
     */
	public static boolean hasError(Map<String, String> respData){
		if (respData==null){
			return true;
		}
		if (respData.get("customError")!=null){
			return true;
		}
		if(!"0000".equals(respData.get("ret_code"))){
			return true;
		}
		return false;
	}
	public static String getCustomError(Map<String, String> respData){
		if (respData==null){
			return null;
		}
		return respData.get("customError");
	}
	public static String getRetCode(Map<String, String> respData){
		if (respData==null){
			return null;
		}
		return respData.get("ret_code");
	}
	public static String getRetMsg(Map<String, String> respData){
		if (respData==null){
			return null;
		}
		if (respData.get("ret_msg")!=null){
			return respData.get("ret_msg");
		}
		return null;
	}
	
}
