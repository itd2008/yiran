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
import com.yiran.wechat.domain.BsProvince;
import com.yiran.wechat.domain.WechatFreightTemplate;
import com.yiran.wechat.service.IBsProvinceService;
import com.yiran.wechat.service.IWechatFreightTemplateService;
import com.yiran.framework.web.base.BaseController;
import com.yiran.common.page.TableDataInfo;
import com.yiran.common.base.AjaxResult;
import com.yiran.common.config.Global;
import com.yiran.common.utils.poi.ExcelUtil;

/**
 * 运费模板 信息操作处理
 * 
 * @author yiran
 * @date 2019-07-06
 */
@Controller
@RequestMapping("/wechat/wechatFreightTemplate")
public class WechatFreightTemplateController extends BaseController
{
    private String prefix = "wechat/wechatFreightTemplate";
	
	@Autowired
	private IWechatFreightTemplateService wechatFreightTemplateService;
	
	@Autowired
	private IBsProvinceService bsProvinceService;
	
	@RequiresPermissions("wechat:wechatFreightTemplate:view")
	@GetMapping()
	public String wechatFreightTemplate()
	{
	    return prefix + "/wechatFreightTemplate";
	}
	
	/**
	 * 查询运费模板列表
	 */
	@RequiresPermissions("wechat:wechatFreightTemplate:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(WechatFreightTemplate wechatFreightTemplate)
	{
		startPage();
        List<WechatFreightTemplate> list = wechatFreightTemplateService.selectWechatFreightTemplateList(wechatFreightTemplate);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出运费模板列表
	 */
	@RequiresPermissions("wechat:wechatFreightTemplate:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(WechatFreightTemplate wechatFreightTemplate)
    {
    	List<WechatFreightTemplate> list = wechatFreightTemplateService.selectWechatFreightTemplateList(wechatFreightTemplate);
        ExcelUtil<WechatFreightTemplate> util = new ExcelUtil<WechatFreightTemplate>(WechatFreightTemplate.class);
        return util.exportExcel(list, "wechatFreightTemplate");
    }
	
	
    @PostMapping("/findTemplates")
    @ResponseBody
    public AjaxResult findTemplates()
    {
    	AjaxResult result = new AjaxResult();
    	List<WechatFreightTemplate> list = wechatFreightTemplateService.selectWechatFreightTemplateList();
    	return result.success(list);
    }
	/**
	 * 新增运费模板
	 */
	@GetMapping("/add")
	public String add(ModelMap mmap)
	{
		List<BsProvince> provinceList = bsProvinceService.selectBsProvinceLists();
		mmap.put("provinceList", provinceList);
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存运费模板
	 */
	@RequiresPermissions("wechat:wechatFreightTemplate:add")
	@Log(title = "运费模板", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(WechatFreightTemplate wechatFreightTemplate)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(wechatFreightTemplateService.insertWechatFreightTemplate(wechatFreightTemplate));
	}

	/**
	 * 修改运费模板
	 */
	@GetMapping("/edit/{freightTemplateId}")
	public String edit(@PathVariable("freightTemplateId") Integer freightTemplateId, ModelMap mmap)
	{
		WechatFreightTemplate wechatFreightTemplate = wechatFreightTemplateService.selectWechatFreightTemplateById(freightTemplateId);
		mmap.put("wechatFreightTemplate", wechatFreightTemplate);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存运费模板
	 */
	@RequiresPermissions("wechat:wechatFreightTemplate:edit")
	@Log(title = "运费模板", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(WechatFreightTemplate wechatFreightTemplate)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(wechatFreightTemplateService.updateWechatFreightTemplate(wechatFreightTemplate));
	}
	
	/**
	 * 删除运费模板
	 */
	@RequiresPermissions("wechat:wechatFreightTemplate:remove")
	@Log(title = "运费模板", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(wechatFreightTemplateService.deleteWechatFreightTemplateByIds(ids));
	}
	
}
