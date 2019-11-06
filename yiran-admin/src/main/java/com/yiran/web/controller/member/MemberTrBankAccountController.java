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
import com.yiran.member.domain.MemberTrBankAccount;
import com.yiran.member.service.IMemberTrBankAccountService;
import com.yiran.framework.web.base.BaseController;
import com.yiran.common.page.TableDataInfo;
import com.yiran.common.base.AjaxResult;
import com.yiran.common.config.Global;
import com.yiran.common.utils.poi.ExcelUtil;

/**
 * 个人银行卡 信息操作处理
 * 
 * @author yiran
 * @date 2019-03-30
 */
@Controller
@RequestMapping("/member/memberTrBankAccount")
public class MemberTrBankAccountController extends BaseController
{
    private String prefix = "member/memberTrBankAccount";
	
	@Autowired
	private IMemberTrBankAccountService memberTrBankAccountService;
	
	@RequiresPermissions("member:memberTrBankAccount:view")
	@GetMapping()
	public String memberTrBankAccount()
	{
	    return prefix + "/memberTrBankAccount";
	}
	
	/**
	 * 查询个人银行卡列表
	 */
	@RequiresPermissions("member:memberTrBankAccount:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(MemberTrBankAccount memberTrBankAccount)
	{
		startPage();
        List<MemberTrBankAccount> list = memberTrBankAccountService.selectMemberTrBankAccountList(memberTrBankAccount);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出个人银行卡列表
	 */
	@RequiresPermissions("member:memberTrBankAccount:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(MemberTrBankAccount memberTrBankAccount)
    {
    	List<MemberTrBankAccount> list = memberTrBankAccountService.selectMemberTrBankAccountList(memberTrBankAccount);
        ExcelUtil<MemberTrBankAccount> util = new ExcelUtil<MemberTrBankAccount>(MemberTrBankAccount.class);
        return util.exportExcel(list, "memberTrBankAccount");
    }
	
	/**
	 * 新增个人银行卡
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存个人银行卡
	 */
	@RequiresPermissions("member:memberTrBankAccount:add")
	@Log(title = "个人银行卡", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(MemberTrBankAccount memberTrBankAccount)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(memberTrBankAccountService.insertMemberTrBankAccount(memberTrBankAccount));
	}

	/**
	 * 修改个人银行卡
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		MemberTrBankAccount memberTrBankAccount = memberTrBankAccountService.selectMemberTrBankAccountById(id);
		mmap.put("memberTrBankAccount", memberTrBankAccount);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存个人银行卡
	 */
	@RequiresPermissions("member:memberTrBankAccount:edit")
	@Log(title = "个人银行卡", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(MemberTrBankAccount memberTrBankAccount)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(memberTrBankAccountService.updateMemberTrBankAccount(memberTrBankAccount));
	}
	
	/**
	 * 删除个人银行卡
	 */
	@RequiresPermissions("member:memberTrBankAccount:remove")
	@Log(title = "个人银行卡", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(memberTrBankAccountService.deleteMemberTrBankAccountByIds(ids));
	}
	
}
