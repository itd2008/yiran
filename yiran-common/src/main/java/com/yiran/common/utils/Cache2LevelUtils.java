package com.yiran.common.utils;

import com.yiran.common.config.Global;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *两级缓存工具类 EHcache -> spring-redis
 * <br>简单逻辑,旨在减轻redis压力
 * <br>如需保证分布式缓存一致性,可引入使用 J2cache : https://gitee.com/ld/J2Cache
 *@version 1.0
 */
public class Cache2LevelUtils  {
    private static final String cacheName = "Cache2Level";
    private static final boolean hasRedis = Global.hasRedis();
    private static Logger log = LoggerFactory.getLogger(Cache2LevelUtils.class);

    /**
     * 存值. l1time 越小, redis 压力越大,分布式数据一致性越高,
     * @param key
     * @param value
     * @param l1time 本地缓存有效时长 秒
     * @param l2time redis缓存有效时长 秒
     * @return
     */
    public static boolean put(String key,Object value,int l1time,long l2time){
        boolean re = false;
        try {
            re = EHCacheUtil.setValue(cacheName, key, value, l1time);
            if (hasRedis) RedisUtils.set(key,value,l2time);
        } catch (Exception e) {
            log.error("存入缓存异常",e);
        }
        return re;
    }

    /**
     * 获取缓存
     * @param key
     * @return
     */
    public static Object get(String key){
        Object value = null;
        try {
            value = EHCacheUtil.getValue(cacheName, key);
            if (value==null&&hasRedis) {
                value = RedisUtils.get(key);
                //再存入本地缓存
                long expireTime = RedisUtils.getExpireTime(key);
                if (expireTime>30){
                    EHCacheUtil.setValue(cacheName,key,value, (int) (expireTime/2));
                }
            }
        } catch (Exception e) {
            log.error("读取缓存异常",e);
        }
        return value;
    }

    /**
     * 移除缓存
     * @param key
     * @return
     */
    public static boolean remove(String key) {
        boolean re = false;
        try {
            re = EHCacheUtil.removeElment(cacheName, key);
            if (hasRedis) RedisUtils.remove(key);
        } catch (Exception e) {
            log.error("移除缓存异常",e);
        }
        return re;
    }
}
