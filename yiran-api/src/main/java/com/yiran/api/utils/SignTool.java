package com.yiran.api.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import com.alibaba.fastjson.JSONObject;
import com.yiran.api.constants.BankPropertyKeyBase;

public class SignTool {

    public static String genSign(JSONObject reqObj,Properties properties)
    {
        String sign = reqObj.getString("sign");
//        String sign_type=reqObj.getString("sign_type");
        // // 生成待签名串
        String sign_src = genSignData(reqObj);
        System.out.println("商户[" + reqObj.getString("oid_partner") + "]待签名原串:"
                + sign_src);
        
        sign = getSignRSA(sign_src,properties);
        System.out.println("商户[" + reqObj.getString("oid_partner") + "]签名串:"
                + sign);

        return sign;
    }
    /**
     * RSA签名验证
     * 
     * @param reqObj
     * @return
     */
    public static String getSignRSA(String sign_src,Properties properties)
    {
    	String PRIVATE_KEY = properties.getProperty(BankPropertyKeyBase.PRIVATE_KEY);
        return TraderRSAUtil.sign(PRIVATE_KEY, sign_src);
    }

    /**
     * 生成待签名串
     * 
     * @param paramMap
     * @return
     */    public static String genSignData(JSONObject jsonObject)
    {
        StringBuffer content = new StringBuffer();

        // 按照key做首字母升序排列
        List<String> keys = new ArrayList<String>(jsonObject.keySet());
        Collections.sort(keys, String.CASE_INSENSITIVE_ORDER);
        for (int i = 0; i < keys.size(); i++)
        {
            String key = (String) keys.get(i);
            // sign 和ip_client 不参与签名
            if ("sign".equals(key))
            {
                continue;
            }
            String value = (String) jsonObject.getString(key);
            // 空串不参与签名
            if (null==value)
            {
                continue;
            }
            content.append((i == 0 ? "" : "&") + key + "=" + value);

        }
        String signSrc = content.toString();
        if (signSrc.startsWith("&"))
        {
            signSrc = signSrc.replaceFirst("&", "");
        }
        return signSrc;
    }

}
