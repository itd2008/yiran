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
import com.yiran.wechat.domain.WechatProductAndPromotion;
import com.yiran.wechat.service.IWechatProductAndPromotionService;
import com.yiran.framework.web.base.BaseController;
import com.yiran.common.page.TableDataInfo;
import com.yiran.common.base.AjaxResult;
import com.yiran.common.config.Global;
import com.yiran.common.utils.poi.ExcelUtil;

/**
 * 商品促销活动关联 信息操作处理
 * 
 * @author yiran
 * @date 2019-06-13
 */
@Controller
@RequestMapping("/wechat/wechatProductAndPromotion")
public class WechatProductAndPromotionController extends BaseController
{
    private String prefix = "wechat/wechatProductAndPromotion";
	
	@Autowired
	private IWechatProductAndPromotionService wechatProductAndPromotionService;
	
	@RequiresPermissions("wechat:wechatProductAndPromotion:view")
	@GetMapping()
	public String wechatProductAndPromotion()
	{
	    return prefix + "/wechatProductAndPromotion";
	}
	
	/**
	 * 查询商品促销活动关联列表
	 */
	@RequiresPermissions("wechat:wechatProductAndPromotion:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(WechatProductAndPromotion wechatProductAndPromotion)
	{
		startPage();
        List<WechatProductAndPromotion> list = wechatProductAndPromotionService.selectWechatProductAndPromotionList(wechatProductAndPromotion);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出商品促销活动关联列表
	 */
	@RequiresPermissions("wechat:wechatProductAndPromotion:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(WechatProductAndPromotion wechatProductAndPromotion)
    {
    	List<WechatProductAndPromotion> list = wechatProductAndPromotionService.selectWechatProductAndPromotionList(wechatProductAndPromotion);
        ExcelUtil<WechatProductAndPromotion> util = new ExcelUtil<WechatProductAndPromotion>(WechatProductAndPromotion.class);
        return util.exportExcel(list, "wechatProductAndPromotion");
    }
	
	/**
	 * 新增商品促销活动关联
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存商品促销活动关联
	 */
	@RequiresPermissions("wechat:wechatProductAndPromotion:add")
	@Log(title = "商品促销活动关联", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(WechatProductAndPromotion wechatProductAndPromotion)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(wechatProductAndPromotionService.insertWechatProductAndPromotion(wechatProductAndPromotion));
	}

	/**
	 * 修改商品促销活动关联
	 */
	@GetMapping("/edit/{productAndPromotionId}")
	public String edit(@PathVariable("productAndPromotionId") Integer productAndPromotionId, ModelMap mmap)
	{
		WechatProductAndPromotion wechatProductAndPromotion = wechatProductAndPromotionService.selectWechatProductAndPromotionById(productAndPromotionId);
		mmap.put("wechatProductAndPromotion", wechatProductAndPromotion);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存商品促销活动关联
	 */
	@RequiresPermissions("wechat:wechatProductAndPromotion:edit")
	@Log(title = "商品促销活动关联", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(WechatProductAndPromotion wechatProductAndPromotion)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(wechatProductAndPromotionService.updateWechatProductAndPromotion(wechatProductAndPromotion));
	}
	
	/**
	 * 删除商品促销活动关联
	 */
	@RequiresPermissions("wechat:wechatProductAndPromotion:remove")
	@Log(title = "商品促销活动关联", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(wechatProductAndPromotionService.deleteWechatProductAndPromotionByIds(ids));
	}
	
}
