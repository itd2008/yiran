package com.yiran.web.controller.payorder;

import java.util.List;
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
import com.yiran.payorder.domain.ChannelPayOrder;
import com.yiran.payorder.domaindo.ChannelPayOrderDO;
import com.yiran.payorder.service.IChannelPayOrderService;
import com.yiran.framework.web.base.BaseController;
import com.yiran.common.page.TableDataInfo;
import com.yiran.common.base.AjaxResult;
import com.yiran.common.config.Global;
import com.yiran.common.utils.poi.ExcelUtil;

/**
 * 渠道支付订单 信息操作处理
 * 
 * @author yiran
 * @date 2019-07-13
 */
@Controller
@RequestMapping("/payorder/channelPayOrder")
public class ChannelPayOrderController extends BaseController
{
    private String prefix = "payorder/channelPayOrder";
	
	@Autowired
	private IChannelPayOrderService channelPayOrderService;
	
	@RequiresPermissions("payorder:channelPayOrder:view")
	@GetMapping()
	public String channelPayOrder()
	{
	    return prefix + "/channelPayOrder";
	}
	
	/**
	 * 查询渠道支付订单列表
	 */
	@RequiresPermissions("payorder:channelPayOrder:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(ChannelPayOrderDO channelPayOrder)
	{
		startPage();
        List<ChannelPayOrderDO> list = channelPayOrderService.selectChannelPayOrderList(channelPayOrder);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出渠道支付订单列表
	 */
	@RequiresPermissions("payorder:channelPayOrder:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ChannelPayOrderDO channelPayOrder)
    {
    	List<ChannelPayOrderDO> list = channelPayOrderService.selectChannelPayOrderList(channelPayOrder);
        ExcelUtil<ChannelPayOrderDO> util = new ExcelUtil<ChannelPayOrderDO>(ChannelPayOrderDO.class);
        return util.exportExcel(list, "channelPayOrder");
    }
	
	/**
	 * 新增渠道支付订单
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存渠道支付订单
	 */
	@RequiresPermissions("payorder:channelPayOrder:add")
	@Log(title = "渠道支付订单", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(ChannelPayOrderDO channelPayOrder)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(channelPayOrderService.insertChannelPayOrder(channelPayOrder));
	}

	/**
	 * 修改渠道支付订单
	 */
	@GetMapping("/edit/{paySeqNo}")
	public String edit(@PathVariable("paySeqNo") String paySeqNo, ModelMap mmap)
	{
		ChannelPayOrderDO channelPayOrder = channelPayOrderService.selectChannelPayOrderById(paySeqNo);
		mmap.put("channelPayOrder", channelPayOrder);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存渠道支付订单
	 */
	@RequiresPermissions("payorder:channelPayOrder:edit")
	@Log(title = "渠道支付订单", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(ChannelPayOrderDO channelPayOrder)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(channelPayOrderService.updateChannelPayOrder(channelPayOrder));
	}
	
	/**
	 * 删除渠道支付订单
	 */
	@RequiresPermissions("payorder:channelPayOrder:remove")
	@Log(title = "渠道支付订单", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(channelPayOrderService.deleteChannelPayOrderByIds(ids));
	}
	
}
