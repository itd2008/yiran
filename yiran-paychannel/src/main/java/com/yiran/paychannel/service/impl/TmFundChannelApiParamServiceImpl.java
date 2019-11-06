package com.yiran.paychannel.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yiran.paychannel.mapper.TmFundChannelApiParamMapper;
import com.yiran.paychannel.domain.TmFundChannelApiParam;
import com.yiran.paychannel.service.ITmFundChannelApiParamService;
import com.yiran.common.support.Convert;

/**
 * 渠道接口参数 服务层实现
 * 
 * @author yiran
 * @date 2019-04-19
 */
@Service
public class TmFundChannelApiParamServiceImpl implements ITmFundChannelApiParamService 
{
	@Autowired
	private TmFundChannelApiParamMapper tmFundChannelApiParamMapper;

	/**
     * 查询渠道接口参数信息
     * 
     * @param paramId 渠道接口参数ID
     * @return 渠道接口参数信息
     */
    @Override
	public TmFundChannelApiParam selectTmFundChannelApiParamById(Integer paramId)
	{
	    return tmFundChannelApiParamMapper.selectTmFundChannelApiParamById(paramId);
	}
	
	/**
     * 查询渠道接口参数列表
     * 
     * @param tmFundChannelApiParam 渠道接口参数信息
     * @return 渠道接口参数集合
     */
	@Override
	public List<TmFundChannelApiParam> selectTmFundChannelApiParamList(TmFundChannelApiParam tmFundChannelApiParam)
	{
	    return tmFundChannelApiParamMapper.selectTmFundChannelApiParamList(tmFundChannelApiParam);
	}
	
    /**
     * 新增渠道接口参数
     * 
     * @param tmFundChannelApiParam 渠道接口参数信息
     * @return 结果
     */
	@Override
	public int insertTmFundChannelApiParam(TmFundChannelApiParam tmFundChannelApiParam)
	{
	    return tmFundChannelApiParamMapper.insertTmFundChannelApiParam(tmFundChannelApiParam);
	}
	
	/**
     * 修改渠道接口参数
     * 
     * @param tmFundChannelApiParam 渠道接口参数信息
     * @return 结果
     */
	@Override
	public int updateTmFundChannelApiParam(TmFundChannelApiParam tmFundChannelApiParam)
	{
	    return tmFundChannelApiParamMapper.updateTmFundChannelApiParam(tmFundChannelApiParam);
	}

	/**
     * 删除渠道接口参数对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteTmFundChannelApiParamByIds(String ids)
	{
		return tmFundChannelApiParamMapper.deleteTmFundChannelApiParamByIds(Convert.toStrArray(ids));
	}
	
}
