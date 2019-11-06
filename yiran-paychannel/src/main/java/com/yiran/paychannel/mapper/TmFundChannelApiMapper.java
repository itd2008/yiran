package com.yiran.paychannel.mapper;

import com.yiran.paychannel.domain.TmFundChannelApi;
import java.util.List;	

/**
 * 资金渠道接口 数据层
 * 
 * @author yiran
 * @date 2019-04-19
 */
public interface TmFundChannelApiMapper 
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
     * 删除资金渠道接口
     * 
     * @param apiCode 资金渠道接口ID
     * @return 结果
     */
	public int deleteTmFundChannelApiById(String apiCode);
	
	/**
     * 批量删除资金渠道接口
     * 
     * @param apiCodes 需要删除的数据ID
     * @return 结果
     */
	public int deleteTmFundChannelApiByIds(String[] apiCodes);
	
}