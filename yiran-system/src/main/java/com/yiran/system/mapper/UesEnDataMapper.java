package com.yiran.system.mapper;

import java.util.List;

import com.yiran.system.domain.UesEnData;	

/**
 * 加密 数据层
 * 
 * @author yiran
 * @date 2019-03-20
 */
public interface UesEnDataMapper 
{
	/**
     * 查询加密信息
     * 
     * @param id 加密ID
     * @return 加密信息
     */
	public UesEnData selectUesEnDataById(Integer id);
	/**
	 *根据票据获取加密对象
	 * @param ticket
	 * @return
	 */
	public UesEnData selectUesEnDataByTicket(String ticket);
	/**
     * 查询加密列表
     * 
     * @param uesEnData 加密信息
     * @return 加密集合
     */
	public List<UesEnData> selectUesEnDataList(UesEnData uesEnData);
	
	/**
     * 新增加密
     * 
     * @param uesEnData 加密信息
     * @return 结果
     */
	public int insertUesEnData(UesEnData uesEnData);
	
	/**
     * 修改加密
     * 
     * @param uesEnData 加密信息
     * @return 结果
     */
	public int updateUesEnData(UesEnData uesEnData);
	
	/**
     * 删除加密
     * 
     * @param id 加密ID
     * @return 结果
     */
	public int deleteUesEnDataById(Integer id);
	
	/**
     * 批量删除加密
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteUesEnDataByIds(String[] ids);
	
}