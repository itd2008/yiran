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
import com.yiran.wechat.domain.WechatIndexPic;
import com.yiran.wechat.service.IWechatIndexPicService;
import com.yiran.framework.web.base.BaseController;
import com.yiran.common.page.TableDataInfo;
import com.yiran.common.support.Convert;
import com.yiran.common.base.AjaxResult;
import com.yiran.common.config.Global;
import com.yiran.common.utils.fastdft.FastDFSHelper;
import com.yiran.common.utils.poi.ExcelUtil;

/**
 * 首页宣传图片 信息操作处理
 * 
 * @author yiran
 * @date 2019-06-13
 */
@Controller
@RequestMapping("/wechat/wechatIndexPic")
public class WechatIndexPicController extends BaseController
{
    private String prefix = "wechat/wechatIndexPic";
	
	@Autowired
	private IWechatIndexPicService wechatIndexPicService;
	@Autowired
   	private FastDFSHelper fastDFSHelper;
	@RequiresPermissions("wechat:wechatIndexPic:view")
	@GetMapping()
	public String wechatIndexPic()
	{
	    return prefix + "/wechatIndexPic";
	}
	
	/**
	 * 查询首页宣传图片列表
	 */
	@RequiresPermissions("wechat:wechatIndexPic:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(WechatIndexPic wechatIndexPic)
	{
		startPage();
        List<WechatIndexPic> list = wechatIndexPicService.selectWechatIndexPicList(wechatIndexPic);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出首页宣传图片列表
	 */
	@RequiresPermissions("wechat:wechatIndexPic:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(WechatIndexPic wechatIndexPic)
    {
    	List<WechatIndexPic> list = wechatIndexPicService.selectWechatIndexPicList(wechatIndexPic);
        ExcelUtil<WechatIndexPic> util = new ExcelUtil<WechatIndexPic>(WechatIndexPic.class);
        return util.exportExcel(list, "wechatIndexPic");
    }
	
	/**
	 * 新增首页宣传图片
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存首页宣传图片
	 */
	@RequiresPermissions("wechat:wechatIndexPic:add")
	@Log(title = "首页宣传图片", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(WechatIndexPic wechatIndexPic)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(wechatIndexPicService.insertWechatIndexPic(wechatIndexPic));
	}

	/**
	 * 修改首页宣传图片
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		WechatIndexPic wechatIndexPic = wechatIndexPicService.selectWechatIndexPicById(id);
		mmap.put("wechatIndexPic", wechatIndexPic);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存首页宣传图片
	 */
	@RequiresPermissions("wechat:wechatIndexPic:edit")
	@Log(title = "首页宣传图片", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(WechatIndexPic wechatIndexPic)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(wechatIndexPicService.updateWechatIndexPic(wechatIndexPic));
	}
	
	/**
	 * 删除首页宣传图片
	 */
	@RequiresPermissions("wechat:wechatIndexPic:remove")
	@Log(title = "首页宣传图片", businessType = BusinessType.DELETE)
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
				WechatIndexPic wechatIndexPic = wechatIndexPicService.selectWechatIndexPicById(Integer.parseInt(removeIds[i]));
				fastDFSHelper.deleteFile(wechatIndexPic.getLinkUrl());
			}
		}
		return toAjax(wechatIndexPicService.deleteWechatIndexPicByIds(ids));
	}
	
}
