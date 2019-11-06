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
import com.yiran.paychannel.domain.TmFundChannelTargetInit;
import com.yiran.paychannel.service.ITmFundChannelTargetInitService;
import com.yiran.framework.web.base.BaseController;
import com.yiran.common.page.TableDataInfo;
import com.yiran.common.base.AjaxResult;
import com.yiran.common.config.Global;
import com.yiran.common.utils.poi.ExcelUtil;

/**
 * 提供资金渠道目标机构 信息操作处理
 * 
 * @author yiran
 * @date 2019-04-20
 */
@Controller
@RequestMapping("/paychannel/tmFundChannelTargetInit")
public class TmFundChannelTargetInitController extends BaseController
{
    private String prefix = "paychannel/tmFundChannelTargetInit";
	
	@Autowired
	private ITmFundChannelTargetInitService tmFundChannelTargetInitService;
	
	@RequiresPermissions("paychannel:tmFundChannelTargetInit:view")
	@GetMapping()
	public String tmFundChannelTargetInit()
	{
	    return prefix + "/tmFundChannelTargetInit";
	}
	
	/**
	 * 查询提供资金渠道目标机构列表
	 */
	@RequiresPermissions("paychannel:tmFundChannelTargetInit:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(TmFundChannelTargetInit tmFundChannelTargetInit)
	{
		startPage();
        List<TmFundChannelTargetInit> list = tmFundChannelTargetInitService.selectTmFundChannelTargetInitList(tmFundChannelTargetInit);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出提供资金渠道目标机构列表
	 */
	@RequiresPermissions("paychannel:tmFundChannelTargetInit:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TmFundChannelTargetInit tmFundChannelTargetInit)
    {
    	List<TmFundChannelTargetInit> list = tmFundChannelTargetInitService.selectTmFundChannelTargetInitList(tmFundChannelTargetInit);
        ExcelUtil<TmFundChannelTargetInit> util = new ExcelUtil<TmFundChannelTargetInit>(TmFundChannelTargetInit.class);
        return util.exportExcel(list, "tmFundChannelTargetInit");
    }
	
	/**
	 * 新增提供资金渠道目标机构
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存提供资金渠道目标机构
	 */
	@RequiresPermissions("paychannel:tmFundChannelTargetInit:add")
	@Log(title = "提供资金渠道目标机构", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(TmFundChannelTargetInit tmFundChannelTargetInit)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(tmFundChannelTargetInitService.insertTmFundChannelTargetInit(tmFundChannelTargetInit));
	}

	/**
	 * 修改提供资金渠道目标机构
	 */
	@GetMapping("/edit/{targetInstCode}")
	public String edit(@PathVariable("targetInstCode") String targetInstCode, ModelMap mmap)
	{
		TmFundChannelTargetInit tmFundChannelTargetInit = tmFundChannelTargetInitService.selectTmFundChannelTargetInitById(targetInstCode);
		mmap.put("tmFundChannelTargetInit", tmFundChannelTargetInit);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存提供资金渠道目标机构
	 */
	@RequiresPermissions("paychannel:tmFundChannelTargetInit:edit")
	@Log(title = "提供资金渠道目标机构", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(TmFundChannelTargetInit tmFundChannelTargetInit)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(tmFundChannelTargetInitService.updateTmFundChannelTargetInit(tmFundChannelTargetInit));
	}
	
	/**
	 * 删除提供资金渠道目标机构
	 */
	@RequiresPermissions("paychannel:tmFundChannelTargetInit:remove")
	@Log(title = "提供资金渠道目标机构", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(tmFundChannelTargetInitService.deleteTmFundChannelTargetInitByIds(ids));
	}
	
}
