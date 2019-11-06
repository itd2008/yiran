package com.yiran.web.controller.system;

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
import com.yiran.common.base.AjaxResult;
import com.yiran.common.config.Global;
import com.yiran.common.enums.BusinessType;
import com.yiran.common.page.TableDataInfo;
import com.yiran.framework.util.ShiroUtils;
import com.yiran.system.domain.SysNotice;
import com.yiran.system.service.ISysNoticeService;
import com.yiran.framework.web.base.BaseController;

/**
 * 公告 信息操作处理
 * 
 * @author yiran
 */
@Controller
@RequestMapping("/system/notice")
public class SysNoticeController extends BaseController
{
    private String prefix = "system/notice";

    @Autowired
    private ISysNoticeService noticeService;

    @RequiresPermissions("system:notice:view")
    @GetMapping()
    public String notice()
    {
        return prefix + "/notice";
    }

    /**
     * 查询公告列表
     */
    @RequiresPermissions("system:notice:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysNotice notice)
    {
        startPage();
        List<SysNotice> list = noticeService.selectNoticeList(notice);
        return getDataTable(list);
    }
    
    /**
     * 查询公告列表
     */
    @GetMapping("/all_list")
    @ResponseBody
    public AjaxResult all_list()
    {
    	SysNotice notice = new SysNotice();
    	notice.setStatus("0");
        List<SysNotice> list = noticeService.selectNoticeList(notice);
        return AjaxResult.success(list);
    }

    /**
     * 新增公告
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存公告
     */
    @RequiresPermissions("system:notice:add")
    @Log(title = "通知公告", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SysNotice notice)
    {
    	if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
        notice.setCreateBy(ShiroUtils.getLoginName());
        return toAjax(noticeService.insertNotice(notice));
    }

    /**
     * 修改公告
     */
    @GetMapping("/edit/{noticeId}")
    public String edit(@PathVariable("noticeId") Long noticeId, ModelMap mmap)
    {
        mmap.put("notice", noticeService.selectNoticeById(noticeId));
        return prefix + "/edit";
    }
    
    /**
     * 显示详情
     */
    @GetMapping("/showNotice/{noticeId}")
    public String showNotice(@PathVariable("noticeId") Long noticeId, ModelMap mmap)
    {
        mmap.put("notice", noticeService.selectNoticeById(noticeId));
        return prefix + "/showNotice";
    }

    /**
     * 修改保存公告
     */
    @RequiresPermissions("system:notice:edit")
    @Log(title = "通知公告", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SysNotice notice)
    {
    	if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
        notice.setUpdateBy(ShiroUtils.getLoginName());
        return toAjax(noticeService.updateNotice(notice));
    }

    /**
     * 删除公告
     */
    @RequiresPermissions("system:notice:remove")
    @Log(title = "通知公告", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
    	if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
        return toAjax(noticeService.deleteNoticeByIds(ids));
    }
}
