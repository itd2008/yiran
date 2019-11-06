package com.yiran.paychannel.utils;

import com.yiran.paychannel.enums.UnUsedExtensionKey;

/**
 * 判断key利用信息
 *
 */
public class ExtensionKeyUtil {

	/**
	 * 判断key是否需要使用
	 * @param key
	 * @return
	 */
	public static boolean isUsed(String key){
		UnUsedExtensionKey unUsedExtensionKey = UnUsedExtensionKey.getByKey(key);
		if(unUsedExtensionKey != null){
			return unUsedExtensionKey.used;
		}
		return true;
	}
	
	/**
	 * 判断key是否需要保存
	 * @param key
	 * @return
	 */
	public static boolean isSaved(String key){
		UnUsedExtensionKey unUsedExtensionKey = UnUsedExtensionKey.getByKey(key);
		if(unUsedExtensionKey != null){
			return unUsedExtensionKey.saved;
		}
		return true;
	}
	
	/**
	 * 
	 * @param key
	 * @return
	 */
	public static boolean isLogged(String key){
		UnUsedExtensionKey unUsedExtensionKey = UnUsedExtensionKey.getByKey(key);
		if(unUsedExtensionKey != null){
			return unUsedExtensionKey.logged;
		}
		return true;
	}
}
