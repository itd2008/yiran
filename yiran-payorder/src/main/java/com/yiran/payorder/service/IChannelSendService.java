package com.yiran.payorder.service;

import com.yiran.payorder.domain.ChannelFundResult;
import com.yiran.payorder.domain.PayInstOrder;
import com.yiran.payorder.exception.CommunicateException;
import com.yiran.payorder.request.ChannelRequest;
import com.yiran.payorder.request.QueryRequest;

/**
 * <p>渠道请求发送服务</p>
 */
public interface IChannelSendService {

    /**
     * 发送机构资金订单
     * @param instOrder
     * @return
     */
    public ChannelFundResult send(PayInstOrder instOrder);


    /**
     * 单笔查询订单
     *
     * @param queryRequest
     * @return
     */
    public ChannelFundResult query(QueryRequest queryRequest);


    /**
     * 下载对账文件
     * @param channelRequest
     * @return
     */
	public ChannelFundResult download(ChannelRequest channelRequest);


}
