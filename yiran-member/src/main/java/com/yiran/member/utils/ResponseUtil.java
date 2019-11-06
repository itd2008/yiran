/**
 * 
 */
package com.yiran.member.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yiran.member.base.Response;
import com.yiran.member.enums.ResponseCode;
import com.yiran.member.exception.MaIllegalArgumentException;


/**
 * <p>响应工具类</p>
 */
public class ResponseUtil {

    static Logger logger = LoggerFactory.getLogger(ResponseUtil.class);

    /**
     * 发生异常时填充响应对象
     * @param response
     * @param e
     */
    public static Response fillResponse(Response response, Exception e) {
        return fillResponse(response, e, null);
    }

    /**
     *  发生异常时填充响应对象,且打印日志
     * @param response
     * @param e
     * @param environment
     * @param msg
     * @return
     */
    public static Response fillResponse(Response response, Exception e, String msg) {
        if (response == null) {
            response = new Response();
        }
        ResponseCode respCode = null;
        String exMsg = e.getMessage();
        if (e instanceof MaIllegalArgumentException) {
            respCode = ResponseCode.ARGUMENT_ERROR;

        } else {
            respCode = ResponseCode.UNKNOWN;
        }
        String respMsg = respCode.getMessage();
        if (!StringUtils.isEmpty(exMsg)) {
            //响应码描述+异常信息 构成完整响应描述信息
            respMsg += ("[" + exMsg + "]");
        } else {
            respMsg += ("[" + e.getClass().getName() + "]");
        }
        response.setResponseCode(respCode.getCode());
        response.setResponseMessage(respMsg);
        if (StringUtils.isNotEmpty(msg)) {
            if (ResponseCode.UNKNOWN.equals(respCode)) {
                logger.error(
                    "[APP<-MA_1]".concat(msg).concat("未知异常,environment:"), e);
            } else {
                if (logger.isWarnEnabled()) {
                    Object[] errArray = new Object[] { response.getResponseCode(),
                            response.getResponseMessage()};
                    if (ResponseCode.ARGUMENT_ERROR.equals(respCode)) {
                        logger.warn(
                            "[APP<-MA_1]".concat(msg).concat("格式异常,错误码:{},错误信息:{},environment:{}"),
                            errArray);
                    } else {
                        logger.warn(
                            "[APP<-MA_1]".concat(msg).concat("业务异常,错误码:{},错误信息:{},environment:{}"),
                            errArray);
                    }
                }
            }
        }
        return response;
    }

    /**
     * 设置成功返回的信息
     * @param response
     */
    public static void setSuccessResponse(Response response) {
        response.setResponseCode(ResponseCode.SUCCESS.getCode());
        response.setResponseMessage(ResponseCode.SUCCESS.getMessage());
    }
}
