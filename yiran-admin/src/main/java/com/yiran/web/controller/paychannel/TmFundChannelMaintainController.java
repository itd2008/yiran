package com.yiran.web.controller.paychannel;

import java.util.List;
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
import org.springframework.web.bind.annotation.ResponseBody;
import com.yiran.common.annotation.Log;
import com.yiran.common.enums.BusinessType;
import com.yiran.paychannel.domain.TmFundChannelMaintain;
import com.yiran.paychannel.service.ITmFundChannelMaintainService;
import com.yiran.framework.web.base.BaseController;
import com.yiran.common.page.TableDataInfo;
import com.yiran.common.base.AjaxResult;
import com.yiran.common.config.Global;
import com.yiran.common.utils.poi.ExcelUtil;

/**
 * 资金渠道维护期 信息操作处理
 * 
 * @author yiran
 * @date 2019-04-19
 */
@Controller
@RequestMapping("/paychannel/tmFundChannelMaintain")
public class TmFundChannelMaintainController extends BaseController
{
	private Logger        logger = LoggerFactory.getLogger(TmFundChannelMaintainController.class);
    private String prefix = "paychannel/tmFundChannelMaintain";
	
	@Autowired
	private ITmFundChannelMaintainService tmFundChannelMaintainService;
	
	@RequiresPermissions("paychannel:tmFundChannelMaintain:view")
	@GetMapping()
	public String tmFundChannelMaintain()
	{
	    return prefix + "/tmFundChannelMaintain";
	}
	
	/**
	 * 查询资金渠道维护期列表
	 */
	@RequiresPermissions("paychannel:tmFundChannelMaintain:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(TmFundChannelMaintain tmFundChannelMaintain)
	{
		startPage();
        List<TmFundChannelMaintain> list = tmFundChannelMaintainService.selectTmFundChannelMaintainList(tmFundChannelMaintain);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出资金渠道维护期列表
	 */
	@RequiresPermissions("paychannel:tmFundChannelMaintain:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TmFundChannelMaintain tmFundChannelMaintain)
    {
    	List<TmFundChannelMaintain> list = tmFundChannelMaintainService.selectTmFundChannelMaintainList(tmFundChannelMaintain);
        ExcelUtil<TmFundChannelMaintain> util = new ExcelUtil<TmFundChannelMaintain>(TmFundChannelMaintain.class);
        return util.exportExcel(list, "tmFundChannelMaintain");
    }
	
	/**
	 * 新增资金渠道维护期
	 */
	@GetMapping("/add/{id}")
	public String add(@PathVariable("id") String id, ModelMap mmap)
	{
		mmap.put("fundChannelCode", id);
	    return prefix + "/add";
	}
	
	@GetMapping("/setChannelMaintenancePeriod/{id}")
	public String setChannelMaintenancePeriod(@PathVariable("id") String id, ModelMap mmap)
	{
		logger.info("获取资金渠道维护期->渠道编号：{}",id);
		mmap.put("fundChannelCode", id);
		TmFundChannelMaintain tmFundChannelMaintain = new TmFundChannelMaintain();
		tmFundChannelMaintain.setFundChannelCode(id);
		List<TmFundChannelMaintain> list = tmFundChannelMaintainService.selectTmFundChannelMaintainList(tmFundChannelMaintain);
		mmap.put("list", list);
		return prefix + "/fundChannelMaintain";
	}
	
	/**
	 * 新增保存资金渠道维护期
	 */
	@RequiresPermissions("paychannel:tmFundChannelMaintain:add")
	@Log(title = "资金渠道维护期", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(TmFundChannelMaintain tmFundChannelMaintain)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(tmFundChannelMaintainService.insertTmFundChannelMaintain(tmFundChannelMaintain));
	}

	/**
	 * 修改资金渠道维护期
	 */
	@GetMapping("/edit/{maintainId}")
	public String edit(@PathVariable("maintainId") Integer maintainId, ModelMap mmap)
	{
		TmFundChannelMaintain tmFundChannelMaintain = tmFundChannelMaintainService.selectTmFundChannelMaintainById(maintainId);
		mmap.put("tmFundChannelMaintain", tmFundChannelMaintain);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存资金渠道维护期
	 */
	@RequiresPermissions("paychannel:tmFundChannelMaintain:edit")
	@Log(title = "资金渠道维护期", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(TmFundChannelMaintain tmFundChannelMaintain)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(tmFundChannelMaintainService.updateTmFundChannelMaintain(tmFundChannelMaintain));
	}
	
	/**
	 * 删除资金渠道维护期
	 */
	@RequiresPermissions("paychannel:tmFundChannelMaintain:remove")
	@Log(title = "资金渠道维护期", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(tmFundChannelMaintainService.deleteTmFundChannelMaintainByIds(ids));
	}
	
}
