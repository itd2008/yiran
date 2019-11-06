package com.yiran.paychannel.mapper;

import com.yiran.paychannel.domain.TmFundChannelApiParam;
import java.util.List;	

/**
 * 渠道接口参数 数据层
 * 
 * @author yiran
 * @date 2019-04-19
 */
public interface TmFundChannelApiParamMapper 
{
	/**
     * 查询渠道接口参数信息
     * 
     * @param paramId 渠道接口参数ID
     * @return 渠道接口参数信息
     */
	public TmFundChannelApiParam selectTmFundChannelApiParamById(Integer paramId);
	
	/**
     * 查询渠道接口参数列表
     * 
     * @param tmFundChannelApiParam 渠道接口参数信息
     * @return 渠道接口参数集合
     */
	public List<TmFundChannelApiParam> selectTmFundChannelApiParamList(TmFundChannelApiParam tmFundChannelApiParam);
	
	/**
     * 新增渠道接口参数
     * 
     * @param tmFundChannelApiParam 渠道接口参数信息
     * @return 结果
     */
	public int insertTmFundChannelApiParam(TmFundChannelApiParam tmFundChannelApiParam);
	
	/**
     * 修改渠道接口参数
     * 
     * @param tmFundChannelApiParam 渠道接口参数信息
     * @return 结果
     */
	public int updateTmFundChannelApiParam(TmFundChannelApiParam tmFundChannelApiParam);
	
	/**
     * 删除渠道接口参数
     * 
     * @param paramId 渠道接口参数ID
     * @return 结果
     */
	public int deleteTmFundChannelApiParamById(Integer paramId);
	
	/**
     * 批量删除渠道接口参数
     * 
     * @param paramIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteTmFundChannelApiParamByIds(String[] paramIds);
	
}