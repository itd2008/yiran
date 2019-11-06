package com.yiran.payorder.utils;

import com.netfinworks.common.lang.StringUtil;

/**
 * <p>业务工具类</p>
 */
public class BizUtil {
    private static final String DETERMINED_TAG = "3";

    /**
     * 是否确定性入款
     * @param paymentCode
     * @return
     */
    public static boolean isDeterminedFundin(String paymentCode) {
        if (StringUtil.isNotBlank(paymentCode) && paymentCode.length() > 2) {
            String tag = paymentCode.substring(1, 2);
            if (DETERMINED_TAG.equals(tag)) {
                return true;
            }
        }
        return false;
    }
}
