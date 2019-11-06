package com.yiran.wechat.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yiran.wechat.mapper.WechatProductCouponMapper;
import com.yiran.wechat.domain.WechatProductCoupon;
import com.yiran.wechat.service.IWechatProductCouponService;
import com.yiran.common.support.Convert;

/**
 * 商品优惠券 服务层实现
 * 
 * @author yiran
 * @date 2019-06-13
 */
@Service
public class WechatProductCouponServiceImpl implements IWechatProductCouponService 
{
	@Autowired
	private WechatProductCouponMapper wechatProductCouponMapper;

	/**
     * 查询商品优惠券信息
     * 
     * @param productCouponId 商品优惠券ID
     * @return 商品优惠券信息
     */
    @Override
	public WechatProductCoupon selectWechatProductCouponById(Integer productCouponId)
	{
	    return wechatProductCouponMapper.selectWechatProductCouponById(productCouponId);
	}
	
	/**
     * 查询商品优惠券列表
     * 
     * @param wechatProductCoupon 商品优惠券信息
     * @return 商品优惠券集合
     */
	@Override
	public List<WechatProductCoupon> selectWechatProductCouponList(WechatProductCoupon wechatProductCoupon)
	{
	    return wechatProductCouponMapper.selectWechatProductCouponList(wechatProductCoupon);
	}
	
    /**
     * 新增商品优惠券
     * 
     * @param wechatProductCoupon 商品优惠券信息
     * @return 结果
     */
	@Override
	public int insertWechatProductCoupon(WechatProductCoupon wechatProductCoupon)
	{
		wechatProductCoupon.setReceiveNum("0");
		wechatProductCoupon.setResidualQuantity(wechatProductCoupon.getQuantity());
	    return wechatProductCouponMapper.insertWechatProductCoupon(wechatProductCoupon);
	}
	
	/**
     * 修改商品优惠券
     * 
     * @param wechatProductCoupon 商品优惠券信息
     * @return 结果
     */
	@Override
	public int updateWechatProductCoupon(WechatProductCoupon wechatProductCoupon)
	{
	    return wechatProductCouponMapper.updateWechatProductCoupon(wechatProductCoupon);
	}

	/**
     * 删除商品优惠券对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteWechatProductCouponByIds(String ids)
	{
		return wechatProductCouponMapper.deleteWechatProductCouponByIds(Convert.toStrArray(ids));
	}

	@Override
	public List<WechatProductCoupon> selectWechatEffectiveProductCouponList() {
		return wechatProductCouponMapper.selectWechatEffectiveProductCouponList();
	}

	@Override
	public WechatProductCoupon selectWechatProductCouponBySerialNo(String serialNo) {
		return wechatProductCouponMapper.selectWechatProductCouponBySerialNo(serialNo);
	}

	@Override
	public List<WechatProductCoupon> selectWechatProductCouponBySerialNos(List<String> serialNos) {
		return wechatProductCouponMapper.selectWechatProductCouponBySerialNos(serialNos);
	}

	@Override
	public int updateWechatProductCouponNumber(WechatProductCoupon productCoupon) {
		return wechatProductCouponMapper.updateWechatProductCouponNumber(productCoupon);
	}
	
}
