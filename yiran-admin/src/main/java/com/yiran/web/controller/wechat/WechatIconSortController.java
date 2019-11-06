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

import com.alibaba.fastjson.JSON;
import com.yiran.common.annotation.Log;
import com.yiran.common.enums.BusinessType;
import com.yiran.wechat.domain.WechatIconSort;
import com.yiran.wechat.service.IWechatIconSortService;

import cn.hutool.json.JSONUtil;

import com.yiran.framework.web.base.BaseController;
import com.yiran.common.page.TableDataInfo;
import com.yiran.common.support.Convert;
import com.yiran.common.base.AjaxResult;
import com.yiran.common.config.Global;
import com.yiran.common.utils.fastdft.FastDFSHelper;
import com.yiran.common.utils.poi.ExcelUtil;

/**
 * 图标分类 信息操作处理
 * 
 * @author yiran
 * @date 2019-06-13
 */
@Controller
@RequestMapping("/wechat/wechatIconSort")
public class WechatIconSortController extends BaseController
{
    private String prefix = "wechat/wechatIconSort";
	
	@Autowired
	private IWechatIconSortService wechatIconSortService;
	@Autowired
   	private FastDFSHelper fastDFSHelper;
	@RequiresPermissions("wechat:wechatIconSort:view")
	@GetMapping()
	public String wechatIconSort()
	{
	    return prefix + "/wechatIconSort";
	}
	
	/**
	 * 查询图标分类列表
	 */
	@RequiresPermissions("wechat:wechatIconSort:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(WechatIconSort wechatIconSort)
	{
		startPage();
        List<WechatIconSort> list = wechatIconSortService.selectWechatIconSortList(wechatIconSort);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出图标分类列表
	 */
	@RequiresPermissions("wechat:wechatIconSort:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(WechatIconSort wechatIconSort)
    {
    	List<WechatIconSort> list = wechatIconSortService.selectWechatIconSortList(wechatIconSort);
        ExcelUtil<WechatIconSort> util = new ExcelUtil<WechatIconSort>(WechatIconSort.class);
        return util.exportExcel(list, "wechatIconSort");
    }
	
	/**
	 * 新增图标分类
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存图标分类
	 */
	@RequiresPermissions("wechat:wechatIconSort:add")
	@Log(title = "图标分类", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(WechatIconSort wechatIconSort)
	{	
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		System.out.println("新增保存图标分类:"+JSONUtil.toJsonStr(wechatIconSort));
		return toAjax(wechatIconSortService.insertWechatIconSort(wechatIconSort));
	}

	/**
	 * 修改图标分类
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		WechatIconSort wechatIconSort = wechatIconSortService.selectWechatIconSortById(id);
		mmap.put("wechatIconSort", wechatIconSort);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存图标分类
	 */
	@RequiresPermissions("wechat:wechatIconSort:edit")
	@Log(title = "图标分类", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(WechatIconSort wechatIconSort)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(wechatIconSortService.updateWechatIconSort(wechatIconSort));
	}
	
	/**
	 * 删除图标分类
	 */
	@RequiresPermissions("wechat:wechatIconSort:remove")
	@Log(title = "图标分类", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		String[] removeIds = Convert.toStrArray(ids);
		if(removeIds.length > 0){
			for (int i = 0; i < removeIds.length; i++) {
				//根据Id获取对象
				WechatIconSort wechatIconSort = wechatIconSortService.selectWechatIconSortById(Integer.parseInt(removeIds[i]));
				fastDFSHelper.deleteFile(wechatIconSort.getIconPath());
			}
		}
		return toAjax(wechatIconSortService.deleteWechatIconSortByIds(ids));
	}
	
	
}
