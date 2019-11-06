package com.yiran.wechat.mapper;

import com.yiran.wechat.domain.WechatReceivingAddress;
import java.util.List;	

/**
 * 收货地址 数据层
 * 
 * @author yiran
 * @date 2019-06-13
 */
public interface WechatReceivingAddressMapper 
{
	/**
     * 查询收货地址信息
     * 
     * @param id 收货地址ID
     * @return 收货地址信息
     */
	public WechatReceivingAddress selectWechatReceivingAddressById(Integer id);
	
	/**
     * 查询收货地址列表
     * 
     * @param wechatReceivingAddress 收货地址信息
     * @return 收货地址集合
     */
	public List<WechatReceivingAddress> selectWechatReceivingAddressList(WechatReceivingAddress wechatReceivingAddress);
	
	/**
     * 新增收货地址
     * 
     * @param wechatReceivingAddress 收货地址信息
     * @return 结果
     */
	public int insertWechatReceivingAddress(WechatReceivingAddress wechatReceivingAddress);
	
	/**
     * 修改收货地址
     * 
     * @param wechatReceivingAddress 收货地址信息
     * @return 结果
     */
	public int updateWechatReceivingAddress(WechatReceivingAddress wechatReceivingAddress);
	
	/**
     * 删除收货地址
     * 
     * @param id 收货地址ID
     * @return 结果
     */
	public int deleteWechatReceivingAddressById(Integer id);
	
	/**
     * 批量删除收货地址
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteWechatReceivingAddressByIds(String[] ids);

	public int updateIsDefaultAddress(Integer id);

	public WechatReceivingAddress queryDefaultAddress(String openId);
	
}