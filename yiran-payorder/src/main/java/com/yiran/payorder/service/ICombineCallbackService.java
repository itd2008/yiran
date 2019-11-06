package com.yiran.payorder.service;

import com.yiran.paychannel.domain.TmFundChannel;
import com.yiran.paychannel.enums.FundChannelApiType;

/**
 *
 * <p>组装渠道交易结果回调地址</p>
 *
 */
public interface ICombineCallbackService {

    /**
     * 组装渠道交易结果回调地址
     *
     *@param channel 资金渠道
     *@param 系统配置类型 netfinworksWebsiteServer-后端回调URL netfinworksWebsitePage-页面回调URL
     * @return
     */
    public String getCallBackUrl(TmFundChannel channel, String type, FundChannelApiType apiType);

}
