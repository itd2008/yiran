package com.yiran.wechat.service;

import com.yiran.wechat.domain.WechatOrderInvoice;
import java.util.List;

/**
 * 订单发票 服务层
 * 
 * @author yiran
 * @date 2019-06-13
 */
public interface IWechatOrderInvoiceService 
{
	/**
     * 查询订单发票信息
     * 
     * @param invoiceId 订单发票ID
     * @return 订单发票信息
     */
	public WechatOrderInvoice selectWechatOrderInvoiceById(Integer invoiceId);
	
	/**
     * 查询订单发票列表
     * 
     * @param wechatOrderInvoice 订单发票信息
     * @return 订单发票集合
     */
	public List<WechatOrderInvoice> selectWechatOrderInvoiceList(WechatOrderInvoice wechatOrderInvoice);
	
	/**
     * 新增订单发票
     * 
     * @param wechatOrderInvoice 订单发票信息
     * @return 结果
     */
	public int insertWechatOrderInvoice(WechatOrderInvoice wechatOrderInvoice);
	
	/**
     * 修改订单发票
     * 
     * @param wechatOrderInvoice 订单发票信息
     * @return 结果
     */
	public int updateWechatOrderInvoice(WechatOrderInvoice wechatOrderInvoice);
		
	/**
     * 删除订单发票信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteWechatOrderInvoiceByIds(String ids);
	
}
