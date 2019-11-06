package com.yiran.web.controller.member;

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
import com.yiran.member.domain.MemberTdConfig;
import com.yiran.member.service.IMemberTdConfigService;
import com.yiran.framework.web.base.BaseController;
import com.yiran.common.page.TableDataInfo;
import com.yiran.common.base.AjaxResult;
import com.yiran.common.config.Global;
import com.yiran.common.utils.poi.ExcelUtil;

/**
 * 配置 信息操作处理
 * 
 * @author yiran
 * @date 2019-03-30
 */
@Controller
@RequestMapping("/member/memberTdConfig")
public class MemberTdConfigController extends BaseController
{
    private String prefix = "member/memberTdConfig";
	
	@Autowired
	private IMemberTdConfigService memberTdConfigService;
	
	@RequiresPermissions("member:memberTdConfig:view")
	@GetMapping()
	public String memberTdConfig()
	{
	    return prefix + "/memberTdConfig";
	}
	
	/**
	 * 查询配置列表
	 */
	@RequiresPermissions("member:memberTdConfig:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(MemberTdConfig memberTdConfig)
	{
		startPage();
        List<MemberTdConfig> list = memberTdConfigService.selectMemberTdConfigList(memberTdConfig);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出配置列表
	 */
	@RequiresPermissions("member:memberTdConfig:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(MemberTdConfig memberTdConfig)
    {
    	List<MemberTdConfig> list = memberTdConfigService.selectMemberTdConfigList(memberTdConfig);
        ExcelUtil<MemberTdConfig> util = new ExcelUtil<MemberTdConfig>(MemberTdConfig.class);
        return util.exportExcel(list, "memberTdConfig");
    }
	
	/**
	 * 新增配置
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存配置
	 */
	@RequiresPermissions("member:memberTdConfig:add")
	@Log(title = "配置", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(MemberTdConfig memberTdConfig)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(memberTdConfigService.insertMemberTdConfig(memberTdConfig));
	}

	/**
	 * 修改配置
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		MemberTdConfig memberTdConfig = memberTdConfigService.selectMemberTdConfigById(id);
		mmap.put("memberTdConfig", memberTdConfig);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存配置
	 */
	@RequiresPermissions("member:memberTdConfig:edit")
	@Log(title = "配置", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(MemberTdConfig memberTdConfig)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(memberTdConfigService.updateMemberTdConfig(memberTdConfig));
	}
	
	/**
	 * 删除配置
	 */
	@RequiresPermissions("member:memberTdConfig:remove")
	@Log(title = "配置", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(memberTdConfigService.deleteMemberTdConfigByIds(ids));
	}
	
}
