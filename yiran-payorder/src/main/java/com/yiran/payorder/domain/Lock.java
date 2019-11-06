/**
 *
 */
package com.yiran.payorder.domain;

import java.util.List;

import com.yiran.payorder.enums.LockType;


/**
 * <p>锁域对象.</p>
 */
public class Lock {
    /** 锁键. */
    private String lockKey;
    /** 联合锁键. */
    private List<String> unionKeys;
    /** 锁名. */
    private String lockName;
    /** 锁描述. */
    private String lockDescription;
    /** 锁类型. */
    private LockType lockType;
    /** 超时时间. */
    private long lockSecond;

    public String getLockKey() {
        return lockKey;
    }
    public void setLockKey(String lockKey) {
        this.lockKey = lockKey;
    }
    public List<String> getUnionKeys() {
        return unionKeys;
    }
    public void setUnionKeys(List<String> unionKeys) {
        this.unionKeys = unionKeys;
    }
    public String getLockName() {
        return lockName;
    }
    public void setLockName(String lockName) {
        this.lockName = lockName;
    }
    public String getLockDescription() {
        return lockDescription;
    }
    public void setLockDescription(String lockDescription) {
        this.lockDescription = lockDescription;
    }
    public LockType getLockType() {
        return lockType;
    }
    public void setLockType(LockType lockType) {
        this.lockType = lockType;
    }
    public long getLockSecond() {
        return lockSecond;
    }
    public void setLockSecond(long lockSecond) {
        this.lockSecond = lockSecond;
    }

}
