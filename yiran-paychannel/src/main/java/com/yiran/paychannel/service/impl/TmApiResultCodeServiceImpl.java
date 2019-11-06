package com.yiran.paychannel.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yiran.paychannel.mapper.TmApiResultCodeMapper;
import com.yiran.paychannel.domain.TmApiResultCode;
import com.yiran.paychannel.enums.FundChannelApiType;
import com.yiran.paychannel.service.ITmApiResultCodeService;
import com.yiran.common.support.Convert;

/**
 * API结果编码 服务层实现
 * 
 * @author yiran
 * @date 2019-04-19
 */
@Service
public class TmApiResultCodeServiceImpl implements ITmApiResultCodeService 
{
	@Autowired
	private TmApiResultCodeMapper tmApiResultCodeMapper;

	/**
     * 查询API结果编码信息
     * 
     * @param apiResultCodeId API结果编码ID
     * @return API结果编码信息
     */
    @Override
	public TmApiResultCode selectTmApiResultCodeById(Integer apiResultCodeId)
	{
	    return tmApiResultCodeMapper.selectTmApiResultCodeById(apiResultCodeId);
	}
	
	/**
     * 查询API结果编码列表
     * 
     * @param tmApiResultCode API结果编码信息
     * @return API结果编码集合
     */
	@Override
	public List<TmApiResultCode> selectTmApiResultCodeList(TmApiResultCode tmApiResultCode)
	{
	    return tmApiResultCodeMapper.selectTmApiResultCodeList(tmApiResultCode);
	}
	
    /**
     * 新增API结果编码
     * 
     * @param tmApiResultCode API结果编码信息
     * @return 结果
     */
	@Override
	public int insertTmApiResultCode(TmApiResultCode tmApiResultCode)
	{
	    return tmApiResultCodeMapper.insertTmApiResultCode(tmApiResultCode);
	}
	
	/**
     * 修改API结果编码
     * 
     * @param tmApiResultCode API结果编码信息
     * @return 结果
     */
	@Override
	public int updateTmApiResultCode(TmApiResultCode tmApiResultCode)
	{
	    return tmApiResultCodeMapper.updateTmApiResultCode(tmApiResultCode);
	}

	/**
     * 删除API结果编码对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteTmApiResultCodeByIds(String ids)
	{
		return tmApiResultCodeMapper.deleteTmApiResultCodeByIds(Convert.toStrArray(ids));
	}

	@Override
	public List<TmApiResultCode> loadByChannelAndResult(String fundChannelCode, FundChannelApiType apiType,
			String resultCode, String resultSubCode) {
		
		List<TmApiResultCode> list = tmApiResultCodeMapper.loadByChannelAndResultCode(fundChannelCode, apiType.getCode(), resultCode, resultSubCode);
		return list;
	}

	@Override
	public void store(TmApiResultCode apiResultCode) {
		apiResultCode.setUseMapping("N");
		tmApiResultCodeMapper.insertTmApiResultCode(apiResultCode);
	}
	
}
