package com.yiran.paychannel.service;

import java.util.List;
import java.util.Map;

import com.yiran.paychannel.domain.TmFundChannel;
import com.yiran.paychannel.domain.TmFundChannelApi;
import com.yiran.paychannel.enums.BizType;
import com.yiran.paychannel.enums.FundChannelApiType;


/**
 * 按照要求过滤API
 */
public interface IApiService {

    /**
     * 按优先级从高到低排序
     * 数字越大优先级越高
     * @param fundChannel
     */
    public void sortFilterApi(TmFundChannel fundChannel, BizType bizType);

    /**
     * 按优先级从高到低排序
     * 数字越大优先级越高
     * @param apis
     * @param bizType
     */
    public void sortFilterApi(List<TmFundChannelApi> apis, BizType bizType);

    /**
     * 过滤后保留单笔API
     * 如果不存在,返回false,API列表置为空
     * @param fundChannel
     */
    public boolean selectSingleApi(TmFundChannel fundChannel);

    /**
     * 过滤后保留单笔查询API
     * 如果不存在,返回false,API列表置为空
     * @param fundChannel
     */
    public boolean selectSingleQueryApi(TmFundChannel fundChannel);
    
    /**
     * 过滤后保留余额查询API
     * 如果不存在,返回false,API列表置为空
     * @param fundChannel
     * @return
     */
    public boolean selectBalanceQueryApi(TmFundChannel fundChannel);

    /**
     * 过滤后保留单笔冲正API
     * 如果不存在,返回false,API列表置为空
     * @param fundChannel
     */
    public boolean selectSingleReversalApi(TmFundChannel fundChannel);

    /**
     * 过滤后保留批量查询API
     * 如果不存在,返回false,API列表置为空
     * @param fundChannel
     */
    public boolean selectBulkQueryApi(TmFundChannel fundChannel);

    /**
     * 过滤后保留批量API
     * 如果不存在,返回false,API列表置为空
     * @param fundChannel
     */
    public boolean selectBulkApi(TmFundChannel fundChannel);

    /**
     * 过滤后保留批量文件API
     * 如果不存在,返回false,API列表置为空
     * @param fundChannel
     */
    public boolean selectBulkFileApi(TmFundChannel fundChannel);

    /**
     * 依据要素过滤,目前hardcode写死,
     * 后期需要重构,维护在数据库中
     * @param fundChannel
     * @param param
     * @return
     */
    public boolean filterApi(TmFundChannel fundChannel, BizType bizType, Map<String, ?> param);

    /**
     * 依据要素过滤,目前hardcode写死,
     * 后期需要重构,维护在数据库中
     * @param apis
     * @param bizType
     * @param param
     * @return
     */
    public boolean filterApi(List<TmFundChannelApi> apis, BizType bizType, Map<String, ?> param);

    /**
     * 依据请求类型过滤API
     * @param apis
     * @param requestType
     * @return
     */
    public boolean filterApi(List<TmFundChannelApi> apis, String requestType);

    /**
     * 依据api类型滤对应的api
     * <p>1.api类型为空,不进行过滤</p>
     * @param fundChannel
     * @param apiType
     * @return
     */
    public boolean filterWithApiType(TmFundChannel fundChannel, FundChannelApiType apiType);

    /**
     * 过滤自动冲退接口
     * <p>1.校验时间限制是否在退款支持的退款有效期内</p>
     * <p>2.退款接口的金额是否有超限</p>
     * @param channel
     * @param param
     * @return
     */
    public String filterRefundApi(TmFundChannel channel, Map<String, ?> param);

    /**
     * 校验渠道是否存在可用api
     * @param fundChannel
     * @return
     */
    public boolean isExistApi(TmFundChannel fundChannel);

    /**
     * 获取控制类型查询api列表
     * @return
     */
    public List<FundChannelApiType> getQueryControlApiTypeList();

    /**
     * 获取查询api对应apimap
     * @return
     */
    public Map<FundChannelApiType, FundChannelApiType> getQueryApiTypeMap();

}
