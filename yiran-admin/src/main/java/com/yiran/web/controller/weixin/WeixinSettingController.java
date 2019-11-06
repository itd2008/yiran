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
import com.yiran.common.enums.BusinessType;
import com.yiran.common.page.TableDataInfo;
import com.yiran.framework.web.base.BaseController;
import com.yiran.weixin.domain.WeixinSetting;
import com.yiran.weixin.service.IWeixinSettingService;

/**
 * 微信基本设置 信息操作处理
 * 
 * @author yiran
 * @date 2018-07-17
 */
@Controller
@RequestMapping("/weixin/weixinSetting")
public class WeixinSettingController extends BaseController
{
    private String prefix = "weixin/weixinSetting";
	
	@Autowired
	private IWeixinSettingService weixinSettingService;
	
	@RequiresPermissions("weixin:weixinSetting:view")
	@GetMapping()
	public String weixinSetting()
	{
	    return prefix + "/weixinSetting";
	}
	
	/**
	 * 查询微信基本设置列表
	 */
	@RequiresPermissions("weixin:weixinSetting:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(WeixinSetting weixinSetting)
	{
		startPage();
        List<WeixinSetting> list = weixinSettingService.selectWeixinSettingList(weixinSetting);
		return getDataTable(list);
	}
	
	/**
	 * 新增微信基本设置
	 */
	@RequiresPermissions("weixin:weixinSetting:add")
	@Log(title = "微信基本设置", businessType = BusinessType.INSERT)
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}

	/**
	 * 修改微信基本设置
	 */
	@RequiresPermissions("weixin:weixinSetting:edit")
	@Log(title = "微信基本设置", businessType = BusinessType.UPDATE)
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, Model model)
	{
		
		WeixinSetting weixinSetting = weixinSettingService.selectWeixinSettingById(id);
		model.addAttribute("weixinSetting", weixinSetting);
	    return prefix + "/edit";
	}
	
	/**
	 * 保存微信基本设置
	 */
	@RequiresPermissions("weixin:weixinSetting:save")
	@Log(title = "微信基本设置", businessType = BusinessType.INSERT)
	@PostMapping("/save")
	@ResponseBody
	public AjaxResult save(WeixinSetting weixinSetting)
	{
		if (weixinSettingService.saveWeixinSetting(weixinSetting) > 0)
		{
			return success();
		}
		return error();
	}
	
	/**
	 * 删除微信基本设置
	 */
	@RequiresPermissions("weixin:weixinSetting:remove")
	@Log(title = "微信基本设置", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{
		
		int rows = weixinSettingService.deleteWeixinSettingByIds(ids);
		if (rows > 0)
        {
            return success();
        }
        return error();
	}
	
}
