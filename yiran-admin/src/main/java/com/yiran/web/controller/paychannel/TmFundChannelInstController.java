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
import com.yiran.paychannel.domain.TmFundChannelInst;
import com.yiran.paychannel.service.ITmFundChannelInstService;
import com.yiran.framework.web.base.BaseController;
import com.yiran.common.page.TableDataInfo;
import com.yiran.common.base.AjaxResult;
import com.yiran.common.config.Global;
import com.yiran.common.utils.poi.ExcelUtil;

/**
 * 提供资金渠道的机构 信息操作处理
 * 
 * @author yiran
 * @date 2019-04-19
 */
@Controller
@RequestMapping("/paychannel/tmFundChannelInst")
public class TmFundChannelInstController extends BaseController
{
    private String prefix = "paychannel/tmFundChannelInst";
	
	@Autowired
	private ITmFundChannelInstService tmFundChannelInstService;
	
	@RequiresPermissions("paychannel:tmFundChannelInst:view")
	@GetMapping()
	public String tmFundChannelInst()
	{
	    return prefix + "/tmFundChannelInst";
	}
	
	/**
	 * 查询提供资金渠道的机构列表
	 */
	@RequiresPermissions("paychannel:tmFundChannelInst:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(TmFundChannelInst tmFundChannelInst)
	{
		startPage();
        List<TmFundChannelInst> list = tmFundChannelInstService.selectTmFundChannelInstList(tmFundChannelInst);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出提供资金渠道的机构列表
	 */
	@RequiresPermissions("paychannel:tmFundChannelInst:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TmFundChannelInst tmFundChannelInst)
    {
    	List<TmFundChannelInst> list = tmFundChannelInstService.selectTmFundChannelInstList(tmFundChannelInst);
        ExcelUtil<TmFundChannelInst> util = new ExcelUtil<TmFundChannelInst>(TmFundChannelInst.class);
        return util.exportExcel(list, "tmFundChannelInst");
    }
	
	/**
	 * 新增提供资金渠道的机构
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存提供资金渠道的机构
	 */
	@RequiresPermissions("paychannel:tmFundChannelInst:add")
	@Log(title = "提供资金渠道的机构", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(TmFundChannelInst tmFundChannelInst)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(tmFundChannelInstService.insertTmFundChannelInst(tmFundChannelInst));
	}

	/**
	 * 修改提供资金渠道的机构
	 */
	@GetMapping("/edit/{instCode}")
	public String edit(@PathVariable("instCode") String instCode, ModelMap mmap)
	{
		TmFundChannelInst tmFundChannelInst = tmFundChannelInstService.selectTmFundChannelInstById(instCode);
		mmap.put("tmFundChannelInst", tmFundChannelInst);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存提供资金渠道的机构
	 */
	@RequiresPermissions("paychannel:tmFundChannelInst:edit")
	@Log(title = "提供资金渠道的机构", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(TmFundChannelInst tmFundChannelInst)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(tmFundChannelInstService.updateTmFundChannelInst(tmFundChannelInst));
	}
	
	/**
	 * 删除提供资金渠道的机构
	 */
	@RequiresPermissions("paychannel:tmFundChannelInst:remove")
	@Log(title = "提供资金渠道的机构", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(tmFundChannelInstService.deleteTmFundChannelInstByIds(ids));
	}
	
}
