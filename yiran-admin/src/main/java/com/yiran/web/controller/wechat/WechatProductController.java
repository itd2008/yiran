package com.yiran.web.controller.wechat;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.yiran.common.annotation.Log;
import com.yiran.common.enums.BusinessType;
import com.yiran.wechat.domain.WechatFreightTemplate;
import com.yiran.wechat.domain.WechatProduct;
import com.yiran.wechat.domain.WechatProductBrand;
import com.yiran.wechat.service.IWechatFreightTemplateService;
import com.yiran.wechat.service.IWechatProductBrandService;
import com.yiran.wechat.service.IWechatProductService;

import cn.hutool.json.JSONUtil;

import com.yiran.framework.web.base.BaseController;
import com.yiran.common.page.TableDataInfo;
import com.yiran.common.base.AjaxResult;
import com.yiran.common.config.Global;
import com.yiran.common.utils.poi.ExcelUtil;

/**
 * 商品 信息操作处理
 * 
 * @author yiran
 * @date 2019-06-13
 */
@Controller
@RequestMapping("/wechat/wechatProduct")
public class WechatProductController extends BaseController
{
	private static final Logger logger = LoggerFactory.getLogger(WechatProductController.class);
	
    private String prefix = "wechat/wechatProduct";
	@Autowired
	private IWechatProductService wechatProductService;
	@Autowired
	private IWechatProductBrandService wechatProductBrandService;
	@Autowired
	private IWechatFreightTemplateService wechatFreightTemplateService;
	@RequiresPermissions("wechat:wechatProduct:view")
	@GetMapping()
	public String wechatProduct()
	{
	    return prefix + "/wechatProduct";
	}
	
	/**
	 * 查询商品列表
	 */
	@RequiresPermissions("wechat:wechatProduct:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(WechatProduct wechatProduct)
	{
		startPage();
        List<WechatProduct> list = wechatProductService.selectWechatProductList(wechatProduct);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出商品列表
	 */
	@RequiresPermissions("wechat:wechatProduct:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(WechatProduct wechatProduct)
    {
    	List<WechatProduct> list = wechatProductService.selectWechatProductList(wechatProduct);
        ExcelUtil<WechatProduct> util = new ExcelUtil<WechatProduct>(WechatProduct.class);
        return util.exportExcel(list, "wechatProduct");
    }
	
	/**
	 * 新增商品
	 */
	@GetMapping("/add")
	public String add(ModelMap mmap)
	{
		//获取所有的运费模板
		List<WechatFreightTemplate> freightTemplates = wechatFreightTemplateService.selectWechatFreightTemplateList();
		mmap.put("freightTemplates", freightTemplates);
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存商品
	 */
	@RequiresPermissions("wechat:wechatProduct:add")
	@Log(title = "商品", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public String addSave(HttpServletRequest request,@RequestParam("linkUrl") MultipartFile[] file,WechatProduct wechatProduct)
	{	
		logger.debug("查询商品列表参数:{}",JSONUtil.toJsonPrettyStr(wechatProduct));
   		wechatProductService.insertWechatProduct(file,wechatProduct);
   		return "redirect:"+prefix + "/list";
	}

	/**
	 * 修改商品
	 */
	@GetMapping("/edit/{productId}")
	public String edit(@PathVariable("productId") Integer productId, ModelMap mmap)
	{
		WechatProduct wechatProduct = wechatProductService.selectWechatProductById(productId);
		mmap.put("wechatProduct", wechatProduct);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存商品
	 */
	@RequiresPermissions("wechat:wechatProduct:edit")
	@Log(title = "商品", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(WechatProduct wechatProduct)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(wechatProductService.updateWechatProduct(wechatProduct));
	}
	
	/**
	 * 删除商品
	 */
	@RequiresPermissions("wechat:wechatProduct:remove")
	@Log(title = "商品", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(wechatProductService.deleteWechatProductByIds(ids));
	}
	
}
