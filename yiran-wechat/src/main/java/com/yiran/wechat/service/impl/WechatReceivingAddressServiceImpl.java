package com.yiran.wechat.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yiran.wechat.mapper.WechatReceivingAddressMapper;
import com.yiran.wechat.domain.WechatReceivingAddress;
import com.yiran.wechat.service.IWechatReceivingAddressService;
import com.yiran.common.support.Convert;

/**
 * 收货地址 服务层实现
 * 
 * @author yiran
 * @date 2019-06-13
 */
@Service
public class WechatReceivingAddressServiceImpl implements IWechatReceivingAddressService 
{
	@Autowired
	private WechatReceivingAddressMapper wechatReceivingAddressMapper;

	/**
     * 查询收货地址信息
     * 
     * @param id 收货地址ID
     * @return 收货地址信息
     */
    @Override
	public WechatReceivingAddress selectWechatReceivingAddressById(Integer id)
	{
	    return wechatReceivingAddressMapper.selectWechatReceivingAddressById(id);
	}
	
	/**
     * 查询收货地址列表
     * 
     * @param wechatReceivingAddress 收货地址信息
     * @return 收货地址集合
     */
	@Override
	public List<WechatReceivingAddress> selectWechatReceivingAddressList(WechatReceivingAddress wechatReceivingAddress)
	{
	    return wechatReceivingAddressMapper.selectWechatReceivingAddressList(wechatReceivingAddress);
	}
	
    /**
     * 新增收货地址
     * 
     * @param wechatReceivingAddress 收货地址信息
     * @return 结果
     */
	@Override
	public int insertWechatReceivingAddress(WechatReceivingAddress wechatReceivingAddress)
	{
	    return wechatReceivingAddressMapper.insertWechatReceivingAddress(wechatReceivingAddress);
	}
	
	/**
     * 修改收货地址
     * 
     * @param wechatReceivingAddress 收货地址信息
     * @return 结果
     */
	@Override
	public int updateWechatReceivingAddress(WechatReceivingAddress wechatReceivingAddress)
	{
	    return wechatReceivingAddressMapper.updateWechatReceivingAddress(wechatReceivingAddress);
	}

	/**
     * 删除收货地址对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteWechatReceivingAddressByIds(String ids)
	{
		return wechatReceivingAddressMapper.deleteWechatReceivingAddressByIds(Convert.toStrArray(ids));
	}

	@Override
	public int updateIsDefaultAddress(Integer id) {
		return wechatReceivingAddressMapper.updateIsDefaultAddress(id);
	}

	@Override
	public int deleteWechatReceivingAddressById(Integer addressId) {
		return wechatReceivingAddressMapper.deleteWechatReceivingAddressById(addressId);
	}

	@Override
	public WechatReceivingAddress queryDefaultAddress(String openId) {
		
		return wechatReceivingAddressMapper.queryDefaultAddress(openId);
	}
	
}
