package com.yiran.payorder.exception;

/**
 * <p>数据库插入违反唯一约束抛出异常</p>
 */
public class DuplicateKeyException extends Exception {

    private static final long serialVersionUID = 8807431150111680162L;

    /**
     * 默认构造方法
     */
    public DuplicateKeyException() {
        super();
    }

    /**
     * 创建一个<code>DuplicateKeyException</code>
     *
     * @param message
     */
    public DuplicateKeyException(String message) {
        super(message);
    }

    /**
     * 创建一个<code>DuplicateKeyException</code>
     *
     * @param cause
     */
    public DuplicateKeyException(Throwable cause) {
        super(cause);
    }

    /**
     * 创建一个<code>DuplicateKeyException</code>
     *
     * @param message
     * @param cause
     */
    public DuplicateKeyException(String message, Throwable cause) {
        super(message, cause);
    }
}
