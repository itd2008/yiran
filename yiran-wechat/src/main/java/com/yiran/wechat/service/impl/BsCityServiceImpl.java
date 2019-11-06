package com.yiran.wechat.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yiran.wechat.mapper.BsCityMapper;
import com.yiran.wechat.domain.BsCity;
import com.yiran.wechat.service.IBsCityService;
import com.yiran.common.support.Convert;

/**
 * 城市设置 服务层实现
 * 
 * @author yiran
 * @date 2019-07-06
 */
@Service
public class BsCityServiceImpl implements IBsCityService 
{
	@Autowired
	private BsCityMapper bsCityMapper;

	/**
     * 查询城市设置信息
     * 
     * @param cityId 城市设置ID
     * @return 城市设置信息
     */
    @Override
	public BsCity selectBsCityById(Integer cityId)
	{
	    return bsCityMapper.selectBsCityById(cityId);
	}
	
	/**
     * 查询城市设置列表
     * 
     * @param bsCity 城市设置信息
     * @return 城市设置集合
     */
	@Override
	public List<BsCity> selectBsCityList(BsCity bsCity)
	{
	    return bsCityMapper.selectBsCityList(bsCity);
	}
	
    /**
     * 新增城市设置
     * 
     * @param bsCity 城市设置信息
     * @return 结果
     */
	@Override
	public int insertBsCity(BsCity bsCity)
	{
	    return bsCityMapper.insertBsCity(bsCity);
	}
	
	/**
     * 修改城市设置
     * 
     * @param bsCity 城市设置信息
     * @return 结果
     */
	@Override
	public int updateBsCity(BsCity bsCity)
	{
	    return bsCityMapper.updateBsCity(bsCity);
	}

	/**
     * 删除城市设置对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteBsCityByIds(String ids)
	{
		return bsCityMapper.deleteBsCityByIds(Convert.toStrArray(ids));
	}
	
}
