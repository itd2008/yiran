package com.yiran.wechat.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yiran.wechat.mapper.WechatOrderProductCommentMapper;
import com.yiran.wechat.domain.WechatOrderProductComment;
import com.yiran.wechat.service.IWechatOrderProductCommentService;
import com.yiran.common.support.Convert;

/**
 * 订单评论 服务层实现
 * 
 * @author yiran
 * @date 2019-06-13
 */
@Service
public class WechatOrderProductCommentServiceImpl implements IWechatOrderProductCommentService 
{
	@Autowired
	private WechatOrderProductCommentMapper wechatOrderProductCommentMapper;

	/**
     * 查询订单评论信息
     * 
     * @param id 订单评论ID
     * @return 订单评论信息
     */
    @Override
	public WechatOrderProductComment selectWechatOrderProductCommentById(Integer id)
	{
	    return wechatOrderProductCommentMapper.selectWechatOrderProductCommentById(id);
	}
	
	/**
     * 查询订单评论列表
     * 
     * @param wechatOrderProductComment 订单评论信息
     * @return 订单评论集合
     */
	@Override
	public List<WechatOrderProductComment> selectWechatOrderProductCommentList(WechatOrderProductComment wechatOrderProductComment)
	{
	    return wechatOrderProductCommentMapper.selectWechatOrderProductCommentList(wechatOrderProductComment);
	}
	
    /**
     * 新增订单评论
     * 
     * @param wechatOrderProductComment 订单评论信息
     * @return 结果
     */
	@Override
	public int insertWechatOrderProductComment(WechatOrderProductComment wechatOrderProductComment)
	{
	    return wechatOrderProductCommentMapper.insertWechatOrderProductComment(wechatOrderProductComment);
	}
	
	/**
     * 修改订单评论
     * 
     * @param wechatOrderProductComment 订单评论信息
     * @return 结果
     */
	@Override
	public int updateWechatOrderProductComment(WechatOrderProductComment wechatOrderProductComment)
	{
	    return wechatOrderProductCommentMapper.updateWechatOrderProductComment(wechatOrderProductComment);
	}

	/**
     * 删除订单评论对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteWechatOrderProductCommentByIds(String ids)
	{
		return wechatOrderProductCommentMapper.deleteWechatOrderProductCommentByIds(Convert.toStrArray(ids));
	}

	/**
	 * 根据商品ID查询评价
	 * @param productId
	 * @return
	 */
	@Override
	public List<WechatOrderProductComment> selectWechatOrderProductCommentByProductId(Integer productId) {
		return wechatOrderProductCommentMapper.selectWechatOrderProductCommentByProductId(String.valueOf(productId));
	}
	
}
