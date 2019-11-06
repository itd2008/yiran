package com.yiran.wechat.service;

import com.yiran.wechat.domain.BsProvince;
import com.yiran.wechat.domain.Province;

import java.util.List;

/**
 * 省份设置 服务层
 * 
 * @author yiran
 * @date 2019-07-06
 */
public interface IBsProvinceService 
{
	/**
     * 查询省份设置信息
     * 
     * @param provinceId 省份设置ID
     * @return 省份设置信息
     */
	public BsProvince selectBsProvinceById(Integer provinceId);
	
	/**
     * 查询省份设置列表
     * 
     * @param bsProvince 省份设置信息
     * @return 省份设置集合
     */
	public List<BsProvince> selectBsProvinceList(BsProvince bsProvince);
	
	/**
     * 新增省份设置
     * 
     * @param bsProvince 省份设置信息
     * @return 结果
     */
	public int insertBsProvince(BsProvince bsProvince);
	
	/**
     * 修改省份设置
     * 
     * @param bsProvince 省份设置信息
     * @return 结果
     */
	public int updateBsProvince(BsProvince bsProvince);
		
	/**
     * 删除省份设置信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteBsProvinceByIds(String ids);

	/**
	 * 获取所有的省份
	 * @return
	 */
	public List<BsProvince> selectBsProvinceLists();
	/**
	 * 获取各个省下的各个市下的所有区
	 * @return
	 */
	public List<Province> findProvinceList();
	
}
