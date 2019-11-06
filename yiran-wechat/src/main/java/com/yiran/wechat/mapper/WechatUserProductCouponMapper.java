package com.yiran.wechat.mapper;

import com.yiran.wechat.domain.WechatUserProductCoupon;
import java.util.List;	

/**
 * 用户优惠券关联 数据层
 * 
 * @author yiran
 * @date 2019-09-08
 */
public interface WechatUserProductCouponMapper 
{
	/**
     * 查询用户优惠券关联信息
     * 
     * @param id 用户优惠券关联ID
     * @return 用户优惠券关联信息
     */
	public WechatUserProductCoupon selectWechatUserProductCouponById(Integer id);
	
	/**
     * 查询用户优惠券关联列表
     * 
     * @param wechatUserProductCoupon 用户优惠券关联信息
     * @return 用户优惠券关联集合
     */
	public List<WechatUserProductCoupon> selectWechatUserProductCouponList(WechatUserProductCoupon wechatUserProductCoupon);
	
	/**
     * 新增用户优惠券关联
     * 
     * @param wechatUserProductCoupon 用户优惠券关联信息
     * @return 结果
     */
	public int insertWechatUserProductCoupon(WechatUserProductCoupon wechatUserProductCoupon);
	
	/**
     * 修改用户优惠券关联
     * 
     * @param wechatUserProductCoupon 用户优惠券关联信息
     * @return 结果
     */
	public int updateWechatUserProductCoupon(WechatUserProductCoupon wechatUserProductCoupon);
	
	/**
     * 删除用户优惠券关联
     * 
     * @param id 用户优惠券关联ID
     * @return 结果
     */
	public int deleteWechatUserProductCouponById(Integer id);
	
	/**
     * 批量删除用户优惠券关联
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteWechatUserProductCouponByIds(String[] ids);

	public int updateWechatUserProductCouponBySerialNo(WechatUserProductCoupon wechatUserProductCoupon);
	
}