package com.yiran.wechat.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yiran.wechat.mapper.BsAreaMapper;
import com.yiran.wechat.domain.BsArea;
import com.yiran.wechat.service.IBsAreaService;
import com.yiran.common.support.Convert;

/**
 * 地区设置 服务层实现
 * 
 * @author yiran
 * @date 2019-07-06
 */
@Service
public class BsAreaServiceImpl implements IBsAreaService 
{
	@Autowired
	private BsAreaMapper bsAreaMapper;

	/**
     * 查询地区设置信息
     * 
     * @param areaId 地区设置ID
     * @return 地区设置信息
     */
    @Override
	public BsArea selectBsAreaById(Integer areaId)
	{
	    return bsAreaMapper.selectBsAreaById(areaId);
	}
	
	/**
     * 查询地区设置列表
     * 
     * @param bsArea 地区设置信息
     * @return 地区设置集合
     */
	@Override
	public List<BsArea> selectBsAreaList(BsArea bsArea)
	{
	    return bsAreaMapper.selectBsAreaList(bsArea);
	}
	
    /**
     * 新增地区设置
     * 
     * @param bsArea 地区设置信息
     * @return 结果
     */
	@Override
	public int insertBsArea(BsArea bsArea)
	{
	    return bsAreaMapper.insertBsArea(bsArea);
	}
	
	/**
     * 修改地区设置
     * 
     * @param bsArea 地区设置信息
     * @return 结果
     */
	@Override
	public int updateBsArea(BsArea bsArea)
	{
	    return bsAreaMapper.updateBsArea(bsArea);
	}

	/**
     * 删除地区设置对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteBsAreaByIds(String ids)
	{
		return bsAreaMapper.deleteBsAreaByIds(Convert.toStrArray(ids));
	}
	
}
