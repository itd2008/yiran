package com.yiran.paychannel.service;

import java.util.Map;

import com.yiran.paychannel.domain.TmFundChannel;
import com.yiran.paychannel.domain.TmFundChannelApi;
import com.yiran.paychannel.enums.FundChannelApiType;
import com.yiran.paychannel.enums.PayMode;
import com.yiran.paychannel.exception.RouteChannelException;

/**
 * 渠道路由
 * @author pandaa
 *
 */
public interface IFundChannelRouter {
	/**
     * <p>依据目标机构和请求类型路由渠道<p>
     * <p>请求类型支持:
     *      1.普通请求类型
     *      2.控制类请求类型
     * </p>
     * @param targetInst
     * @param payMode
     * @param requestType
     * @param param
     * @return
     * @throws RouteChannelException
     */
    public TmFundChannel routerFundChannel(String targetInst, PayMode payMode, String requestType,
                                         Map<String, ?> param) throws RouteChannelException;

    /**
     * 白名单路由
     * 依据渠道编码和API类型路由渠道并选定API
     * <p>apiType为空选定所有API,不过滤<p>
     * @param fundChannelCode 不能为空
     * @param apiType 
     * @return
     */
    public TmFundChannel routerFundChannel(String fundChannelCode, FundChannelApiType apiType);

    /**
     * 白名单路由
     * 依据渠道编码和请求类型路由渠道并选定api
     * @param fundChannelCode
     * @param requestType
     * @return
     */
    public TmFundChannel routerFundChannel(String fundChannelCode, String targetInst,
                                         PayMode payMode, String requestType, Map<String, ?> param)
                                                                                                   throws RouteChannelException;

    /**
     * 路由退款渠道
     * @param fundChannelCode
     * @param apiType
     * @return
     */
    public TmFundChannel routerRefundChannel(String fundChannelCode) throws RouteChannelException;
    /**
     * 根据资金渠道编号查询资金渠道
     * @param fundChannelCode
     * @return
     */
    public TmFundChannel getFundChannel(String fundChannelCode) throws RouteChannelException;

    /**
     * 根据资金渠道api编码获取API信息
     * @param fundChannelApiCode
     * @return
     */
    public TmFundChannelApi getFundChannelApi(String fundChannelApiCode);
}
