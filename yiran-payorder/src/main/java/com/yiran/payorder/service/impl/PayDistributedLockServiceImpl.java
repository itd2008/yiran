package com.yiran.payorder.service.impl;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.CannotAcquireLockException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.yiran.payorder.mapper.PayDistributedLockMapper;
import com.yiran.payorder.domain.Lock;
import com.yiran.payorder.domain.PayDistributedLock;
import com.yiran.payorder.enums.LockStatus;
import com.yiran.payorder.enums.LockType;
import com.yiran.payorder.exception.LockException;
import com.yiran.payorder.service.IPayDistributedLockService;
import com.netfinworks.common.lang.StringUtil;
import com.netfinworks.common.util.DateUtil;
import com.yiran.common.support.Convert;

/**
 * 分布式锁 服务层实现
 * 
 * @author yiran
 * @date 2019-08-11
 */
@Service
public class PayDistributedLockServiceImpl implements IPayDistributedLockService 
{
	 private Logger   logger = LoggerFactory.getLogger(PayDistributedLockServiceImpl.class);
	@Autowired
	private PayDistributedLockMapper payDistributedLockMapper;
	@Autowired
	private TransactionTemplate payTransactionTemplateNew; // 必须使用内部事务
	/**
     * 查询分布式锁信息
     * 
     * @param lockTicket 分布式锁ID
     * @return 分布式锁信息
     */
    @Override
	public PayDistributedLock selectPayDistributedLockById(String lockTicket)
	{
	    return payDistributedLockMapper.selectPayDistributedLockById(lockTicket);
	}
	
	/**
     * 查询分布式锁列表
     * 
     * @param payDistributedLock 分布式锁信息
     * @return 分布式锁集合
     */
	@Override
	public List<PayDistributedLock> selectPayDistributedLockList(PayDistributedLock payDistributedLock)
	{
	    return payDistributedLockMapper.selectPayDistributedLockList(payDistributedLock);
	}
	
    /**
     * 新增分布式锁
     * 
     * @param payDistributedLock 分布式锁信息
     * @return 结果
     */
	@Override
	public int insertPayDistributedLock(PayDistributedLock payDistributedLock)
	{
	    return payDistributedLockMapper.insertPayDistributedLock(payDistributedLock);
	}
	
	/**
     * 修改分布式锁
     * 
     * @param payDistributedLock 分布式锁信息
     * @return 结果
     */
	@Override
	public int updatePayDistributedLock(PayDistributedLock payDistributedLock)
	{
	    return payDistributedLockMapper.updatePayDistributedLock(payDistributedLock);
	}

