package com.yiran.wechat.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yiran.wechat.mapper.WechatUserProductCouponMapper;
import com.yiran.wechat.domain.WechatUserProductCoupon;
import com.yiran.wechat.service.IWechatUserProductCouponService;
import com.yiran.common.support.Convert;

/**
 * 用户优惠券关联 服务层实现
 * 
 * @author yiran
 * @date 2019-09-08
 */
@Service
public class WechatUserProductCouponServiceImpl implements IWechatUserProductCouponService 
{
	@Autowired
	private WechatUserProductCouponMapper wechatUserProductCouponMapper;

	/**
     * 查询用户优惠券关联信息
     * 
     * @param id 用户优惠券关联ID
     * @return 用户优惠券关联信息
     */
    @Override
	public WechatUserProductCoupon selectWechatUserProductCouponById(Integer id)
	{
	    return wechatUserProductCouponMapper.selectWechatUserProductCouponById(id);
	}
	
	/**
     * 查询用户优惠券关联列表
     * 
     * @param wechatUserProductCoupon 用户优惠券关联信息
     * @return 用户优惠券关联集合
     */
	@Override
	public List<WechatUserProductCoupon> selectWechatUserProductCouponList(WechatUserProductCoupon wechatUserProductCoupon)
	{
	    return wechatUserProductCouponMapper.selectWechatUserProductCouponList(wechatUserProductCoupon);
	}
	
    /**
     * 新增用户优惠券关联
     * 
     * @param wechatUserProductCoupon 用户优惠券关联信息
     * @return 结果
     */
	@Override
	public int insertWechatUserProductCoupon(WechatUserProductCoupon wechatUserProductCoupon)
	{
	    return wechatUserProductCouponMapper.insertWechatUserProductCoupon(wechatUserProductCoupon);
	}
	
	/**
     * 修改用户优惠券关联
     * 
     * @param wechatUserProductCoupon 用户优惠券关联信息
     * @return 结果
     */
	@Override
	public int updateWechatUserProductCoupon(WechatUserProductCoupon wechatUserProductCoupon)
	{
	    return wechatUserProductCouponMapper.updateWechatUserProductCoupon(wechatUserProductCoupon);
	}

	/**
     * 删除用户优惠券关联对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteWechatUserProductCouponByIds(String ids)
	{
		return wechatUserProductCouponMapper.deleteWechatUserProductCouponByIds(Convert.toStrArray(ids));
	}

	@Override
	public int updateWechatUserProductCouponBySerialNo(WechatUserProductCoupon wechatUserProductCoupon) {
		return wechatUserProductCouponMapper.updateWechatUserProductCouponBySerialNo(wechatUserProductCoupon);
	}
	
}
