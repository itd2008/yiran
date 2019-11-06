package com.yiran.web.controller.paychannel;

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
import com.yiran.paychannel.domain.TmUnityResultCode;
import com.yiran.paychannel.service.ITmUnityResultCodeService;
import com.yiran.framework.web.base.BaseController;
import com.yiran.common.page.TableDataInfo;
import com.yiran.common.base.AjaxResult;
import com.yiran.common.config.Global;
import com.yiran.common.utils.poi.ExcelUtil;

/**
 * 统一结果代码 信息操作处理
 * 
 * @author yiran
 * @date 2019-04-19
 */
@Controller
@RequestMapping("/paychannel/tmUnityResultCode")
public class TmUnityResultCodeController extends BaseController
{
    private String prefix = "paychannel/tmUnityResultCode";
	
	@Autowired
	private ITmUnityResultCodeService tmUnityResultCodeService;
	
	@RequiresPermissions("paychannel:tmUnityResultCode:view")
	@GetMapping()
	public String tmUnityResultCode()
	{
	    return prefix + "/tmUnityResultCode";
	}
	
	/**
	 * 查询统一结果代码列表
	 */
	@RequiresPermissions("paychannel:tmUnityResultCode:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(TmUnityResultCode tmUnityResultCode)
	{
		startPage();
        List<TmUnityResultCode> list = tmUnityResultCodeService.selectTmUnityResultCodeList(tmUnityResultCode);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出统一结果代码列表
	 */
	@RequiresPermissions("paychannel:tmUnityResultCode:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TmUnityResultCode tmUnityResultCode)
    {
    	List<TmUnityResultCode> list = tmUnityResultCodeService.selectTmUnityResultCodeList(tmUnityResultCode);
        ExcelUtil<TmUnityResultCode> util = new ExcelUtil<TmUnityResultCode>(TmUnityResultCode.class);
        return util.exportExcel(list, "tmUnityResultCode");
    }
	
	/**
	 * 新增统一结果代码
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存统一结果代码
	 */
	@RequiresPermissions("paychannel:tmUnityResultCode:add")
	@Log(title = "统一结果代码", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(TmUnityResultCode tmUnityResultCode)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(tmUnityResultCodeService.insertTmUnityResultCode(tmUnityResultCode));
	}

	/**
	 * 修改统一结果代码
	 */
	@GetMapping("/edit/{unityResultCode}")
	public String edit(@PathVariable("unityResultCode") String unityResultCode, ModelMap mmap)
	{
		TmUnityResultCode tmUnityResultCode = tmUnityResultCodeService.selectTmUnityResultCodeById(unityResultCode);
		mmap.put("tmUnityResultCode", tmUnityResultCode);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存统一结果代码
	 */
	@RequiresPermissions("paychannel:tmUnityResultCode:edit")
	@Log(title = "统一结果代码", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(TmUnityResultCode tmUnityResultCode)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(tmUnityResultCodeService.updateTmUnityResultCode(tmUnityResultCode));
	}
	
	/**
	 * 删除统一结果代码
	 */
	@RequiresPermissions("paychannel:tmUnityResultCode:remove")
	@Log(title = "统一结果代码", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(tmUnityResultCodeService.deleteTmUnityResultCodeByIds(ids));
	}
	
}
