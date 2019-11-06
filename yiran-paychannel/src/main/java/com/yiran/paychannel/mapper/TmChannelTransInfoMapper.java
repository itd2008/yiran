package com.yiran.paychannel.mapper;

import com.yiran.paychannel.domain.TmChannelTransInfoDO;

import java.util.List;

import org.apache.ibatis.annotations.Param;	

/**
 * 渠道交易需要用到的特定 数据层
 * 
 * @author yiran
 * @date 2019-07-26
 */
public interface TmChannelTransInfoMapper 
{
	/**
     * 查询渠道交易需要用到的特定信息
     * 
     * @param transId 渠道交易需要用到的特定ID
     * @return 渠道交易需要用到的特定信息
     */
	public TmChannelTransInfoDO selectTmChannelTransInfoById(Integer transId);
	
	/**
     * 查询渠道交易需要用到的特定列表
     * 
     * @param tmChannelTransInfo 渠道交易需要用到的特定信息
     * @return 渠道交易需要用到的特定集合
     */
	public List<TmChannelTransInfoDO> selectTmChannelTransInfoList(TmChannelTransInfoDO tmChannelTransInfo);
	
	/**
     * 新增渠道交易需要用到的特定
     * 
     * @param tmChannelTransInfo 渠道交易需要用到的特定信息
     * @return 结果
     */
	public int insertTmChannelTransInfo(TmChannelTransInfoDO tmChannelTransInfo);
	
	/**
     * 修改渠道交易需要用到的特定
     * 
     * @param tmChannelTransInfo 渠道交易需要用到的特定信息
     * @return 结果
     */
	public int updateTmChannelTransInfo(TmChannelTransInfoDO tmChannelTransInfo);
	
	/**
     * 删除渠道交易需要用到的特定
     * 
     * @param transId 渠道交易需要用到的特定ID
     * @return 结果
     */
	public int deleteTmChannelTransInfoById(Integer transId);
	
	/**
     * 批量删除渠道交易需要用到的特定
     * 
     * @param transIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteTmChannelTransInfoByIds(String[] transIds);

	/**
	 * 
	 * @param apiType
	 * @param status
	 * @param fundChannelCode
	 * @param transCode
	 * @return
	 */
	public List<TmChannelTransInfoDO> loadByCondition(@Param("apiType")String apiType, 
			@Param("status")String status, 
			@Param("fundChannelCode")String fundChannelCode,
			@Param("transCode")String transCode);

	
}