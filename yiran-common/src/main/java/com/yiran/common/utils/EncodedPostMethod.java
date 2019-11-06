package com.yiran.common.utils;

import org.apache.commons.httpclient.methods.PostMethod;

public class EncodedPostMethod extends PostMethod {

    private HttpEncoding requestEncoding;
    private HttpEncoding responseEncoding;

    public EncodedPostMethod(String url) {
        super(url);
        requestEncoding = HttpEncoding.GB2312;
        responseEncoding = HttpEncoding.GB2312;
    }

    public EncodedPostMethod(String url, HttpEncoding httpEncoding) {
        super(url);
        requestEncoding = httpEncoding;
        responseEncoding = httpEncoding;
    }

    public EncodedPostMethod(String url, HttpEncoding reqEncoding, HttpEncoding respEncoding) {
        super(url);
        requestEncoding = reqEncoding;
        responseEncoding = respEncoding;
    }

    @Override
    public String getRequestCharSet() {
        return requestEncoding.getCode();
    }

    @Override
    public String getResponseCharSet() {
        return responseEncoding.getCode();
    }
}
