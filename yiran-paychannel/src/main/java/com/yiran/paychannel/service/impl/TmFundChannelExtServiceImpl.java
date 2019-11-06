package com.yiran.paychannel.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yiran.paychannel.mapper.TmFundChannelExtMapper;
import com.yiran.paychannel.domain.TmFundChannelExt;
import com.yiran.paychannel.service.ITmFundChannelExtService;
import com.yiran.common.support.Convert;

/**
 * 资金渠道特性 服务层实现
 * 
 * @author yiran
 * @date 2019-04-19
 */
@Service
public class TmFundChannelExtServiceImpl implements ITmFundChannelExtService 
{
	@Autowired
	private TmFundChannelExtMapper tmFundChannelExtMapper;

	/**
     * 查询资金渠道特性信息
     * 
     * @param extId 资金渠道特性ID
     * @return 资金渠道特性信息
     */
    @Override
	public TmFundChannelExt selectTmFundChannelExtById(Integer extId)
	{
	    return tmFundChannelExtMapper.selectTmFundChannelExtById(extId);
	}
	
	/**
     * 查询资金渠道特性列表
     * 
     * @param tmFundChannelExt 资金渠道特性信息
     * @return 资金渠道特性集合
     */
	@Override
	public List<TmFundChannelExt> selectTmFundChannelExtList(TmFundChannelExt tmFundChannelExt)
	{
	    return tmFundChannelExtMapper.selectTmFundChannelExtList(tmFundChannelExt);
	}
	
    /**
     * 新增资金渠道特性
     * 
     * @param tmFundChannelExt 资金渠道特性信息
     * @return 结果
     */
	@Override
	public int insertTmFundChannelExt(TmFundChannelExt tmFundChannelExt)
	{
	    return tmFundChannelExtMapper.insertTmFundChannelExt(tmFundChannelExt);
	}
	
	/**
     * 修改资金渠道特性
     * 
     * @param tmFundChannelExt 资金渠道特性信息
     * @return 结果
     */
	@Override
	public int updateTmFundChannelExt(TmFundChannelExt tmFundChannelExt)
	{
	    return tmFundChannelExtMapper.updateTmFundChannelExt(tmFundChannelExt);
	}

	/**
     * 删除资金渠道特性对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteTmFundChannelExtByIds(String ids)
	{
		return tmFundChannelExtMapper.deleteTmFundChannelExtByIds(Convert.toStrArray(ids));
	}
	
}
