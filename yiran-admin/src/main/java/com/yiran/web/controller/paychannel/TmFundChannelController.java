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
import com.yiran.paychannel.domain.TmFundChannel;
import com.yiran.paychannel.domain.TmFundChannelInst;
import com.yiran.paychannel.service.ITmFundChannelInstService;
import com.yiran.paychannel.service.ITmFundChannelService;

import cn.hutool.core.date.DateUtil;

import com.yiran.framework.web.base.BaseController;
import com.yiran.common.page.TableDataInfo;
import com.yiran.common.base.AjaxResult;
import com.yiran.common.config.Global;
import com.yiran.common.utils.poi.ExcelUtil;

/**
 * 资金渠道 信息操作处理
 * 
 * @author yiran
 * @date 2019-04-19
 */
@Controller
@RequestMapping("/paychannel/tmFundChannel")
public class TmFundChannelController extends BaseController
{
    private String prefix = "paychannel/tmFundChannel";
	
	@Autowired
	private ITmFundChannelService tmFundChannelService;
	
	@Autowired
	private ITmFundChannelInstService tmFundChannelInstService;
	
	@RequiresPermissions("paychannel:tmFundChannel:view")
	@GetMapping()
	public String tmFundChannel()
	{
	    return prefix + "/tmFundChannel";
	}
	
	/**
	 * 查询资金渠道列表
	 */
	@RequiresPermissions("paychannel:tmFundChannel:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(TmFundChannel tmFundChannel)
	{
		startPage();
        List<TmFundChannel> list = tmFundChannelService.selectTmFundChannelList(tmFundChannel);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出资金渠道列表
	 */
	@RequiresPermissions("paychannel:tmFundChannel:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TmFundChannel tmFundChannel)
    {
    	List<TmFundChannel> list = tmFundChannelService.selectTmFundChannelList(tmFundChannel);
        ExcelUtil<TmFundChannel> util = new ExcelUtil<TmFundChannel>(TmFundChannel.class);
        return util.exportExcel(list, "tmFundChannel");
    }
	
	/**
	 * 新增资金渠道
	 */
	@GetMapping("/add")
	public String add(ModelMap mmap)
	{
		List<TmFundChannelInst> fundChannelInstList = tmFundChannelInstService.selectTmFundChannelInstList(new TmFundChannelInst());
		mmap.put("fundChannelInstList", fundChannelInstList);
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存资金渠道
	 */
	@RequiresPermissions("paychannel:tmFundChannel:add")
	@Log(title = "资金渠道", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(TmFundChannel tmFundChannel)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(tmFundChannelService.insertTmFundChannel(tmFundChannel));
	}

	/**
	 * 修改资金渠道
	 */
	@GetMapping("/edit/{fundChannelCode}")
	public String edit(@PathVariable("fundChannelCode") String fundChannelCode, ModelMap mmap)
	{
		TmFundChannel tmFundChannel = tmFundChannelService.selectTmFundChannelById(fundChannelCode);
		mmap.put("tmFundChannel", tmFundChannel);
		List<TmFundChannelInst> fundChannelInstList = tmFundChannelInstService.selectTmFundChannelInstList(new TmFundChannelInst());
		mmap.put("fundChannelInstList", fundChannelInstList);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存资金渠道
	 */
	@RequiresPermissions("paychannel:tmFundChannel:edit")
	@Log(title = "资金渠道", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(TmFundChannel tmFundChannel)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(tmFundChannelService.updateTmFundChannel(tmFundChannel));
	}
	
	/**
	 * 删除资金渠道
	 */
	@RequiresPermissions("paychannel:tmFundChannel:remove")
	@Log(title = "资金渠道", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(tmFundChannelService.deleteTmFundChannelByIds(ids));
	}
	
}
