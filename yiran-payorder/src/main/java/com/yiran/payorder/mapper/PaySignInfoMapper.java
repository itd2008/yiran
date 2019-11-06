package com.yiran.payorder.mapper;

import com.yiran.payorder.domain.PaySignInfo;
import com.yiran.payorder.domaindo.PaySignInfoDO;

import java.util.List;

import org.apache.ibatis.annotations.Param;	

/**
 * 签约 数据层
 * 
 * @author yiran
 * @date 2019-07-13
 */
public interface PaySignInfoMapper 
{
	/**
     * 查询签约信息
     * 
     * @param signReqId 签约ID
     * @return 签约信息
     */
	public PaySignInfoDO selectPaySignInfoById(Integer signReqId);
	
	/**
     * 查询签约列表
     * 
     * @param paySignInfo 签约信息
     * @return 签约集合
     */
	public List<PaySignInfoDO> selectPaySignInfoList(PaySignInfoDO paySignInfo);
	
	/**
     * 新增签约
     * 
     * @param paySignInfo 签约信息
     * @return 结果
     */
	public int insertPaySignInfo(PaySignInfoDO paySignInfo);
	
	/**
     * 修改签约
     * 
     * @param paySignInfo 签约信息
     * @return 结果
     */
	public int updatePaySignInfo(PaySignInfoDO paySignInfo);
	
	/**
     * 删除签约
     * 
     * @param signReqId 签约ID
     * @return 结果
     */
	public int deletePaySignInfoById(Integer signReqId);
	
	/**
     * 批量删除签约
     * 
     * @param signReqIds 需要删除的数据ID
     * @return 结果
     */
	public int deletePaySignInfoByIds(String[] signReqIds);

	public List<PaySignInfoDO> loadSignInfoByBankCardId(@Param("bankCardId") String bankCardId, 
			@Param("memberId")String memberId, @Param("signSource")String signSource);

	public PaySignInfoDO fetchFinishSignInfoByFundChannelCode(@Param("fundChannelCode")String fundChannelCode, 
			@Param("cardNo")String cardNo, 
			@Param("status")String status);
	
}