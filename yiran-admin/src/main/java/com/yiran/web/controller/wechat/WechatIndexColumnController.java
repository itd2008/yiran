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
import com.yiran.wechat.domain.WechatIndexColumn;
import com.yiran.wechat.service.IWechatIndexColumnService;
import com.yiran.framework.web.base.BaseController;
import com.yiran.common.page.TableDataInfo;
import com.yiran.common.base.AjaxResult;
import com.yiran.common.config.Global;
import com.yiran.common.utils.poi.ExcelUtil;

/**
 * 首页栏目 信息操作处理
 * 
 * @author yiran
 * @date 2019-06-13
 */
@Controller
@RequestMapping("/wechat/wechatIndexColumn")
public class WechatIndexColumnController extends BaseController
{
    private String prefix = "wechat/wechatIndexColumn";
	
	@Autowired
	private IWechatIndexColumnService wechatIndexColumnService;
	
	@RequiresPermissions("wechat:wechatIndexColumn:view")
	@GetMapping()
	public String wechatIndexColumn()
	{
	    return prefix + "/wechatIndexColumn";
	}
	
	/**
	 * 查询首页栏目列表
	 */
	@RequiresPermissions("wechat:wechatIndexColumn:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(WechatIndexColumn wechatIndexColumn)
	{
		startPage();
        List<WechatIndexColumn> list = wechatIndexColumnService.selectWechatIndexColumnList(wechatIndexColumn);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出首页栏目列表
	 */
	@RequiresPermissions("wechat:wechatIndexColumn:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(WechatIndexColumn wechatIndexColumn)
    {
    	List<WechatIndexColumn> list = wechatIndexColumnService.selectWechatIndexColumnList(wechatIndexColumn);
        ExcelUtil<WechatIndexColumn> util = new ExcelUtil<WechatIndexColumn>(WechatIndexColumn.class);
        return util.exportExcel(list, "wechatIndexColumn");
    }
	
	/**
	 * 新增首页栏目
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存首页栏目
	 */
	@RequiresPermissions("wechat:wechatIndexColumn:add")
	@Log(title = "首页栏目", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(WechatIndexColumn wechatIndexColumn)
	{		
		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(wechatIndexColumnService.insertWechatIndexColumn(wechatIndexColumn));
	}

	/**
	 * 修改首页栏目
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		WechatIndexColumn wechatIndexColumn = wechatIndexColumnService.selectWechatIndexColumnById(id);
		mmap.put("wechatIndexColumn", wechatIndexColumn);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存首页栏目
	 */
	@RequiresPermissions("wechat:wechatIndexColumn:edit")
	@Log(title = "首页栏目", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(WechatIndexColumn wechatIndexColumn)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(wechatIndexColumnService.updateWechatIndexColumn(wechatIndexColumn));
	}
	
	/**
	 * 删除首页栏目
	 */
	@RequiresPermissions("wechat:wechatIndexColumn:remove")
	@Log(title = "首页栏目", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(wechatIndexColumnService.deleteWechatIndexColumnByIds(ids));
	}
	
}
