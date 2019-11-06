package com.yiran.paychannel.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yiran.paychannel.mapper.TmFundChannelInstMapper;
import com.yiran.paychannel.domain.TmFundChannelInst;
import com.yiran.paychannel.service.ITmFundChannelInstService;
import com.yiran.common.support.Convert;

/**
 * 提供资金渠道的机构 服务层实现
 * 
 * @author yiran
 * @date 2019-04-19
 */
@Service
public class TmFundChannelInstServiceImpl implements ITmFundChannelInstService 
{
	@Autowired
	private TmFundChannelInstMapper tmFundChannelInstMapper;

	/**
     * 查询提供资金渠道的机构信息
     * 
     * @param instCode 提供资金渠道的机构ID
     * @return 提供资金渠道的机构信息
     */
    @Override
	public TmFundChannelInst selectTmFundChannelInstById(String instCode)
	{
	    return tmFundChannelInstMapper.selectTmFundChannelInstById(instCode);
	}
	
	/**
     * 查询提供资金渠道的机构列表
     * 
     * @param tmFundChannelInst 提供资金渠道的机构信息
     * @return 提供资金渠道的机构集合
     */
	@Override
	public List<TmFundChannelInst> selectTmFundChannelInstList(TmFundChannelInst tmFundChannelInst)
	{
	    return tmFundChannelInstMapper.selectTmFundChannelInstList(tmFundChannelInst);
	}
	
    /**
     * 新增提供资金渠道的机构
     * 
     * @param tmFundChannelInst 提供资金渠道的机构信息
     * @return 结果
     */
	@Override
	public int insertTmFundChannelInst(TmFundChannelInst tmFundChannelInst)
	{
	    return tmFundChannelInstMapper.insertTmFundChannelInst(tmFundChannelInst);
	}
	
	/**
     * 修改提供资金渠道的机构
     * 
     * @param tmFundChannelInst 提供资金渠道的机构信息
     * @return 结果
     */
	@Override
	public int updateTmFundChannelInst(TmFundChannelInst tmFundChannelInst)
	{
	    return tmFundChannelInstMapper.updateTmFundChannelInst(tmFundChannelInst);
	}

	/**
     * 删除提供资金渠道的机构对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteTmFundChannelInstByIds(String ids)
	{
		return tmFundChannelInstMapper.deleteTmFundChannelInstByIds(Convert.toStrArray(ids));
	}
	
}
