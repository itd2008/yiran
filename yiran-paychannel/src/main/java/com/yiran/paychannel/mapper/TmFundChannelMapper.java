package com.yiran.paychannel.mapper;

import com.yiran.paychannel.domain.TmFundChannel;
import java.util.List;

import org.apache.ibatis.annotations.Param;	

/**
 * 资金渠道 数据层
 * 
 * @author yiran
 * @date 2019-04-19
 */
public interface TmFundChannelMapper 
{
	/**
     * 查询资金渠道信息
     * 
     * @param fundChannelCode 资金渠道ID
     * @return 资金渠道信息
     */
	public TmFundChannel selectTmFundChannelById(String fundChannelCode);
	
	/**
     * 查询资金渠道列表
     * 
     * @param tmFundChannel 资金渠道信息
     * @return 资金渠道集合
     */
	public List<TmFundChannel> selectTmFundChannelList(TmFundChannel tmFundChannel);
	
	/**
     * 新增资金渠道
     * 
     * @param tmFundChannel 资金渠道信息
     * @return 结果
     */
	public int insertTmFundChannel(TmFundChannel tmFundChannel);
	
	/**
     * 修改资金渠道
     * 
     * @param tmFundChannel 资金渠道信息
     * @return 结果
     */
	public int updateTmFundChannel(TmFundChannel tmFundChannel);
	
	/**
     * 删除资金渠道
     * 
     * @param fundChannelCode 资金渠道ID
     * @return 结果
     */
	public int deleteTmFundChannelById(String fundChannelCode);
	
	/**
     * 批量删除资金渠道
     * 
     * @param fundChannelCodes 需要删除的数据ID
     * @return 结果
     */
	public int deleteTmFundChannelByIds(String[] fundChannelCodes);

	/**
	 * 更具目标机构和支付方式获取渠道编码
	 * @param targetInst
	 * @param payModeList
	 * @return
	 */
	public List<TmFundChannel> getAvailableFundChannels(@Param("targetInst")String targetInst, @Param("payModeList")List<String> payModeList);

	public List<TmFundChannel> loadAll();
	
}