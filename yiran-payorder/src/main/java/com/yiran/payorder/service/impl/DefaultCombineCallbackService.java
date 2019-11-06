package com.yiran.payorder.service.impl;


import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yiran.paychannel.domain.TmFundChannel;
import com.yiran.paychannel.domain.TmFundChannelApi;
import com.yiran.paychannel.enums.FundChannelApiType;
import com.yiran.paychannel.service.IFundChannelRouter;
import com.yiran.payorder.constant.BasicConstant;
import com.yiran.payorder.service.ICombineCallbackService;

@Service("combineCallbackService")
public class DefaultCombineCallbackService implements BasicConstant, ICombineCallbackService {

    @Autowired
    private IFundChannelRouter fundChannelRouter;

    @Override
    public String getCallBackUrl(TmFundChannel channel, String type, FundChannelApiType apiType) {
        TmFundChannel result = fundChannelRouter.routerFundChannel(channel.getFundChannelCode(),
            apiType);
        if (result != null && CollectionUtils.isNotEmpty(result.getApiList())) {
        	TmFundChannelApi fundChannelApi = result.getApiList().get(0);
            if (PAGE_URL.equals(type)) {
                return fundChannelApi.getApiUri();
            } else {
                return fundChannelApi.getApiUri();
            }
        } else {
            return "";
        }
    }
}
