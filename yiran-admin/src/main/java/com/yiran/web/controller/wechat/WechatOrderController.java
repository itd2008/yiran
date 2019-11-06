package com.yiran.web.controller.wechat;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.yiran.common.annotation.Log;
import com.yiran.common.enums.BusinessType;
import com.yiran.wechat.domain.WechatOrder;
import com.yiran.wechat.domain.WechatOrderDetail;
import com.yiran.wechat.enums.OrderStatus;
import com.yiran.wechat.service.IWechatOrderDetailService;
import com.yiran.wechat.service.IWechatOrderService;
import com.yiran.weixin.service.IPushMessageService;


import com.yiran.framework.web.base.BaseController;
import com.yiran.paychannel.utils.MapUtil;
import com.yiran.common.page.TableDataInfo;
import com.yiran.common.base.AjaxResult;
import com.yiran.common.config.Global;
import com.yiran.common.utils.poi.ExcelUtil;

/**
 * 订单 信息操作处理
 * 
 * @author yiran
 * @date 2019-06-13
 */
@Controller
@RequestMapping("/wechat/wechatOrder")
public class WechatOrderController extends BaseController
{
    private String prefix = "wechat/wechatOrder";
	
	@Autowired
	private IWechatOrderService wechatOrderService;
	@Autowired
	private IPushMessageService pushMessageService;
	@Autowired
	private IWechatOrderDetailService wechatOrderDetailService;
	
	@RequiresPermissions("wechat:wechatOrder:view")
	@GetMapping()
	public String wechatOrder()
	{
	    return prefix + "/wechatOrder";
	}
	
	/**
	 * 查询订单列表
	 */
	@RequiresPermissions("wechat:wechatOrder:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(WechatOrder wechatOrder)
	{
		startPage();
        List<WechatOrder> list = wechatOrderService.selectWechatOrderList(wechatOrder);
		return getDataTable(list);
	}
		
	@GetMapping("/orderDetail/{orderId}")
	public String orderDetail(@PathVariable("orderId") Integer orderId, ModelMap mmap)
	{
		mmap.put("orderId", orderId);
		return prefix + "/orderDetail";
	}
	
	@PostMapping("/orderDetailList/{orderId}")
	@ResponseBody
	public TableDataInfo orderDetailList(@PathVariable("orderId") Integer orderId)
	{
		WechatOrderDetail wechatOrderDetail = new WechatOrderDetail();
		wechatOrderDetail.setOrderId(String.valueOf(orderId));
		startPage();
        List<WechatOrderDetail> list = wechatOrderDetailService.selectWechatOrderDetailList(wechatOrderDetail);
		return getDataTable(list);
	}
	
	/**
	 * 导出订单列表
	 */
	@RequiresPermissions("wechat:wechatOrder:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(WechatOrder wechatOrder)
    {
    	List<WechatOrder> list = wechatOrderService.selectWechatOrderList(wechatOrder);
        ExcelUtil<WechatOrder> util = new ExcelUtil<WechatOrder>(WechatOrder.class);
        return util.exportExcel(list, "wechatOrder");
    }
	
	/**
	 * 新增订单
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存订单
	 */
	@RequiresPermissions("wechat:wechatOrder:add")
	@Log(title = "订单", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(WechatOrder wechatOrder)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(wechatOrderService.insertWechatOrder(wechatOrder));
	}

	/**
	 * 修改订单
	 */
	@GetMapping("/edit/{orderId}")
	public String edit(@PathVariable("orderId") Integer orderId, ModelMap mmap)
	{
		WechatOrder wechatOrder = wechatOrderService.selectWechatOrderById(orderId);
		mmap.put("wechatOrder", wechatOrder);
	    return prefix + "/edit";
	}
	
	/**
	 * 订单发货
	 */
	@GetMapping("/sendGood/{orderId}")
	public String sendGood(@PathVariable("orderId") Integer orderId, ModelMap mmap)
	{
		WechatOrder wechatOrder = wechatOrderService.selectWechatOrderById(orderId);
		mmap.put("wechatOrder", wechatOrder);
	    return prefix + "/sendgoods";
	}
	
	/**
	 * 订单发货
	 */
	@Log(title = "订单发货", businessType = BusinessType.UPDATE)
	@PostMapping("/sendGood")
	@ResponseBody
	public AjaxResult sendGood(WechatOrder wechatOrder)
	{	
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		
		WechatOrder order = new WechatOrder();
		order.setOrderId(wechatOrder.getOrderId());
		order.setOrderlogisticsId(wechatOrder.getOrderlogisticsId());
		//订单状态修改为已发货OrderStatus
		order.setOrderStatus(OrderStatus.SHIPPED.getCode());
		order.setDeliveryTime(new Date());
		int flag = wechatOrderService.updateWechatOrder(order);
		//推送消息
		Map<String,String> mapMessage = new HashMap<String,String>();
		mapMessage.put("openId", wechatOrderService.selectWechatOrderById(wechatOrder.getOrderId()).getWeixinUser().getOpenid());
		mapMessage.put("delivername", wechatOrder.getDelivername());
		mapMessage.put("trackingNumber", wechatOrder.getOrderlogisticsId());
		pushMessageService.pushCommoditHasBeenNotified(MapUtil.mapToJson(mapMessage));
		return toAjax(flag);
	}
	
	/**
	 * 修改保存订单
	 */
	@RequiresPermissions("wechat:wechatOrder:edit")
	@Log(title = "订单", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(WechatOrder wechatOrder)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(wechatOrderService.updateWechatOrder(wechatOrder));
	}
	
	/**
	 * 删除订单
	 */
	@RequiresPermissions("wechat:wechatOrder:remove")
	@Log(title = "订单", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(wechatOrderService.deleteWechatOrderByIds(ids));
	}
	
}
