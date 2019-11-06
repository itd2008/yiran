package com.yiran.web.controller.wechat;

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
import com.yiran.wechat.domain.WechatOrderProductComment;
import com.yiran.wechat.service.IWechatOrderProductCommentService;
import com.yiran.framework.web.base.BaseController;
import com.yiran.common.page.TableDataInfo;
import com.yiran.common.base.AjaxResult;
import com.yiran.common.config.Global;
import com.yiran.common.utils.poi.ExcelUtil;

/**
 * 订单评论 信息操作处理
 * 
 * @author yiran
 * @date 2019-06-13
 */
@Controller
@RequestMapping("/wechat/wechatOrderProductComment")
public class WechatOrderProductCommentController extends BaseController
{
    private String prefix = "wechat/wechatOrderProductComment";
	
	@Autowired
	private IWechatOrderProductCommentService wechatOrderProductCommentService;
	
	@RequiresPermissions("wechat:wechatOrderProductComment:view")
	@GetMapping()
	public String wechatOrderProductComment()
	{
	    return prefix + "/wechatOrderProductComment";
	}
	
	/**
	 * 查询订单评论列表
	 */
	@RequiresPermissions("wechat:wechatOrderProductComment:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(WechatOrderProductComment wechatOrderProductComment)
	{
		startPage();
        List<WechatOrderProductComment> list = wechatOrderProductCommentService.selectWechatOrderProductCommentList(wechatOrderProductComment);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出订单评论列表
	 */
	@RequiresPermissions("wechat:wechatOrderProductComment:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(WechatOrderProductComment wechatOrderProductComment)
    {
    	List<WechatOrderProductComment> list = wechatOrderProductCommentService.selectWechatOrderProductCommentList(wechatOrderProductComment);
        ExcelUtil<WechatOrderProductComment> util = new ExcelUtil<WechatOrderProductComment>(WechatOrderProductComment.class);
        return util.exportExcel(list, "wechatOrderProductComment");
    }
	
	/**
	 * 新增订单评论
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存订单评论
	 */
	@RequiresPermissions("wechat:wechatOrderProductComment:add")
	@Log(title = "订单评论", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(WechatOrderProductComment wechatOrderProductComment)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(wechatOrderProductCommentService.insertWechatOrderProductComment(wechatOrderProductComment));
	}

	/**
	 * 修改订单评论
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		WechatOrderProductComment wechatOrderProductComment = wechatOrderProductCommentService.selectWechatOrderProductCommentById(id);
		mmap.put("wechatOrderProductComment", wechatOrderProductComment);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存订单评论
	 */
	@RequiresPermissions("wechat:wechatOrderProductComment:edit")
	@Log(title = "订单评论", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(WechatOrderProductComment wechatOrderProductComment)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(wechatOrderProductCommentService.updateWechatOrderProductComment(wechatOrderProductComment));
	}
	
	/**
	 * 删除订单评论
	 */
	@RequiresPermissions("wechat:wechatOrderProductComment:remove")
	@Log(title = "订单评论", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(wechatOrderProductCommentService.deleteWechatOrderProductCommentByIds(ids));
	}
	
}
