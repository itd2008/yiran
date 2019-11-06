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
import com.yiran.wechat.domain.WechatUserProductCoupon;
import com.yiran.wechat.service.IWechatUserProductCouponService;
import com.yiran.framework.web.base.BaseController;
import com.yiran.common.page.TableDataInfo;
import com.yiran.common.base.AjaxResult;
import com.yiran.common.config.Global;
import com.yiran.common.utils.poi.ExcelUtil;

/**
 * 用户优惠券关联 信息操作处理
 * 
 * @author yiran
 * @date 2019-09-08
 */
@Controller
@RequestMapping("/wechat/wechatUserProductCoupon")
public class WechatUserProductCouponController extends BaseController
{
    private String prefix = "wechat/wechatUserProductCoupon";
	
	@Autowired
	private IWechatUserProductCouponService wechatUserProductCouponService;
	
	@RequiresPermissions("wechat:wechatUserProductCoupon:view")
	@GetMapping()
	public String wechatUserProductCoupon()
	{
	    return prefix + "/wechatUserProductCoupon";
	}
	
	/**
	 * 查询用户优惠券关联列表
	 */
	@RequiresPermissions("wechat:wechatUserProductCoupon:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(WechatUserProductCoupon wechatUserProductCoupon)
	{
		startPage();
        List<WechatUserProductCoupon> list = wechatUserProductCouponService.selectWechatUserProductCouponList(wechatUserProductCoupon);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出用户优惠券关联列表
	 */
	@RequiresPermissions("wechat:wechatUserProductCoupon:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(WechatUserProductCoupon wechatUserProductCoupon)
    {
    	List<WechatUserProductCoupon> list = wechatUserProductCouponService.selectWechatUserProductCouponList(wechatUserProductCoupon);
        ExcelUtil<WechatUserProductCoupon> util = new ExcelUtil<WechatUserProductCoupon>(WechatUserProductCoupon.class);
        return util.exportExcel(list, "wechatUserProductCoupon");
    }
	
	/**
	 * 新增用户优惠券关联
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存用户优惠券关联
	 */
	@RequiresPermissions("wechat:wechatUserProductCoupon:add")
	@Log(title = "用户优惠券关联", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(WechatUserProductCoupon wechatUserProductCoupon)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(wechatUserProductCouponService.insertWechatUserProductCoupon(wechatUserProductCoupon));
	}

	/**
	 * 修改用户优惠券关联
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		WechatUserProductCoupon wechatUserProductCoupon = wechatUserProductCouponService.selectWechatUserProductCouponById(id);
		mmap.put("wechatUserProductCoupon", wechatUserProductCoupon);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存用户优惠券关联
	 */
	@RequiresPermissions("wechat:wechatUserProductCoupon:edit")
	@Log(title = "用户优惠券关联", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(WechatUserProductCoupon wechatUserProductCoupon)
	{	
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(wechatUserProductCouponService.updateWechatUserProductCoupon(wechatUserProductCoupon));
	}
	
	/**
	 * 删除用户优惠券关联
	 */
	@RequiresPermissions("wechat:wechatUserProductCoupon:remove")
	@Log(title = "用户优惠券关联", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(wechatUserProductCouponService.deleteWechatUserProductCouponByIds(ids));
	}
	
}
