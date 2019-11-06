package com.yiran.paychannel.service;

import com.yiran.paychannel.domain.TmChannelTransInfo;
import java.util.List;
import java.util.Map;

/**
 * 渠道交易需要用到的特定 服务层
 * 
 * @author yiran
 * @date 2019-07-26
 */
public interface ITmChannelTransInfoService 
{
	/**
     * 查询渠道交易需要用到的特定信息
     * 
     * @param transId 渠道交易需要用到的特定ID
     * @return 渠道交易需要用到的特定信息
     */
	public TmChannelTransInfo selectTmChannelTransInfoById(Integer transId);
	
	/**
     * 查询渠道交易需要用到的特定列表
     * 
     * @param tmChannelTransInfo 渠道交易需要用到的特定信息
     * @return 渠道交易需要用到的特定集合
     */
	public List<TmChannelTransInfo> selectTmChannelTransInfoList(TmChannelTransInfo tmChannelTransInfo);
	
	/**
     * 新增渠道交易需要用到的特定
     * 
     * @param tmChannelTransInfo 渠道交易需要用到的特定信息
     * @return 结果
     */
	public int insertTmChannelTransInfo(TmChannelTransInfo tmChannelTransInfo);
	
	/**
     * 修改渠道交易需要用到的特定
     * 
     * @param tmChannelTransInfo 渠道交易需要用到的特定信息
     * @return 结果
     */
	public int updateTmChannelTransInfo(TmChannelTransInfo tmChannelTransInfo);
		
	/**
     * 删除渠道交易需要用到的特定信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteTmChannelTransInfoByIds(String ids);

	/**
	 * 依据资金渠道编码装载渠道交易相关信息
	 * @param fundChannelCode
	 * @param transCode
	 * @return
	 */
	public Map<String, String> getTransInfo(String fundChannelCode, String transCode);

	
}
