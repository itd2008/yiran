package com.yiran.web.controller.wechat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.yiran.wechat.domain.WechatProductBrand;
import com.yiran.wechat.domain.WechatProductSpecification;
import com.yiran.wechat.service.IWechatProductSpecificationService;
import com.yiran.framework.web.base.BaseController;
import com.yiran.system.domain.SysOperLog;
import com.yiran.common.page.TableDataInfo;
import com.yiran.common.base.AjaxResult;
import com.yiran.common.config.Global;
import com.yiran.common.utils.poi.ExcelUtil;

/**
 * 商品规格 信息操作处理
 * 
 * @author yiran
 * @date 2019-06-25
 */
@Controller
@RequestMapping("/wechat/wechatProductSpecification")
public class WechatProductSpecificationController extends BaseController
{
    private String prefix = "wechat/wechatProductSpecification";
	
	@Autowired
	private IWechatProductSpecificationService wechatProductSpecificationService;
	
	@RequiresPermissions("wechat:wechatProductSpecification:view")
	@GetMapping()
	public String wechatProductSpecification()
	{
	    return prefix + "/wechatProductSpecification";
	}
	
	/**
	 * 查询商品规格列表
	 */
	@RequiresPermissions("wechat:wechatProductSpecification:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(WechatProductSpecification wechatProductSpecification)
	{
		startPage();
        List<WechatProductSpecification> list = wechatProductSpecificationService.selectWechatProductSpecificationList(wechatProductSpecification);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出商品规格列表
	 */
	@RequiresPermissions("wechat:wechatProductSpecification:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(WechatProductSpecification wechatProductSpecification)
    {
    	List<WechatProductSpecification> list = wechatProductSpecificationService.selectWechatProductSpecificationList(wechatProductSpecification);
        ExcelUtil<WechatProductSpecification> util = new ExcelUtil<WechatProductSpecification>(WechatProductSpecification.class);
        return util.exportExcel(list, "wechatProductSpecification");
    }
	
	/**
	 * 新增商品规格
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存商品规格
	 */
	@RequiresPermissions("wechat:wechatProductSpecification:add")
	@Log(title = "商品规格", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(WechatProductSpecification wechatProductSpecification)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(wechatProductSpecificationService.insertWechatProductSpecification(wechatProductSpecification));
	}
	
	@GetMapping("/findProductSpecificationByCategoryId/{categoryId}")
	@ResponseBody
	public AjaxResult findProductSpecificationByCategoryId(@PathVariable("categoryId") Integer categoryId)
	{
		AjaxResult result = new AjaxResult();
		List<WechatProductSpecification> grouplist = wechatProductSpecificationService.selectWechatProductSpecificationListByCategoryId(categoryId);
		System.out.println("根据类别分组结果:"+JSON.toJSONString(grouplist));
		Map<String,Object> map = new HashMap<String,Object>();
		if(grouplist!=null){
			//根据类型查询类型对应的值
			for (WechatProductSpecification wps : grouplist) {
				//根据规格类型和类目查询这个规格类型下所有的规格值
				List<WechatProductSpecification> list = wechatProductSpecificationService.selectSpecificationListByCategoryIdAndSpecificationType(categoryId,wps.getSpecificationCode());
				map.put(wps.getSpecificationCode(), list);
			}
			
		}
	    return result.success(map);
	}

	/**
	 * 修改商品规格
	 */
	@GetMapping("/edit/{productSpecification}")
	public String edit(@PathVariable("productSpecification") Integer productSpecification, ModelMap mmap)
	{
		WechatProductSpecification wechatProductSpecification = wechatProductSpecificationService.selectWechatProductSpecificationById(productSpecification);
		mmap.put("wechatProductSpecification", wechatProductSpecification);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存商品规格
	 */
	@RequiresPermissions("wechat:wechatProductSpecification:edit")
	@Log(title = "商品规格", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(WechatProductSpecification wechatProductSpecification)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(wechatProductSpecificationService.updateWechatProductSpecification(wechatProductSpecification));
	}
	
	/**
	 * 删除商品规格
	 */
	@RequiresPermissions("wechat:wechatProductSpecification:remove")
	@Log(title = "商品规格", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(wechatProductSpecificationService.deleteWechatProductSpecificationByIds(ids));
	}
	
}
