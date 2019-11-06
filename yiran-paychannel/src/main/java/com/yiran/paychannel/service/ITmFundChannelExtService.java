package com.yiran.paychannel.service;

import com.yiran.paychannel.domain.TmFundChannelExt;
import java.util.List;

/**
 * 资金渠道特性 服务层
 * 
 * @author yiran
 * @date 2019-04-19
 */
public interface ITmFundChannelExtService 
{
	/**
     * 查询资金渠道特性信息
     * 
     * @param extId 资金渠道特性ID
     * @return 资金渠道特性信息
     */
	public TmFundChannelExt selectTmFundChannelExtById(Integer extId);
	
	/**
     * 查询资金渠道特性列表
     * 
     * @param tmFundChannelExt 资金渠道特性信息
     * @return 资金渠道特性集合
     */
	public List<TmFundChannelExt> selectTmFundChannelExtList(TmFundChannelExt tmFundChannelExt);
	
	/**
     * 新增资金渠道特性
     * 
     * @param tmFundChannelExt 资金渠道特性信息
     * @return 结果
     */
	public int insertTmFundChannelExt(TmFundChannelExt tmFundChannelExt);
	
	/**
     * 修改资金渠道特性
     * 
     * @param tmFundChannelExt 资金渠道特性信息
     * @return 结果
     */
	public int updateTmFundChannelExt(TmFundChannelExt tmFundChannelExt);
		
	/**
     * 删除资金渠道特性信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteTmFundChannelExtByIds(String ids);
	
}
