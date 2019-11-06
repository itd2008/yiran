package com.yiran.paychannel.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yiran.paychannel.mapper.TmApiNoModeMapper;
import com.yiran.paychannel.domain.Sequence;
import com.yiran.paychannel.domain.TmApiNoMode;
import com.yiran.paychannel.service.ISequenceService;
import com.yiran.paychannel.service.ITmApiNoModeService;
import com.yiran.common.support.Convert;

/**
 * 资金源接口订单号模式 服务层实现
 * 
 * @author yiran
 * @date 2019-04-19
 */
@Service
public class TmApiNoModeServiceImpl implements ITmApiNoModeService 
{
	@Autowired
	private TmApiNoModeMapper tmApiNoModeMapper;
	@Autowired
	private ISequenceService sequenceService;

	/**
     * 查询资金源接口订单号模式信息
     * 
     * @param apiNoModeId 资金源接口订单号模式ID
     * @return 资金源接口订单号模式信息
     */
    @Override
	public TmApiNoMode selectTmApiNoModeById(Integer apiNoModeId)
	{
	    return tmApiNoModeMapper.selectTmApiNoModeById(apiNoModeId);
	}
	
	/**
     * 查询资金源接口订单号模式列表
     * 
     * @param tmApiNoMode 资金源接口订单号模式信息
     * @return 资金源接口订单号模式集合
     */
	@Override
	public List<TmApiNoMode> selectTmApiNoModeList(TmApiNoMode tmApiNoMode)
	{
	    return tmApiNoModeMapper.selectTmApiNoModeList(tmApiNoMode);
	}
	
    /**
     * 新增资金源接口订单号模式
     * 
     * @param tmApiNoMode 资金源接口订单号模式信息
     * @return 结果
     */
	@Override
	public int insertTmApiNoMode(TmApiNoMode tmApiNoMode)
	{
		Sequence sequence = new Sequence();
		sequence.setName(tmApiNoMode.getSeqName());//序列名
		sequence.setCurrentValue(1000);//初始值
		sequence.setIncrement(1);//增量
		sequenceService.insertSequence(sequence);
	    return tmApiNoModeMapper.insertTmApiNoMode(tmApiNoMode);
	}
	
	/**
     * 修改资金源接口订单号模式
     * 
     * @param tmApiNoMode 资金源接口订单号模式信息
     * @return 结果
     */
	@Override
	public int updateTmApiNoMode(TmApiNoMode tmApiNoMode)
	{
	    return tmApiNoModeMapper.updateTmApiNoMode(tmApiNoMode);
	}

	/**
     * 删除资金源接口订单号模式对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteTmApiNoModeByIds(String ids)
	{
		return tmApiNoModeMapper.deleteTmApiNoModeByIds(Convert.toStrArray(ids));
	}
	
}
