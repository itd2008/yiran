package com.yiran.payorder.service;

import com.yiran.payorder.domaindo.PayBankCardInfoDO;

import java.util.List;

/**
 * 银行卡 服务层
 * 
 * @author yiran
 * @date 2019-07-13
 */
public interface IPayBankCardInfoService 
{
	/**
     * 查询银行卡信息
     * 
     * @param bankCardId 银行卡ID
     * @return 银行卡信息
     */
	public PayBankCardInfoDO selectPayBankCardInfoById(Integer bankCardId);
	
	/**
     * 查询银行卡列表
     * 
     * @param payBankCardInfo 银行卡信息
     * @return 银行卡集合
     */
	public List<PayBankCardInfoDO> selectPayBankCardInfoList(PayBankCardInfoDO payBankCardInfo);
	
	/**
     * 新增银行卡
     * 
     * @param payBankCardInfo 银行卡信息
     * @return 结果
     */
	public int insertPayBankCardInfo(PayBankCardInfoDO payBankCardInfo);
	
	/**
     * 修改银行卡
     * 
     * @param payBankCardInfo 银行卡信息
     * @return 结果
     */
	public int updatePayBankCardInfo(PayBankCardInfoDO payBankCardInfo);
		
	/**
     * 删除银行卡信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deletePayBankCardInfoByIds(String ids);
	
	
}
