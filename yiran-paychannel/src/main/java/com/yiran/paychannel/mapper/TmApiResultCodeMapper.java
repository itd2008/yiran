package com.yiran.paychannel.mapper;

import com.yiran.paychannel.domain.TmApiResultCode;
import java.util.List;

import org.apache.ibatis.annotations.Param;	

/**
 * API结果编码 数据层
 * 
 * @author yiran
 * @date 2019-04-19
 */
public interface TmApiResultCodeMapper 
{
	/**
     * 查询API结果编码信息
     * 
     * @param apiResultCodeId API结果编码ID
     * @return API结果编码信息
     */
	public TmApiResultCode selectTmApiResultCodeById(Integer apiResultCodeId);
	
	/**
     * 查询API结果编码列表
     * 
     * @param tmApiResultCode API结果编码信息
     * @return API结果编码集合
     */
	public List<TmApiResultCode> selectTmApiResultCodeList(TmApiResultCode tmApiResultCode);
	
	/**
     * 新增API结果编码
     * 
     * @param tmApiResultCode API结果编码信息
     * @return 结果
     */
	public int insertTmApiResultCode(TmApiResultCode tmApiResultCode);
	
	/**
     * 修改API结果编码
     * 
     * @param tmApiResultCode API结果编码信息
     * @return 结果
     */
	public int updateTmApiResultCode(TmApiResultCode tmApiResultCode);
	
	/**
     * 删除API结果编码
     * 
     * @param apiResultCodeId API结果编码ID
     * @return 结果
     */
	public int deleteTmApiResultCodeById(Integer apiResultCodeId);
	
	/**
     * 批量删除API结果编码
     * 
     * @param apiResultCodeIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteTmApiResultCodeByIds(String[] apiResultCodeIds);

	public List<TmApiResultCode> loadByChannelAndResultCode(@Param("fundChannelCode")String fundChannelCode, 
			@Param("api_type")String api_type,
			@Param("resultCode")String resultCode,
			@Param("resultSubCode")String resultSubCode);
	
}