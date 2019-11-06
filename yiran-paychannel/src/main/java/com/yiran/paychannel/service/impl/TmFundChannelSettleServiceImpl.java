package com.yiran.paychannel.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yiran.paychannel.mapper.TmFundChannelSettleMapper;
import com.yiran.paychannel.domain.TmFundChannelSettle;
import com.yiran.paychannel.service.ITmFundChannelSettleService;
import com.yiran.common.support.Convert;

/**
 * 资金渠道算 服务层实现
 * 
 * @author yiran
 * @date 2019-04-19
 */
@Service
public class TmFundChannelSettleServiceImpl implements ITmFundChannelSettleService 
{
	@Autowired
	private TmFundChannelSettleMapper tmFundChannelSettleMapper;

	/**
     * 查询资金渠道算信息
     * 
     * @param settleId 资金渠道算ID
     * @return 资金渠道算信息
     */
    @Override
	public TmFundChannelSettle selectTmFundChannelSettleById(Integer settleId)
	{
	    return tmFundChannelSettleMapper.selectTmFundChannelSettleById(settleId);
	}
	
	/**
     * 查询资金渠道算列表
     * 
     * @param tmFundChannelSettle 资金渠道算信息
     * @return 资金渠道算集合
     */
	@Override
	public List<TmFundChannelSettle> selectTmFundChannelSettleList(TmFundChannelSettle tmFundChannelSettle)
	{
	    return tmFundChannelSettleMapper.selectTmFundChannelSettleList(tmFundChannelSettle);
	}
	
    /**
     * 新增资金渠道算
     * 
     * @param tmFundChannelSettle 资金渠道算信息
     * @return 结果
     */
	@Override
	public int insertTmFundChannelSettle(TmFundChannelSettle tmFundChannelSettle)
	{
	    return tmFundChannelSettleMapper.insertTmFundChannelSettle(tmFundChannelSettle);
	}
	
	/**
     * 修改资金渠道算
     * 
     * @param tmFundChannelSettle 资金渠道算信息
     * @return 结果
     */
	@Override
	public int updateTmFundChannelSettle(TmFundChannelSettle tmFundChannelSettle)
	{
	    return tmFundChannelSettleMapper.updateTmFundChannelSettle(tmFundChannelSettle);
	}

	/**
     * 删除资金渠道算对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteTmFundChannelSettleByIds(String ids)
	{
		return tmFundChannelSettleMapper.deleteTmFundChannelSettleByIds(Convert.toStrArray(ids));
	}
	
}
