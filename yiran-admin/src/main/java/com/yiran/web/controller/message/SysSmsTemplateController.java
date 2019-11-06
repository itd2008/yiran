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
import com.yiran.message.domain.SendAuthCodeRequest;
import com.yiran.message.domain.SysSmsTemplate;
import com.yiran.message.service.ISmsSendService;
import com.yiran.message.service.ISysSmsTemplateService;
import com.yiran.framework.web.base.BaseController;
import com.yiran.common.page.TableDataInfo;
import com.yiran.common.base.AjaxResult;
import com.yiran.common.config.Global;
import com.yiran.common.utils.poi.ExcelUtil;

/**
 * 短信模板 信息操作处理
 * 
 * @author yiran
 * @date 2019-03-08
 */
@Controller
@RequestMapping("/message/sysSmsTemplate")
public class SysSmsTemplateController extends BaseController
{
    private String prefix = "message/sysSmsTemplate";
	
	@Autowired
	private ISysSmsTemplateService sysSmsTemplateService;
	@Autowired
	private ISmsSendService smsSendService;
	
	@RequiresPermissions("message:sysSmsTemplate:view")
	@GetMapping()
	public String sysSmsTemplate()
	{
	    return prefix + "/sysSmsTemplate";
	}
	
	/**
	 * 查询短信模板列表
	 */
	@RequiresPermissions("message:sysSmsTemplate:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(SysSmsTemplate sysSmsTemplate)
	{
		startPage();
        List<SysSmsTemplate> list = sysSmsTemplateService.selectSysSmsTemplateList(sysSmsTemplate);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出短信模板列表
	 */
	@RequiresPermissions("message:sysSmsTemplate:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SysSmsTemplate sysSmsTemplate)
    {
    	List<SysSmsTemplate> list = sysSmsTemplateService.selectSysSmsTemplateList(sysSmsTemplate);
        ExcelUtil<SysSmsTemplate> util = new ExcelUtil<SysSmsTemplate>(SysSmsTemplate.class);
        return util.exportExcel(list, "sysSmsTemplate");
    }
	
	/**
	 * 新增短信模板
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存短信模板
	 */
	@RequiresPermissions("message:sysSmsTemplate:add")
	@Log(title = "短信模板", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(SysSmsTemplate sysSmsTemplate)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(sysSmsTemplateService.insertSysSmsTemplate(sysSmsTemplate));
	}

	/**
	 * 修改短信模板
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		SysSmsTemplate sysSmsTemplate = sysSmsTemplateService.selectSysSmsTemplateById(id);
		mmap.put("sysSmsTemplate", sysSmsTemplate);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存短信模板
	 */
	@RequiresPermissions("message:sysSmsTemplate:edit")
	@Log(title = "短信模板", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(SysSmsTemplate sysSmsTemplate)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(sysSmsTemplateService.updateSysSmsTemplate(sysSmsTemplate));
	}
	
	/**
	 * 删除短信模板
	 */
	@RequiresPermissions("message:sysSmsTemplate:remove")
	@Log(title = "短信模板", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(sysSmsTemplateService.deleteSysSmsTemplateByIds(ids));
	}
	
	@PostMapping("/checkTemplateIdUnique")
    @ResponseBody
    public String checkTemplateIdUnique(SysSmsTemplate smsTemplate)
    {
        String uniqueFlag = "0";
        SysSmsTemplate template = sysSmsTemplateService.selectSmsTemplateByTemplateId(smsTemplate.getTemplateId());
        if(template!=null){
        	uniqueFlag = "1";
        }
        return uniqueFlag;
    }
	
	@RequiresPermissions("sms:smsTemplate:sendSms")
	@GetMapping("/sendSms")
	public String sendSms()
	{
	    return prefix + "/sendSms";
	}
	
	@RequiresPermissions("sms:smsTemplate:send")
	@Log(title = "发送短信", businessType = BusinessType.INSERT)
	@PostMapping("/send")
	@ResponseBody
	public AjaxResult send(SendAuthCodeRequest request)
	{
		return smsSendService.sendAuthCode(request);
	}
	
	@RequiresPermissions("sms:smsTemplate:verifyCode")
	@Log(title = "短信验证码验证", businessType = BusinessType.INSERT)
	@PostMapping("/verifyCode")
	@ResponseBody
	public AjaxResult verifyCode(SendAuthCodeRequest request)
	{
		return smsSendService.verifyMobileAuthCode(request);
	}
}
