package com.yiran.paychannel.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yiran.paychannel.mapper.TmFundChannelMaintainMapper;
import com.yiran.paychannel.domain.TmFundChannelMaintain;
import com.yiran.paychannel.service.ITmFundChannelMaintainService;
import com.yiran.common.support.Convert;

/**
 * 资金渠道维护期 服务层实现
 * 
 * @author yiran
 * @date 2019-04-19
 */
@Service
public class TmFundChannelMaintainServiceImpl implements ITmFundChannelMaintainService 
{
	@Autowired
	private TmFundChannelMaintainMapper tmFundChannelMaintainMapper;

	/**
     * 查询资金渠道维护期信息
     * 
     * @param maintainId 资金渠道维护期ID
     * @return 资金渠道维护期信息
     */
    @Override
	public TmFundChannelMaintain selectTmFundChannelMaintainById(Integer maintainId)
	{
	    return tmFundChannelMaintainMapper.selectTmFundChannelMaintainById(maintainId);
	}
	
	/**
     * 查询资金渠道维护期列表
     * 
     * @param tmFundChannelMaintain 资金渠道维护期信息
     * @return 资金渠道维护期集合
     */
	@Override
	public List<TmFundChannelMaintain> selectTmFundChannelMaintainList(TmFundChannelMaintain tmFundChannelMaintain)
	{
	    return tmFundChannelMaintainMapper.selectTmFundChannelMaintainList(tmFundChannelMaintain);
	}
	
    /**
     * 新增资金渠道维护期
     * 
     * @param tmFundChannelMaintain 资金渠道维护期信息
     * @return 结果
     */
	@Override
	public int insertTmFundChannelMaintain(TmFundChannelMaintain tmFundChannelMaintain)
	{
	    return tmFundChannelMaintainMapper.insertTmFundChannelMaintain(tmFundChannelMaintain);
	}
	
	/**
     * 修改资金渠道维护期
     * 
     * @param tmFundChannelMaintain 资金渠道维护期信息
     * @return 结果
     */
	@Override
	public int updateTmFundChannelMaintain(TmFundChannelMaintain tmFundChannelMaintain)
	{
	    return tmFundChannelMaintainMapper.updateTmFundChannelMaintain(tmFundChannelMaintain);
	}

	/**
     * 删除资金渠道维护期对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteTmFundChannelMaintainByIds(String ids)
	{
		return tmFundChannelMaintainMapper.deleteTmFundChannelMaintainByIds(Convert.toStrArray(ids));
	}
	
}
