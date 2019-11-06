package com.yiran.wechat.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yiran.wechat.mapper.WechatIndexPicMapper;
import com.yiran.wechat.domain.WechatIndexPic;
import com.yiran.wechat.service.IWechatIndexPicService;
import com.yiran.common.support.Convert;

/**
 * 首页宣传图片 服务层实现
 * 
 * @author yiran
 * @date 2019-06-13
 */
@Service
public class WechatIndexPicServiceImpl implements IWechatIndexPicService 
{
	@Autowired
	private WechatIndexPicMapper wechatIndexPicMapper;

	/**
     * 查询首页宣传图片信息
     * 
     * @param id 首页宣传图片ID
     * @return 首页宣传图片信息
     */
    @Override
	public WechatIndexPic selectWechatIndexPicById(Integer id)
	{
	    return wechatIndexPicMapper.selectWechatIndexPicById(id);
	}
	
	/**
     * 查询首页宣传图片列表
     * 
     * @param wechatIndexPic 首页宣传图片信息
     * @return 首页宣传图片集合
     */
	@Override
	public List<WechatIndexPic> selectWechatIndexPicList(WechatIndexPic wechatIndexPic)
	{
	    return wechatIndexPicMapper.selectWechatIndexPicList(wechatIndexPic);
	}
	
    /**
     * 新增首页宣传图片
     * 
     * @param wechatIndexPic 首页宣传图片信息
     * @return 结果
     */
	@Override
	public int insertWechatIndexPic(WechatIndexPic wechatIndexPic)
	{
	    return wechatIndexPicMapper.insertWechatIndexPic(wechatIndexPic);
	}
	
	/**
     * 修改首页宣传图片
     * 
     * @param wechatIndexPic 首页宣传图片信息
     * @return 结果
     */
	@Override
	public int updateWechatIndexPic(WechatIndexPic wechatIndexPic)
	{
	    return wechatIndexPicMapper.updateWechatIndexPic(wechatIndexPic);
	}

	/**
     * 删除首页宣传图片对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteWechatIndexPicByIds(String ids)
	{
		return wechatIndexPicMapper.deleteWechatIndexPicByIds(Convert.toStrArray(ids));
	}
	
}