	/**
     * 删除分布式锁对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deletePayDistributedLockByIds(String ids)
	{
		return payDistributedLockMapper.deletePayDistributedLockByIds(Convert.toStrArray(ids));
	}

	@Override
	public boolean lock(Lock domain) throws LockException {
		if (domain == null)
			return false;

		if (domain.getLockKey() == null) {
			throw new LockException("无法锁定，空的锁键LockKey.");
		}

		if (domain.getUnionKeys() != null) {
			for (String key : domain.getUnionKeys()) {
				if (StringUtil.isBlank(key)) {
					throw new LockException("无法锁定，空的关联锁键LockKey.");
				}
			}
		}

		if (domain.getLockType() == null) {
			throw new LockException("请设定锁类型.");
		}

		if (LockType.SYNCHRON.equals(domain.getLockType())) {

			return synchronLock(getkeys(domain));
		} else {

			if (domain.getLockSecond() <= 0) {
				throw new LockException("请设定锁超时时间.");
			}

			return exclusiveLock(domain);
		}
	}
	
	private List<String> getkeys(Lock domain) {
		List<String> keys = new ArrayList<String>();
		keys.add(domain.getLockKey());
		List<String> list = domain.getUnionKeys();
		if (list != null) {
			keys.addAll(list);
		}
		return keys;
	}

	private String getkeys(Lock domain, String separ) {
		StringBuilder rev = new StringBuilder();
		rev.append(domain.getLockKey());

		List<String> list = domain.getUnionKeys();
		if (list != null) {
			for (String s : list) {
				rev.append(separ);
				rev.append(s);
			}
		}
		return rev.toString();
	}
	
	private String li2str(List<String> li, String separ) {
		StringBuilder rev = new StringBuilder();
		if (li != null) {
			int i = 0;
			for (String s : li) {
				if (separ != null && i == 0)
					rev.append(separ);
				rev.append(s);
				i++;
			}
		}
		return rev.toString();
	}
	/**
	 * 同步锁.
	 *
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected boolean synchronLock(final List<String> keys) {

		List<PayDistributedLock> locks = payDistributedLockMapper.lock(null, keys); // 阻塞锁

		if (locks.size() == 0) { // 不存在，则保存数据
			if (logger.isDebugEnabled()) {
				logger.info("无任何锁：" + keys);
			}
			final StringBuffer _key = new StringBuffer();
			try {

				payTransactionTemplateNew.execute(new TransactionCallback() {

					@Override
					public Object doInTransaction(TransactionStatus status) {

						for (String key : keys) {
							_key.append(":key:" + key);
							PayDistributedLock ddo = new PayDistributedLock();
							ddo.setLockTicket(key);
							ddo.setLockType(LockType.SYNCHRON.getCode());
							payDistributedLockMapper.insertPayDistributedLock(ddo);
						}

						if (logger.isDebugEnabled()) {
							logger.debug("插入同步锁：" + keys);
						}

						return null;
					}

				});
			} catch (Exception e) {
				logger.error("执行分布式同步加锁异常,key = " + _key.toString(), e);
				return false;
			}

			return synchronLock(keys);

		}

		return true;
	}

	/**
	 * 互斥锁.
	 *
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected boolean exclusiveLock(final Lock domain) {
		if (logger.isDebugEnabled()) {
			logger.debug("加入分布式锁：lockKey=" + domain.getLockKey());
		}

		try {
			final String machineIp = InetAddress.getLocalHost().getHostAddress();
			final String machineHostName = InetAddress.getLocalHost().getHostName();
			final List<String> keyList = getkeys(domain);

			return (Boolean) payTransactionTemplateNew.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {

					List<PayDistributedLock> locks = payDistributedLockMapper.lockNoWait(null, keyList); // 锁定

					String logSuf = getLogSuf(domain, machineIp, machineHostName);

					if (locks.size() == 0) { // 不存在，则保存数据 只对主锁 联合键默认当已存在
						if (logger.isDebugEnabled()) {
							logger.debug("无任何锁：" + logSuf);
						}
						return createAndLock(domain, machineIp, machineHostName, logSuf);

					} else { // 存在

						int keySize = 1;
						if (domain.getUnionKeys() != null)
							keySize += domain.getUnionKeys().size();

						PayDistributedLock mainLock = null;
						for (PayDistributedLock ddo : locks) {
							if (ddo.getLockTicket().equals(domain.getLockKey())) {
								mainLock = ddo;
								break;
							}
						}

						if (mainLock == null) { // 如果主锁不存在 创建主锁
							if (logger.isDebugEnabled()) {
								logger.debug("还没存在主锁：" + logSuf);
							}

							return createAndLock(domain, machineIp, machineHostName, logSuf);

						} else {
							if (locks.size() != keySize) { // 部份存在
								logger.warn("锁定任务时发现关联的锁键在数据库不存在[" + li2str(domain.getUnionKeys(), ",") + "]");
							}
						}

						if (LockStatus.EXCUTE.equals(LockStatus.getByCode(mainLock.getLockStatus()))) {
							if (DateUtil.getDiffSeconds(mainLock.getGmtModified(), mainLock.getGmtLock()) >= domain.getLockSecond()) { // 判断主锁记录是否超时
								if (logger.isDebugEnabled()) {
									logger.debug("锁定任务超时：" + logSuf);
								}

								payDistributedLockMapper.updateByKey(LockStatus.EXCUTE.getCode(), machineIp, machineHostName, keyList, LockStatus.EXCUTE
										.getCode(),true);

								if (logger.isDebugEnabled()) {
									logger.debug("解除原锁定任务，并重新加锁成功：" + logSuf);
								}
								return true; // 锁定成功，
												// 因为是在同一个事务内，状态不可能变更，因此肯定是修改成功的

							} else {
								if (logger.isDebugEnabled())
									logger.debug("无法锁住记录，目前在执行。" + logSuf);

								status.setRollbackOnly();
								return false; // 正在执行 不能加锁 等待解锁服务
							}
						} else { // 任务已完成
							payDistributedLockMapper.updateByKey(LockStatus.EXCUTE.getCode(), machineIp, machineHostName, keyList, LockStatus.FINISH
									.getCode(),true); // 正常锁定

							if (logger.isDebugEnabled()) {
								logger.debug("锁定任务成功：" + logSuf);
							}
							return true;
						}
					}
				}

				/**
				 * @param domain
				 * @param machineIp
				 * @param machineHostName
				 * @return
				 */
				private String getLogSuf(final Lock domain, final String machineIp, final String machineHostName) {
					StringBuilder sb = new StringBuilder();
					sb.append("lockKey=");
					sb.append(domain.getLockKey());
					sb.append(",lockSecond=");
					sb.append(domain.getLockSecond());
					sb.append(",machineIp=");
					sb.append(machineIp);
					sb.append(",machineHostName=");
					sb.append(machineHostName);
					String logSuf = sb.toString();
					return logSuf;
				}

