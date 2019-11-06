package com.yiran.paychannel.service;

import com.yiran.paychannel.domain.TmFundChannel;
import java.util.List;

/**
 * 资金渠道 服务层
 * 
 * @author yiran
 * @date 2019-04-19
 */
public interface ITmFundChannelService 
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
     * 删除资金渠道信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteTmFundChannelByIds(String ids);
	/**
	 * 目标机构和支付方式获取渠道
	 * @param targetInst
	 * @param payModeList
	 * @return
	 */
	 public List<TmFundChannel> getAvailableFundChannels(String targetInst, List<String> payModeList);

	 /**
	  * 根据渠道编号获取渠道信息
	  * @param fundChannelCode
	  * @return
	  */
	public TmFundChannel getFundChannel(String fundChannelCode);

	public List<TmFundChannel> loadAllFundChannels();
	
}
