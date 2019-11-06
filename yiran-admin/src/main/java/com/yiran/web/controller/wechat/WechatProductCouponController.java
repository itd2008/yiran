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
import com.yiran.wechat.domain.WechatProductCoupon;
import com.yiran.wechat.service.IWechatProductCouponService;
import com.yiran.framework.web.base.BaseController;
import com.yiran.common.page.TableDataInfo;
import com.yiran.common.base.AjaxResult;
import com.yiran.common.config.Global;
import com.yiran.common.utils.poi.ExcelUtil;

/**
 * 商品优惠券 信息操作处理
 * 
 * @author yiran
 * @date 2019-06-13
 */
@Controller
@RequestMapping("/wechat/wechatProductCoupon")
public class WechatProductCouponController extends BaseController
{
    private String prefix = "wechat/wechatProductCoupon";
	
	@Autowired
	private IWechatProductCouponService wechatProductCouponService;
	
	@RequiresPermissions("wechat:wechatProductCoupon:view")
	@GetMapping()
	public String wechatProductCoupon()
	{
	    return prefix + "/wechatProductCoupon";
	}
	
	/**
	 * 查询商品优惠券列表
	 */
	@RequiresPermissions("wechat:wechatProductCoupon:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(WechatProductCoupon wechatProductCoupon)
	{
		startPage();
        List<WechatProductCoupon> list = wechatProductCouponService.selectWechatProductCouponList(wechatProductCoupon);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出商品优惠券列表
	 */
	@RequiresPermissions("wechat:wechatProductCoupon:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(WechatProductCoupon wechatProductCoupon)
    {
    	List<WechatProductCoupon> list = wechatProductCouponService.selectWechatProductCouponList(wechatProductCoupon);
        ExcelUtil<WechatProductCoupon> util = new ExcelUtil<WechatProductCoupon>(WechatProductCoupon.class);
        return util.exportExcel(list, "wechatProductCoupon");
    }
	
	/**
	 * 新增商品优惠券
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存商品优惠券
	 */
	@RequiresPermissions("wechat:wechatProductCoupon:add")
	@Log(title = "商品优惠券", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(WechatProductCoupon wechatProductCoupon)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(wechatProductCouponService.insertWechatProductCoupon(wechatProductCoupon));
	}

	/**
	 * 修改商品优惠券
	 */
	@GetMapping("/edit/{productCouponId}")
	public String edit(@PathVariable("productCouponId") Integer productCouponId, ModelMap mmap)
	{
		WechatProductCoupon wechatProductCoupon = wechatProductCouponService.selectWechatProductCouponById(productCouponId);
		mmap.put("wechatProductCoupon", wechatProductCoupon);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存商品优惠券
	 */
	@RequiresPermissions("wechat:wechatProductCoupon:edit")
	@Log(title = "商品优惠券", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(WechatProductCoupon wechatProductCoupon)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(wechatProductCouponService.updateWechatProductCoupon(wechatProductCoupon));
	}
	
	/**
	 * 删除商品优惠券
	 */
	@RequiresPermissions("wechat:wechatProductCoupon:remove")
	@Log(title = "商品优惠券", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{	
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(wechatProductCouponService.deleteWechatProductCouponByIds(ids));
	}
	
}
