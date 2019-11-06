package com.yiran.web.controller.weixin;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yiran.common.annotation.Log;
import com.yiran.common.base.AjaxResult;
import com.yiran.common.config.Global;
import com.yiran.common.enums.BusinessType;
import com.yiran.common.page.TableDataInfo;
import com.yiran.framework.web.base.BaseController;
import com.yiran.weixin.domain.WeixinTemplate;
import com.yiran.weixin.service.IWeixinTemplateService;

/**
 * 微信模板 信息操作处理
 * 
 * @author yiran
 * @date 2018-10-27
 */
@Controller
@RequestMapping("/weixin/weixinTemplate")
public class WeixinTemplateController extends BaseController
{
    private String prefix = "weixin/weixinTemplate";
	
	@Autowired
	private IWeixinTemplateService weixinTemplateService;
	
	@RequiresPermissions("weixin:weixinTemplate:view")
	@GetMapping()
	public String weixinTemplate()
	{
	    return prefix + "/weixinTemplate";
	}
	
	/**
	 * 查询微信模板列表
	 */
	@RequiresPermissions("weixin:weixinTemplate:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(WeixinTemplate weixinTemplate)
	{
		startPage();
        List<WeixinTemplate> list = weixinTemplateService.selectWeixinTemplateList(weixinTemplate);
		return getDataTable(list);
	}
	
	/**
	 * 新增微信模板
	 */
	@RequiresPermissions("weixin:weixinTemplate:add")
	@Log(title = "微信模板", businessType = BusinessType.INSERT)
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}

	/**
	 * 修改微信模板
	 */
	@RequiresPermissions("weixin:weixinTemplate:edit")
	@Log(title = "微信模板", businessType = BusinessType.UPDATE)
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") String id, Model model)
	{
		WeixinTemplate weixinTemplate = weixinTemplateService.selectWeixinTemplateById(id);
		model.addAttribute("weixinTemplate", weixinTemplate);
	    return prefix + "/edit";
	}
	
	/**
	 * 保存微信模板
	 */
	@RequiresPermissions("weixin:weixinTemplate:save")
	@Log(title = "微信模板", businessType = BusinessType.INSERT)
	@PostMapping("/save")
	@ResponseBody
	public AjaxResult save(WeixinTemplate weixinTemplate)
	{
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		if (weixinTemplateService.saveWeixinTemplate(weixinTemplate) > 0)
		{
			return success();
		}
		return error();
	}
	
	/**
	 * 删除微信模板
	 */
	@RequiresPermissions("weixin:weixinTemplate:remove")
	@Log(title = "微信模板", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		int rows = weixinTemplateService.deleteWeixinTemplateByIds(ids);
		if (rows > 0)
        {
            return success();
        }
        return error();
	}
	
}
