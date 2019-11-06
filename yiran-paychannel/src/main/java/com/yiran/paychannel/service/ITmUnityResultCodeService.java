package com.yiran.paychannel.service;

import com.yiran.paychannel.domain.TmApiResultCode;
import com.yiran.paychannel.domain.TmUnityResultCode;
import com.yiran.paychannel.enums.FundChannelApiType;

import java.util.List;
import java.util.Map;

/**
 * 统一结果代码 服务层
 * 
 * @author yiran
 * @date 2019-04-19
 */
public interface ITmUnityResultCodeService 
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
     * 删除统一结果代码信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteTmUnityResultCodeByIds(String ids);
	
	/**
	 * 检查统一结果码是否存在
	 * @param unityResultCode
	 * @return
	 */
	public String checkUnityResultCode(String unityResultCode);

	public TmApiResultCode parse(String fundChannelCode, FundChannelApiType apiType,
            String resultCode, String resultSubCode,
            Map<String, Object> extension,String memo);
	
}
