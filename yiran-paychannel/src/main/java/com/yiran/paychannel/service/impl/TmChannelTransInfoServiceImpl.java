package com.yiran.paychannel.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.yiran.paychannel.mapper.TmChannelTransInfoMapper;
import com.yiran.paychannel.converte.ChannelTransInfoConverter;
import com.yiran.paychannel.domain.TmChannelTransInfo;
import com.yiran.paychannel.domain.TmChannelTransInfoDO;
import com.yiran.paychannel.enums.ChannelTransInfoStatus;
import com.yiran.paychannel.enums.FundChannelApiType;
import com.yiran.paychannel.service.ITmChannelTransInfoService;
import com.yiran.paychannel.utils.CommonConverter;
import com.yiran.common.support.Convert;

/**
 * 渠道交易需要用到的特定 服务层实现
 * 
 * @author yiran
 * @date 2019-07-26
 */
@Service
public class TmChannelTransInfoServiceImpl implements ITmChannelTransInfoService 
{
	@Autowired
	private TmChannelTransInfoMapper tmChannelTransInfoMapper;

	/**
     * 查询渠道交易需要用到的特定信息
     * 
     * @param transId 渠道交易需要用到的特定ID
     * @return 渠道交易需要用到的特定信息
     */
    @Override
	public TmChannelTransInfo selectTmChannelTransInfoById(Integer transId)
	{
	    return ChannelTransInfoConverter.convert(tmChannelTransInfoMapper.selectTmChannelTransInfoById(transId));
	}
	
	/**
     * 查询渠道交易需要用到的特定列表
     * 
     * @param tmChannelTransInfo 渠道交易需要用到的特定信息
     * @return 渠道交易需要用到的特定集合
     */
	@Override
	public List<TmChannelTransInfo> selectTmChannelTransInfoList(TmChannelTransInfo tmChannelTransInfo)
	{
	    return ChannelTransInfoConverter.convert(tmChannelTransInfoMapper.selectTmChannelTransInfoList(ChannelTransInfoConverter.convert(tmChannelTransInfo)));
	}
	
    /**
     * 新增渠道交易需要用到的特定
     * 
     * @param tmChannelTransInfo 渠道交易需要用到的特定信息
     * @return 结果
     */
	@Override
	public int insertTmChannelTransInfo(TmChannelTransInfo tmChannelTransInfo)
	{
	    return tmChannelTransInfoMapper.insertTmChannelTransInfo(ChannelTransInfoConverter.convert(tmChannelTransInfo));
	}
	
	/**
     * 修改渠道交易需要用到的特定
     * 
     * @param tmChannelTransInfo 渠道交易需要用到的特定信息
     * @return 结果
     */
	@Override
	public int updateTmChannelTransInfo(TmChannelTransInfo tmChannelTransInfo)
	{
	    return tmChannelTransInfoMapper.updateTmChannelTransInfo(ChannelTransInfoConverter.convert(tmChannelTransInfo));
	}

	/**
     * 删除渠道交易需要用到的特定对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteTmChannelTransInfoByIds(String ids)
	{
		return tmChannelTransInfoMapper.deleteTmChannelTransInfoByIds(Convert.toStrArray(ids));
	}

	@Override
	public Map<String, String> getTransInfo(String fundChannelCode, String transCode) {
		List<TmChannelTransInfo> list = loadByCondition(fundChannelCode,
	            transCode, ChannelTransInfoStatus.IN_PORCESS, null);
	        if (!CollectionUtils.isEmpty(list)) {
	            
	            return list.get(0).getExtension();
	        }
	        return null;
	}

	private List<TmChannelTransInfo> loadByCondition(String fundChannelCode, String transCode,
            ChannelTransInfoStatus status,
            FundChannelApiType apiType) {
		List<TmChannelTransInfoDO> doList;
        List<TmChannelTransInfo> result = new ArrayList<TmChannelTransInfo>();
        if (apiType != null) {
            doList = tmChannelTransInfoMapper.loadByCondition(apiType.getCode(), status.getCode(),
                fundChannelCode, transCode);
        } else {
            doList = tmChannelTransInfoMapper.loadByCondition(null, status.getCode(), fundChannelCode,
                transCode);
        }
        if (!CollectionUtils.isEmpty(doList)) {
            for (TmChannelTransInfoDO object : doList) {
                result.add(ChannelTransInfoConverter.convert(object));
            }
        }
        return result;
	}
	
	
}
