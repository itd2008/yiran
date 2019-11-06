package com.yiran.web.controller.message;

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
import com.yiran.message.domain.NotifyMsgLog;
import com.yiran.message.service.INotifyMsgLogService;
import com.yiran.framework.web.base.BaseController;
import com.yiran.common.page.TableDataInfo;
import com.yiran.common.base.AjaxResult;
import com.yiran.common.config.Global;
import com.yiran.common.utils.poi.ExcelUtil;

/**
 * 消息通知日志 信息操作处理
 * 
 * @author yiran
 * @date 2019-03-08
 */
@Controller
@RequestMapping("/message/notifyMsgLog")
public class NotifyMsgLogController extends BaseController
{
    private String prefix = "message/notifyMsgLog";
	
	@Autowired
	private INotifyMsgLogService notifyMsgLogService;
	
	@RequiresPermissions("message:notifyMsgLog:view")
	@GetMapping()
	public String notifyMsgLog()
	{
	    return prefix + "/notifyMsgLog";
	}
	
	/**
	 * 查询消息通知日志列表
	 */
	@RequiresPermissions("message:notifyMsgLog:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(NotifyMsgLog notifyMsgLog)
	{
		startPage();
        List<NotifyMsgLog> list = notifyMsgLogService.selectNotifyMsgLogList(notifyMsgLog);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出消息通知日志列表
	 */
	@RequiresPermissions("message:notifyMsgLog:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(NotifyMsgLog notifyMsgLog)
    {
    	List<NotifyMsgLog> list = notifyMsgLogService.selectNotifyMsgLogList(notifyMsgLog);
        ExcelUtil<NotifyMsgLog> util = new ExcelUtil<NotifyMsgLog>(NotifyMsgLog.class);
        return util.exportExcel(list, "notifyMsgLog");
    }
	
	/**
	 * 新增消息通知日志
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存消息通知日志
	 */
	@RequiresPermissions("message:notifyMsgLog:add")
	@Log(title = "消息通知日志", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(NotifyMsgLog notifyMsgLog)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(notifyMsgLogService.insertNotifyMsgLog(notifyMsgLog));
	}

	/**
	 * 修改消息通知日志
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		NotifyMsgLog notifyMsgLog = notifyMsgLogService.selectNotifyMsgLogById(id);
		mmap.put("notifyMsgLog", notifyMsgLog);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存消息通知日志
	 */
	@RequiresPermissions("message:notifyMsgLog:edit")
	@Log(title = "消息通知日志", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(NotifyMsgLog notifyMsgLog)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(notifyMsgLogService.updateNotifyMsgLog(notifyMsgLog));
	}
	
	/**
	 * 删除消息通知日志
	 */
	@RequiresPermissions("message:notifyMsgLog:remove")
	@Log(title = "消息通知日志", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(notifyMsgLogService.deleteNotifyMsgLogByIds(ids));
	}
	
}
