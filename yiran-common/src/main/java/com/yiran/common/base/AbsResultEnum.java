package com.yiran.common.base;
/**
 * 抽象结果接口
 * @author pandaa
 *
 */
public interface AbsResultEnum {
	/**
     * 结果码
     *
     * @return String
     */
    String getCode();

    /**
     * 结果消息
     *
     * @return String
     */
    String getMessage();

}
