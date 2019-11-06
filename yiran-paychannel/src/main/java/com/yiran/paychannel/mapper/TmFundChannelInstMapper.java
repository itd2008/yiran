package com.yiran.paychannel.mapper;

import com.yiran.paychannel.domain.TmFundChannelInst;
import java.util.List;	

/**
 * 提供资金渠道的机构 数据层
 * 
 * @author yiran
 * @date 2019-04-19
 */
public interface TmFundChannelInstMapper 
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
     * 删除提供资金渠道的机构
     * 
     * @param instCode 提供资金渠道的机构ID
     * @return 结果
     */
	public int deleteTmFundChannelInstById(String instCode);
	
	/**
     * 批量删除提供资金渠道的机构
     * 
     * @param instCodes 需要删除的数据ID
     * @return 结果
     */
	public int deleteTmFundChannelInstByIds(String[] instCodes);
	
}