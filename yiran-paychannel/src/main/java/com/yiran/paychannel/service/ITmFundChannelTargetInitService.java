package com.yiran.paychannel.service;

import com.yiran.paychannel.domain.TmFundChannelTargetInit;
import java.util.List;

/**
 * 提供资金渠道目标机构 服务层
 * 
 * @author yiran
 * @date 2019-04-20
 */
public interface ITmFundChannelTargetInitService 
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
     * 删除提供资金渠道目标机构信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteTmFundChannelTargetInitByIds(String ids);
	
}
