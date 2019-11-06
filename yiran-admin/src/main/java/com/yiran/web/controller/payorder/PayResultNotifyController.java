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
import com.yiran.payorder.domain.PayResultNotify;
import com.yiran.payorder.service.IPayResultNotifyService;
import com.yiran.framework.web.base.BaseController;
import com.yiran.common.page.TableDataInfo;
import com.yiran.common.base.AjaxResult;
import com.yiran.common.config.Global;
import com.yiran.common.utils.poi.ExcelUtil;

/**
 * 支付结果通知 信息操作处理
 * 
 * @author yiran
 * @date 2019-08-14
 */
@Controller
@RequestMapping("/payorder/payResultNotify")
public class PayResultNotifyController extends BaseController
{
    private String prefix = "payorder/payResultNotify";
	
	@Autowired
	private IPayResultNotifyService payResultNotifyService;
	
	@RequiresPermissions("payorder:payResultNotify:view")
	@GetMapping()
	public String payResultNotify()
	{
	    return prefix + "/payResultNotify";
	}
	
	/**
	 * 查询支付结果通知列表
	 */
	@RequiresPermissions("payorder:payResultNotify:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(PayResultNotify payResultNotify)
	{
		startPage();
        List<PayResultNotify> list = payResultNotifyService.selectPayResultNotifyList(payResultNotify);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出支付结果通知列表
	 */
	@RequiresPermissions("payorder:payResultNotify:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(PayResultNotify payResultNotify)
    {
    	List<PayResultNotify> list = payResultNotifyService.selectPayResultNotifyList(payResultNotify);
        ExcelUtil<PayResultNotify> util = new ExcelUtil<PayResultNotify>(PayResultNotify.class);
        return util.exportExcel(list, "payResultNotify");
    }
	
	/**
	 * 新增支付结果通知
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存支付结果通知
	 */
	@RequiresPermissions("payorder:payResultNotify:add")
	@Log(title = "支付结果通知", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(PayResultNotify payResultNotify)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(payResultNotifyService.insertPayResultNotify(payResultNotify));
	}

	/**
	 * 修改支付结果通知
	 */
	@GetMapping("/edit/{notifyId}")
	public String edit(@PathVariable("notifyId") String notifyId, ModelMap mmap)
	{
		PayResultNotify payResultNotify = payResultNotifyService.selectPayResultNotifyById(notifyId);
		mmap.put("payResultNotify", payResultNotify);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存支付结果通知
	 */
	@RequiresPermissions("payorder:payResultNotify:edit")
	@Log(title = "支付结果通知", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(PayResultNotify payResultNotify)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(payResultNotifyService.updatePayResultNotify(payResultNotify));
	}
	
	/**
	 * 删除支付结果通知
	 */
	@RequiresPermissions("payorder:payResultNotify:remove")
	@Log(title = "支付结果通知", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(payResultNotifyService.deletePayResultNotifyByIds(ids));
	}
	
}
