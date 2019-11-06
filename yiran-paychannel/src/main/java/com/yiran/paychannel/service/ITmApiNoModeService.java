package com.yiran.paychannel.service;

import com.yiran.paychannel.domain.TmApiNoMode;
import java.util.List;

/**
 * 资金源接口订单号模式 服务层
 * 
 * @author yiran
 * @date 2019-04-19
 */
public interface ITmApiNoModeService 
{
	/**
     * 查询资金源接口订单号模式信息
     * 
     * @param apiNoModeId 资金源接口订单号模式ID
     * @return 资金源接口订单号模式信息
     */
	public TmApiNoMode selectTmApiNoModeById(Integer apiNoModeId);
	
	/**
     * 查询资金源接口订单号模式列表
     * 
     * @param tmApiNoMode 资金源接口订单号模式信息
     * @return 资金源接口订单号模式集合
     */
	public List<TmApiNoMode> selectTmApiNoModeList(TmApiNoMode tmApiNoMode);
	
	/**
     * 新增资金源接口订单号模式
     * 
     * @param tmApiNoMode 资金源接口订单号模式信息
     * @return 结果
     */
	public int insertTmApiNoMode(TmApiNoMode tmApiNoMode);
	
	/**
     * 修改资金源接口订单号模式
     * 
     * @param tmApiNoMode 资金源接口订单号模式信息
     * @return 结果
     */
	public int updateTmApiNoMode(TmApiNoMode tmApiNoMode);
		
	/**
     * 删除资金源接口订单号模式信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteTmApiNoModeByIds(String ids);
	
}
