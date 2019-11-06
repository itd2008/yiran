package com.yiran.payorder.request;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.yiran.paychannel.enums.FundChannelApiType;

/**
 * <p>渠道请求基类</p>
 * 各类渠道请求此基类进行扩展
 */
public class ChannelRequest implements Serializable {
    private static final long     serialVersionUID = -2526536023145997502L;

    /** 资金渠道代码 */
    protected String              fundChannelCode;
    /** API类型 */
    protected FundChannelApiType  apiType;
    /** API地址 */
    protected String              apiUrl;
    /** 收单机构编码 */
    protected String              instCode;
    /** 机构订单号 */
    protected String              instOrderNo;
    /** 商户号 */
    protected String              merchantId;
    /** 机构回调地址, 后台回调 */
    protected String              callbackServerUrl;
    /** 机构回调地址, 页面跳转*/
    protected String              callbackPageUrl;
    /** 扩展信息 */
    protected Map<String, String> extension        = new ConcurrentHashMap<String, String>();

    /**
     * 默认构造
     */
    public ChannelRequest() {
    }

    /**
     * 根据API类型构造
     * @param fundChannelCode
     * @param apiType
     */
    public ChannelRequest(String fundChannelCode, FundChannelApiType apiType) {
        this.fundChannelCode = fundChannelCode;
        this.apiType = apiType;
    }

    public String getFundChannelCode() {
        return fundChannelCode;
    }

    public void setFundChannelCode(String fundChannelCode) {
        this.fundChannelCode = fundChannelCode;
    }

    public FundChannelApiType getApiType() {
        return apiType;
    }

    public void setApiType(FundChannelApiType apiType) {
        this.apiType = apiType;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public String getInstCode() {
        return instCode;
    }

    public void setInstCode(String instCode) {
        this.instCode = instCode;
    }

    public String getInstOrderNo() {
        return instOrderNo;
    }

    public void setInstOrderNo(String instOrderNo) {
        this.instOrderNo = instOrderNo;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getCallbackServerUrl() {
        return callbackServerUrl;
    }

    public void setCallbackServerUrl(String callbackServerUrl) {
        this.callbackServerUrl = callbackServerUrl;
    }

    public String getCallbackPageUrl() {
        return callbackPageUrl;
    }

    public void setCallbackPageUrl(String callbackPageUrl) {
        this.callbackPageUrl = callbackPageUrl;
    }

    public Map<String, String> getExtension() {
        return extension;
    }

    public void setExtension(Map<String, String> extension) {
        this.extension = extension;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
