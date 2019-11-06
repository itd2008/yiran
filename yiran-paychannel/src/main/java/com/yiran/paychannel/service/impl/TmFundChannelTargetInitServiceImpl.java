package com.yiran.paychannel.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yiran.paychannel.mapper.TmFundChannelTargetInitMapper;
import com.yiran.paychannel.domain.TmFundChannelTargetInit;
import com.yiran.paychannel.service.ITmFundChannelTargetInitService;
import com.yiran.common.support.Convert;

/**
 * 提供资金渠道目标机构 服务层实现
 * 
 * @author yiran
 * @date 2019-04-20
 */
@Service
public class TmFundChannelTargetInitServiceImpl implements ITmFundChannelTargetInitService 
{
	@Autowired
	private TmFundChannelTargetInitMapper tmFundChannelTargetInitMapper;

	/**
     * 查询提供资金渠道目标机构信息
     * 
     * @param targetInstCode 提供资金渠道目标机构ID
     * @return 提供资金渠道目标机构信息
     */
    @Override
	public TmFundChannelTargetInit selectTmFundChannelTargetInitById(String targetInstCode)
	{
	    return tmFundChannelTargetInitMapper.selectTmFundChannelTargetInitById(targetInstCode);
	}
	
	/**
     * 查询提供资金渠道目标机构列表
     * 
     * @param tmFundChannelTargetInit 提供资金渠道目标机构信息
     * @return 提供资金渠道目标机构集合
     */
	@Override
	public List<TmFundChannelTargetInit> selectTmFundChannelTargetInitList(TmFundChannelTargetInit tmFundChannelTargetInit)
	{
	    return tmFundChannelTargetInitMapper.selectTmFundChannelTargetInitList(tmFundChannelTargetInit);
	}
	
    /**
     * 新增提供资金渠道目标机构
     * 
     * @param tmFundChannelTargetInit 提供资金渠道目标机构信息
     * @return 结果
     */
	@Override
	public int insertTmFundChannelTargetInit(TmFundChannelTargetInit tmFundChannelTargetInit)
	{
	    return tmFundChannelTargetInitMapper.insertTmFundChannelTargetInit(tmFundChannelTargetInit);
	}
	
	/**
     * 修改提供资金渠道目标机构
     * 
     * @param tmFundChannelTargetInit 提供资金渠道目标机构信息
     * @return 结果
     */
	@Override
	public int updateTmFundChannelTargetInit(TmFundChannelTargetInit tmFundChannelTargetInit)
	{
	    return tmFundChannelTargetInitMapper.updateTmFundChannelTargetInit(tmFundChannelTargetInit);
	}

	/**
     * 删除提供资金渠道目标机构对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteTmFundChannelTargetInitByIds(String ids)
	{
		return tmFundChannelTargetInitMapper.deleteTmFundChannelTargetInitByIds(Convert.toStrArray(ids));
	}
	
}
