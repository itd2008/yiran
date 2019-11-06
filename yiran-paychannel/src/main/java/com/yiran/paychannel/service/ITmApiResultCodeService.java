package com.yiran.paychannel.service;

import com.yiran.paychannel.domain.TmApiResultCode;
import com.yiran.paychannel.enums.FundChannelApiType;

import java.util.List;

/**
 * API结果编码 服务层
 * 
 * @author yiran
 * @date 2019-04-19
 */
public interface ITmApiResultCodeService 
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
     * 删除API结果编码信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteTmApiResultCodeByIds(String ids);

	public List<TmApiResultCode> loadByChannelAndResult(String fundChannelCode, FundChannelApiType apiType,
			String resultCode, String tempResultSubCode);

	public void store(TmApiResultCode apiResultCode);

	
}
