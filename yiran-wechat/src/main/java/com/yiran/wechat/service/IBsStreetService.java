package com.yiran.wechat.service;

import com.yiran.wechat.domain.BsStreet;
import java.util.List;

/**
 * 街道设置 服务层
 * 
 * @author yiran
 * @date 2019-07-06
 */
public interface IBsStreetService 
{
	/**
     * 查询街道设置信息
     * 
     * @param streetId 街道设置ID
     * @return 街道设置信息
     */
	public BsStreet selectBsStreetById(Integer streetId);
	
	/**
     * 查询街道设置列表
     * 
     * @param bsStreet 街道设置信息
     * @return 街道设置集合
     */
	public List<BsStreet> selectBsStreetList(BsStreet bsStreet);
	
	/**
     * 新增街道设置
     * 
     * @param bsStreet 街道设置信息
     * @return 结果
     */
	public int insertBsStreet(BsStreet bsStreet);
	
	/**
     * 修改街道设置
     * 
     * @param bsStreet 街道设置信息
     * @return 结果
     */
	public int updateBsStreet(BsStreet bsStreet);
		
	/**
     * 删除街道设置信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteBsStreetByIds(String ids);
	
}
