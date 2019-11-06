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
import com.yiran.paychannel.domain.TmFundChannelApi;
import com.yiran.paychannel.domain.TmFundChannelApiParam;
import com.yiran.paychannel.service.ITmFundChannelApiParamService;
import com.yiran.framework.web.base.BaseController;
import com.yiran.common.page.TableDataInfo;
import com.yiran.common.base.AjaxResult;
import com.yiran.common.config.Global;
import com.yiran.common.utils.poi.ExcelUtil;

/**
 * 渠道接口参数 信息操作处理
 * 
 * @author yiran
 * @date 2019-04-19
 */
@Controller
@RequestMapping("/paychannel/tmFundChannelApiParam")
public class TmFundChannelApiParamController extends BaseController
{
	private Logger        logger = LoggerFactory.getLogger(TmFundChannelApiParamController.class);
    private String prefix = "paychannel/tmFundChannelApiParam";
	
	@Autowired
	private ITmFundChannelApiParamService tmFundChannelApiParamService;
	
	@RequiresPermissions("paychannel:tmFundChannelApiParam:view")
	@GetMapping()
	public String tmFundChannelApiParam()
	{
	    return prefix + "/tmFundChannelApiParam";
	}
	
	/**
	 * 查询渠道接口参数列表
	 */
	@RequiresPermissions("paychannel:tmFundChannelApiParam:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(TmFundChannelApiParam tmFundChannelApiParam)
	{
		startPage();
        List<TmFundChannelApiParam> list = tmFundChannelApiParamService.selectTmFundChannelApiParamList(tmFundChannelApiParam);
		return getDataTable(list);
	}
	
	@GetMapping("/setChannelApiParam/{apiCode}")
	public String setChannelApiParam(@PathVariable("apiCode") String apiCode, ModelMap mmap)
	{
		logger.info("获取 资金渠道接口->渠道API编号：{}",apiCode);
		mmap.put("apiCode", apiCode);
		TmFundChannelApiParam tmFundChannelApiParam = new TmFundChannelApiParam();
		tmFundChannelApiParam.setApiCode(apiCode);
		List<TmFundChannelApiParam> list = tmFundChannelApiParamService.selectTmFundChannelApiParamList(tmFundChannelApiParam);
		mmap.put("list", list);
	    return prefix + "/fundChannelApiParam";
	}
	
	/**
	 * 导出渠道接口参数列表
	 */
	@RequiresPermissions("paychannel:tmFundChannelApiParam:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TmFundChannelApiParam tmFundChannelApiParam)
    {
    	List<TmFundChannelApiParam> list = tmFundChannelApiParamService.selectTmFundChannelApiParamList(tmFundChannelApiParam);
        ExcelUtil<TmFundChannelApiParam> util = new ExcelUtil<TmFundChannelApiParam>(TmFundChannelApiParam.class);
        return util.exportExcel(list, "tmFundChannelApiParam");
    }
	
	/**
	 * 新增渠道接口参数
	 */
	@GetMapping("/add/{apiCode}")
	public String add(@PathVariable("apiCode") String apiCode, ModelMap mmap)
	{
		mmap.put("apiCode", apiCode);
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存渠道接口参数
	 */
	@RequiresPermissions("paychannel:tmFundChannelApiParam:add")
	@Log(title = "渠道接口参数", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(TmFundChannelApiParam tmFundChannelApiParam)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(tmFundChannelApiParamService.insertTmFundChannelApiParam(tmFundChannelApiParam));
	}

	/**
	 * 修改渠道接口参数
	 */
	@GetMapping("/edit/{paramId}")
	public String edit(@PathVariable("paramId") Integer paramId, ModelMap mmap)
	{
		TmFundChannelApiParam tmFundChannelApiParam = tmFundChannelApiParamService.selectTmFundChannelApiParamById(paramId);
		mmap.put("tmFundChannelApiParam", tmFundChannelApiParam);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存渠道接口参数
	 */
	@RequiresPermissions("paychannel:tmFundChannelApiParam:edit")
	@Log(title = "渠道接口参数", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(TmFundChannelApiParam tmFundChannelApiParam)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(tmFundChannelApiParamService.updateTmFundChannelApiParam(tmFundChannelApiParam));
	}
	
	/**
	 * 删除渠道接口参数
	 */
	@RequiresPermissions("paychannel:tmFundChannelApiParam:remove")
	@Log(title = "渠道接口参数", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(tmFundChannelApiParamService.deleteTmFundChannelApiParamByIds(ids));
	}
	
}
