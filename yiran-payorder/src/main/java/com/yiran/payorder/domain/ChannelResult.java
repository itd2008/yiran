package com.yiran.payorder.domain;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.netfinworks.biz.common.util.BaseResult;
import com.yiran.paychannel.enums.FundChannelApiType;

/**
 * <p>渠道结果对象</p>
 * 针对不同业务结果扩展此基类
 */
public class ChannelResult extends BaseResult {
    private static final long  serialVersionUID = 9159442540047469222L;

    /** API类型 */
    private FundChannelApiType apiType;
    /** 渠道统一结果代码，进过改造的渠道不填写此值，由CMF完成映射 */
    private String             resultCode;
    /** 提交机构订单号 */
    private String             instOrderNo;
    /** 机构返回流水号 */
    private String             instReturnOrderNo;
    /** 渠道API结果码 ， 为资金结构实际返回码 */
    private String             apiResultCode;
    /** 渠道API结果子码 */
    private String             apiResultSubCode;
    /** 描述信息 */
    private String             apiResultMessage;
    /** 描述信息 */
    private String             apiResultSubMessage;
    /** 扩展信息 */
    private String             extension;

    /**
     * html表单(支付宝，微信，第三方支付返回表单的情景)
     */
    private String 				fromHtml;

    /**
     * 默认构造
     */
    public ChannelResult() {
    }

    /**
     * 构造结果及信息构造
     * @param success
     */
    public ChannelResult(boolean success) {
        this.success = success;
    }

    /**
     * 根据结果代码构造
     * @param success
     * @param resultCode
     */
    public ChannelResult(boolean success, String resultCode) {
        this.success = success;
        this.resultCode = resultCode;
    }

    /**
     * 根据结果代码构造
     * @param success
     * @param resultCode
     * @param message
     */
    public ChannelResult(boolean success, String resultCode, String message) {
        this.success = success;
        this.resultCode = resultCode;
        this.resultMessage = message;
    }

    /**
     * 根据结果代码构造
     * @param success
     * @param resultCode
     * @param message
     * @param apiType
     */
    public ChannelResult(boolean success, String resultCode, String message,
                         FundChannelApiType apiType) {
        this.success = success;
        this.resultCode = resultCode;
        this.resultMessage = message;
        this.apiType = apiType;
    }

    public FundChannelApiType getApiType() {
        return apiType;
    }

    public void setApiType(FundChannelApiType apiType) {
        this.apiType = apiType;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getInstOrderNo() {
        return instOrderNo;
    }

    public void setInstOrderNo(String instOrderNo) {
        this.instOrderNo = instOrderNo;
    }

    public String getInstReturnOrderNo() {
        return instReturnOrderNo;
    }

    public void setInstReturnOrderNo(String instReturnOrderNo) {
        this.instReturnOrderNo = instReturnOrderNo;
    }

    public String getApiResultCode() {
        return apiResultCode;
    }

    public void setApiResultCode(String apiResultCode) {
        this.apiResultCode = apiResultCode;
    }

    public String getApiResultSubCode() {
        return apiResultSubCode;
    }

    public void setApiResultSubCode(String apiResultSubCode) {
        this.apiResultSubCode = apiResultSubCode;
    }

  
    public String getApiResultMessage() {
        return apiResultMessage;
    }

    public void setApiResultMessage(String apiResultMessage) {
        this.apiResultMessage = apiResultMessage;
    }

    public String getApiResultSubMessage() {
        return apiResultSubMessage;
    }

    public void setApiResultSubMessage(String apiResultSubMessage) {
        this.apiResultSubMessage = apiResultSubMessage;
    }
    
    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    
    public String getFromHtml() {
		return fromHtml;
	}

	public void setFromHtml(String fromHtml) {
		this.fromHtml = fromHtml;
	}

	@Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
