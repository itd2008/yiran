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
import com.yiran.wechat.domain.WechatProductBrand;
import com.yiran.wechat.service.IWechatProductBrandService;
import com.yiran.framework.web.base.BaseController;
import com.yiran.common.page.TableDataInfo;
import com.yiran.common.support.Convert;
import com.yiran.common.base.AjaxResult;
import com.yiran.common.config.Global;
import com.yiran.common.utils.fastdft.FastDFSHelper;
import com.yiran.common.utils.poi.ExcelUtil;

/**
 * 商品品牌 信息操作处理
 * 
 * @author yiran
 * @date 2019-06-13
 */
@Controller
@RequestMapping("/wechat/wechatProductBrand")
public class WechatProductBrandController extends BaseController
{
    private String prefix = "wechat/wechatProductBrand";
    @Autowired
   	private FastDFSHelper fastDFSHelper;
	@Autowired
	private IWechatProductBrandService wechatProductBrandService;
	
	@RequiresPermissions("wechat:wechatProductBrand:view")
	@GetMapping()
	public String wechatProductBrand()
	{
	    return prefix + "/wechatProductBrand";
	}
	
	/**
	 * 查询商品品牌列表
	 */
	@RequiresPermissions("wechat:wechatProductBrand:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(WechatProductBrand wechatProductBrand)
	{
		startPage();
        List<WechatProductBrand> list = wechatProductBrandService.selectWechatProductBrandList(wechatProductBrand);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出商品品牌列表
	 */
	@RequiresPermissions("wechat:wechatProductBrand:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(WechatProductBrand wechatProductBrand)
    {
    	List<WechatProductBrand> list = wechatProductBrandService.selectWechatProductBrandList(wechatProductBrand);
        ExcelUtil<WechatProductBrand> util = new ExcelUtil<WechatProductBrand>(WechatProductBrand.class);
        return util.exportExcel(list, "wechatProductBrand");
    }
	
	/**
	 * 新增商品品牌
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存商品品牌
	 */
	@RequiresPermissions("wechat:wechatProductBrand:add")
	@Log(title = "商品品牌", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(WechatProductBrand wechatProductBrand)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(wechatProductBrandService.insertWechatProductBrand(wechatProductBrand));
	}

	/**
	 * 修改商品品牌
	 */
	@GetMapping("/edit/{productBrandId}")
	public String edit(@PathVariable("productBrandId") Integer productBrandId, ModelMap mmap)
	{
		WechatProductBrand wechatProductBrand = wechatProductBrandService.selectWechatProductBrandById(productBrandId);
		mmap.put("wechatProductBrand", wechatProductBrand);
	    return prefix + "/edit";
	}
	
	/**
	 * 
	 */
	@GetMapping("/findBrandsByCategoryId/{categoryId}")
	@ResponseBody
	public AjaxResult findBrandsByCategoryId(@PathVariable("categoryId") String categoryId)
	{
		AjaxResult result = new AjaxResult();
		List<WechatProductBrand> list = wechatProductBrandService.selectWechatProductBrandListByCategoryId(categoryId);
	    return result.success(list);
	}
	/**
	 * 修改保存商品品牌
	 */
	@RequiresPermissions("wechat:wechatProductBrand:edit")
	@Log(title = "商品品牌", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(WechatProductBrand wechatProductBrand)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(wechatProductBrandService.updateWechatProductBrand(wechatProductBrand));
	}
	
	/**
	 * 删除商品品牌
	 */
	@RequiresPermissions("wechat:wechatProductBrand:remove")
	@Log(title = "商品品牌", businessType = BusinessType.DELETE)
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
				WechatProductBrand wechatProductBrand = wechatProductBrandService.selectWechatProductBrandById(Integer.parseInt(removeIds[i]));
				fastDFSHelper.deleteFile(wechatProductBrand.getImageUrl());
			}
		}
		return toAjax(wechatProductBrandService.deleteWechatProductBrandByIds(ids));
	}
	
}
