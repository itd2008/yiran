package com.yiran.web.controller.amqp;

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
import com.yiran.amqp.domain.MqExchanges;
import com.yiran.amqp.domain.MqQueue;
import com.yiran.amqp.service.IMqExchangesService;
import com.yiran.amqp.service.IMqQueueService;
import com.yiran.framework.web.base.BaseController;
import com.yiran.common.page.TableDataInfo;
import com.yiran.common.base.AjaxResult;
import com.yiran.common.config.Global;
import com.yiran.common.utils.poi.ExcelUtil;

/**
 * RabbitMQ队列 信息操作处理
 * 
 * @author yiran
 * @date 2019-04-28
 */
@Controller
@RequestMapping("/amqp/mqQueue")
public class MqQueueController extends BaseController
{
    private String prefix = "amqp/mqQueue";
	
	@Autowired
	private IMqQueueService mqQueueService;
	@Autowired
	private IMqExchangesService mqExchangesService;
	
	@RequiresPermissions("amqp:mqQueue:view")
	@GetMapping()
	public String mqQueue()
	{
	    return prefix + "/mqQueue";
	}
	
	/**
	 * 查询RabbitMQ队列列表
	 */
	@RequiresPermissions("amqp:mqQueue:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(MqQueue mqQueue)
	{
		startPage();
        List<MqQueue> list = mqQueueService.selectMqQueueList(mqQueue);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出RabbitMQ队列列表
	 */
	@RequiresPermissions("amqp:mqQueue:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(MqQueue mqQueue)
    {
    	List<MqQueue> list = mqQueueService.selectMqQueueList(mqQueue);
        ExcelUtil<MqQueue> util = new ExcelUtil<MqQueue>(MqQueue.class);
        return util.exportExcel(list, "mqQueue");
    }
	
	/**
	 * 新增RabbitMQ队列
	 */
	@GetMapping("/add")
	public String add(ModelMap mmap)
	{
		List<MqExchanges> list = mqExchangesService.selectAllMqExchangesList();
		mmap.put("exchangesList", list);
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存RabbitMQ队列
	 */
	@RequiresPermissions("amqp:mqQueue:add")
	@Log(title = "RabbitMQ队列", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(MqQueue mqQueue)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(mqQueueService.insertMqQueue(mqQueue));
	}

	/**
	 * 修改RabbitMQ队列
	 */
	@GetMapping("/edit/{queueId}")
	public String edit(@PathVariable("queueId") Integer queueId, ModelMap mmap)
	{
		MqQueue mqQueue = mqQueueService.selectMqQueueById(queueId);
		mmap.put("mqQueue", mqQueue);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存RabbitMQ队列
	 */
	@RequiresPermissions("amqp:mqQueue:edit")
	@Log(title = "RabbitMQ队列", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(MqQueue mqQueue)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(mqQueueService.updateMqQueue(mqQueue));
	}
	
	/**
	 * 删除RabbitMQ队列
	 */
	@RequiresPermissions("amqp:mqQueue:remove")
	@Log(title = "RabbitMQ队列", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(mqQueueService.deleteMqQueueByIds(ids));
	}
	
}
