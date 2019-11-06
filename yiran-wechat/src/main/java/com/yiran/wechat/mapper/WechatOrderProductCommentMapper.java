package com.yiran.wechat.mapper;

import com.yiran.wechat.domain.WechatOrderProductComment;
import java.util.List;	

/**
 * 订单评论 数据层
 * 
 * @author yiran
 * @date 2019-06-13
 */
public interface WechatOrderProductCommentMapper 
{
	/**
     * 查询订单评论信息
     * 
     * @param id 订单评论ID
     * @return 订单评论信息
     */
	public WechatOrderProductComment selectWechatOrderProductCommentById(Integer id);
	
	/**
     * 查询订单评论列表
     * 
     * @param wechatOrderProductComment 订单评论信息
     * @return 订单评论集合
     */
	public List<WechatOrderProductComment> selectWechatOrderProductCommentList(WechatOrderProductComment wechatOrderProductComment);
	
	/**
     * 新增订单评论
     * 
     * @param wechatOrderProductComment 订单评论信息
     * @return 结果
     */
	public int insertWechatOrderProductComment(WechatOrderProductComment wechatOrderProductComment);
	
	/**
     * 修改订单评论
     * 
     * @param wechatOrderProductComment 订单评论信息
     * @return 结果
     */
	public int updateWechatOrderProductComment(WechatOrderProductComment wechatOrderProductComment);
	
	/**
     * 删除订单评论
     * 
     * @param id 订单评论ID
     * @return 结果
     */
	public int deleteWechatOrderProductCommentById(Integer id);
	
	/**
     * 批量删除订单评论
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteWechatOrderProductCommentByIds(String[] ids);

	/**
	 * 根据商品ID查询评价
	 * @param productId
	 * @return
	 */
	public List<WechatOrderProductComment> selectWechatOrderProductCommentByProductId(String productId);
	
}