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
import com.yiran.member.domain.MemberTrVerifyRef;
import com.yiran.member.service.IMemberTrVerifyRefService;
import com.yiran.framework.web.base.BaseController;
import com.yiran.common.page.TableDataInfo;
import com.yiran.common.base.AjaxResult;
import com.yiran.common.config.Global;
import com.yiran.common.utils.poi.ExcelUtil;

/**
 * 认证关系 信息操作处理
 * 
 * @author yiran
 * @date 2019-03-30
 */
@Controller
@RequestMapping("/member/memberTrVerifyRef")
public class MemberTrVerifyRefController extends BaseController
{
    private String prefix = "member/memberTrVerifyRef";
	
	@Autowired
	private IMemberTrVerifyRefService memberTrVerifyRefService;
	
	@RequiresPermissions("member:memberTrVerifyRef:view")
	@GetMapping()
	public String memberTrVerifyRef()
	{
	    return prefix + "/memberTrVerifyRef";
	}
	
	/**
	 * 查询认证关系列表
	 */
	@RequiresPermissions("member:memberTrVerifyRef:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(MemberTrVerifyRef memberTrVerifyRef)
	{
		startPage();
        List<MemberTrVerifyRef> list = memberTrVerifyRefService.selectMemberTrVerifyRefList(memberTrVerifyRef);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出认证关系列表
	 */
	@RequiresPermissions("member:memberTrVerifyRef:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(MemberTrVerifyRef memberTrVerifyRef)
    {
    	List<MemberTrVerifyRef> list = memberTrVerifyRefService.selectMemberTrVerifyRefList(memberTrVerifyRef);
        ExcelUtil<MemberTrVerifyRef> util = new ExcelUtil<MemberTrVerifyRef>(MemberTrVerifyRef.class);
        return util.exportExcel(list, "memberTrVerifyRef");
    }
	
	/**
	 * 新增认证关系
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存认证关系
	 */
	@RequiresPermissions("member:memberTrVerifyRef:add")
	@Log(title = "认证关系", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(MemberTrVerifyRef memberTrVerifyRef)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(memberTrVerifyRefService.insertMemberTrVerifyRef(memberTrVerifyRef));
	}

	/**
	 * 修改认证关系
	 */
	@GetMapping("/edit/{verifyId}")
	public String edit(@PathVariable("verifyId") Integer verifyId, ModelMap mmap)
	{
		MemberTrVerifyRef memberTrVerifyRef = memberTrVerifyRefService.selectMemberTrVerifyRefById(verifyId);
		mmap.put("memberTrVerifyRef", memberTrVerifyRef);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存认证关系
	 */
	@RequiresPermissions("member:memberTrVerifyRef:edit")
	@Log(title = "认证关系", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(MemberTrVerifyRef memberTrVerifyRef)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(memberTrVerifyRefService.updateMemberTrVerifyRef(memberTrVerifyRef));
	}
	
	/**
	 * 删除认证关系
	 */
	@RequiresPermissions("member:memberTrVerifyRef:remove")
	@Log(title = "认证关系", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{	
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(memberTrVerifyRefService.deleteMemberTrVerifyRefByIds(ids));
	}
	
}
