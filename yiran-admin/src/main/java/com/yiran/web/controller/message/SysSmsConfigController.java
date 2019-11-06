package com.yiran.web.controller.message;

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
import com.yiran.message.domain.SysSmsConfig;
import com.yiran.message.service.ISysSmsConfigService;
import com.yiran.framework.web.base.BaseController;
import com.yiran.common.page.TableDataInfo;
import com.yiran.common.base.AjaxResult;
import com.yiran.common.config.Global;
import com.yiran.common.utils.poi.ExcelUtil;

/**
 * 短信基本设置 信息操作处理
 * 
 * @author yiran
 * @date 2019-03-08
 */
@Controller
@RequestMapping("/message/sysSmsConfig")
public class SysSmsConfigController extends BaseController
{
    private String prefix = "message/sysSmsConfig";
	
	@Autowired
	private ISysSmsConfigService sysSmsConfigService;
	
	@RequiresPermissions("message:sysSmsConfig:view")
	@GetMapping()
	public String sysSmsConfig()
	{
	    return prefix + "/sysSmsConfig";
	}
	
	/**
	 * 查询短信基本设置列表
	 */
	@RequiresPermissions("message:sysSmsConfig:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(SysSmsConfig sysSmsConfig)
	{
		startPage();
        List<SysSmsConfig> list = sysSmsConfigService.selectSysSmsConfigList(sysSmsConfig);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出短信基本设置列表
	 */
	@RequiresPermissions("message:sysSmsConfig:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SysSmsConfig sysSmsConfig)
    {
    	List<SysSmsConfig> list = sysSmsConfigService.selectSysSmsConfigList(sysSmsConfig);
        ExcelUtil<SysSmsConfig> util = new ExcelUtil<SysSmsConfig>(SysSmsConfig.class);
        return util.exportExcel(list, "sysSmsConfig");
    }
	
	/**
	 * 新增短信基本设置
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存短信基本设置
	 */
	@RequiresPermissions("message:sysSmsConfig:add")
	@Log(title = "短信基本设置", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(SysSmsConfig sysSmsConfig)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(sysSmsConfigService.insertSysSmsConfig(sysSmsConfig));
	}

	/**
	 * 修改短信基本设置
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		SysSmsConfig sysSmsConfig = sysSmsConfigService.selectSysSmsConfigById(id);
		mmap.put("sysSmsConfig", sysSmsConfig);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存短信基本设置
	 */
	@RequiresPermissions("message:sysSmsConfig:edit")
	@Log(title = "短信基本设置", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(SysSmsConfig sysSmsConfig)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(sysSmsConfigService.updateSysSmsConfig(sysSmsConfig));
	}
	
	/**
	 * 删除短信基本设置
	 */
	@RequiresPermissions("message:sysSmsConfig:remove")
	@Log(title = "短信基本设置", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(sysSmsConfigService.deleteSysSmsConfigByIds(ids));
	}
	
}
