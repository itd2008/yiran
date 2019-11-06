package com.yiran.payorder.service;


import com.netfinworks.biz.common.util.BaseResult;
import com.yiran.payorder.domain.ChannelFundResult;
import com.yiran.payorder.domain.ChannelNotifyResult;
import com.yiran.payorder.domain.ChannelPayOrder;
import com.yiran.payorder.domain.PayInstOrder;
import com.yiran.payorder.domain.PayInstOrderResult;
import com.yiran.payorder.enums.OrderRiskStatus;

/**
 * <p>机构结果服务</p>
 */
public interface InstitutionResultService {

    /**
     * 渠道结果出来,复用proccess(InstOrder instOrder, ChannelFundResult channelFundResult)
     * @param channelFundResult
     * @param isSync 是否同步返回结果
     * @return
     */
    ChannelNotifyResult process(ChannelFundResult channelFundResult, boolean isSync);

    /**
     * 渠道结果处理
     * @param instOrder
     * @param channelFundResult
     * @param isSync 是否同步返回结果
     * @return
     */
    PayInstOrderResult process(PayInstOrder instOrder, ChannelFundResult channelFundResult, boolean isSync);

    /**
     * 内部针对直接有结果的进行处理
     * @param ChannelPayOrder
     * @param instOrder
     * @param instResult
     * @param isSync
     * @return
     */
    public BaseResult process(ChannelPayOrder channelPayOrder, PayInstOrder instOrder, PayInstOrderResult instResult,
                              boolean isSync);

    /**
     * 风控通知结果后,渠道结果处理
     * @param cmfOrder
     * @param instOrder
     * @param instResult
     * @param isSync
     * @param riskStatus
     * @return
     * @throws WrongStateException
     */
    BaseResult process(ChannelPayOrder channelPayOrder, PayInstOrder instOrder, PayInstOrderResult instResult,
                       boolean isSync, OrderRiskStatus riskStatus);

   

}
