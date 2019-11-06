package com.yiran.paychannel.mapper;

import com.yiran.paychannel.domain.TmFundChannelTargetInit;
import java.util.List;	

/**
 * 提供资金渠道目标机构 数据层
 * 
 * @author yiran
 * @date 2019-04-20
 */
public interface TmFundChannelTargetInitMapper 
{
	/**
     * 查询提供资金渠道目标机构信息
     * 
     * @param targetInstCode 提供资金渠道目标机构ID
     * @return 提供资金渠道目标机构信息
     */
	public TmFundChannelTargetInit selectTmFundChannelTargetInitById(String targetInstCode);
	
	/**
     * 查询提供资金渠道目标机构列表
     * 
     * @param tmFundChannelTargetInit 提供资金渠道目标机构信息
     * @return 提供资金渠道目标机构集合
     */
	public List<TmFundChannelTargetInit> selectTmFundChannelTargetInitList(TmFundChannelTargetInit tmFundChannelTargetInit);
	
	/**
     * 新增提供资金渠道目标机构
     * 
     * @param tmFundChannelTargetInit 提供资金渠道目标机构信息
     * @return 结果
     */
	public int insertTmFundChannelTargetInit(TmFundChannelTargetInit tmFundChannelTargetInit);
	
	/**
     * 修改提供资金渠道目标机构
     * 
     * @param tmFundChannelTargetInit 提供资金渠道目标机构信息
     * @return 结果
     */
	public int updateTmFundChannelTargetInit(TmFundChannelTargetInit tmFundChannelTargetInit);
	
	/**
     * 删除提供资金渠道目标机构
     * 
     * @param targetInstCode 提供资金渠道目标机构ID
     * @return 结果
     */
	public int deleteTmFundChannelTargetInitById(String targetInstCode);
	
	/**
     * 批量删除提供资金渠道目标机构
     * 
     * @param targetInstCodes 需要删除的数据ID
     * @return 结果
     */
	public int deleteTmFundChannelTargetInitByIds(String[] targetInstCodes);
	
}