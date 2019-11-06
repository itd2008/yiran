package com.yiran.web.controller.weixin;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yiran.common.annotation.Log;
import com.yiran.common.base.AjaxResult;
import com.yiran.common.config.Global;
import com.yiran.common.enums.BusinessType;
import com.yiran.common.page.TableDataInfo;
import com.yiran.framework.web.base.BaseController;
import com.yiran.weixin.domain.WeixinUser;
import com.yiran.weixin.service.IWeixinUserService;

/**
 * 公众号微信用户 信息操作处理
 * 
 * @author yiran
 * @date 2018-08-19
 */
@Controller
@RequestMapping("/weixin/weixinUser")
public class WeixinUserController extends BaseController
{
    private String prefix = "weixin/weixinUser";
	
	@Autowired
	private IWeixinUserService weixinUserService;
	
	@RequiresPermissions("weixin:weixinUser:view")
	@GetMapping()
	public String weixinUser()
	{
	    return prefix + "/weixinUser";
	}
	
	/**
	 * 查询公众号微信用户列表
	 */
	@RequiresPermissions("weixin:weixinUser:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(WeixinUser weixinUser)
	{
		startPage();
        List<WeixinUser> list = weixinUserService.selectWeixinUserList(weixinUser);
		return getDataTable(list);
	}
	
	/**
	 * 新增公众号微信用户
	 */
	@RequiresPermissions("weixin:weixinUser:add")
	@Log(title = "公众号微信用户", businessType = BusinessType.INSERT)
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}

	/**
	 * 修改公众号微信用户
	 */
	@RequiresPermissions("weixin:weixinUser:edit")
	@Log(title = "公众号微信用户", businessType = BusinessType.UPDATE)
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") String id, Model model)
	{
		WeixinUser weixinUser = weixinUserService.selectWeixinUserById(id);
		model.addAttribute("weixinUser", weixinUser);
	    return prefix + "/edit";
	}
	
	/**
	 * 保存公众号微信用户
	 */
	@RequiresPermissions("weixin:weixinUser:save")
	@Log(title = "公众号微信用户", businessType = BusinessType.INSERT)
	@PostMapping("/save")
	@ResponseBody
	public AjaxResult save(WeixinUser weixinUser)
	{
		if (weixinUserService.saveWeixinUser(weixinUser) > 0)
		{
			return success();
		}
		return error();
	}
	
	/**
	 * 删除公众号微信用户
	 */
	@RequiresPermissions("weixin:weixinUser:remove")
	@Log(title = "公众号微信用户", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		int rows = weixinUserService.deleteWeixinUserByIds(ids);
		if (rows > 0)
        {
            return success();
        }
        return error();
	}
	
}
