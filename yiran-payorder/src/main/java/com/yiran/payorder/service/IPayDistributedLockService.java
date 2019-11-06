package com.yiran.payorder.service;

import com.yiran.payorder.domain.Lock;
import com.yiran.payorder.domain.PayDistributedLock;
import com.yiran.payorder.exception.LockException;

import java.util.List;

/**
 * 分布式锁 服务层
 * 
 * @author yiran
 * @date 2019-08-11
 */
public interface IPayDistributedLockService 
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
     * 删除分布式锁信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deletePayDistributedLockByIds(String ids);

	/**
     * 普通的并发锁操作， 同步锁意味着阻塞，需要依赖于是外部的数据库事务.
     * @param domain
     * @return 成功失败
     * @throws LockException
     *
     */
    boolean lock(Lock domain) throws LockException;

    /**
     * 互斥锁释放.
     * @param domain
     * @return 成功失败
     * @throws LockException
     *
     */
    boolean release(Lock domain) throws LockException;
	
}
