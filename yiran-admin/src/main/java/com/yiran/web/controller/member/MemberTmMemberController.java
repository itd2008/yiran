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
import com.yiran.member.domain.MemberTmMember;
import com.yiran.member.service.IMemberTmMemberService;
import com.yiran.framework.web.base.BaseController;
import com.yiran.common.page.TableDataInfo;
import com.yiran.common.base.AjaxResult;
import com.yiran.common.config.Global;
import com.yiran.common.utils.poi.ExcelUtil;

/**
 * 会员 信息操作处理
 * 
 * @author yiran
 * @date 2019-03-30
 */
@Controller
@RequestMapping("/member/memberTmMember")
public class MemberTmMemberController extends BaseController
{
    private String prefix = "member/memberTmMember";
	
	@Autowired
	private IMemberTmMemberService memberTmMemberService;
	
	@RequiresPermissions("member:memberTmMember:view")
	@GetMapping()
	public String memberTmMember()
	{
	    return prefix + "/memberTmMember";
	}
	
	/**
	 * 查询会员列表
	 */
	@RequiresPermissions("member:memberTmMember:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(MemberTmMember memberTmMember)
	{
		startPage();
        List<MemberTmMember> list = memberTmMemberService.selectMemberTmMemberList(memberTmMember);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出会员列表
	 */
	@RequiresPermissions("member:memberTmMember:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(MemberTmMember memberTmMember)
    {
    	List<MemberTmMember> list = memberTmMemberService.selectMemberTmMemberList(memberTmMember);
        ExcelUtil<MemberTmMember> util = new ExcelUtil<MemberTmMember>(MemberTmMember.class);
        return util.exportExcel(list, "memberTmMember");
    }
	
	/**
	 * 新增会员
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存会员
	 */
	@RequiresPermissions("member:memberTmMember:add")
	@Log(title = "会员", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(MemberTmMember memberTmMember)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(memberTmMemberService.insertMemberTmMember(memberTmMember));
	}

	/**
	 * 修改会员
	 */
	@GetMapping("/edit/{memberId}")
	public String edit(@PathVariable("memberId") String memberId, ModelMap mmap)
	{
		MemberTmMember memberTmMember = memberTmMemberService.selectMemberTmMemberById(memberId);
		mmap.put("memberTmMember", memberTmMember);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存会员
	 */
	@RequiresPermissions("member:memberTmMember:edit")
	@Log(title = "会员", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(MemberTmMember memberTmMember)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(memberTmMemberService.updateMemberTmMember(memberTmMember));
	}
	
	/**
	 * 删除会员
	 */
	@RequiresPermissions("member:memberTmMember:remove")
	@Log(title = "会员", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(memberTmMemberService.deleteMemberTmMemberByIds(ids));
	}
	
}