				/**
				 * @param domain
				 * @param machineIp
				 * @param machineHostName
				 * @param logSuf
				 */
				private boolean createAndLock(final Lock domain, final String machineIp, final String machineHostName, String logSuf) {
					PayDistributedLock ddo = new PayDistributedLock();
					ddo.setMachineHost(machineHostName);
					ddo.setMachineIp(machineIp);
					ddo.setLockStatus(LockStatus.EXCUTE.getCode());
					ddo.setLockTicket(domain.getLockKey());
					ddo.setLockDesc(domain.getLockDescription());
					ddo.setLockName(domain.getLockName());
					ddo.setLockType(LockType.EXCLUSION.getCode());

					payDistributedLockMapper.insertPayDistributedLock(ddo);

					if (logger.isDebugEnabled()) {
						logger.debug("锁定任务成功：" + logSuf);
					}

					return true;
				}
			});
		} catch (CannotAcquireLockException e) { // 已有锁
			return false;
		} catch (Exception e) {
			logger.error("执行分布式互斥加锁异常," + e);
			return false;
		}
	}

	@Override
	public boolean release(Lock domain) throws LockException {

		if (logger.isDebugEnabled()) {
			logger.debug("释放分布式锁：lockKey=" + domain.getLockKey());
		}
		if (!LockType.EXCLUSION.equals(domain.getLockType())) {
			throw new LockException("只有互斥锁才允许释放.");
		}
		try {
			final List<String> keyList = getkeys(domain);
			final String machineIp = InetAddress.getLocalHost().getHostAddress();
			final String machineHostName = InetAddress.getLocalHost().getHostName();

			return (Boolean) payTransactionTemplateNew.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {

					int c = payDistributedLockMapper.updateByKey(LockStatus.FINISH.getCode(), machineIp, machineHostName, keyList, LockStatus.EXCUTE.getCode(),false);
					if (c > 0) {
						if (logger.isDebugEnabled()) {
							logger.debug("释放锁成功！lockKey=" + domain.getLockKey());
						}
						return true;
					}
					if (logger.isDebugEnabled()) {
						logger.debug("释放锁不成功！lockKey=" + domain.getLockKey());
					}
					return false;
				}
			});
		} catch (Exception e) {
			logger.error("执行分布式互斥解锁异常," + e);
			return false;
		}
	
	}
	
}
