package com.yiran.wechat.service;

import com.yiran.wechat.domain.BsArea;
import java.util.List;

/**
 * 地区设置 服务层
 * 
 * @author yiran
 * @date 2019-07-06
 */
public interface IBsAreaService 
{
	/**
     * 查询地区设置信息
     * 
     * @param areaId 地区设置ID
     * @return 地区设置信息
     */
	public BsArea selectBsAreaById(Integer areaId);
	
	/**
     * 查询地区设置列表
     * 
     * @param bsArea 地区设置信息
     * @return 地区设置集合
     */
	public List<BsArea> selectBsAreaList(BsArea bsArea);
	
	/**
     * 新增地区设置
     * 
     * @param bsArea 地区设置信息
     * @return 结果
     */
	public int insertBsArea(BsArea bsArea);
	
	/**
     * 修改地区设置
     * 
     * @param bsArea 地区设置信息
     * @return 结果
     */
	public int updateBsArea(BsArea bsArea);
		
	/**
     * 删除地区设置信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteBsAreaByIds(String ids);
	
}
