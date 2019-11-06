package com.yiran.payorder.mapper;

import com.yiran.payorder.domain.PayDistributedLock;
import java.util.List;

import org.apache.ibatis.annotations.Param;	

/**
 * 分布式锁 数据层
 * 
 * @author yiran
 * @date 2019-08-11
 */
public interface PayDistributedLockMapper 
{
	/**
     * 查询分布式锁信息
     * 
     * @param lockTicket 分布式锁ID
     * @return 分布式锁信息
     */
	public PayDistributedLock selectPayDistributedLockById(String lockTicket);
	
	/**
     * 查询分布式锁列表
     * 
     * @param payDistributedLock 分布式锁信息
     * @return 分布式锁集合
     */
	public List<PayDistributedLock> selectPayDistributedLockList(PayDistributedLock payDistributedLock);
	
	/**
     * 新增分布式锁
     * 
     * @param payDistributedLock 分布式锁信息
     * @return 结果
     */
	public int insertPayDistributedLock(PayDistributedLock payDistributedLock);
	
	/**
     * 修改分布式锁
     * 
     * @param payDistributedLock 分布式锁信息
     * @return 结果
     */
	public int updatePayDistributedLock(PayDistributedLock payDistributedLock);
	
	/**
     * 删除分布式锁
     * 
     * @param lockTicket 分布式锁ID
     * @return 结果
     */
	public int deletePayDistributedLockById(String lockTicket);
	
	/**
     * 批量删除分布式锁
     * 
     * @param lockTickets 需要删除的数据ID
     * @return 结果
     */
	public int deletePayDistributedLockByIds(String[] lockTickets);

	/**
	 * 上锁
	 * @param lockTicket
	 * @param lockTicketList
	 * @return
	 */
	public List<PayDistributedLock> lock(
			@Param("lockTicket") String lockTicket, 
			@Param("lockTicketList") List<String> lockTicketList);

	public List<PayDistributedLock> lockNoWait(
			@Param("lockTicket") String lockTicket, 
			@Param("lockTicketList") List<String> lockTicketList);

	public int updateByKey(
			@Param("lockStatus") String lockStatus, 
			@Param("machineIp")String machineIp, 
			@Param("machineHostName")String machineHostName, 
			@Param("lockTicketList")List<String> lockTicketList, 
			@Param("oldLockStatus")String oldLockStatus,
			@Param("isLock")Boolean isLock );
	
}