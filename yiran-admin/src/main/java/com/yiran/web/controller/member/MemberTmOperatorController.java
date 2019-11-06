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
import com.yiran.member.domain.MemberTmOperator;
import com.yiran.member.service.IMemberTmOperatorService;
import com.yiran.framework.web.base.BaseController;
import com.yiran.common.page.TableDataInfo;
import com.yiran.common.base.AjaxResult;
import com.yiran.common.config.Global;
import com.yiran.common.utils.poi.ExcelUtil;

/**
 * 操作员 信息操作处理
 * 
 * @author yiran
 * @date 2019-03-31
 */
@Controller
@RequestMapping("/member/memberTmOperator")
public class MemberTmOperatorController extends BaseController
{
    private String prefix = "member/memberTmOperator";
	
	@Autowired
	private IMemberTmOperatorService memberTmOperatorService;
	
	@RequiresPermissions("member:memberTmOperator:view")
	@GetMapping()
	public String memberTmOperator()
	{
	    return prefix + "/memberTmOperator";
	}
	
	/**
	 * 查询操作员列表
	 */
	@RequiresPermissions("member:memberTmOperator:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(MemberTmOperator memberTmOperator)
	{
		startPage();
        List<MemberTmOperator> list = memberTmOperatorService.selectMemberTmOperatorList(memberTmOperator);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出操作员列表
	 */
	@RequiresPermissions("member:memberTmOperator:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(MemberTmOperator memberTmOperator)
    {
    	List<MemberTmOperator> list = memberTmOperatorService.selectMemberTmOperatorList(memberTmOperator);
        ExcelUtil<MemberTmOperator> util = new ExcelUtil<MemberTmOperator>(MemberTmOperator.class);
        return util.exportExcel(list, "memberTmOperator");
    }
	
	/**
	 * 新增操作员
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存操作员
	 */
	@RequiresPermissions("member:memberTmOperator:add")
	@Log(title = "操作员", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(MemberTmOperator memberTmOperator)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(memberTmOperatorService.insertMemberTmOperator(memberTmOperator));
	}

	/**
	 * 修改操作员
	 */
	@GetMapping("/edit/{operatorId}")
	public String edit(@PathVariable("operatorId") String operatorId, ModelMap mmap)
	{
		MemberTmOperator memberTmOperator = memberTmOperatorService.selectMemberTmOperatorById(operatorId);
		mmap.put("memberTmOperator", memberTmOperator);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存操作员
	 */
	@RequiresPermissions("member:memberTmOperator:edit")
	@Log(title = "操作员", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(MemberTmOperator memberTmOperator)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(memberTmOperatorService.updateMemberTmOperator(memberTmOperator));
	}
	
	/**
	 * 删除操作员
	 */
	@RequiresPermissions("member:memberTmOperator:remove")
	@Log(title = "操作员", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(memberTmOperatorService.deleteMemberTmOperatorByIds(ids));
	}
	
}
