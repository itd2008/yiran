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
import com.yiran.member.domain.MemberTrPassword;
import com.yiran.member.service.IMemberTrPasswordService;
import com.yiran.framework.web.base.BaseController;
import com.yiran.common.page.TableDataInfo;
import com.yiran.common.base.AjaxResult;
import com.yiran.common.config.Global;
import com.yiran.common.utils.poi.ExcelUtil;

/**
 * 会员支付密码 信息操作处理
 * 
 * @author yiran
 * @date 2019-03-30
 */
@Controller
@RequestMapping("/member/memberTrPassword")
public class MemberTrPasswordController extends BaseController
{
    private String prefix = "member/memberTrPassword";
	
	@Autowired
	private IMemberTrPasswordService memberTrPasswordService;
	
	@RequiresPermissions("member:memberTrPassword:view")
	@GetMapping()
	public String memberTrPassword()
	{
	    return prefix + "/memberTrPassword";
	}
	
	/**
	 * 查询会员支付密码列表
	 */
	@RequiresPermissions("member:memberTrPassword:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(MemberTrPassword memberTrPassword)
	{
		startPage();
        List<MemberTrPassword> list = memberTrPasswordService.selectMemberTrPasswordList(memberTrPassword);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出会员支付密码列表
	 */
	@RequiresPermissions("member:memberTrPassword:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(MemberTrPassword memberTrPassword)
    {
    	List<MemberTrPassword> list = memberTrPasswordService.selectMemberTrPasswordList(memberTrPassword);
        ExcelUtil<MemberTrPassword> util = new ExcelUtil<MemberTrPassword>(MemberTrPassword.class);
        return util.exportExcel(list, "memberTrPassword");
    }
	
	/**
	 * 新增会员支付密码
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存会员支付密码
	 */
	@RequiresPermissions("member:memberTrPassword:add")
	@Log(title = "会员支付密码", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(MemberTrPassword memberTrPassword)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(memberTrPasswordService.insertMemberTrPassword(memberTrPassword));
	}

	/**
	 * 修改会员支付密码
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		MemberTrPassword memberTrPassword = memberTrPasswordService.selectMemberTrPasswordById(id);
		mmap.put("memberTrPassword", memberTrPassword);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存会员支付密码
	 */
	@RequiresPermissions("member:memberTrPassword:edit")
	@Log(title = "会员支付密码", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(MemberTrPassword memberTrPassword)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(memberTrPasswordService.updateMemberTrPassword(memberTrPassword));
	}
	
	/**
	 * 删除会员支付密码
	 */
	@RequiresPermissions("member:memberTrPassword:remove")
	@Log(title = "会员支付密码", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(memberTrPasswordService.deleteMemberTrPasswordByIds(ids));
	}
	
}
