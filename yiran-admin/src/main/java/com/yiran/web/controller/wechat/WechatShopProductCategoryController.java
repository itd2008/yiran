package com.yiran.web.controller.wechat;

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
import com.yiran.common.annotation.Log;
import com.yiran.common.enums.BusinessType;
import com.yiran.wechat.domain.WechatShopProductCategory;
import com.yiran.wechat.service.IWechatShopProductCategoryService;
import com.yiran.framework.web.base.BaseController;
import com.yiran.system.domain.SysMenu;
import com.yiran.common.page.TableDataInfo;
import com.yiran.common.base.AjaxResult;
import com.yiran.common.config.Global;
import com.yiran.common.utils.poi.ExcelUtil;

/**
 * 商品类目 信息操作处理
 * 
 * @author yiran
 * @date 2019-06-13
 */
@Controller
@RequestMapping("/wechat/wechatShopProductCategory")
public class WechatShopProductCategoryController extends BaseController
{
    private String prefix = "wechat/wechatShopProductCategory";
	
	@Autowired
	private IWechatShopProductCategoryService wechatShopProductCategoryService;
	
	@RequiresPermissions("wechat:wechatShopProductCategory:view")
	@GetMapping()
	public String wechatShopProductCategory()
	{
	    return prefix + "/wechatShopProductCategory";
	}
	
	/**
	 * 查询商品类目列表
	 */
	@RequiresPermissions("wechat:wechatShopProductCategory:list")
	@GetMapping("/list")
	@ResponseBody
	public List<WechatShopProductCategory> list(WechatShopProductCategory wechatShopProductCategory)
	{
        List<WechatShopProductCategory> list = wechatShopProductCategoryService.selectWechatShopProductCategoryList(wechatShopProductCategory);
		return list;
	}
	/**
     * 加载网站栏目列表树
     */
    @GetMapping("/treeData")
    @ResponseBody
    public List<Map<String, Object>> treeData()
    {
        List<Map<String, Object>> tree = wechatShopProductCategoryService.selectChannelTree();
        return tree;
    }
    
    /**
     * 选择栏目树
     */
    @GetMapping("/selectChannelTree/{id}")
    public String selectChannelTree(@PathVariable("id") Integer id, ModelMap model)
    {
        model.put("productCategory", wechatShopProductCategoryService.selectProductCategoryById(id));
        return prefix + "/tree";
    }
    
    /**
     * 校验栏目名称
     */
    @PostMapping("/checkChannelNameUnique")
    @ResponseBody
    public String checkChannelNameUnique(WechatShopProductCategory productCategory)
    {
        String uniqueFlag = "0";
        if (productCategory != null)
        {
            uniqueFlag = wechatShopProductCategoryService.checkDeptNameUnique(productCategory);
        }
        return uniqueFlag;
    }
	
	/**
	 * 导出商品类目列表
	 */
	@RequiresPermissions("wechat:wechatShopProductCategory:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(WechatShopProductCategory wechatShopProductCategory)
    {
    	List<WechatShopProductCategory> list = wechatShopProductCategoryService.selectWechatShopProductCategoryList(wechatShopProductCategory);
        ExcelUtil<WechatShopProductCategory> util = new ExcelUtil<WechatShopProductCategory>(WechatShopProductCategory.class);
        return util.exportExcel(list, "wechatShopProductCategory");
    }
	
	/**
	 * 新增商品类目
	 */
    @GetMapping("/add/{parentId}")
    public String add(@PathVariable("parentId") Integer parentId, ModelMap mmap)
    {
    	WechatShopProductCategory wechatShopProductCategory = null;
        if (0 != parentId)
        {
        	wechatShopProductCategory = wechatShopProductCategoryService.selectWechatShopProductCategoryById(parentId);
        }
        else
        {
        	wechatShopProductCategory = new WechatShopProductCategory();
        	wechatShopProductCategory.setId(0);
        	wechatShopProductCategory.setName("商品类目");
        }
        mmap.put("wechatShopProductCategory", wechatShopProductCategory);
        return prefix + "/add";
    }

	
	/**
	 * 新增保存商品类目
	 */
	@RequiresPermissions("wechat:wechatShopProductCategory:add")
	@Log(title = "商品类目", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(WechatShopProductCategory wechatShopProductCategory)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(wechatShopProductCategoryService.insertWechatShopProductCategory(wechatShopProductCategory));
	}

	/**
	 * 修改商品类目
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		WechatShopProductCategory wechatShopProductCategory = wechatShopProductCategoryService.selectWechatShopProductCategoryById(id);
		mmap.put("wechatShopProductCategory", wechatShopProductCategory);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存商品类目
	 */
	@RequiresPermissions("wechat:wechatShopProductCategory:edit")
	@Log(title = "商品类目", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(WechatShopProductCategory wechatShopProductCategory)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(wechatShopProductCategoryService.updateWechatShopProductCategory(wechatShopProductCategory));
	}
	
	/**
	 * 删除商品类目
	 */
	@RequiresPermissions("wechat:wechatShopProductCategory:remove")
	@Log(title = "商品类目", businessType = BusinessType.DELETE)
	@PostMapping( "/remove/{id}")
	@ResponseBody
	public AjaxResult remove(@PathVariable("id") Integer id)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		if (wechatShopProductCategoryService.selectChannelCount(id) > 0)
        {
            return error(1, "存在下级类目,不允许删除");
        }
        if (wechatShopProductCategoryService.deleteWechatShopProductCategoryById(id) > 0)
        {
            return success();
        }
        return error();
	}
	
}
