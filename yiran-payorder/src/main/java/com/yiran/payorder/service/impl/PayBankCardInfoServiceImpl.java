package com.yiran.payorder.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yiran.payorder.mapper.PayBankCardInfoMapper;
import com.yiran.payorder.domain.PayBankCardInfo;
import com.yiran.payorder.domaindo.PayBankCardInfoDO;
import com.yiran.payorder.service.IPayBankCardInfoService;
import com.yiran.common.support.Convert;

/**
 * 银行卡 服务层实现
 * 
 * @author yiran
 * @date 2019-07-13
 */
@Service
public class PayBankCardInfoServiceImpl implements IPayBankCardInfoService 
{
	@Autowired
	private PayBankCardInfoMapper payBankCardInfoMapper;

	/**
     * 查询银行卡信息
     * 
     * @param bankCardId 银行卡ID
     * @return 银行卡信息
     */
    @Override
	public PayBankCardInfoDO selectPayBankCardInfoById(Integer bankCardId)
	{
	    return payBankCardInfoMapper.selectPayBankCardInfoById(bankCardId);
	}
	
	/**
     * 查询银行卡列表
     * 
     * @param payBankCardInfo 银行卡信息
     * @return 银行卡集合
     */
	@Override
	public List<PayBankCardInfoDO> selectPayBankCardInfoList(PayBankCardInfoDO payBankCardInfo)
	{
	    return payBankCardInfoMapper.selectPayBankCardInfoList(payBankCardInfo);
	}
	
    /**
     * 新增银行卡
     * 
     * @param payBankCardInfo 银行卡信息
     * @return 结果
     */
	@Override
	public int insertPayBankCardInfo(PayBankCardInfoDO payBankCardInfo)
	{
	    return payBankCardInfoMapper.insertPayBankCardInfo(payBankCardInfo);
	}
	
	/**
     * 修改银行卡
     * 
     * @param payBankCardInfo 银行卡信息
     * @return 结果
     */
	@Override
	public int updatePayBankCardInfo(PayBankCardInfoDO payBankCardInfo)
	{
	    return payBankCardInfoMapper.updatePayBankCardInfo(payBankCardInfo);
	}

	/**
     * 删除银行卡对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deletePayBankCardInfoByIds(String ids)
	{
		return payBankCardInfoMapper.deletePayBankCardInfoByIds(Convert.toStrArray(ids));
	}
	
}
