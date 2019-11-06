package com.yiran.paychannel.mapper;

import com.yiran.paychannel.domain.TmFundChannelSettle;
import java.util.List;	

/**
 * 资金渠道算 数据层
 * 
 * @author yiran
 * @date 2019-04-19
 */
public interface TmFundChannelSettleMapper 
{
	/**
     * 查询资金渠道算信息
     * 
     * @param settleId 资金渠道算ID
     * @return 资金渠道算信息
     */
	public TmFundChannelSettle selectTmFundChannelSettleById(Integer settleId);
	
	/**
     * 查询资金渠道算列表
     * 
     * @param tmFundChannelSettle 资金渠道算信息
     * @return 资金渠道算集合
     */
	public List<TmFundChannelSettle> selectTmFundChannelSettleList(TmFundChannelSettle tmFundChannelSettle);
	
	/**
     * 新增资金渠道算
     * 
     * @param tmFundChannelSettle 资金渠道算信息
     * @return 结果
     */
	public int insertTmFundChannelSettle(TmFundChannelSettle tmFundChannelSettle);
	
	/**
     * 修改资金渠道算
     * 
     * @param tmFundChannelSettle 资金渠道算信息
     * @return 结果
     */
	public int updateTmFundChannelSettle(TmFundChannelSettle tmFundChannelSettle);
	
	/**
     * 删除资金渠道算
     * 
     * @param settleId 资金渠道算ID
     * @return 结果
     */
	public int deleteTmFundChannelSettleById(Integer settleId);
	
	/**
     * 批量删除资金渠道算
     * 
     * @param settleIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteTmFundChannelSettleByIds(String[] settleIds);
	
}