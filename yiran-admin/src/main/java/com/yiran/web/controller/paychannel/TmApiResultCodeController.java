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
import com.yiran.paychannel.domain.TmApiResultCode;
import com.yiran.paychannel.service.ITmApiResultCodeService;
import com.yiran.paychannel.service.ITmUnityResultCodeService;
import com.yiran.system.domain.SysUser;
import com.yiran.framework.web.base.BaseController;
import com.yiran.common.page.TableDataInfo;
import com.yiran.common.base.AjaxResult;
import com.yiran.common.config.Global;
import com.yiran.common.utils.poi.ExcelUtil;

/**
 * API结果编码 信息操作处理
 * 
 * @author yiran
 * @date 2019-04-19
 */
@Controller
@RequestMapping("/paychannel/tmApiResultCode")
public class TmApiResultCodeController extends BaseController
{
    private String prefix = "paychannel/tmApiResultCode";
	
	@Autowired
	private ITmApiResultCodeService tmApiResultCodeService;
	@Autowired
	private ITmUnityResultCodeService tmUnityResultCodeService;
	
	@RequiresPermissions("paychannel:tmApiResultCode:view")
	@GetMapping()
	public String tmApiResultCode()
	{
	    return prefix + "/tmApiResultCode";
	}
	
	/**
	 * 查询API结果编码列表
	 */
	@RequiresPermissions("paychannel:tmApiResultCode:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(TmApiResultCode tmApiResultCode)
	{
		startPage();
        List<TmApiResultCode> list = tmApiResultCodeService.selectTmApiResultCodeList(tmApiResultCode);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出API结果编码列表
	 */
	@RequiresPermissions("paychannel:tmApiResultCode:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TmApiResultCode tmApiResultCode)
    {
    	List<TmApiResultCode> list = tmApiResultCodeService.selectTmApiResultCodeList(tmApiResultCode);
        ExcelUtil<TmApiResultCode> util = new ExcelUtil<TmApiResultCode>(TmApiResultCode.class);
        return util.exportExcel(list, "tmApiResultCode");
    }
	
	/**
	 * 新增API结果编码
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存API结果编码
	 */
	@RequiresPermissions("paychannel:tmApiResultCode:add")
	@Log(title = "API结果编码", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(TmApiResultCode tmApiResultCode)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(tmApiResultCodeService.insertTmApiResultCode(tmApiResultCode));
	}
	
	@PostMapping("/checkUnityResultCode")
    @ResponseBody
    public String checkUnityResultCode(TmApiResultCode tmApiResultCode)
    {
        return tmUnityResultCodeService.checkUnityResultCode(tmApiResultCode.getUnityResultCode());
    }

	/**
	 * 修改API结果编码
	 */
	@GetMapping("/edit/{apiResultCodeId}")
	public String edit(@PathVariable("apiResultCodeId") Integer apiResultCodeId, ModelMap mmap)
	{
		TmApiResultCode tmApiResultCode = tmApiResultCodeService.selectTmApiResultCodeById(apiResultCodeId);
		mmap.put("tmApiResultCode", tmApiResultCode);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存API结果编码
	 */
	@RequiresPermissions("paychannel:tmApiResultCode:edit")
	@Log(title = "API结果编码", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(TmApiResultCode tmApiResultCode)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(tmApiResultCodeService.updateTmApiResultCode(tmApiResultCode));
	}
	
	/**
	 * 删除API结果编码
	 */
	@RequiresPermissions("paychannel:tmApiResultCode:remove")
	@Log(title = "API结果编码", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(tmApiResultCodeService.deleteTmApiResultCodeByIds(ids));
	}
	
}
