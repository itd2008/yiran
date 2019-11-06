package com.yiran.wechat.mapper;

import com.yiran.wechat.domain.WechatProductAlbum;
import java.util.List;	

/**
 * 商品相册 数据层
 * 
 * @author yiran
 * @date 2019-06-13
 */
public interface WechatProductAlbumMapper 
{
	/**
     * 查询商品相册信息
     * 
     * @param productAlbumId 商品相册ID
     * @return 商品相册信息
     */
	public WechatProductAlbum selectWechatProductAlbumById(Integer productAlbumId);
	
	/**
     * 查询商品相册列表
     * 
     * @param wechatProductAlbum 商品相册信息
     * @return 商品相册集合
     */
	public List<WechatProductAlbum> selectWechatProductAlbumList(WechatProductAlbum wechatProductAlbum);
	
	/**
     * 新增商品相册
     * 
     * @param wechatProductAlbum 商品相册信息
     * @return 结果
     */
	public int insertWechatProductAlbum(WechatProductAlbum wechatProductAlbum);
	
	/**
     * 修改商品相册
     * 
     * @param wechatProductAlbum 商品相册信息
     * @return 结果
     */
	public int updateWechatProductAlbum(WechatProductAlbum wechatProductAlbum);
	
	/**
     * 删除商品相册
     * 
     * @param productAlbumId 商品相册ID
     * @return 结果
     */
	public int deleteWechatProductAlbumById(Integer productAlbumId);
	
	/**
     * 批量删除商品相册
     * 
     * @param productAlbumIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteWechatProductAlbumByIds(String[] productAlbumIds);

	public List<WechatProductAlbum> selectWechatProductAlbumByProductId(Integer productId);
	
}