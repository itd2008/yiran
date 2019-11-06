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
import com.yiran.member.domain.MemberTmContact;
import com.yiran.member.service.IMemberTmContactService;
import com.yiran.framework.web.base.BaseController;
import com.yiran.common.page.TableDataInfo;
import com.yiran.common.base.AjaxResult;
import com.yiran.common.config.Global;
import com.yiran.common.utils.poi.ExcelUtil;

/**
 * 联系人 信息操作处理
 * 
 * @author yiran
 * @date 2019-03-30
 */
@Controller
@RequestMapping("/member/memberTmContact")
public class MemberTmContactController extends BaseController
{
    private String prefix = "member/memberTmContact";
	
	@Autowired
	private IMemberTmContactService memberTmContactService;
	
	@RequiresPermissions("member:memberTmContact:view")
	@GetMapping()
	public String memberTmContact()
	{
	    return prefix + "/memberTmContact";
	}
	
	/**
	 * 查询联系人列表
	 */
	@RequiresPermissions("member:memberTmContact:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(MemberTmContact memberTmContact)
	{
		startPage();
        List<MemberTmContact> list = memberTmContactService.selectMemberTmContactList(memberTmContact);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出联系人列表
	 */
	@RequiresPermissions("member:memberTmContact:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(MemberTmContact memberTmContact)
    {
    	List<MemberTmContact> list = memberTmContactService.selectMemberTmContactList(memberTmContact);
        ExcelUtil<MemberTmContact> util = new ExcelUtil<MemberTmContact>(MemberTmContact.class);
        return util.exportExcel(list, "memberTmContact");
    }
	
	/**
	 * 新增联系人
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存联系人
	 */
	@RequiresPermissions("member:memberTmContact:add")
	@Log(title = "联系人", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(MemberTmContact memberTmContact)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(memberTmContactService.insertMemberTmContact(memberTmContact));
	}

	/**
	 * 修改联系人
	 */
	@GetMapping("/edit/{contactId}")
	public String edit(@PathVariable("contactId") Integer contactId, ModelMap mmap)
	{
		MemberTmContact memberTmContact = memberTmContactService.selectMemberTmContactById(contactId);
		mmap.put("memberTmContact", memberTmContact);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存联系人
	 */
	@RequiresPermissions("member:memberTmContact:edit")
	@Log(title = "联系人", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(MemberTmContact memberTmContact)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(memberTmContactService.updateMemberTmContact(memberTmContact));
	}
	
	/**
	 * 删除联系人
	 */
	@RequiresPermissions("member:memberTmContact:remove")
	@Log(title = "联系人", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(memberTmContactService.deleteMemberTmContactByIds(ids));
	}
	
}
