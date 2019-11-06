package com.yiran.payorder.facade;


import com.yiran.payorder.domain.ChannelFundResult;
import com.yiran.payorder.exception.CommunicateException;
import com.yiran.payorder.request.ChannelRequest;


/**
 * <p>渠道资金类统一门面</p>
 * 渠道所有支付相关请求通过此门面进行交互
 * <br/>
 * 支持业务如下：
 * <ul>
 *  <li>退款</li>
 *  <li>支付</li>
 * </ul>
 * com.yiran.payorder.facade
 */
public interface ChannelFundFacade {
    /**
     * 单笔渠道申请
     * 与支付相关渠道请求由此发起
     * 请求对象及结果对象根据不同的业务大类进行扩展
     * @param request
     * @return
     */
    ChannelFundResult apply(ChannelRequest request)throws CommunicateException;
}
