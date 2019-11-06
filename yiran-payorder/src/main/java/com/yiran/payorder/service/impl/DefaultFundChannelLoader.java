package com.yiran.payorder.service.impl;

import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netfinworks.common.lang.StringUtil;
import com.yiran.paychannel.domain.TmFundChannel;
import com.yiran.paychannel.enums.BizType;
import com.yiran.paychannel.enums.ExtensionKey;
import com.yiran.paychannel.exception.RouteChannelException;
import com.yiran.paychannel.service.IFundChannelRouter;
import com.yiran.paychannel.utils.ExpressionUtil;
import com.yiran.payorder.domain.ChannelPayOrder;
import com.yiran.payorder.domain.InstBaseOrder;
import com.yiran.payorder.request.ChannelRequest;
import com.yiran.payorder.service.IFundChannelLoader;
import com.yiran.payorder.utils.FilterAttributeUtil;


/**
 * <p>资金渠道加载器默认实现</p>
 */
@Service
public class DefaultFundChannelLoader implements IFundChannelLoader {
    /** 表达式工具类 */
    private static final ExpressionUtil util = new ExpressionUtil();

    /** 资金渠道路由器 */
    @Autowired
    private IFundChannelRouter fundChannelRouter;

    @Override
    public TmFundChannel load(ChannelPayOrder channelPayOrder, InstBaseOrder preOrder) throws RouteChannelException {
        if (preOrder != null && BizType.REFUND.equals(channelPayOrder.getBizType())) {
            return fundChannelRouter.routerRefundChannel(preOrder.getFundChannelCode());
        }
        if (StringUtil.isNotEmpty(channelPayOrder.getExtension().get(ExtensionKey.WHITE_CHANNEL_CODE.key))) {
            return fundChannelRouter.routerFundChannel(
            		channelPayOrder.getExtension().get(ExtensionKey.WHITE_CHANNEL_CODE.key),
            		channelPayOrder.getInstCode(), channelPayOrder.getPayMode(), channelPayOrder.getRequestType().getCode(),
                buildParam(channelPayOrder));
        }
        return fundChannelRouter.routerFundChannel(channelPayOrder.getInstCode(), channelPayOrder.getPayMode(),
        		channelPayOrder.getRequestType().getCode(), buildParam(channelPayOrder));
    }

   

    /**
     * 组装参数
     * @param cmfOrder
     * @return
     */
    @SuppressWarnings("unchecked")
    private Map<String, ?> buildParam(ChannelPayOrder channelPayOrder) {
        //TODO:后续修改路由逻辑的时候这部分代码需要
        Map<String, Object> param = (Map<String, Object>) FilterAttributeUtil.convert(channelPayOrder);
        // 增加工具类
        param.put("util", util);
        return param;
    }



	@Override
	public TmFundChannel loadFundChannel(ChannelRequest channelRequest) {
		 return fundChannelRouter.routerFundChannel(channelRequest.getFundChannelCode(), channelRequest.getApiType());
	}

    

}
