package com.yiran.paychannel.service;

import com.yiran.paychannel.domain.TmFundChannel;
import com.yiran.paychannel.domain.TmFundChannelApi;
import com.yiran.paychannel.enums.FundChannelApiType;

import java.util.List;

/**
 * 资金渠道接口 服务层
 * 
 * @author yiran
 * @date 2019-04-19
 */
public interface ITmFundChannelApiService 
{
	/**
     * 查询资金渠道接口信息
     * 
     * @param apiCode 资金渠道接口ID
     * @return 资金渠道接口信息
     */
	public TmFundChannelApi selectTmFundChannelApiById(String apiCode);
	
	/**
     * 查询资金渠道接口列表
     * 
     * @param tmFundChannelApi 资金渠道接口信息
     * @return 资金渠道接口集合
     */
	public List<TmFundChannelApi> selectTmFundChannelApiList(TmFundChannelApi tmFundChannelApi);
	
	/**
     * 新增资金渠道接口
     * 
     * @param tmFundChannelApi 资金渠道接口信息
     * @return 结果
     */
	public int insertTmFundChannelApi(TmFundChannelApi tmFundChannelApi);
	
	/**
     * 修改资金渠道接口
     * 
     * @param tmFundChannelApi 资金渠道接口信息
     * @return 结果
     */
	public int updateTmFundChannelApi(TmFundChannelApi tmFundChannelApi);
		
	/**
     * 删除资金渠道接口信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteTmFundChannelApiByIds(String ids);
	/**
     * 依据请求类型过滤API
     * @param apis
     * @param requestType
     * @return
     */
    public boolean filterApi(List<TmFundChannelApi> apis, String requestType);

	public boolean filterWithApiType(TmFundChannel fundChannel, FundChannelApiType apiType);
	/**
	 * 根据渠道APICoode获取渠道API信息
	 * @param fundChannelApi
	 * @return
	 */
	public TmFundChannelApi getFundChannelApi(String apiCode);
	
}
