package com.yiran.paychannel.mapper;

import com.yiran.paychannel.domain.TmFundChannelMaintain;
import java.util.List;	

/**
 * 资金渠道维护期 数据层
 * 
 * @author yiran
 * @date 2019-04-19
 */
public interface TmFundChannelMaintainMapper 
{
	/**
     * 查询资金渠道维护期信息
     * 
     * @param maintainId 资金渠道维护期ID
     * @return 资金渠道维护期信息
     */
	public TmFundChannelMaintain selectTmFundChannelMaintainById(Integer maintainId);
	
	/**
     * 查询资金渠道维护期列表
     * 
     * @param tmFundChannelMaintain 资金渠道维护期信息
     * @return 资金渠道维护期集合
     */
	public List<TmFundChannelMaintain> selectTmFundChannelMaintainList(TmFundChannelMaintain tmFundChannelMaintain);
	
	/**
     * 新增资金渠道维护期
     * 
     * @param tmFundChannelMaintain 资金渠道维护期信息
     * @return 结果
     */
	public int insertTmFundChannelMaintain(TmFundChannelMaintain tmFundChannelMaintain);
	
	/**
     * 修改资金渠道维护期
     * 
     * @param tmFundChannelMaintain 资金渠道维护期信息
     * @return 结果
     */
	public int updateTmFundChannelMaintain(TmFundChannelMaintain tmFundChannelMaintain);
	
	/**
     * 删除资金渠道维护期
     * 
     * @param maintainId 资金渠道维护期ID
     * @return 结果
     */
	public int deleteTmFundChannelMaintainById(Integer maintainId);
	
	/**
     * 批量删除资金渠道维护期
     * 
     * @param maintainIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteTmFundChannelMaintainByIds(String[] maintainIds);
	
}