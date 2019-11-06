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
import com.yiran.payorder.domain.PayRefundOrder;
import com.yiran.payorder.service.IPayRefundOrderService;
import com.yiran.framework.web.base.BaseController;
import com.yiran.common.page.TableDataInfo;
import com.yiran.common.base.AjaxResult;
import com.yiran.common.config.Global;
import com.yiran.common.utils.poi.ExcelUtil;

/**
 * 退款订单 信息操作处理
 * 
 * @author yiran
 * @date 2019-07-13
 */
@Controller
@RequestMapping("/payorder/payRefundOrder")
public class PayRefundOrderController extends BaseController
{
    private String prefix = "payorder/payRefundOrder";
	
	@Autowired
	private IPayRefundOrderService payRefundOrderService;
	
	@RequiresPermissions("payorder:payRefundOrder:view")
	@GetMapping()
	public String payRefundOrder()
	{
	    return prefix + "/payRefundOrder";
	}
	
	/**
	 * 查询退款订单列表
	 */
	@RequiresPermissions("payorder:payRefundOrder:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(PayRefundOrder payRefundOrder)
	{
		startPage();
        List<PayRefundOrder> list = payRefundOrderService.selectPayRefundOrderList(payRefundOrder);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出退款订单列表
	 */
	@RequiresPermissions("payorder:payRefundOrder:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(PayRefundOrder payRefundOrder)
    {
    	List<PayRefundOrder> list = payRefundOrderService.selectPayRefundOrderList(payRefundOrder);
        ExcelUtil<PayRefundOrder> util = new ExcelUtil<PayRefundOrder>(PayRefundOrder.class);
        return util.exportExcel(list, "payRefundOrder");
    }
	
	/**
	 * 新增退款订单
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存退款订单
	 */
	@RequiresPermissions("payorder:payRefundOrder:add")
	@Log(title = "退款订单", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(PayRefundOrder payRefundOrder)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(payRefundOrderService.insertPayRefundOrder(payRefundOrder));
	}

	/**
	 * 修改退款订单
	 */
	@GetMapping("/edit/{instOrderId}")
	public String edit(@PathVariable("instOrderId") Integer instOrderId, ModelMap mmap)
	{
		PayRefundOrder payRefundOrder = payRefundOrderService.selectPayRefundOrderById(instOrderId);
		mmap.put("payRefundOrder", payRefundOrder);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存退款订单
	 */
	@RequiresPermissions("payorder:payRefundOrder:edit")
	@Log(title = "退款订单", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(PayRefundOrder payRefundOrder)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(payRefundOrderService.updatePayRefundOrder(payRefundOrder));
	}
	
	/**
	 * 删除退款订单
	 */
	@RequiresPermissions("payorder:payRefundOrder:remove")
	@Log(title = "退款订单", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(payRefundOrderService.deletePayRefundOrderByIds(ids));
	}
	
}
