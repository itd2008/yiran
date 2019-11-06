package com.yiran.paychannel.mapper;

import com.yiran.paychannel.domain.TmUnityResultCode;
import java.util.List;	

/**
 * 统一结果代码 数据层
 * 
 * @author yiran
 * @date 2019-04-19
 */
public interface TmUnityResultCodeMapper 
{
	/**
     * 查询统一结果代码信息
     * 
     * @param unityResultCode 统一结果代码ID
     * @return 统一结果代码信息
     */
	public TmUnityResultCode selectTmUnityResultCodeById(String unityResultCode);
	
	/**
     * 查询统一结果代码列表
     * 
     * @param tmUnityResultCode 统一结果代码信息
     * @return 统一结果代码集合
     */
	public List<TmUnityResultCode> selectTmUnityResultCodeList(TmUnityResultCode tmUnityResultCode);
	
	/**
     * 新增统一结果代码
     * 
     * @param tmUnityResultCode 统一结果代码信息
     * @return 结果
     */
	public int insertTmUnityResultCode(TmUnityResultCode tmUnityResultCode);
	
	/**
     * 修改统一结果代码
     * 
     * @param tmUnityResultCode 统一结果代码信息
     * @return 结果
     */
	public int updateTmUnityResultCode(TmUnityResultCode tmUnityResultCode);
	
	/**
     * 删除统一结果代码
     * 
     * @param unityResultCode 统一结果代码ID
     * @return 结果
     */
	public int deleteTmUnityResultCodeById(String unityResultCode);
	
	/**
     * 批量删除统一结果代码
     * 
     * @param unityResultCodes 需要删除的数据ID
     * @return 结果
     */
	public int deleteTmUnityResultCodeByIds(String[] unityResultCodes);

	public int checkUnityResultCode(String unityResultCode);
	
}