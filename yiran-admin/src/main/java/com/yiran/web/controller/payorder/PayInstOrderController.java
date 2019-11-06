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
import com.yiran.payorder.domain.PayInstOrder;
import com.yiran.payorder.domaindo.PayInstOrderDO;
import com.yiran.payorder.service.IPayInstOrderService;
import com.yiran.framework.web.base.BaseController;
import com.yiran.common.page.TableDataInfo;
import com.yiran.common.base.AjaxResult;
import com.yiran.common.config.Global;
import com.yiran.common.utils.poi.ExcelUtil;

/**
 * 提交机构订单 信息操作处理
 * 
 * @author yiran
 * @date 2019-07-13
 */
@Controller
@RequestMapping("/payorder/payInstOrder")
public class PayInstOrderController extends BaseController
{
    private String prefix = "payorder/payInstOrder";
	
	@Autowired
	private IPayInstOrderService payInstOrderService;
	
	@RequiresPermissions("payorder:payInstOrder:view")
	@GetMapping()
	public String payInstOrder()
	{
	    return prefix + "/payInstOrder";
	}
	
	/**
	 * 查询提交机构订单列表
	 */
	@RequiresPermissions("payorder:payInstOrder:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(PayInstOrderDO payInstOrder)
	{
		startPage();
        List<PayInstOrderDO> list = payInstOrderService.selectPayInstOrderList(payInstOrder);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出提交机构订单列表
	 */
	@RequiresPermissions("payorder:payInstOrder:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(PayInstOrderDO payInstOrder)
    {
    	List<PayInstOrderDO> list = payInstOrderService.selectPayInstOrderList(payInstOrder);
        ExcelUtil<PayInstOrderDO> util = new ExcelUtil<PayInstOrderDO>(PayInstOrderDO.class);
        return util.exportExcel(list, "payInstOrder");
    }
	
	/**
	 * 新增提交机构订单
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存提交机构订单
	 */
	@RequiresPermissions("payorder:payInstOrder:add")
	@Log(title = "提交机构订单", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(PayInstOrderDO payInstOrder)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(payInstOrderService.insertPayInstOrder(payInstOrder));
	}

	/**
	 * 修改提交机构订单
	 */
	@GetMapping("/edit/{instOrderId}")
	public String edit(@PathVariable("instOrderId") Integer instOrderId, ModelMap mmap)
	{
		PayInstOrderDO payInstOrder = payInstOrderService.selectPayInstOrderById(instOrderId);
		mmap.put("payInstOrder", payInstOrder);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存提交机构订单
	 */
	@RequiresPermissions("payorder:payInstOrder:edit")
	@Log(title = "提交机构订单", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(PayInstOrderDO payInstOrder)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(payInstOrderService.updatePayInstOrder(payInstOrder));
	}
	
	/**
	 * 删除提交机构订单
	 */
	@RequiresPermissions("payorder:payInstOrder:remove")
	@Log(title = "提交机构订单", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(payInstOrderService.deletePayInstOrderByIds(ids));
	}
	
}
