package com.yiran.wechat.service;

import com.yiran.wechat.domain.WechatProductCoupon;
import java.util.List;

/**
 * 商品优惠券 服务层
 * 
 * @author yiran
 * @date 2019-06-13
 */
public interface IWechatProductCouponService 
{
	/**
     * 查询商品优惠券信息
     * 
     * @param productCouponId 商品优惠券ID
     * @return 商品优惠券信息
     */
	public WechatProductCoupon selectWechatProductCouponById(Integer productCouponId);
	
	/**
     * 查询商品优惠券列表
     * 
     * @param wechatProductCoupon 商品优惠券信息
     * @return 商品优惠券集合
     */
	public List<WechatProductCoupon> selectWechatProductCouponList(WechatProductCoupon wechatProductCoupon);
	
	/**
     * 新增商品优惠券
     * 
     * @param wechatProductCoupon 商品优惠券信息
     * @return 结果
     */
	public int insertWechatProductCoupon(WechatProductCoupon wechatProductCoupon);
	
	/**
     * 修改商品优惠券
     * 
     * @param wechatProductCoupon 商品优惠券信息
     * @return 结果
     */
	public int updateWechatProductCoupon(WechatProductCoupon wechatProductCoupon);
		
	/**
     * 删除商品优惠券信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteWechatProductCouponByIds(String ids);

	/**
	 * 获取有效优惠券
	 * @return
	 */
	public List<WechatProductCoupon> selectWechatEffectiveProductCouponList();

	/**
	 * 根据序号获取优惠券
	 * @param serialNo
	 * @return
	 */
	public WechatProductCoupon selectWechatProductCouponBySerialNo(String serialNo);

	/**
	 * 根据序列号集合获取优惠券
	 * @param serialNos
	 * @return
	 */
	public List<WechatProductCoupon> selectWechatProductCouponBySerialNos(List<String> serialNos);

	public int updateWechatProductCouponNumber(WechatProductCoupon productCoupon);
	
}
