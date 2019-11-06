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
import com.yiran.member.domain.MemberTrCompanyMember;
import com.yiran.member.service.IMemberTrCompanyMemberService;
import com.yiran.framework.web.base.BaseController;
import com.yiran.common.page.TableDataInfo;
import com.yiran.common.base.AjaxResult;
import com.yiran.common.config.Global;
import com.yiran.common.utils.poi.ExcelUtil;

/**
 * 企业会员 信息操作处理
 * 
 * @author yiran
 * @date 2019-03-30
 */
@Controller
@RequestMapping("/member/memberTrCompanyMember")
public class MemberTrCompanyMemberController extends BaseController
{
    private String prefix = "member/memberTrCompanyMember";
	
	@Autowired
	private IMemberTrCompanyMemberService memberTrCompanyMemberService;
	
	@RequiresPermissions("member:memberTrCompanyMember:view")
	@GetMapping()
	public String memberTrCompanyMember()
	{
	    return prefix + "/memberTrCompanyMember";
	}
	
	/**
	 * 查询企业会员列表
	 */
	@RequiresPermissions("member:memberTrCompanyMember:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(MemberTrCompanyMember memberTrCompanyMember)
	{
		startPage();
        List<MemberTrCompanyMember> list = memberTrCompanyMemberService.selectMemberTrCompanyMemberList(memberTrCompanyMember);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出企业会员列表
	 */
	@RequiresPermissions("member:memberTrCompanyMember:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(MemberTrCompanyMember memberTrCompanyMember)
    {
    	List<MemberTrCompanyMember> list = memberTrCompanyMemberService.selectMemberTrCompanyMemberList(memberTrCompanyMember);
        ExcelUtil<MemberTrCompanyMember> util = new ExcelUtil<MemberTrCompanyMember>(MemberTrCompanyMember.class);
        return util.exportExcel(list, "memberTrCompanyMember");
    }
	
	/**
	 * 新增企业会员
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存企业会员
	 */
	@RequiresPermissions("member:memberTrCompanyMember:add")
	@Log(title = "企业会员", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(MemberTrCompanyMember memberTrCompanyMember)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(memberTrCompanyMemberService.insertMemberTrCompanyMember(memberTrCompanyMember));
	}

	/**
	 * 修改企业会员
	 */
	@GetMapping("/edit/{memberId}")
	public String edit(@PathVariable("memberId") String memberId, ModelMap mmap)
	{
		MemberTrCompanyMember memberTrCompanyMember = memberTrCompanyMemberService.selectMemberTrCompanyMemberById(memberId);
		mmap.put("memberTrCompanyMember", memberTrCompanyMember);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存企业会员
	 */
	@RequiresPermissions("member:memberTrCompanyMember:edit")
	@Log(title = "企业会员", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(MemberTrCompanyMember memberTrCompanyMember)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(memberTrCompanyMemberService.updateMemberTrCompanyMember(memberTrCompanyMember));
	}
	
	/**
	 * 删除企业会员
	 */
	@RequiresPermissions("member:memberTrCompanyMember:remove")
	@Log(title = "企业会员", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(memberTrCompanyMemberService.deleteMemberTrCompanyMemberByIds(ids));
	}
	
}
