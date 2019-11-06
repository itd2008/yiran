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
import com.yiran.wechat.domain.WechatProductPromotionEvent;
import com.yiran.wechat.service.IWechatProductPromotionEventService;
import com.yiran.framework.web.base.BaseController;
import com.yiran.common.page.TableDataInfo;
import com.yiran.common.base.AjaxResult;
import com.yiran.common.config.Global;
import com.yiran.common.utils.poi.ExcelUtil;

/**
 * 商品促销活动 信息操作处理
 * 
 * @author yiran
 * @date 2019-06-13
 */
@Controller
@RequestMapping("/wechat/wechatProductPromotionEvent")
public class WechatProductPromotionEventController extends BaseController
{
    private String prefix = "wechat/wechatProductPromotionEvent";
	
	@Autowired
	private IWechatProductPromotionEventService wechatProductPromotionEventService;
	
	@RequiresPermissions("wechat:wechatProductPromotionEvent:view")
	@GetMapping()
	public String wechatProductPromotionEvent()
	{
	    return prefix + "/wechatProductPromotionEvent";
	}
	
	/**
	 * 查询商品促销活动列表
	 */
	@RequiresPermissions("wechat:wechatProductPromotionEvent:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(WechatProductPromotionEvent wechatProductPromotionEvent)
	{
		startPage();
        List<WechatProductPromotionEvent> list = wechatProductPromotionEventService.selectWechatProductPromotionEventList(wechatProductPromotionEvent);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出商品促销活动列表
	 */
	@RequiresPermissions("wechat:wechatProductPromotionEvent:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(WechatProductPromotionEvent wechatProductPromotionEvent)
    {
    	List<WechatProductPromotionEvent> list = wechatProductPromotionEventService.selectWechatProductPromotionEventList(wechatProductPromotionEvent);
        ExcelUtil<WechatProductPromotionEvent> util = new ExcelUtil<WechatProductPromotionEvent>(WechatProductPromotionEvent.class);
        return util.exportExcel(list, "wechatProductPromotionEvent");
    }
	
	/**
	 * 新增商品促销活动
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存商品促销活动
	 */
	@RequiresPermissions("wechat:wechatProductPromotionEvent:add")
	@Log(title = "商品促销活动", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(WechatProductPromotionEvent wechatProductPromotionEvent)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(wechatProductPromotionEventService.insertWechatProductPromotionEvent(wechatProductPromotionEvent));
	}

	/**
	 * 修改商品促销活动
	 */
	@GetMapping("/edit/{productPromotionEventId}")
	public String edit(@PathVariable("productPromotionEventId") Integer productPromotionEventId, ModelMap mmap)
	{
		WechatProductPromotionEvent wechatProductPromotionEvent = wechatProductPromotionEventService.selectWechatProductPromotionEventById(productPromotionEventId);
		mmap.put("wechatProductPromotionEvent", wechatProductPromotionEvent);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存商品促销活动
	 */
	@RequiresPermissions("wechat:wechatProductPromotionEvent:edit")
	@Log(title = "商品促销活动", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(WechatProductPromotionEvent wechatProductPromotionEvent)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(wechatProductPromotionEventService.updateWechatProductPromotionEvent(wechatProductPromotionEvent));
	}
	
	/**
	 * 删除商品促销活动
	 */
	@RequiresPermissions("wechat:wechatProductPromotionEvent:remove")
	@Log(title = "商品促销活动", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(wechatProductPromotionEventService.deleteWechatProductPromotionEventByIds(ids));
	}
	
}
