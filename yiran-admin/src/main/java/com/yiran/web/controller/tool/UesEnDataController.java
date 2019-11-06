package com.yiran.web.controller.tool;

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
import com.yiran.common.enums.EncryptType;
import com.yiran.framework.web.base.BaseController;
import com.yiran.system.domain.UesEnData;
import com.yiran.system.service.IUesEnDataService;
import com.yiran.system.service.UesServiceClient;

import cn.hutool.json.JSONUtil;

import com.yiran.common.page.TableDataInfo;
import com.yiran.common.base.AjaxResult;
import com.yiran.common.config.Global;
import com.yiran.common.utils.poi.ExcelUtil;

/**
 * 加密 信息操作处理
 * 
 * @author yiran
 * @date 2019-03-20
 */
@Controller
@RequestMapping("/tool/uesEnData")
public class UesEnDataController extends BaseController
{
	private Logger    logger = LoggerFactory.getLogger(getClass());
    private String prefix = "tool/uesEnData";
	
	@Autowired
	private IUesEnDataService uesEnDataService;
	@Autowired
	private UesServiceClient uesServiceClient;
	@RequiresPermissions("tool:uesEnData:view")
	@GetMapping()
	public String uesEnData()
	{
	    return prefix + "/uesEnData";
	}
	
	/**
	 * 查询加密列表
	 */
	@RequiresPermissions("tool:uesEnData:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(UesEnData uesEnData)
	{
		startPage();
        List<UesEnData> list = uesEnDataService.selectUesEnDataList(uesEnData);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出加密列表
	 */
	@RequiresPermissions("tool:uesEnData:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(UesEnData uesEnData)
    {
    	List<UesEnData> list = uesEnDataService.selectUesEnDataList(uesEnData);
        ExcelUtil<UesEnData> util = new ExcelUtil<UesEnData>(UesEnData.class);
        return util.exportExcel(list, "uesEnData");
    }
	
	/**
	 * 加密页面
	 */
	@GetMapping("/encrypt")
	@RequiresPermissions("tool:uesEnData:view")
	public String encrypt()
	{
	    return prefix + "/encrypt";
	}
	
	/**
	 * 加密
	 * @throws Exception 
	 */
	@PostMapping("/encrypt")
	@ResponseBody
	public AjaxResult doEncrypt(UesEnData uesEnData)
	{	
		
		logger.info("加密原文:{}",JSONUtil.toJsonStr(uesEnData));
		String ticket = "";
		try {
			ticket = uesServiceClient.encryptData(uesEnData.getOriginalText(), EncryptType.AES);
		} catch (Exception e) {
			AjaxResult.error("调用UES加密异常");
		}
		logger.info("加密后ticket:{}",ticket);
		 //加密具体操作
		 return AjaxResult.success("加密成功!",ticket);
	}

	/**
	 * 解密
	 */
	@GetMapping("/decrypt")
	@RequiresPermissions("tool:uesEnData:view")
	public String decrypt()
	{
	    return prefix + "/decrypt";
	}
	
	/**
	 * 解密
	 */
	@PostMapping("/decrypt")
	@ResponseBody
	public AjaxResult doDecrypt(UesEnData uesEnData)
	{	
		logger.info("票据ticket:{}",JSONUtil.toJsonStr(uesEnData));
		String originalText = uesServiceClient.getDataByTicket(uesEnData.getTicket(), EncryptType.AES);
		logger.info("根据票据ticket:{},解密原文:{}",uesEnData.getTicket(),originalText);
		//解密具体操作
		 return AjaxResult.success("解密成功!",originalText);
	}
	
	/**
	 * 删除加密
	 */
	@RequiresPermissions("tool:uesEnData:remove")
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(uesEnDataService.deleteUesEnDataByIds(ids));
	}
	
}
