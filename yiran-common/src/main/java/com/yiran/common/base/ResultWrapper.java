package com.yiran.common.base;

import java.io.Serializable;
/**
 * 返回结果对象
 * @author pandaa
 *
 * @param <T>
 */
public class ResultWrapper<T> implements Serializable {

    private static final long serialVersionUID = 6453592274095284751L;

    /**
     * 结果数据载体
     */
    private T data;

    /**
     * 结果码
     */
    private String code;

    /**
     * 结果消息
     */
    private String msg;

    /**
     * 是否执行成功
     */
    private boolean success;

    /**
     * 异常信息
     */
    private Exception exception;

    public T getData() {
        return data;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public boolean getSuccess() {
        return success;
    }

    public Exception getException() {
        return exception;
    }

    /**
     * 私有构造器
     *
     * @param code    响应编码
     * @param message 响应消息
     */
    private ResultWrapper(String code, String message) {
        this.code = code;
        this.msg = message;
        assert DefaultResultEnum.SUCCESS.getCode() != null;
        this.success = DefaultResultEnum.SUCCESS.getCode().equals(code);
    }

    /**
     * 实例化
     *
     * @param absResultEnum 抽象响应枚举
     * @return ResultWrapper
     */
    public static ResultWrapper newInstance(AbsResultEnum absResultEnum) {
        if (null == absResultEnum) {
            absResultEnum = DefaultResultEnum.SUCCESS;
        }
        return new ResultWrapper(absResultEnum.getCode(), absResultEnum.getMessage());
    }

    /**
     * 实例化
     *
     * @param code    响应编码
     * @param message 响应消息
     * @return ResultWrapper
     */
    public static ResultWrapper newInstance(String code, String message) {
        assert code != null && message != null;
        return new ResultWrapper(code, message);
    }

    /**
     * 设置数据
     *
     * @param data 数据
     * @return ResultWrapper
     */
    public ResultWrapper<T> putData(T data) {
        this.data = data;
        return this;
    }

    /**
     * 成功
     *
     * @return ResultWrapper
     */
    public static ResultWrapper ok() {
        return new ResultWrapper(DefaultResultEnum.SUCCESS.getCode(), DefaultResultEnum.SUCCESS.getMessage());
    }

    /**
     * 失败
     *
     * @return ResultWrapper
     */
    public static ResultWrapper fail() {
        return new ResultWrapper(DefaultResultEnum.FAIL.getCode(), DefaultResultEnum.FAIL.getMessage());
    }

    /**
     * 异常
     *
     * @return ResultWrapper
     */
    public static ResultWrapper error() {
        ResultWrapper resultWrapper = new ResultWrapper(DefaultResultEnum.ERROR.getCode(), DefaultResultEnum.ERROR.getMessage());
        resultWrapper.exception = new Exception(DefaultResultEnum.ERROR.getMessage());
        return resultWrapper;
    }

    /**
     * 其它
     *
     * @param message 响应消息
     * @return ResultWrapper
     */
    public static ResultWrapper other(String message) {
        return new ResultWrapper(DefaultResultEnum.FAIL.getCode(), message);
    }

    /**
     * 异常
     *
     * @param exception 异常
     * @return ResultWrapper
     */
    public static ResultWrapper error(Exception exception) {
        ResultWrapper resultWrapper = new ResultWrapper<>(DefaultResultEnum.ERROR.getCode(), null != exception ? exception.getMessage() : DefaultResultEnum.ERROR.getMessage());
        resultWrapper.exception = exception;
        return resultWrapper;
    }

    /**
     * 是否存在异常
     *
     * @return boolean
     */
    public boolean hasError() {
        return null != this.exception;
    }
}
