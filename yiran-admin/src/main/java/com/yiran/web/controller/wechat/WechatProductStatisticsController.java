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
import com.yiran.wechat.domain.WechatProductStatistics;
import com.yiran.wechat.service.IWechatProductStatisticsService;
import com.yiran.framework.web.base.BaseController;
import com.yiran.common.page.TableDataInfo;
import com.yiran.common.base.AjaxResult;
import com.yiran.common.config.Global;
import com.yiran.common.utils.poi.ExcelUtil;

/**
 * 商品统计 信息操作处理
 * 
 * @author yiran
 * @date 2019-06-13
 */
@Controller
@RequestMapping("/wechat/wechatProductStatistics")
public class WechatProductStatisticsController extends BaseController
{
    private String prefix = "wechat/wechatProductStatistics";
	
	@Autowired
	private IWechatProductStatisticsService wechatProductStatisticsService;
	
	@RequiresPermissions("wechat:wechatProductStatistics:view")
	@GetMapping()
	public String wechatProductStatistics()
	{
	    return prefix + "/wechatProductStatistics";
	}
	
	/**
	 * 查询商品统计列表
	 */
	@RequiresPermissions("wechat:wechatProductStatistics:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(WechatProductStatistics wechatProductStatistics)
	{
		startPage();
        List<WechatProductStatistics> list = wechatProductStatisticsService.selectWechatProductStatisticsList(wechatProductStatistics);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出商品统计列表
	 */
	@RequiresPermissions("wechat:wechatProductStatistics:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(WechatProductStatistics wechatProductStatistics)
    {
    	List<WechatProductStatistics> list = wechatProductStatisticsService.selectWechatProductStatisticsList(wechatProductStatistics);
        ExcelUtil<WechatProductStatistics> util = new ExcelUtil<WechatProductStatistics>(WechatProductStatistics.class);
        return util.exportExcel(list, "wechatProductStatistics");
    }
	
	/**
	 * 新增商品统计
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存商品统计
	 */
	@RequiresPermissions("wechat:wechatProductStatistics:add")
	@Log(title = "商品统计", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(WechatProductStatistics wechatProductStatistics)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(wechatProductStatisticsService.insertWechatProductStatistics(wechatProductStatistics));
	}

	/**
	 * 修改商品统计
	 */
	@GetMapping("/edit/{productId}")
	public String edit(@PathVariable("productId") Integer productId, ModelMap mmap)
	{
		WechatProductStatistics wechatProductStatistics = wechatProductStatisticsService.selectWechatProductStatisticsById(productId);
		mmap.put("wechatProductStatistics", wechatProductStatistics);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存商品统计
	 */
	@RequiresPermissions("wechat:wechatProductStatistics:edit")
	@Log(title = "商品统计", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(WechatProductStatistics wechatProductStatistics)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(wechatProductStatisticsService.updateWechatProductStatistics(wechatProductStatistics));
	}
	
	/**
	 * 删除商品统计
	 */
	@RequiresPermissions("wechat:wechatProductStatistics:remove")
	@Log(title = "商品统计", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(wechatProductStatisticsService.deleteWechatProductStatisticsByIds(ids));
	}
	
}
