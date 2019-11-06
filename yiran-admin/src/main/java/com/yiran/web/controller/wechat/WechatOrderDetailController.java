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
import com.yiran.wechat.domain.WechatOrderDetail;
import com.yiran.wechat.service.IWechatOrderDetailService;
import com.yiran.framework.web.base.BaseController;
import com.yiran.common.page.TableDataInfo;
import com.yiran.common.base.AjaxResult;
import com.yiran.common.config.Global;
import com.yiran.common.utils.poi.ExcelUtil;

/**
 * 订单商品详情 信息操作处理
 * 
 * @author yiran
 * @date 2019-06-13
 */
@Controller
@RequestMapping("/wechat/wechatOrderDetail")
public class WechatOrderDetailController extends BaseController
{
    private String prefix = "wechat/wechatOrderDetail";
	
	@Autowired
	private IWechatOrderDetailService wechatOrderDetailService;
	
	@RequiresPermissions("wechat:wechatOrderDetail:view")
	@GetMapping()
	public String wechatOrderDetail()
	{
	    return prefix + "/wechatOrderDetail";
	}
	
	/**
	 * 查询订单商品详情列表
	 */
	@RequiresPermissions("wechat:wechatOrderDetail:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(WechatOrderDetail wechatOrderDetail)
	{
		startPage();
        List<WechatOrderDetail> list = wechatOrderDetailService.selectWechatOrderDetailList(wechatOrderDetail);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出订单商品详情列表
	 */
	@RequiresPermissions("wechat:wechatOrderDetail:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(WechatOrderDetail wechatOrderDetail)
    {
    	List<WechatOrderDetail> list = wechatOrderDetailService.selectWechatOrderDetailList(wechatOrderDetail);
        ExcelUtil<WechatOrderDetail> util = new ExcelUtil<WechatOrderDetail>(WechatOrderDetail.class);
        return util.exportExcel(list, "wechatOrderDetail");
    }
	
	/**
	 * 新增订单商品详情
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存订单商品详情
	 */
	@RequiresPermissions("wechat:wechatOrderDetail:add")
	@Log(title = "订单商品详情", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(WechatOrderDetail wechatOrderDetail)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(wechatOrderDetailService.insertWechatOrderDetail(wechatOrderDetail));
	}

	/**
	 * 修改订单商品详情
	 */
	@GetMapping("/edit/{orderDetailId}")
	public String edit(@PathVariable("orderDetailId") Integer orderDetailId, ModelMap mmap)
	{
		WechatOrderDetail wechatOrderDetail = wechatOrderDetailService.selectWechatOrderDetailById(orderDetailId);
		mmap.put("wechatOrderDetail", wechatOrderDetail);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存订单商品详情
	 */
	@RequiresPermissions("wechat:wechatOrderDetail:edit")
	@Log(title = "订单商品详情", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(WechatOrderDetail wechatOrderDetail)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(wechatOrderDetailService.updateWechatOrderDetail(wechatOrderDetail));
	}
	
	/**
	 * 删除订单商品详情
	 */
	@RequiresPermissions("wechat:wechatOrderDetail:remove")
	@Log(title = "订单商品详情", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(wechatOrderDetailService.deleteWechatOrderDetailByIds(ids));
	}
	
}
