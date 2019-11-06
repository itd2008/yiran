package com.yiran.paychannel.service;

import com.yiran.paychannel.domain.TmFundChannelInst;
import java.util.List;

/**
 * 提供资金渠道的机构 服务层
 * 
 * @author yiran
 * @date 2019-04-19
 */
public interface ITmFundChannelInstService 
{
	/**
     * 查询提供资金渠道的机构信息
     * 
     * @param instCode 提供资金渠道的机构ID
     * @return 提供资金渠道的机构信息
     */
	public TmFundChannelInst selectTmFundChannelInstById(String instCode);
	
	/**
     * 查询提供资金渠道的机构列表
     * 
     * @param tmFundChannelInst 提供资金渠道的机构信息
     * @return 提供资金渠道的机构集合
     */
	public List<TmFundChannelInst> selectTmFundChannelInstList(TmFundChannelInst tmFundChannelInst);
	
	/**
     * 新增提供资金渠道的机构
     * 
     * @param tmFundChannelInst 提供资金渠道的机构信息
     * @return 结果
     */
	public int insertTmFundChannelInst(TmFundChannelInst tmFundChannelInst);
	
	/**
     * 修改提供资金渠道的机构
     * 
     * @param tmFundChannelInst 提供资金渠道的机构信息
     * @return 结果
     */
	public int updateTmFundChannelInst(TmFundChannelInst tmFundChannelInst);
		
	/**
     * 删除提供资金渠道的机构信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteTmFundChannelInstByIds(String ids);
	
}
