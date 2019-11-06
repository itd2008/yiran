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
import com.yiran.wechat.domain.WechatOrderLogistics;
import com.yiran.wechat.service.IWechatOrderLogisticsService;
import com.yiran.framework.web.base.BaseController;
import com.yiran.common.page.TableDataInfo;
import com.yiran.common.base.AjaxResult;
import com.yiran.common.config.Global;
import com.yiran.common.utils.poi.ExcelUtil;

/**
 * 订单物流 信息操作处理
 * 
 * @author yiran
 * @date 2019-06-13
 */
@Controller
@RequestMapping("/wechat/wechatOrderLogistics")
public class WechatOrderLogisticsController extends BaseController
{
    private String prefix = "wechat/wechatOrderLogistics";
	
	@Autowired
	private IWechatOrderLogisticsService wechatOrderLogisticsService;
	
	@RequiresPermissions("wechat:wechatOrderLogistics:view")
	@GetMapping()
	public String wechatOrderLogistics()
	{
	    return prefix + "/wechatOrderLogistics";
	}
	
	/**
	 * 查询订单物流列表
	 */
	@RequiresPermissions("wechat:wechatOrderLogistics:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(WechatOrderLogistics wechatOrderLogistics)
	{
		startPage();
        List<WechatOrderLogistics> list = wechatOrderLogisticsService.selectWechatOrderLogisticsList(wechatOrderLogistics);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出订单物流列表
	 */
	@RequiresPermissions("wechat:wechatOrderLogistics:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(WechatOrderLogistics wechatOrderLogistics)
    {
    	List<WechatOrderLogistics> list = wechatOrderLogisticsService.selectWechatOrderLogisticsList(wechatOrderLogistics);
        ExcelUtil<WechatOrderLogistics> util = new ExcelUtil<WechatOrderLogistics>(WechatOrderLogistics.class);
        return util.exportExcel(list, "wechatOrderLogistics");
    }
	
	/**
	 * 新增订单物流
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存订单物流
	 */
	@RequiresPermissions("wechat:wechatOrderLogistics:add")
	@Log(title = "订单物流", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(WechatOrderLogistics wechatOrderLogistics)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(wechatOrderLogisticsService.insertWechatOrderLogistics(wechatOrderLogistics));
	}

	/**
	 * 修改订单物流
	 */
	@GetMapping("/edit/{orderlogisticsId}")
	public String edit(@PathVariable("orderlogisticsId") Integer orderlogisticsId, ModelMap mmap)
	{
		WechatOrderLogistics wechatOrderLogistics = wechatOrderLogisticsService.selectWechatOrderLogisticsById(orderlogisticsId);
		mmap.put("wechatOrderLogistics", wechatOrderLogistics);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存订单物流
	 */
	@RequiresPermissions("wechat:wechatOrderLogistics:edit")
	@Log(title = "订单物流", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(WechatOrderLogistics wechatOrderLogistics)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(wechatOrderLogisticsService.updateWechatOrderLogistics(wechatOrderLogistics));
	}
	
	/**
	 * 删除订单物流
	 */
	@RequiresPermissions("wechat:wechatOrderLogistics:remove")
	@Log(title = "订单物流", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(wechatOrderLogisticsService.deleteWechatOrderLogisticsByIds(ids));
	}
	
}
