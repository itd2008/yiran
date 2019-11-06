package com.yiran.api.utils;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.netfinworks.common.lang.StringUtil;
import com.yiran.api.constants.ExtensionKeys;
import com.yiran.api.constants.ReturnCode;
import com.yiran.paychannel.enums.ResponseType;

public class ChannelHelper {


    public static final String            INERNAL_ERROR_CODE                  = ReturnCode.FAILED;

    public static final String            INERNAL_ERROR_CODE_MSG              = "渠道内部异常";

    public static final String            INST_NOT_RETURN_SUCH_ORDER_CODE     = "NNNNNNNN";

    public static final String            INST_NOT_RETURN_SUCH_ORDER_CODE_MSG = "机构未返回该订单信息(机构未找到)";

    public static final String            OPERATE_NOT_SEND_TO_INST            = "FFFFFFFF";

    public static final String            OPERATE_NOT_SEND_TO_INST_MSG        = "发送到机构前出错，操作失败";

    public static final String            INST_RETURN_ERROR_NO_CODE           = "IEIEIEIE";

    public static final String            INST_RETURN_ERROR_NO_CODE_MSG       = "机构返回异常无状态码";

    private String                        instReturnCode;

    private String                        instReturnMsg;

    private String                        instReturnSubCode;

    private String                        instReturnSubMsg;

    private java.util.Map<String, String> instReturnData;

    private String                        responseToInstData;

    private ResponseType                  responseType;

    private String                        userPayDomain;

    private String                        userPayIP;

    public String getUserPayDomain() {
        return userPayDomain;
    }

    public void setUserPayDomain(String userPayDomain) {
        this.userPayDomain = userPayDomain;
    }

    public String getUserPayIP() {
        return userPayIP;
    }

    public void setUserPayIP(String userPayIP) {
        this.userPayIP = userPayIP;
    }

    public java.util.Map<String, String> getInstReturnData() {
        return instReturnData;
    }

    public void setInstReturnData(java.util.Map<String, String> instReturnData) {
        this.instReturnData = instReturnData;
    }

    public void setInstReturnData(String key, String retData) {
        if (this.instReturnData == null)
            this.instReturnData = new HashMap<String, String>();
        this.instReturnData.put(key, retData);
    }

    public String getResponseToInstData() {
        return responseToInstData;
    }

    public void setResponseToInstData(String responseToInstData) {
        this.responseToInstData = responseToInstData;
    }

    public ResponseType getResponseType() {
        return responseType;
    }

    public void setResponseType(ResponseType responseType) {
        this.responseType = responseType;
    }

    public String getInstReturnCode() {
        return instReturnCode;
    }

    public void setInstReturnCode(String instReturnCode) {
        this.instReturnCode = instReturnCode;
    }

    public String getInstReturnMsg() {
        return instReturnMsg;
    }

    public void setInstReturnMsg(String instReturnMsg) {
        this.instReturnMsg = instReturnMsg;
    }

    public String getInstReturnSubCode() {
        return instReturnSubCode;
    }

    public void setInstReturnSubCode(String instReturnSubCode) {
        this.instReturnSubCode = instReturnSubCode;
    }

    public String getInstReturnSubMsg() {
        return instReturnSubMsg;
    }

    public void setInstReturnSubMsg(String instReturnSubMsg) {
        this.instReturnSubMsg = instReturnSubMsg;
    }

    /**
     * 渠道内部异常，返回CMF统一处理
     * @return
     */
    public static Map<String,String> buildErrorReturn() {

        ChannelHelper helper = new ChannelHelper();
        helper.setInstReturnCode(INERNAL_ERROR_CODE);
        helper.setInstReturnMsg(INERNAL_ERROR_CODE_MSG);
        helper.setResponseType(ResponseType.ERROR);

        return helper.build();
    }

    /**
     * 机构未返回数据，部分银行查无此订单等，但无状态码。
     * 根据银行不同，可判定为未提交到银行，或者查询失败
     * @return
     */
    public static Map<String,String> buildInstNoData() {
        ChannelHelper helper = new ChannelHelper();
        helper.setInstReturnCode(INST_NOT_RETURN_SUCH_ORDER_CODE);
        helper.setInstReturnMsg(INST_NOT_RETURN_SUCH_ORDER_CODE_MSG);
        return helper.build();
    }

    /**
     * 部分银行通讯成功，但是由于验签等错误，仅有一个描述，或者描述中部分文字为返回码
     * 这种返回，由渠道定义返回码。
     * 该返回码并不代表机构返回了结果，而是表示机构的一类错误
     * @return
     */
    public static Map<String,String> buildInstReturnErrorNoCode() {

        ChannelHelper helper = new ChannelHelper();
        helper.setInstReturnCode(INST_RETURN_ERROR_NO_CODE);
        helper.setInstReturnMsg(INST_RETURN_ERROR_NO_CODE_MSG);
        return helper.build();
    }

    /**
     * 渠道內部错误，发送到机构前失败，操作失败。
     * @return
     */
    public static Map<String,String> buildNotSendToInstFailed() {

        ChannelHelper helper = new ChannelHelper();
        helper.setInstReturnCode(OPERATE_NOT_SEND_TO_INST);
        helper.setInstReturnMsg(OPERATE_NOT_SEND_TO_INST_MSG);
        return helper.build();
    }

    public Map<String,String> build() {

        Map<String,String> data = new HashMap<String,String> ();

        if(instReturnCode!=null)
        data.put(ExtensionKeys.INST_RETURN_CODE, this.instReturnCode);
        if(instReturnMsg!=null)
        data.put(ExtensionKeys.INST_RETURN_MSG, this.instReturnMsg);
        if(instReturnSubCode!=null)
        data.put(ExtensionKeys.INST_RETURN_SUB_CODE, this.instReturnSubCode);
        if(instReturnSubMsg!=null)
        data.put(ExtensionKeys.INST_RETURN_SUB_MSG, this.instReturnSubMsg);

        if (this.responseType != null)
            data.put(ExtensionKeys.RESPONSE_TO_INST_TYPE, this.responseType.name());
        if (this.instReturnData != null)
            data.put(ExtensionKeys.BANK_EXT_INFO, JSON.toJSONString(this.instReturnData));

        if (!StringUtil.isBlank(this.userPayDomain))
            data.put(ExtensionKeys.WEIBO_PAY_USER_PAY_DOMAIN, this.userPayDomain);

        if (!StringUtil.isBlank(this.userPayIP))
            data.put(ExtensionKeys.WEIBO_PAY_USER_PAY_IP, this.userPayIP);

        if (!StringUtil.isBlank(this.responseToInstData))
            data.put(ExtensionKeys.RETURN_TO_INST_INFO, this.responseToInstData);
        return data;
    }
    

}
