package com.yiran.pay.sdk.service.impl;

import com.yiran.pay.sdk.service.EncryptAndDecryptService;

abstract class AbstractEncryptAndDecryptServiceImpl implements EncryptAndDecryptService {

    /**
     * 加密
     *
     * @param key
     * @param data
     * @return
     */
    @Override
    public Object encrypt(String key, String data) {
        return null;
    }

    /**
     * 解密
     *
     * @param key
     * @param data
     * @return
     */
    @Override
    public Object decrypt(String key, String data) {
        return null;
    }
}
