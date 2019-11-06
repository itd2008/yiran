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
import com.yiran.wechat.domain.WechatProductAlbum;
import com.yiran.wechat.service.IWechatProductAlbumService;
import com.yiran.framework.web.base.BaseController;
import com.yiran.common.page.TableDataInfo;
import com.yiran.common.base.AjaxResult;
import com.yiran.common.config.Global;
import com.yiran.common.utils.poi.ExcelUtil;

/**
 * 商品相册 信息操作处理
 * 
 * @author yiran
 * @date 2019-06-13
 */
@Controller
@RequestMapping("/wechat/wechatProductAlbum")
public class WechatProductAlbumController extends BaseController
{
    private String prefix = "wechat/wechatProductAlbum";
	
	@Autowired
	private IWechatProductAlbumService wechatProductAlbumService;
	
	@RequiresPermissions("wechat:wechatProductAlbum:view")
	@GetMapping()
	public String wechatProductAlbum()
	{
	    return prefix + "/wechatProductAlbum";
	}
	
	/**
	 * 查询商品相册列表
	 */
	@RequiresPermissions("wechat:wechatProductAlbum:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(WechatProductAlbum wechatProductAlbum)
	{
		startPage();
        List<WechatProductAlbum> list = wechatProductAlbumService.selectWechatProductAlbumList(wechatProductAlbum);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出商品相册列表
	 */
	@RequiresPermissions("wechat:wechatProductAlbum:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(WechatProductAlbum wechatProductAlbum)
    {
    	List<WechatProductAlbum> list = wechatProductAlbumService.selectWechatProductAlbumList(wechatProductAlbum);
        ExcelUtil<WechatProductAlbum> util = new ExcelUtil<WechatProductAlbum>(WechatProductAlbum.class);
        return util.exportExcel(list, "wechatProductAlbum");
    }
	
	/**
	 * 新增商品相册
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存商品相册
	 */
	@RequiresPermissions("wechat:wechatProductAlbum:add")
	@Log(title = "商品相册", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(WechatProductAlbum wechatProductAlbum)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(wechatProductAlbumService.insertWechatProductAlbum(wechatProductAlbum));
	}

	/**
	 * 修改商品相册
	 */
	@GetMapping("/edit/{productAlbumId}")
	public String edit(@PathVariable("productAlbumId") Integer productAlbumId, ModelMap mmap)
	{
		WechatProductAlbum wechatProductAlbum = wechatProductAlbumService.selectWechatProductAlbumById(productAlbumId);
		mmap.put("wechatProductAlbum", wechatProductAlbum);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存商品相册
	 */
	@RequiresPermissions("wechat:wechatProductAlbum:edit")
	@Log(title = "商品相册", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(WechatProductAlbum wechatProductAlbum)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(wechatProductAlbumService.updateWechatProductAlbum(wechatProductAlbum));
	}
	
	/**
	 * 删除商品相册
	 */
	@RequiresPermissions("wechat:wechatProductAlbum:remove")
	@Log(title = "商品相册", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(wechatProductAlbumService.deleteWechatProductAlbumByIds(ids));
	}
	
}
