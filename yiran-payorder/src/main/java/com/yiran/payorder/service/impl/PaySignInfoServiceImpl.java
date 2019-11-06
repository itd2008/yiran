package com.yiran.payorder.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yiran.payorder.mapper.PaySignInfoMapper;
import com.yiran.payorder.domain.PaySignInfo;
import com.yiran.payorder.domaindo.PaySignInfoDO;
import com.yiran.payorder.service.IPaySignInfoService;
import com.yiran.common.support.Convert;

/**
 * 签约 服务层实现
 * 
 * @author yiran
 * @date 2019-07-13
 */
@Service
public class PaySignInfoServiceImpl implements IPaySignInfoService 
{
	@Autowired
	private PaySignInfoMapper paySignInfoMapper;

	/**
     * 查询签约信息
     * 
     * @param signReqId 签约ID
     * @return 签约信息
     */
    @Override
	public PaySignInfoDO selectPaySignInfoById(Integer signReqId)
	{
	    return paySignInfoMapper.selectPaySignInfoById(signReqId);
	}
	
	/**
     * 查询签约列表
     * 
     * @param paySignInfo 签约信息
     * @return 签约集合
     */
	@Override
	public List<PaySignInfoDO> selectPaySignInfoList(PaySignInfoDO paySignInfo)
	{
	    return paySignInfoMapper.selectPaySignInfoList(paySignInfo);
	}
	
    /**
     * 新增签约
     * 
     * @param paySignInfo 签约信息
     * @return 结果
     */
	@Override
	public int insertPaySignInfo(PaySignInfoDO paySignInfo)
	{
	    return paySignInfoMapper.insertPaySignInfo(paySignInfo);
	}
	
	/**
     * 修改签约
     * 
     * @param paySignInfo 签约信息
     * @return 结果
     */
	@Override
	public int updatePaySignInfo(PaySignInfoDO paySignInfo)
	{
	    return paySignInfoMapper.updatePaySignInfo(paySignInfo);
	}

	/**
     * 删除签约对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deletePaySignInfoByIds(String ids)
	{
		return paySignInfoMapper.deletePaySignInfoByIds(Convert.toStrArray(ids));
	}
	
}
