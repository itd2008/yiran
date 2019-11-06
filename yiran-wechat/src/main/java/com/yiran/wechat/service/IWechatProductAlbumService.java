package com.yiran.wechat.service;

import com.yiran.wechat.domain.WechatProductAlbum;
import java.util.List;

/**
 * 商品相册 服务层
 * 
 * @author yiran
 * @date 2019-06-13
 */
public interface IWechatProductAlbumService 
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
     * 删除商品相册信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteWechatProductAlbumByIds(String ids);

	/**
	 * 根据产品ID获取去轮播图
	 * @param productId
	 * @return
	 */
	public List<WechatProductAlbum> selectWechatProductAlbumByProductId(Integer productId);
	
}
