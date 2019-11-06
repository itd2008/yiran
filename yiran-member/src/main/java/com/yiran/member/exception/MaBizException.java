package com.yiran.member.exception;

import com.yiran.member.enums.ResponseCode;

/**
 *
 * <p>MA 业务异常</p>
 */
public class MaBizException extends Exception {
    private static final long serialVersionUID = 5558419943675631531L;

    /**
     * 应答码
     */
    private ResponseCode      code;

    /**
     * 构造方法
     * @param code
     */
    public MaBizException(ResponseCode code) {
        super(code.getMessage());
        this.code = code;
    }

    /**
     * 构造方法
     * @param code
     * @param message
     */
    public MaBizException(ResponseCode code, String message) {
        super(message);
        this.code = code;
    }

    /**
     * 构造方法
     * @param code
     * @param message
     * @param cause
     */
    public MaBizException(ResponseCode code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public ResponseCode getCode() {
        return code;
    }

    public String getCodeStr() {
        return code.getCode();
    }

    public void setCode(ResponseCode code) {
        this.code = code;
    }
}
