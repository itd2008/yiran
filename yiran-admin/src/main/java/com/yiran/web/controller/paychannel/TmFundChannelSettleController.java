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
import com.yiran.paychannel.domain.TmFundChannelSettle;
import com.yiran.paychannel.service.ITmFundChannelSettleService;
import com.yiran.framework.web.base.BaseController;
import com.yiran.common.page.TableDataInfo;
import com.yiran.common.base.AjaxResult;
import com.yiran.common.config.Global;
import com.yiran.common.utils.poi.ExcelUtil;

/**
 * 资金渠道算 信息操作处理
 * 
 * @author yiran
 * @date 2019-04-19
 */
@Controller
@RequestMapping("/paychannel/tmFundChannelSettle")
public class TmFundChannelSettleController extends BaseController
{
    private String prefix = "paychannel/tmFundChannelSettle";
	
	@Autowired
	private ITmFundChannelSettleService tmFundChannelSettleService;
	
	@RequiresPermissions("paychannel:tmFundChannelSettle:view")
	@GetMapping()
	public String tmFundChannelSettle()
	{
	    return prefix + "/tmFundChannelSettle";
	}
	
	/**
	 * 查询资金渠道算列表
	 */
	@RequiresPermissions("paychannel:tmFundChannelSettle:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(TmFundChannelSettle tmFundChannelSettle)
	{
		startPage();
        List<TmFundChannelSettle> list = tmFundChannelSettleService.selectTmFundChannelSettleList(tmFundChannelSettle);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出资金渠道算列表
	 */
	@RequiresPermissions("paychannel:tmFundChannelSettle:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TmFundChannelSettle tmFundChannelSettle)
    {
    	List<TmFundChannelSettle> list = tmFundChannelSettleService.selectTmFundChannelSettleList(tmFundChannelSettle);
        ExcelUtil<TmFundChannelSettle> util = new ExcelUtil<TmFundChannelSettle>(TmFundChannelSettle.class);
        return util.exportExcel(list, "tmFundChannelSettle");
    }
	
	/**
	 * 新增资金渠道算
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存资金渠道算
	 */
	@RequiresPermissions("paychannel:tmFundChannelSettle:add")
	@Log(title = "资金渠道算", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(TmFundChannelSettle tmFundChannelSettle)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(tmFundChannelSettleService.insertTmFundChannelSettle(tmFundChannelSettle));
	}

	/**
	 * 修改资金渠道算
	 */
	@GetMapping("/edit/{settleId}")
	public String edit(@PathVariable("settleId") Integer settleId, ModelMap mmap)
	{
		TmFundChannelSettle tmFundChannelSettle = tmFundChannelSettleService.selectTmFundChannelSettleById(settleId);
		mmap.put("tmFundChannelSettle", tmFundChannelSettle);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存资金渠道算
	 */
	@RequiresPermissions("paychannel:tmFundChannelSettle:edit")
	@Log(title = "资金渠道算", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(TmFundChannelSettle tmFundChannelSettle)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(tmFundChannelSettleService.updateTmFundChannelSettle(tmFundChannelSettle));
	}
	
	/**
	 * 删除资金渠道算
	 */
	@RequiresPermissions("paychannel:tmFundChannelSettle:remove")
	@Log(title = "资金渠道算", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(tmFundChannelSettleService.deleteTmFundChannelSettleByIds(ids));
	}
	
}
