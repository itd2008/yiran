/**
 *
 */
package com.yiran.payorder.exception;

/**
 * <p>锁异常.</p>
 */
public class LockException extends Exception {

    private static final long serialVersionUID = 7138794971443706697L;

    /**
     * 默认构造方法
     */
    public LockException() {
        super();
    }

    /**
     * 创建一个<code>LockException</code>
     *
     * @param message
     */
    public LockException(String message) {
        super(message);
    }

    /**
     * 创建一个<code>LockException</code>
     *
     * @param cause
     */
    public LockException(Throwable cause) {
        super(cause);
    }

    /**
     * 创建一个<code>LockException</code>
     *
     * @param message
     * @param cause
     */
    public LockException(String message, Throwable cause) {
        super(message, cause);
    }
}
