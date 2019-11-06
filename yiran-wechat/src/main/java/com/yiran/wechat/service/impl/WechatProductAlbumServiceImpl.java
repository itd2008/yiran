package com.yiran.wechat.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yiran.wechat.mapper.WechatProductAlbumMapper;
import com.yiran.wechat.domain.WechatProductAlbum;
import com.yiran.wechat.service.IWechatProductAlbumService;
import com.yiran.common.support.Convert;

/**
 * 商品相册 服务层实现
 * 
 * @author yiran
 * @date 2019-06-13
 */
@Service
public class WechatProductAlbumServiceImpl implements IWechatProductAlbumService 
{
	@Autowired
	private WechatProductAlbumMapper wechatProductAlbumMapper;

	/**
     * 查询商品相册信息
     * 
     * @param productAlbumId 商品相册ID
     * @return 商品相册信息
     */
    @Override
	public WechatProductAlbum selectWechatProductAlbumById(Integer productAlbumId)
	{
	    return wechatProductAlbumMapper.selectWechatProductAlbumById(productAlbumId);
	}
	
	/**
     * 查询商品相册列表
     * 
     * @param wechatProductAlbum 商品相册信息
     * @return 商品相册集合
     */
	@Override
	public List<WechatProductAlbum> selectWechatProductAlbumList(WechatProductAlbum wechatProductAlbum)
	{
	    return wechatProductAlbumMapper.selectWechatProductAlbumList(wechatProductAlbum);
	}
	
    /**
     * 新增商品相册
     * 
     * @param wechatProductAlbum 商品相册信息
     * @return 结果
     */
	@Override
	public int insertWechatProductAlbum(WechatProductAlbum wechatProductAlbum)
	{
	    return wechatProductAlbumMapper.insertWechatProductAlbum(wechatProductAlbum);
	}
	
	/**
     * 修改商品相册
     * 
     * @param wechatProductAlbum 商品相册信息
     * @return 结果
     */
	@Override
	public int updateWechatProductAlbum(WechatProductAlbum wechatProductAlbum)
	{
	    return wechatProductAlbumMapper.updateWechatProductAlbum(wechatProductAlbum);
	}

	/**
     * 删除商品相册对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteWechatProductAlbumByIds(String ids)
	{
		return wechatProductAlbumMapper.deleteWechatProductAlbumByIds(Convert.toStrArray(ids));
	}

	@Override
	public List<WechatProductAlbum> selectWechatProductAlbumByProductId(Integer productId) {
		return wechatProductAlbumMapper.selectWechatProductAlbumByProductId(productId);
	}
	
}
