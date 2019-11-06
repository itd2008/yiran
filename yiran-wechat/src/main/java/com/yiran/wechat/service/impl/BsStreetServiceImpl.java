package com.yiran.wechat.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yiran.wechat.mapper.BsStreetMapper;
import com.yiran.wechat.domain.BsStreet;
import com.yiran.wechat.service.IBsStreetService;
import com.yiran.common.support.Convert;

/**
 * 街道设置 服务层实现
 * 
 * @author yiran
 * @date 2019-07-06
 */
@Service
public class BsStreetServiceImpl implements IBsStreetService 
{
	@Autowired
	private BsStreetMapper bsStreetMapper;

	/**
     * 查询街道设置信息
     * 
     * @param streetId 街道设置ID
     * @return 街道设置信息
     */
    @Override
	public BsStreet selectBsStreetById(Integer streetId)
	{
	    return bsStreetMapper.selectBsStreetById(streetId);
	}
	
	/**
     * 查询街道设置列表
     * 
     * @param bsStreet 街道设置信息
     * @return 街道设置集合
     */
	@Override
	public List<BsStreet> selectBsStreetList(BsStreet bsStreet)
	{
	    return bsStreetMapper.selectBsStreetList(bsStreet);
	}
	
    /**
     * 新增街道设置
     * 
     * @param bsStreet 街道设置信息
     * @return 结果
     */
	@Override
	public int insertBsStreet(BsStreet bsStreet)
	{
	    return bsStreetMapper.insertBsStreet(bsStreet);
	}
	
	/**
     * 修改街道设置
     * 
     * @param bsStreet 街道设置信息
     * @return 结果
     */
	@Override
	public int updateBsStreet(BsStreet bsStreet)
	{
	    return bsStreetMapper.updateBsStreet(bsStreet);
	}

	/**
     * 删除街道设置对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteBsStreetByIds(String ids)
	{
		return bsStreetMapper.deleteBsStreetByIds(Convert.toStrArray(ids));
	}
	
}
