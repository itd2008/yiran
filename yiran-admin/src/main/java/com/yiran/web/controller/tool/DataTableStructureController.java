package com.yiran.web.controller.tool;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yiran.framework.web.base.BaseController;
/**
 * 数据表结构
 * @author pandaa
 *
 */
@Controller
@RequestMapping("/tool/dataTableStructure")
public class DataTableStructureController extends BaseController{
	private Logger    logger = LoggerFactory.getLogger(getClass());
    private String prefix = "tool/dataTableStructure";
    
    @GetMapping("/timingTasks")
	public String timingTasks()
	{
	    return prefix + "/timingTasks";
	}
    
    @GetMapping("/memberManagement")
	public String memberManagement()
	{
	    return prefix + "/memberManagement";
	}
    
    @GetMapping("/provincialAndUrbanStreets")
	public String provincialAndUrbanStreets()
	{
	    return prefix + "/provincialAndUrbanStreets";
	}
    
    @GetMapping("/wechatPublicNumber")
	public String wechatPublicNumber()
	{
	    return prefix + "/wechatPublicNumber";
	}
    
    @GetMapping("/wechatMall")
	public String wechatMall()
	{
	    return prefix + "/wechatMall";
	}
    
    @GetMapping("/systemManagement")
	public String systemManagement()
	{
	    return prefix + "/systemManagement";
	}
    @GetMapping("/paymentChannels")
    public String paymentChannels()
    {
    	return prefix + "/paymentChannels";
    }
    
    
}
