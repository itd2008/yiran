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
import com.yiran.member.domain.MemberTrVerifyEntity;
import com.yiran.member.service.IMemberTrVerifyEntityService;
import com.yiran.framework.web.base.BaseController;
import com.yiran.common.page.TableDataInfo;
import com.yiran.common.base.AjaxResult;
import com.yiran.common.config.Global;
import com.yiran.common.utils.poi.ExcelUtil;

/**
 * 认证实体 信息操作处理
 * 
 * @author yiran
 * @date 2019-03-30
 */
@Controller
@RequestMapping("/member/memberTrVerifyEntity")
public class MemberTrVerifyEntityController extends BaseController
{
    private String prefix = "member/memberTrVerifyEntity";
	
	@Autowired
	private IMemberTrVerifyEntityService memberTrVerifyEntityService;
	
	@RequiresPermissions("member:memberTrVerifyEntity:view")
	@GetMapping()
	public String memberTrVerifyEntity()
	{
	    return prefix + "/memberTrVerifyEntity";
	}
	
	/**
	 * 查询认证实体列表
	 */
	@RequiresPermissions("member:memberTrVerifyEntity:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(MemberTrVerifyEntity memberTrVerifyEntity)
	{
		startPage();
        List<MemberTrVerifyEntity> list = memberTrVerifyEntityService.selectMemberTrVerifyEntityList(memberTrVerifyEntity);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出认证实体列表
	 */
	@RequiresPermissions("member:memberTrVerifyEntity:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(MemberTrVerifyEntity memberTrVerifyEntity)
    {
    	List<MemberTrVerifyEntity> list = memberTrVerifyEntityService.selectMemberTrVerifyEntityList(memberTrVerifyEntity);
        ExcelUtil<MemberTrVerifyEntity> util = new ExcelUtil<MemberTrVerifyEntity>(MemberTrVerifyEntity.class);
        return util.exportExcel(list, "memberTrVerifyEntity");
    }
	
	/**
	 * 新增认证实体
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存认证实体
	 */
	@RequiresPermissions("member:memberTrVerifyEntity:add")
	@Log(title = "认证实体", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(MemberTrVerifyEntity memberTrVerifyEntity)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(memberTrVerifyEntityService.insertMemberTrVerifyEntity(memberTrVerifyEntity));
	}

	/**
	 * 修改认证实体
	 */
	@GetMapping("/edit/{verifyEntityId}")
	public String edit(@PathVariable("verifyEntityId") Integer verifyEntityId, ModelMap mmap)
	{
		MemberTrVerifyEntity memberTrVerifyEntity = memberTrVerifyEntityService.selectMemberTrVerifyEntityById(verifyEntityId);
		mmap.put("memberTrVerifyEntity", memberTrVerifyEntity);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存认证实体
	 */
	@RequiresPermissions("member:memberTrVerifyEntity:edit")
	@Log(title = "认证实体", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(MemberTrVerifyEntity memberTrVerifyEntity)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(memberTrVerifyEntityService.updateMemberTrVerifyEntity(memberTrVerifyEntity));
	}
	
	/**
	 * 删除认证实体
	 */
	@RequiresPermissions("member:memberTrVerifyEntity:remove")
	@Log(title = "认证实体", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(memberTrVerifyEntityService.deleteMemberTrVerifyEntityByIds(ids));
	}
	
}
