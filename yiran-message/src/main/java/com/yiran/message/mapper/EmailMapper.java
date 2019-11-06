package com.yiran.message.mapper;

import java.util.List;

import com.yiran.message.domain.Email;
/**
 * 系统邮件 数据层
 */
public interface EmailMapper {

    /**
     * 邮件入库
     * @param email
     * @return
     */
	public int insertEmail(Email email);

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
	 * 批量逻辑删除
	 * @param emailIds
	 * @return
	 */
	public int deleteEmailByIds(Long[] emailIds);
	
	/**
	 * 批量物理删除
	 * @param emailIds
	 * @return
	 */
	public int truncateEmailByIds(Long[] emailIds);
}
