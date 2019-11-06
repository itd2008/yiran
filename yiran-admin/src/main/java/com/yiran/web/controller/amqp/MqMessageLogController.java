package com.yiran.web.controller.amqp;

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
import com.alibaba.fastjson.JSON;
import com.yiran.amqp.domain.AmqoRequrst;
import com.yiran.amqp.domain.MqMessageLog;
import com.yiran.amqp.service.IAmqpService;
import com.yiran.amqp.service.IMqMessageLogService;
import com.yiran.framework.web.base.BaseController;

import cn.hutool.core.lang.UUID;

import com.yiran.common.page.TableDataInfo;
import com.yiran.common.base.AjaxResult;
import com.yiran.common.config.Global;
import com.yiran.common.utils.poi.ExcelUtil;

/**
 * MQ消息记录 信息操作处理
 * 
 * @author yiran
 * @date 2019-06-12
 */
@Controller
@RequestMapping("/amqp/mqMessageLog")
public class MqMessageLogController extends BaseController
{
    private String prefix = "amqp/mqMessageLog";
	
	@Autowired
	private IMqMessageLogService mqMessageLogService;
	@Autowired
	private IAmqpService amqpService;
	@RequiresPermissions("amqp:mqMessageLog:view")
	@GetMapping()
	public String mqMessageLog()
	{
	    return prefix + "/mqMessageLog";
	}
	
	/**
	 * 查询MQ消息记录列表
	 */
	@RequiresPermissions("amqp:mqMessageLog:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(MqMessageLog mqMessageLog)
	{
		startPage();
        List<MqMessageLog> list = mqMessageLogService.selectMqMessageLogList(mqMessageLog);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出MQ消息记录列表
	 */
	@RequiresPermissions("amqp:mqMessageLog:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(MqMessageLog mqMessageLog)
    {
    	List<MqMessageLog> list = mqMessageLogService.selectMqMessageLogList(mqMessageLog);
        ExcelUtil<MqMessageLog> util = new ExcelUtil<MqMessageLog>(MqMessageLog.class);
        return util.exportExcel(list, "mqMessageLog");
    }
	
	/**
	 * 新增MQ消息记录
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存MQ消息记录
	 */
	@RequiresPermissions("amqp:mqMessageLog:add")
	@Log(title = "MQ消息记录", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(MqMessageLog mqMessageLog)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(mqMessageLogService.insertMqMessageLog(mqMessageLog));
	}

	/**
	 * 修改MQ消息记录
	 */
	@GetMapping("/edit/{messageId}")
	public String edit(@PathVariable("messageId") String messageId, ModelMap mmap)
	{
		MqMessageLog mqMessageLog = mqMessageLogService.selectMqMessageLogById(messageId);
		mmap.put("mqMessageLog", mqMessageLog);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改MQ消息记录
	 */
	@GetMapping("/humanIntervention/{id}")
	@Log(title = "人工介入MQ消息发送", businessType = BusinessType.OTHER)
	@ResponseBody
	public AjaxResult humanIntervention(@PathVariable("id") String id, ModelMap mmap)
	{
		MqMessageLog mqMessageLog = mqMessageLogService.selectMqMessageLogById(id);
		AmqoRequrst requrst = new AmqoRequrst();
    	requrst.setMessageId(mqMessageLog.getMessageId());
    	requrst.setExchange(mqMessageLog.getExchange());
    	requrst.setRoutingKey(mqMessageLog.getRoutingKey());
    	requrst.setSave(true);
    	Map<String,Object> mapData = JSON.parseObject(mqMessageLog.getMessage(),Map.class); 
    	requrst.setMap(mapData);
    	amqpService.sendMessage(requrst);
		return AjaxResult.success("消息发送成功!");
	}
	
	/**
	 * 修改保存MQ消息记录
	 */
	@RequiresPermissions("amqp:mqMessageLog:edit")
	@Log(title = "MQ消息记录", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(MqMessageLog mqMessageLog)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(mqMessageLogService.updateMqMessageLog(mqMessageLog));
	}
	
	/**
	 * 删除MQ消息记录
	 */
	@RequiresPermissions("amqp:mqMessageLog:remove")
	@Log(title = "MQ消息记录", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(mqMessageLogService.deleteMqMessageLogByIds(ids));
	}
	
}
