package com.yiran.web.controller.paychannel;

import java.util.ArrayList;
import java.util.Iterator;
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

import com.netfinworks.common.lang.StringUtil;
import com.yiran.common.annotation.Log;
import com.yiran.common.enums.BusinessType;
import com.yiran.paychannel.domain.TmFundChannel;
import com.yiran.paychannel.domain.TmFundChannelTargetInit;
import com.yiran.paychannel.domain.TrFcTargetInstRelation;
import com.yiran.paychannel.service.ITmFundChannelService;
import com.yiran.paychannel.service.ITmFundChannelTargetInitService;
import com.yiran.paychannel.service.ITrFcTargetInstRelationService;

import cn.hutool.json.JSONUtil;

import com.yiran.framework.web.base.BaseController;
import com.yiran.common.page.TableDataInfo;
import com.yiran.common.base.AjaxResult;
import com.yiran.common.config.Global;
import com.yiran.common.utils.poi.ExcelUtil;

/**
 * 目标机构 信息操作处理
 * 
 * @author yiran
 * @date 2019-04-19
 */
@Controller
@RequestMapping("/paychannel/trFcTargetInstRelation")
public class TrFcTargetInstRelationController extends BaseController
{
	private Logger        logger = LoggerFactory.getLogger(TrFcTargetInstRelationController.class);
    private String prefix = "paychannel/trFcTargetInstRelation";
    @Autowired
    private ITmFundChannelTargetInitService tmFundChannelTargetInitService;
	@Autowired
	private ITrFcTargetInstRelationService trFcTargetInstRelationService;
	
	@Autowired
	private ITmFundChannelService tmFundChannelService;
	
	@RequiresPermissions("paychannel:trFcTargetInstRelation:view")
	@GetMapping()
	public String trFcTargetInstRelation()
	{
	    return prefix + "/trFcTargetInstRelation";
	}
	
	/**
	 * 查询目标机构列表
	 */
	@RequiresPermissions("paychannel:trFcTargetInstRelation:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(TrFcTargetInstRelation trFcTargetInstRelation)
	{
		startPage();
        List<TrFcTargetInstRelation> list = trFcTargetInstRelationService.selectTrFcTargetInstRelationList(trFcTargetInstRelation);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出目标机构列表
	 */
	@RequiresPermissions("paychannel:trFcTargetInstRelation:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TrFcTargetInstRelation trFcTargetInstRelation)
    {
    	List<TrFcTargetInstRelation> list = trFcTargetInstRelationService.selectTrFcTargetInstRelationList(trFcTargetInstRelation);
        ExcelUtil<TrFcTargetInstRelation> util = new ExcelUtil<TrFcTargetInstRelation>(TrFcTargetInstRelation.class);
        return util.exportExcel(list, "trFcTargetInstRelation");
    }
	
	
	/**
	 * 新增目标机构
	 */
	@GetMapping("/setTargetInst/{id}")
	public String setTargetInst(@PathVariable("id") String id, ModelMap mmap)
	{
		logger.info("设置目标机构->渠道编号：{}",id);
		mmap.put("fundChannelCode", id);
		//根据渠道号获取渠道信息
		TmFundChannel tmFundChannel = tmFundChannelService.selectTmFundChannelById(id);
		String fundChannelName = tmFundChannel.getName();
		logger.info("设置目标机构->渠道编名称：{}",fundChannelName);
		mmap.put("fundChannelName", fundChannelName);
		//获取目标机构
		TmFundChannelTargetInit tmFundChannelTargetInit = new TmFundChannelTargetInit();
		List<TmFundChannelTargetInit> instList = tmFundChannelTargetInitService.selectTmFundChannelTargetInitList(tmFundChannelTargetInit);
		mmap.put("instList", instList);
		TrFcTargetInstRelation trFcTargetInstRelation = new TrFcTargetInstRelation();
		trFcTargetInstRelation.setFundChannelCode(id);
		List<TrFcTargetInstRelation> list = trFcTargetInstRelationService.selectTrFcTargetInstRelationList(trFcTargetInstRelation);
		mmap.put("targetInstRelationList", list);
	    return prefix + "/add";
	}
	/**
	 * 新增保存目标机构
	 */
	@RequiresPermissions("paychannel:trFcTargetInstRelation:add")
	@Log(title = "目标机构", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(TrFcTargetInstRelation trFcTargetInstRelation)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		
		logger.info("添加目标机构：{}",JSONUtil.toJsonStr(trFcTargetInstRelation));
		//获取
		String targetInstCode = trFcTargetInstRelation.getTargetInstCode();
		List<TrFcTargetInstRelation> list = new ArrayList<TrFcTargetInstRelation>();
		if(!StringUtil.isBlank(targetInstCode)){
			String[] splits = targetInstCode.split(",");
			if(splits.length >0){
				for (int i = 0; i < splits.length; i++) {
					TrFcTargetInstRelation fir =new TrFcTargetInstRelation();
					fir.setFundChannelCode(trFcTargetInstRelation.getFundChannelCode());
					fir.setTargetInstCode(splits[i]);
					list.add(fir);
				}
			}
		}
		return toAjax(trFcTargetInstRelationService.insertBatchTrFcTargetInstRelations(list));
	}

	/**
	 * 修改目标机构
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		TrFcTargetInstRelation trFcTargetInstRelation = trFcTargetInstRelationService.selectTrFcTargetInstRelationById(id);
		mmap.put("trFcTargetInstRelation", trFcTargetInstRelation);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存目标机构
	 */
	@RequiresPermissions("paychannel:trFcTargetInstRelation:edit")
	@Log(title = "目标机构", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(TrFcTargetInstRelation trFcTargetInstRelation)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(trFcTargetInstRelationService.updateTrFcTargetInstRelation(trFcTargetInstRelation));
	}
	
	/**
	 * 删除目标机构
	 */
	@RequiresPermissions("paychannel:trFcTargetInstRelation:remove")
	@Log(title = "目标机构", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(trFcTargetInstRelationService.deleteTrFcTargetInstRelationByIds(ids));
	}
	
}
