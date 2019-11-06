package com.yiran.paychannel.utils;

import java.util.HashMap;
import java.util.Map.Entry;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSON;
import com.netfinworks.common.domain.Extension;
import com.netfinworks.common.domain.Kvp;
import com.netfinworks.common.lang.StringUtil;

/**
 * <p>通用转换类</p>
 */
public class CommonConverter {
    private static final String KEY_SPLIT_TAG = "_";

    /**
     * 转换扩展信息
     * @param extension
     * @return
     */
    public static CustomMap convert(Extension extension) {
        CustomMap map = new CustomMap();
        if (extension == null) {
            return map;
        }
        for (Kvp kvp : extension.getEntryList()) {
            CustomMap.Entry entry = new CustomMap.Entry();
            entry.setKey(kvp.getKey());
            entry.setValue(kvp.getValue());
            map.getEntry().add(entry);
        }
        return map;
    }
    
    /**
     * 将扩展对象转换成json字符串保存到数据库
     * @param str
     * @return
     */
    public static java.util.Map<String, String> convertFromDb(String str) {
        java.util.Map<String, String> extension = new HashMap<String, String>();
        if (StringUtil.isEmpty(str)) {
            return extension;
        }
        String[][] list = JSON.parseObject(str, String[][].class);
        for (int i = 0; i < list.length; i++) {
            extension.put(convertKey(list[i][0]), list[i][1]);
        }
        return extension;
    }

    /**
     * 转换扩展信息
     * @param extension
     * @return
     */
    public static java.util.Map<String, String> convertMap(Extension extension) {
        java.util.Map<String, String> map = new HashMap<String, String>();
        if (extension == null) {
            return map;
        }
        for (Kvp kvp : extension.getEntryList()) {
            map.put(convertKey(kvp.getKey()), kvp.getValue());
        }
        return map;
    }

    /**
     * 根据MAP转换为extension
     * @param map
     * @return
     */
    public static Extension convertExtension(java.util.Map<String, String> map) {
        Extension extension = new Extension();
        if (CollectionUtils.isEmpty(map)) {
            return extension;
        }
        for (Entry<String, String> entry : map.entrySet()) {
            extension.add(convertKey(entry.getKey()), entry.getValue());
        }
        return extension;
    }
    
    /**
     * 根据MAP转换为extension
     * 不转换key
     * @param map
     * @return
     */
    public static Extension convertExtensionWithoutConvertKey(java.util.Map<String, String> map) {
        Extension extension = new Extension();
        if (CollectionUtils.isEmpty(map)) {
            return extension;
        }
        for (Entry<String, String> entry : map.entrySet()) {
            extension.add(entry.getKey(), entry.getValue());
        }
        return extension;
    }

    


    /**
     * 转换字符串
     * 例：
     * AAAA_BBB_CCC-->aaaaBbbCcc
     * aaA_B       -->aaaB
     * aSa_b_C     -->asaBC
     * @param origKey
     * @return
     */
    public static String convertKey(String origKey) {
        if (StringUtil.isBlank(origKey)) {
            return origKey;
        }
        if (!origKey.contains(KEY_SPLIT_TAG)) {
            return origKey;
        }
        String[] wordArray = origKey.trim().toLowerCase().split(KEY_SPLIT_TAG);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < wordArray.length; i++) {
            String word = wordArray[i];
            if (i == 0) {
                sb.append(word);
            } else {
                sb.append(StringUtil.substring(word, 0, 1).toUpperCase());
                sb.append(StringUtil.substring(word, 1, word.length()));
            }
        }

        return sb.toString();
    }

	/**
     * 将json字符串从数据库中反序列话后使用
     * @param extension
     * @return
     */
    public static String convertToDb(java.util.Map<String, String> extension) {
        if (CollectionUtils.isEmpty(extension)) {
            return StringUtil.EMPTY_STRING;
        }

        int size = 0;
        for (String key : extension.keySet()) {
            if (!ExtensionKeyUtil.isSaved(key)) {
                continue;
            }
            size++;
        }
        String[][] list = new String[size][2];
        int i = 0;
        for (Entry<String, String> entry : extension.entrySet()) {
            if (!ExtensionKeyUtil.isSaved(entry.getKey())) {
                continue;
            }
            list[i][0] = convertKey(entry.getKey());
            list[i][1] = entry.getValue();
            i = i + 1;
        }
        return JSON.toJSONString(list);
    }
}
