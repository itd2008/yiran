package com.yiran.message.service;

import java.util.List;

import com.yiran.message.domain.Email;
import com.yiran.system.domain.SysUser;
/**
 * 系统邮件 服务层
 */
public interface IEmailService {

	/**
	 * 邮件列表
	 * @param email
	 * @return
	 */
	public List<Email> selectEmailList(Email email);
	
	/**
	 * 邮件详情
	 * @param emailId
	 * @return
	 */
	public Email selectEmailById(Long emailId);
	
	/**
	 * 发送内部邮件
	 * @param email
	 * @return
	 */
	public int sendWithInside(Email email,SysUser user);

	/**
	 * 发送外部邮件
	 * @param email
	 * @return
	 */
	public int sendWithOutside(Email email,SysUser user);

	/**
	 * 批量逻辑删除
	 * @param ids
	 * @return
	 */
	public int deleteEmailByIds(String ids);
	
	/**
	 * 批量物理删除
	 * @param ids
	 * @return
	 */
	public int truncateEmailByIds(String ids);

	/**
	 * 移入回收站
	 * @param email
	 * @return
	 */
	public int moveToRecoveryBin(Email email);
	
	/**
	 * 恢复到收件箱
	 * @param email
	 * @return
	 */
	public int moveToInBox(Email email);
	
	/**
	 * 保存至草稿
	 * @param email
	 * @return
	 */
	public int saveToRough(Email email ,SysUser user);
}
