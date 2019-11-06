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
import com.yiran.wechat.domain.WechatShoppingCart;
import com.yiran.wechat.service.IWechatShoppingCartService;
import com.yiran.framework.web.base.BaseController;
import com.yiran.common.page.TableDataInfo;
import com.yiran.common.base.AjaxResult;
import com.yiran.common.config.Global;
import com.yiran.common.utils.poi.ExcelUtil;

/**
 * 购物车 信息操作处理
 * 
 * @author yiran
 * @date 2019-06-13
 */
@Controller
@RequestMapping("/wechat/wechatShoppingCart")
public class WechatShoppingCartController extends BaseController
{
    private String prefix = "wechat/wechatShoppingCart";
	
	@Autowired
	private IWechatShoppingCartService wechatShoppingCartService;
	
	@RequiresPermissions("wechat:wechatShoppingCart:view")
	@GetMapping()
	public String wechatShoppingCart()
	{
	    return prefix + "/wechatShoppingCart";
	}
	
	/**
	 * 查询购物车列表
	 */
	@RequiresPermissions("wechat:wechatShoppingCart:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(WechatShoppingCart wechatShoppingCart)
	{
		startPage();
        List<WechatShoppingCart> list = wechatShoppingCartService.selectWechatShoppingCartList(wechatShoppingCart);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出购物车列表
	 */
	@RequiresPermissions("wechat:wechatShoppingCart:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(WechatShoppingCart wechatShoppingCart)
    {
    	List<WechatShoppingCart> list = wechatShoppingCartService.selectWechatShoppingCartList(wechatShoppingCart);
        ExcelUtil<WechatShoppingCart> util = new ExcelUtil<WechatShoppingCart>(WechatShoppingCart.class);
        return util.exportExcel(list, "wechatShoppingCart");
    }
	
	/**
	 * 新增购物车
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存购物车
	 */
	@RequiresPermissions("wechat:wechatShoppingCart:add")
	@Log(title = "购物车", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(WechatShoppingCart wechatShoppingCart)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(wechatShoppingCartService.insertWechatShoppingCart(wechatShoppingCart));
	}

	/**
	 * 修改购物车
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		WechatShoppingCart wechatShoppingCart = wechatShoppingCartService.selectWechatShoppingCartById(id);
		mmap.put("wechatShoppingCart", wechatShoppingCart);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存购物车
	 */
	@RequiresPermissions("wechat:wechatShoppingCart:edit")
	@Log(title = "购物车", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(WechatShoppingCart wechatShoppingCart)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(wechatShoppingCartService.updateWechatShoppingCart(wechatShoppingCart));
	}
	
	/**
	 * 删除购物车
	 */
	@RequiresPermissions("wechat:wechatShoppingCart:remove")
	@Log(title = "购物车", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(wechatShoppingCartService.deleteWechatShoppingCartByIds(ids));
	}
	
}
