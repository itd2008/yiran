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
import com.yiran.member.domain.MemberTmMemberIdentity;
import com.yiran.member.service.IMemberTmMemberIdentityService;
import com.yiran.framework.web.base.BaseController;
import com.yiran.common.page.TableDataInfo;
import com.yiran.common.base.AjaxResult;
import com.yiran.common.config.Global;
import com.yiran.common.utils.poi.ExcelUtil;

/**
 * 会员标识 信息操作处理
 * 
 * @author yiran
 * @date 2019-03-31
 */
@Controller
@RequestMapping("/member/memberTmMemberIdentity")
public class MemberTmMemberIdentityController extends BaseController
{
    private String prefix = "member/memberTmMemberIdentity";
	
	@Autowired
	private IMemberTmMemberIdentityService memberTmMemberIdentityService;
	
	@RequiresPermissions("member:memberTmMemberIdentity:view")
	@GetMapping()
	public String memberTmMemberIdentity()
	{
	    return prefix + "/memberTmMemberIdentity";
	}
	
	/**
	 * 查询会员标识列表
	 */
	@RequiresPermissions("member:memberTmMemberIdentity:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(MemberTmMemberIdentity memberTmMemberIdentity)
	{
		startPage();
        List<MemberTmMemberIdentity> list = memberTmMemberIdentityService.selectMemberTmMemberIdentityList(memberTmMemberIdentity);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出会员标识列表
	 */
	@RequiresPermissions("member:memberTmMemberIdentity:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(MemberTmMemberIdentity memberTmMemberIdentity)
    {
    	List<MemberTmMemberIdentity> list = memberTmMemberIdentityService.selectMemberTmMemberIdentityList(memberTmMemberIdentity);
        ExcelUtil<MemberTmMemberIdentity> util = new ExcelUtil<MemberTmMemberIdentity>(MemberTmMemberIdentity.class);
        return util.exportExcel(list, "memberTmMemberIdentity");
    }
	
	/**
	 * 新增会员标识
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存会员标识
	 */
	@RequiresPermissions("member:memberTmMemberIdentity:add")
	@Log(title = "会员标识", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(MemberTmMemberIdentity memberTmMemberIdentity)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(memberTmMemberIdentityService.insertMemberTmMemberIdentity(memberTmMemberIdentity));
	}

	/**
	 * 修改会员标识
	 */
	@GetMapping("/edit/{memberId}")
	public String edit(@PathVariable("memberId") String memberId, ModelMap mmap)
	{
		MemberTmMemberIdentity memberTmMemberIdentity = memberTmMemberIdentityService.selectMemberTmMemberIdentityById(memberId);
		mmap.put("memberTmMemberIdentity", memberTmMemberIdentity);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存会员标识
	 */
	@RequiresPermissions("member:memberTmMemberIdentity:edit")
	@Log(title = "会员标识", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(MemberTmMemberIdentity memberTmMemberIdentity)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(memberTmMemberIdentityService.updateMemberTmMemberIdentity(memberTmMemberIdentity));
	}
	
	/**
	 * 删除会员标识
	 */
	@RequiresPermissions("member:memberTmMemberIdentity:remove")
	@Log(title = "会员标识", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(memberTmMemberIdentityService.deleteMemberTmMemberIdentityByIds(ids));
	}
	
}
