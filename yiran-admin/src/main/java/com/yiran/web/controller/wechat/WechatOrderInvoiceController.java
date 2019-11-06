package com.yiran.web.controller.wechat;

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
import com.yiran.wechat.domain.WechatOrderInvoice;
import com.yiran.wechat.service.IWechatOrderInvoiceService;
import com.yiran.framework.web.base.BaseController;
import com.yiran.common.page.TableDataInfo;
import com.yiran.common.base.AjaxResult;
import com.yiran.common.config.Global;
import com.yiran.common.utils.poi.ExcelUtil;

/**
 * 订单发票 信息操作处理
 * 
 * @author yiran
 * @date 2019-06-13
 */
@Controller
@RequestMapping("/wechat/wechatOrderInvoice")
public class WechatOrderInvoiceController extends BaseController
{
    private String prefix = "wechat/wechatOrderInvoice";
	
	@Autowired
	private IWechatOrderInvoiceService wechatOrderInvoiceService;
	
	@RequiresPermissions("wechat:wechatOrderInvoice:view")
	@GetMapping()
	public String wechatOrderInvoice()
	{
	    return prefix + "/wechatOrderInvoice";
	}
	
	/**
	 * 查询订单发票列表
	 */
	@RequiresPermissions("wechat:wechatOrderInvoice:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(WechatOrderInvoice wechatOrderInvoice)
	{
		startPage();
        List<WechatOrderInvoice> list = wechatOrderInvoiceService.selectWechatOrderInvoiceList(wechatOrderInvoice);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出订单发票列表
	 */
	@RequiresPermissions("wechat:wechatOrderInvoice:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(WechatOrderInvoice wechatOrderInvoice)
    {
    	List<WechatOrderInvoice> list = wechatOrderInvoiceService.selectWechatOrderInvoiceList(wechatOrderInvoice);
        ExcelUtil<WechatOrderInvoice> util = new ExcelUtil<WechatOrderInvoice>(WechatOrderInvoice.class);
        return util.exportExcel(list, "wechatOrderInvoice");
    }
	
	/**
	 * 新增订单发票
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存订单发票
	 */
	@RequiresPermissions("wechat:wechatOrderInvoice:add")
	@Log(title = "订单发票", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(WechatOrderInvoice wechatOrderInvoice)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(wechatOrderInvoiceService.insertWechatOrderInvoice(wechatOrderInvoice));
	}

	/**
	 * 修改订单发票
	 */
	@GetMapping("/edit/{invoiceId}")
	public String edit(@PathVariable("invoiceId") Integer invoiceId, ModelMap mmap)
	{
		WechatOrderInvoice wechatOrderInvoice = wechatOrderInvoiceService.selectWechatOrderInvoiceById(invoiceId);
		mmap.put("wechatOrderInvoice", wechatOrderInvoice);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存订单发票
	 */
	@RequiresPermissions("wechat:wechatOrderInvoice:edit")
	@Log(title = "订单发票", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(WechatOrderInvoice wechatOrderInvoice)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(wechatOrderInvoiceService.updateWechatOrderInvoice(wechatOrderInvoice));
	}
	
	/**
	 * 删除订单发票
	 */
	@RequiresPermissions("wechat:wechatOrderInvoice:remove")
	@Log(title = "订单发票", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(wechatOrderInvoiceService.deleteWechatOrderInvoiceByIds(ids));
	}
	
}
