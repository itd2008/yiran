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
import com.yiran.paychannel.domain.TmApiNoMode;
import com.yiran.paychannel.domain.TmFundChannelApi;
import com.yiran.paychannel.service.ITmApiNoModeService;
import com.yiran.paychannel.service.ITmFundChannelApiService;
import com.yiran.framework.web.base.BaseController;
import com.yiran.common.page.TableDataInfo;
import com.yiran.common.base.AjaxResult;
import com.yiran.common.config.Global;
import com.yiran.common.utils.poi.ExcelUtil;

/**
 * 资金渠道接口 信息操作处理
 * 
 * @author yiran
 * @date 2019-04-19
 */
@Controller
@RequestMapping("/paychannel/tmFundChannelApi")
public class TmFundChannelApiController extends BaseController
{
	private Logger        logger = LoggerFactory.getLogger(TmFundChannelApiController.class);
    private String prefix = "paychannel/tmFundChannelApi";
	
	@Autowired
	private ITmFundChannelApiService tmFundChannelApiService;
	@Autowired
	private ITmApiNoModeService tmApiNoModeService;
	
	@RequiresPermissions("paychannel:tmFundChannelApi:view")
	@GetMapping()
	public String tmFundChannelApi()
	{
	    return prefix + "/tmFundChannelApi";
	}
	
	/**
	 * 查询资金渠道接口列表
	 */
	@RequiresPermissions("paychannel:tmFundChannelApi:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(TmFundChannelApi tmFundChannelApi)
	{
		startPage();
        List<TmFundChannelApi> list = tmFundChannelApiService.selectTmFundChannelApiList(tmFundChannelApi);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出资金渠道接口列表
	 */
	@RequiresPermissions("paychannel:tmFundChannelApi:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TmFundChannelApi tmFundChannelApi)
    {
    	List<TmFundChannelApi> list = tmFundChannelApiService.selectTmFundChannelApiList(tmFundChannelApi);
        ExcelUtil<TmFundChannelApi> util = new ExcelUtil<TmFundChannelApi>(TmFundChannelApi.class);
        return util.exportExcel(list, "tmFundChannelApi");
    }
	
	/**
	 * 新增资金渠道接口
	 */
	@GetMapping("/add/{id}")
	public String add(@PathVariable("id") String id, ModelMap mmap)
	{
		mmap.put("fundChannelCode", id);
		List<TmApiNoMode> apiNoModeList = tmApiNoModeService.selectTmApiNoModeList(new TmApiNoMode());
		mmap.put("apiNoModeList", apiNoModeList);
	    return prefix + "/add";
	}
	
	@GetMapping("/setChannelApi/{id}")
	public String setChannelApi(@PathVariable("id") String id, ModelMap mmap)
	{
		logger.info("获取 资金渠道接口->渠道编号：{}",id);
		mmap.put("fundChannelCode", id);
		TmFundChannelApi tmFundChannelApi = new TmFundChannelApi();
		tmFundChannelApi.setFundChannelCode(id);
		List<TmFundChannelApi> list = tmFundChannelApiService.selectTmFundChannelApiList(tmFundChannelApi);
		mmap.put("list", list);
	    return prefix + "/fundChannelApi";
	}
	
	/**
	 * 新增保存资金渠道接口
	 */
	@RequiresPermissions("paychannel:tmFundChannelApi:add")
	@Log(title = "资金渠道接口", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(TmFundChannelApi tmFundChannelApi)
	{	
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		StringBuffer sb = new StringBuffer();
		String apiCode = tmFundChannelApi.getApiCode();
		sb.append(apiCode);
		sb.append("-");
		sb.append(tmFundChannelApi.getApiType());
		logger.info("资金渠道接口->ApiCode:{}",sb.toString());
		tmFundChannelApi.setApiCode(sb.toString());
		return toAjax(tmFundChannelApiService.insertTmFundChannelApi(tmFundChannelApi));
	}

	/**
	 * 修改资金渠道接口
	 */
	@GetMapping("/edit/{apiCode}")
	public String edit(@PathVariable("apiCode") String apiCode, ModelMap mmap)
	{
		TmFundChannelApi tmFundChannelApi = tmFundChannelApiService.selectTmFundChannelApiById(apiCode);
		mmap.put("tmFundChannelApi", tmFundChannelApi);
		List<TmApiNoMode> apiNoModeList = tmApiNoModeService.selectTmApiNoModeList(new TmApiNoMode());
		mmap.put("apiNoModeList", apiNoModeList);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存资金渠道接口
	 */
	@RequiresPermissions("paychannel:tmFundChannelApi:edit")
	@Log(title = "资金渠道接口", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(TmFundChannelApi tmFundChannelApi)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(tmFundChannelApiService.updateTmFundChannelApi(tmFundChannelApi));
	}
	
	/**
	 * 删除资金渠道接口
	 */
	@RequiresPermissions("paychannel:tmFundChannelApi:remove")
	@Log(title = "资金渠道接口", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(tmFundChannelApiService.deleteTmFundChannelApiByIds(ids));
	}
	
}
