package com.yiran.wechat.service;

import com.yiran.wechat.domain.BsCity;
import java.util.List;

/**
 * 城市设置 服务层
 * 
 * @author yiran
 * @date 2019-07-06
 */
public interface IBsCityService 
{
	/**
     * 查询城市设置信息
     * 
     * @param cityId 城市设置ID
     * @return 城市设置信息
     */
	public BsCity selectBsCityById(Integer cityId);
	
	/**
     * 查询城市设置列表
     * 
     * @param bsCity 城市设置信息
     * @return 城市设置集合
     */
	public List<BsCity> selectBsCityList(BsCity bsCity);
	
	/**
     * 新增城市设置
     * 
     * @param bsCity 城市设置信息
     * @return 结果
     */
	public int insertBsCity(BsCity bsCity);
	
	/**
     * 修改城市设置
     * 
     * @param bsCity 城市设置信息
     * @return 结果
     */
	public int updateBsCity(BsCity bsCity);
		
	/**
     * 删除城市设置信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteBsCityByIds(String ids);
	
}
