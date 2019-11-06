package com.yiran.pay.sdk.utils;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
public abstract class NameValuePairUtil {

    /**
     * 将Map转换为List<{@link NameValuePair}>.
     * 
     * @param map
     * @return
     */
    public static List<NameValuePair> convert(Map<String, String> map) {
        List<NameValuePair> nameValuePairs = new ArrayList<>();
        map.forEach((key, value) -> {
            nameValuePairs.add(new BasicNameValuePair(key, value));
        });

        return nameValuePairs;
    }

}
