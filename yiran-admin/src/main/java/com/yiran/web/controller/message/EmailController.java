package com.yiran.web.controller.message;

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

import com.yiran.common.base.AjaxResult;
import com.yiran.common.config.Global;
import com.yiran.common.page.TableDataInfo;
import com.yiran.framework.util.ShiroUtils;
import com.yiran.framework.web.base.BaseController;
import com.yiran.message.domain.Email;
import com.yiran.message.service.IEmailService;
import com.yiran.system.domain.SysUser;
import com.yiran.system.service.ISysUserService;

@Controller
@RequestMapping("/message/email")
public class EmailController extends BaseController {

	private String prefix = "message/email";
	
	@Autowired
	private ISysUserService sysUserService;
	
	@Autowired
	private IEmailService emailService;
	
	/**
	 * 默认收件箱
	 * @param mmap
	 * @return
	 */
	@RequiresPermissions("message:email:view")
	@GetMapping("/{link}")
	public String view(ModelMap mmap, @PathVariable("link") String link, String type, String label) {
		mmap.put("emailLink", link);
		mmap.put("emailType", type);
		mmap.put("emailLabel", label);
		return prefix + "/email";
	}
	
	@PostMapping("/list/{link}")
	@ResponseBody
	public TableDataInfo list(Email email,@PathVariable("link") String link, String type, String label) {
		startPage();
		//设置文件夹列表
		if("0".equals(link)){
			email.setToUser(getUserId());
			email.setEmailFolder("1");
		}else{
			email.setFormUser(getUserId());
			email.setEmailFolder(link);
		}
		email.setEmailType(type);
		email.setEmailLabel(label);
		List<Email> list = emailService.selectEmailList(email);
		return getDataTable(list);
	}
	
	/**
	 * 写信页
	 * @param mmap
	 * @return
	 */
	@GetMapping("/write")
	public String write(ModelMap mmap) {
		mmap.put("users", sysUserService.selectUserList(new SysUser()));
		return prefix + "/write";
	}
	
	/**
	 * 读信页
	 * @param mmap
	 * @return
	 */
	@GetMapping("/read")
	public String read(ModelMap mmap,Long emailId) {
		mmap.put("email", emailService.selectEmailById(emailId));
		return prefix + "/read";
	}
	
	/**
	 * 发送内部邮件
	 * @param email
	 * @return
	 */
	@PostMapping("/sendWithInside")
	@ResponseBody
	public AjaxResult sendWithInside(Email email) {
		
		return toAjax(emailService.sendWithInside(email,ShiroUtils.getSysUser()));
	}
	
	/**
	 * 发送外部邮件
	 * @param email
	 * @return
	 */
	@PostMapping("/sendWithOutside")
	@ResponseBody
	public AjaxResult sendWithOutside(Email email) {
		return toAjax(emailService.sendWithOutside(email,ShiroUtils.getSysUser()));
	}
	
	/**
	 * 移入回收站
	 * @param email
	 * @return
	 */
	@PostMapping("/moveToRecoveryBin")
	@ResponseBody
	public AjaxResult moveToRecoveryBin(Email email) {
		return toAjax(emailService.moveToRecoveryBin(email));
	}
	
	/**
	 * 恢复到收件箱
	 * @param email
	 * @return
	 */
	@PostMapping("/moveToInBox")
	@ResponseBody
	public AjaxResult moveToInBox(Email email) {
		return toAjax(emailService.moveToInBox(email));
	}
	
	/**
	 * 保存至草稿
	 * @param email
	 * @return
	 */
	@PostMapping("/saveToRough")
	@ResponseBody
	public AjaxResult saveToRough(Email email) {
		return toAjax(emailService.saveToRough(email,ShiroUtils.getSysUser()));
	}
	
	/**
	 * 批量逻辑删除
	 * @param ids
	 * @return
	 */
	@PostMapping("/logicRemove")
	@ResponseBody
	public AjaxResult logicRemove(String ids) {
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		try {
			return toAjax(emailService.deleteEmailByIds(ids));
		} catch (Exception e) {
			return error(e.getMessage());
		}
	}
	
	/**
	 * 批量物理删除
	 * @param ids
	 * @return
	 */
	@PostMapping("/physicsRemove")
	@ResponseBody
	public AjaxResult physicsRemove(String ids) {
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		try {
			return toAjax(emailService.truncateEmailByIds(ids));
		} catch (Exception e) {
			return error(e.getMessage());
		}
	}
}
