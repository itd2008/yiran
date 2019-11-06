package com.yiran.payorder.service;

import com.yiran.paychannel.exception.RouteChannelException;
import com.yiran.payorder.domain.ChannelFundResult;
import com.yiran.payorder.domain.ChannelResult;
import com.yiran.payorder.exception.CommunicateException;

/**
 * 统一分发查询接口
 * @author pandaa
 *
 */
public interface IDistributeQueryService {
	/**
     * 查询结果
     * 1. 查询是否存在有未比对的结果,若存在直接装载
     * 2. 查询是否存在单笔查询接口,若存在,调用单笔查询接口查询结果
     * 3. 存在多笔记录,如果都是成功,处理中,返回成功结果
     * 4. 存在多笔记录,如果都是失败,处理中,返回失败结果
     * 5. 存在多笔记录,如果都是处理中,随机获取处理中结果
     * 6. 如果既存在成功,又存在失败,报警
     * @param instOrderId
     * @return
     */
    public ChannelFundResult queryResult(Integer instOrderId) throws CommunicateException,
                                                          RouteChannelException;


 
}
