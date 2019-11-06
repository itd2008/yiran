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
import com.yiran.member.domain.MemberTrMemberAccountDetail;
import com.yiran.member.service.IMemberTrMemberAccountDetailService;
import com.yiran.framework.web.base.BaseController;
import com.yiran.common.page.TableDataInfo;
import com.yiran.common.base.AjaxResult;
import com.yiran.common.config.Global;
import com.yiran.common.utils.poi.ExcelUtil;

/**
 * 会员账户明细 信息操作处理
 * 
 * @author yiran
 * @date 2019-04-04
 */
@Controller
@RequestMapping("/member/memberTrMemberAccountDetail")
public class MemberTrMemberAccountDetailController extends BaseController
{
    private String prefix = "member/memberTrMemberAccountDetail";
	
	@Autowired
	private IMemberTrMemberAccountDetailService memberTrMemberAccountDetailService;
	
	@RequiresPermissions("member:memberTrMemberAccountDetail:view")
	@GetMapping()
	public String memberTrMemberAccountDetail()
	{
	    return prefix + "/memberTrMemberAccountDetail";
	}
	
	/**
	 * 查询会员账户明细列表
	 */
	@RequiresPermissions("member:memberTrMemberAccountDetail:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(MemberTrMemberAccountDetail memberTrMemberAccountDetail)
	{
		startPage();
        List<MemberTrMemberAccountDetail> list = memberTrMemberAccountDetailService.selectMemberTrMemberAccountDetailList(memberTrMemberAccountDetail);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出会员账户明细列表
	 */
	@RequiresPermissions("member:memberTrMemberAccountDetail:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(MemberTrMemberAccountDetail memberTrMemberAccountDetail)
    {
    	List<MemberTrMemberAccountDetail> list = memberTrMemberAccountDetailService.selectMemberTrMemberAccountDetailList(memberTrMemberAccountDetail);
        ExcelUtil<MemberTrMemberAccountDetail> util = new ExcelUtil<MemberTrMemberAccountDetail>(MemberTrMemberAccountDetail.class);
        return util.exportExcel(list, "memberTrMemberAccountDetail");
    }
	
	/**
	 * 新增会员账户明细
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存会员账户明细
	 */
	@RequiresPermissions("member:memberTrMemberAccountDetail:add")
	@Log(title = "会员账户明细", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(MemberTrMemberAccountDetail memberTrMemberAccountDetail)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(memberTrMemberAccountDetailService.insertMemberTrMemberAccountDetail(memberTrMemberAccountDetail));
	}

	/**
	 * 修改会员账户明细
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		MemberTrMemberAccountDetail memberTrMemberAccountDetail = memberTrMemberAccountDetailService.selectMemberTrMemberAccountDetailById(id);
		mmap.put("memberTrMemberAccountDetail", memberTrMemberAccountDetail);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存会员账户明细
	 */
	@RequiresPermissions("member:memberTrMemberAccountDetail:edit")
	@Log(title = "会员账户明细", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(MemberTrMemberAccountDetail memberTrMemberAccountDetail)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(memberTrMemberAccountDetailService.updateMemberTrMemberAccountDetail(memberTrMemberAccountDetail));
	}
	
	/**
	 * 删除会员账户明细
	 */
	@RequiresPermissions("member:memberTrMemberAccountDetail:remove")
	@Log(title = "会员账户明细", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(memberTrMemberAccountDetailService.deleteMemberTrMemberAccountDetailByIds(ids));
	}
	
}
