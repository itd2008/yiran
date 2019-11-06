package com.yiran.wechat.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yiran.wechat.mapper.WechatOrderInvoiceMapper;
import com.yiran.wechat.domain.WechatOrderInvoice;
import com.yiran.wechat.service.IWechatOrderInvoiceService;
import com.yiran.common.support.Convert;

/**
 * 订单发票 服务层实现
 * 
 * @author yiran
 * @date 2019-06-13
 */
@Service
public class WechatOrderInvoiceServiceImpl implements IWechatOrderInvoiceService 
{
	@Autowired
	private WechatOrderInvoiceMapper wechatOrderInvoiceMapper;

	/**
     * 查询订单发票信息
     * 
     * @param invoiceId 订单发票ID
     * @return 订单发票信息
     */
    @Override
	public WechatOrderInvoice selectWechatOrderInvoiceById(Integer invoiceId)
	{
	    return wechatOrderInvoiceMapper.selectWechatOrderInvoiceById(invoiceId);
	}
	
	/**
     * 查询订单发票列表
     * 
     * @param wechatOrderInvoice 订单发票信息
     * @return 订单发票集合
     */
	@Override
	public List<WechatOrderInvoice> selectWechatOrderInvoiceList(WechatOrderInvoice wechatOrderInvoice)
	{
	    return wechatOrderInvoiceMapper.selectWechatOrderInvoiceList(wechatOrderInvoice);
	}
	
    /**
     * 新增订单发票
     * 
     * @param wechatOrderInvoice 订单发票信息
     * @return 结果
     */
	@Override
	public int insertWechatOrderInvoice(WechatOrderInvoice wechatOrderInvoice)
	{
	    return wechatOrderInvoiceMapper.insertWechatOrderInvoice(wechatOrderInvoice);
	}
	
	/**
     * 修改订单发票
     * 
     * @param wechatOrderInvoice 订单发票信息
     * @return 结果
     */
	@Override
	public int updateWechatOrderInvoice(WechatOrderInvoice wechatOrderInvoice)
	{
	    return wechatOrderInvoiceMapper.updateWechatOrderInvoice(wechatOrderInvoice);
	}

	/**
     * 删除订单发票对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteWechatOrderInvoiceByIds(String ids)
	{
		return wechatOrderInvoiceMapper.deleteWechatOrderInvoiceByIds(Convert.toStrArray(ids));
	}
	
}
