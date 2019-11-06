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
import com.yiran.wechat.domain.WechatReceivingAddress;
import com.yiran.wechat.service.IWechatReceivingAddressService;
import com.yiran.framework.web.base.BaseController;
import com.yiran.common.page.TableDataInfo;
import com.yiran.common.base.AjaxResult;
import com.yiran.common.config.Global;
import com.yiran.common.utils.poi.ExcelUtil;

/**
 * 收货地址 信息操作处理
 * 
 * @author yiran
 * @date 2019-06-13
 */
@Controller
@RequestMapping("/wechat/wechatReceivingAddress")
public class WechatReceivingAddressController extends BaseController
{
    private String prefix = "wechat/wechatReceivingAddress";
	
	@Autowired
	private IWechatReceivingAddressService wechatReceivingAddressService;
	
	@RequiresPermissions("wechat:wechatReceivingAddress:view")
	@GetMapping()
	public String wechatReceivingAddress()
	{
	    return prefix + "/wechatReceivingAddress";
	}
	
	/**
	 * 查询收货地址列表
	 */
	@RequiresPermissions("wechat:wechatReceivingAddress:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(WechatReceivingAddress wechatReceivingAddress)
	{
		startPage();
        List<WechatReceivingAddress> list = wechatReceivingAddressService.selectWechatReceivingAddressList(wechatReceivingAddress);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出收货地址列表
	 */
	@RequiresPermissions("wechat:wechatReceivingAddress:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(WechatReceivingAddress wechatReceivingAddress)
    {
    	List<WechatReceivingAddress> list = wechatReceivingAddressService.selectWechatReceivingAddressList(wechatReceivingAddress);
        ExcelUtil<WechatReceivingAddress> util = new ExcelUtil<WechatReceivingAddress>(WechatReceivingAddress.class);
        return util.exportExcel(list, "wechatReceivingAddress");
    }
	
	/**
	 * 新增收货地址
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存收货地址
	 */
	@RequiresPermissions("wechat:wechatReceivingAddress:add")
	@Log(title = "收货地址", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(WechatReceivingAddress wechatReceivingAddress)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(wechatReceivingAddressService.insertWechatReceivingAddress(wechatReceivingAddress));
	}

	/**
	 * 修改收货地址
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		WechatReceivingAddress wechatReceivingAddress = wechatReceivingAddressService.selectWechatReceivingAddressById(id);
		mmap.put("wechatReceivingAddress", wechatReceivingAddress);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存收货地址
	 */
	@RequiresPermissions("wechat:wechatReceivingAddress:edit")
	@Log(title = "收货地址", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(WechatReceivingAddress wechatReceivingAddress)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(wechatReceivingAddressService.updateWechatReceivingAddress(wechatReceivingAddress));
	}
	
	/**
	 * 删除收货地址
	 */
	@RequiresPermissions("wechat:wechatReceivingAddress:remove")
	@Log(title = "收货地址", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(wechatReceivingAddressService.deleteWechatReceivingAddressByIds(ids));
	}
	
}
