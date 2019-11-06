package com.yiran.payorder.service;

import com.yiran.payorder.domain.PaySignInfo;
import com.yiran.payorder.domaindo.PaySignInfoDO;

import java.util.List;

/**
 * 签约 服务层
 * 
 * @author yiran
 * @date 2019-07-13
 */
public interface IPaySignInfoService 
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
     * 删除签约信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deletePaySignInfoByIds(String ids);
	
}
