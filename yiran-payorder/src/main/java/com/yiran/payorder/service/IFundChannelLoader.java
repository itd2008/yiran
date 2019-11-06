package com.yiran.payorder.service;

import com.yiran.paychannel.domain.TmFundChannel;
import com.yiran.paychannel.exception.RouteChannelException;
import com.yiran.payorder.domain.ChannelPayOrder;
import com.yiran.payorder.domain.InstBaseOrder;
import com.yiran.payorder.request.ChannelRequest;

/**
 * <p>资金渠道加载器</p>
 */
public interface IFundChannelLoader {

    /**
     * 加载资金渠道
     * @param cmfOrder
     * @param preOrder
     * @return
     * @throws RouteChannelException
     */
    public TmFundChannel load(ChannelPayOrder channelPayOrder, InstBaseOrder preOrder) throws RouteChannelException;

    /**
     * 获取资金源渠道
     * @param channelRequest
     * @return
     */
	public TmFundChannel loadFundChannel(ChannelRequest channelRequest);

   
}
