package com.yiran.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yiran.common.config.Global;
import com.yiran.common.utils.StringUtils;
import com.yiran.common.utils.spring.SpringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * redicache 工具类
 * 在application.yml文件内的Spring-redis的run配置启动,true为启动;false为不启动;
 *
 * @company
 */
public class RedisUtils {
    /**
     * 日志对象
     */
    private static Logger logger = LoggerFactory.getLogger(RedisUtils.class);

    /**
     * The constant SHIRO_REDIS.
     */
    public static String SHIRO_REDIS="shiro_redis_cache";
    /**
     * The constant SHIRO_REDIS_OBJECT.
     */
    public static String SHIRO_REDIS_OBJECT="org.apache.shiro.subject.SimplePrincipalCollection";
    /**
     * The constant KEY_PREFIX.
     */
    public static String KEY_PREFIX = Global.getConfig("spring.redis.keyPrefix");
    /**
     * The constant expireTime.
     */
    public static Long expireTime= Long.parseLong(Global.getConfig("spring.redis.expireTime"));
    /**
     * The constant RUN_MESSAGE.
     */
    public static String RUN_MESSAGE="请开启Redis服务,还有Redis密码配置是否有误，而且密码不能为空（为空时Redis服务会连接不上）。";
    private static RedisTemplate redisTemplate = SpringUtils.getBean("redisTemplate");
    private static StringRedisTemplate stringRedisTemplate = SpringUtils.getBean("stringRedisTemplate");;

    /**
     * Get spring.redis.expireTime 配置时间.
     *
     * @return the string
     * @date 2018年12月10日 23:05:39
     * @company
     */
    public static String getExpire(){
        if(expireTime!=null) {
            return String.valueOf(expireTime / 60) + "分钟";
        } else {
            return "0分钟";
        }
    }

    /**
     * 获取 key 的有效期（秒）
     *
     * @param key the key
     * @return the long
     * @date 2018年12月10日 23:05:39
     * @company
     */
    public static long getExpireTime(String key){
        if(!run()) { return 0l; }
        long time = redisTemplate.getExpire(key,TimeUnit.SECONDS);
        return time;
    }
    /**
     * 删除缓存<br>
     * 根据key精确匹配删除
     * @param key
     */
    public static void del(String... key){
        if(key!=null && key.length > 0){
            if(key.length == 1){
                redisTemplate.delete(key[0]);
            }else{
                redisTemplate.delete(CollectionUtils.arrayToList(key));
            }
        }
    }

    /**
     * 批量删除<br>
     * （该操作会执行模糊查询，请尽量不要使用，以免影响性能或误删）
     * @param pattern
     */
    public static void batchDel(String... pattern){
        for (String kp : pattern) {
            redisTemplate.delete(redisTemplate.keys(kp + "*"));
        }
    }

    /**
     * 取得缓存（int型）
     * @param key
     * @return
     */
    public static Integer getInt(String key){
        String value = stringRedisTemplate.boundValueOps(key).get();
        if(StringUtils.isNotBlank(value)){
            return Integer.valueOf(value);
        }
        return null;
    }

    /**
     * 取得缓存（字符串类型）
     * @param key
     * @return
     */
    public static String getStr(String key){
        return stringRedisTemplate.boundValueOps(key).get();
    }

    /**
     * 取得缓存（字符串类型）
     * @param key
     * @return
     */
    public static String getStr(String key, boolean retain){
        String value = stringRedisTemplate.boundValueOps(key).get();
        if(!retain){
            redisTemplate.delete(key);
        }
        return value;
    }

    /**
     * 获取缓存<br>
     * 注：基本数据类型(Character除外)，请直接使用get(String key, Class<T> clazz)取值
     * @param key
     * @return
     */
    public static Object getObj(String key){
        return redisTemplate.boundValueOps(key).get();
    }

    /**
     * 获取缓存<br>
     * 注：java 8种基本类型的数据请直接使用get(String key, Class<T> clazz)取值
     * @param key
     * @param retain    是否保留
     * @return
     */
    public static Object getObj(String key, boolean retain){
        Object obj = redisTemplate.boundValueOps(key).get();
        if(!retain){
            redisTemplate.delete(key);
        }
        return obj;
    }

    /**
     * 获取缓存<br>
     * 注：该方法暂不支持Character数据类型
     * @param key   key
     * @param clazz 类型
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T get(String key, Class<T> clazz) {
        return (T)redisTemplate.boundValueOps(key).get();
    }

    /**
     * 获取缓存json对象<br>
     * @param key   key
     * @param clazz 类型
     * @return
     */
    public static <T> T getJson(String key, Class<T> clazz) {
        return JSON.parseObject(stringRedisTemplate.boundValueOps(key).get(),clazz);
    }

    /**
     * 将value对象写入缓存
     * @param key
     * @param value
     * @param time 失效时间(秒)
     */
    public static void set(String key,Object value,long time){
        if(value.getClass().equals(String.class)){
            stringRedisTemplate.opsForValue().set(key, value.toString());
        }else if(value.getClass().equals(Integer.class)){
            stringRedisTemplate.opsForValue().set(key, value.toString());
        }else if(value.getClass().equals(Double.class)){
            stringRedisTemplate.opsForValue().set(key, value.toString());
        }else if(value.getClass().equals(Float.class)){
            stringRedisTemplate.opsForValue().set(key, value.toString());
        }else if(value.getClass().equals(Short.class)){
            stringRedisTemplate.opsForValue().set(key, value.toString());
        }else if(value.getClass().equals(Long.class)){
            stringRedisTemplate.opsForValue().set(key, value.toString());
        }else if(value.getClass().equals(Boolean.class)){
            stringRedisTemplate.opsForValue().set(key, value.toString());
        }else{
            redisTemplate.opsForValue().set(key, value);
        }
        if(time > 0){
            redisTemplate.expire(key, time, TimeUnit.SECONDS);
        }
    }

    /**
     * 将value对象以JSON格式写入缓存
     * @param key
     * @param value
     * @param time 失效时间(秒)
     */
    public static void setJson(String key,Object value,long time){
        stringRedisTemplate.opsForValue().set(key, JSON.toJSONString(value));
        if(time > 0){
            stringRedisTemplate.expire(key, time, TimeUnit.SECONDS);
        }
    }

    /**
     * 更新key对象field的值
     * @param key   缓存key
     * @param field 缓存对象field
     * @param value 缓存对象field值
     */
    public static void setJsonField(String key, String field, String value){
        JSONObject obj = JSON.parseObject(stringRedisTemplate.boundValueOps(key).get());
        obj.put(field, value);
        stringRedisTemplate.opsForValue().set(key, obj.toJSONString());
    }


    /**
     * 递减操作
     * @param key
     * @param by
     * @return
     */
    public static double decr(String key, double by){
        return redisTemplate.opsForValue().increment(key, -by);
    }

    /**
     * 递增操作
     * @param key
     * @param by
     * @return
     */
    public static double incr(String key, double by){
        return redisTemplate.opsForValue().increment(key, by);
    }

    /**
     * 获取double类型值
     * @param key
     * @return
     */
    public static double getDouble(String key) {
        String value = stringRedisTemplate.boundValueOps(key).get();
        if(StringUtils.isNotBlank(value)){
            return Double.valueOf(value);
        }
        return 0d;
    }

    /**
     * 设置double类型值
     * @param key
     * @param value
     * @param time 失效时间(秒)
     */
    public static void setDouble(String key, double value, long time) {
        stringRedisTemplate.opsForValue().set(key, String.valueOf(value));
        if(time > 0){
            stringRedisTemplate.expire(key, time, TimeUnit.SECONDS);
        }
    }

    /**
     * 设置double类型值
     * @param key
     * @param value
     * @param time 失效时间(秒)
     */
    public static void setInt(String key, int value, long time) {
        stringRedisTemplate.opsForValue().set(key, String.valueOf(value));
        if(time > 0){
            stringRedisTemplate.expire(key, time, TimeUnit.SECONDS);
        }
    }

    /**
     * 将map写入缓存
     * @param key
     * @param map
     * @param time 失效时间(秒)
     */
    public static <T> void setMap(String key, Map<String, T> map, long time){
        redisTemplate.opsForHash().putAll(key, map);
    }

    /**
     * 将map写入缓存
     * @param key
     * @param map
     * @param time 失效时间(秒)
     */
    @SuppressWarnings("unchecked")
    public static <T> void setMap(String key, T obj, long time){
        Map<String, String> map = (Map<String, String>) JSON.parseObject(JSON.toJSONString(obj),  Map.class);
        redisTemplate.opsForHash().putAll(key, map);
    }



    /**
     * 向key对应的map中添加缓存对象
     * @param key
     * @param map
     */
    public static <T> void addMap(String key, Map<String, T> map){
        redisTemplate.opsForHash().putAll(key, map);
    }

    /**
     * 向key对应的map中添加缓存对象
     * @param key   cache对象key
     * @param field map对应的key
     * @param value     值
     */
    public static void addMap(String key, String field, String value){
        redisTemplate.opsForHash().put(key, field, value);
    }

    /**
     * 向key对应的map中添加缓存对象
     * @param key   cache对象key
     * @param field map对应的key
     * @param obj   对象
     */
    public static <T> void addMap(String key, String field, T obj){
        redisTemplate.opsForHash().put(key, field, obj);
    }

    /**
     * 获取map缓存
     * @param key
     * @param clazz
     * @return
     */
    public static <T> Map<String, T> mget(String key, Class<T> clazz){
        BoundHashOperations<String, String, T> boundHashOperations = redisTemplate.boundHashOps(key);
        return boundHashOperations.entries();
    }

    /**
     * 获取map缓存
     * @param key
     * @param clazz
     * @return
     */
    public static <T> T getMap(String key, Class<T> clazz){
        BoundHashOperations<String, String, String> boundHashOperations = redisTemplate.boundHashOps(key);
        Map<String, String> map = boundHashOperations.entries();
        return JSON.parseObject(JSON.toJSONString(map), clazz);
    }

    /**
     * 获取map缓存中的某个对象
     * @param key
     * @param field
     * @param clazz
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T getMapField(String key, String field, Class<T> clazz){
        return (T)redisTemplate.boundHashOps(key).get(field);
    }

    /**
     * 删除map中的某个对象
     * @author lh
     * @date 2016年8月10日
     * @param key   map对应的key
     * @param field map中该对象的key
     */
    public void delMapField(String key, String... field){
        BoundHashOperations<String, String, ?> boundHashOperations = redisTemplate.boundHashOps(key);
        boundHashOperations.delete(field);
    }

    /**
     * 设定缓存的失效时间
     *
     * @author FangJun
     * @date 2016年8月14日
     * @param key 缓存KEY
     * @param time 失效时间(秒)
     */
    public static void expire(String key, long time) {
        if(time > 0){
            redisTemplate.expire(key, time, TimeUnit.SECONDS);
        }
    }

    /**
     * 添加set
     * @param key
     * @param value
     */
    public static void sadd(String key, String... value) {
        redisTemplate.boundSetOps(key).add(value);
    }

    /**
     * 删除set集合中的对象
     * @param key
     * @param value
     */
    public static void srem(String key, String... value) {
        redisTemplate.boundSetOps(key).remove(value);
    }

    /**
     * set重命名
     * @param oldkey
     * @param newkey
     */
    public static void srename(String oldkey, String newkey){
        redisTemplate.boundSetOps(oldkey).rename(newkey);
    }

    /**
     * 短信缓存
     * @author fxl
     * @date 2016年9月11日
     * @param key
     * @param value
     * @param time
     */
    public static void setIntForPhone(String key,Object value,int time){
        stringRedisTemplate.opsForValue().set(key, JSON.toJSONString(value));
        if(time > 0){
            stringRedisTemplate.expire(key, time, TimeUnit.SECONDS);
        }
    }

    /**
     * 模糊查询keys
     * @param pattern
     * @return
     */
    public static Set<String> keys(String pattern){
        return redisTemplate.keys(pattern);
    }


    /**
     * 批量删除对应的value
     *
     * @param keys the keys
     * @author PanMeiCheng
     * @date 2018年12月10日 23:05:39
     * @company
     */
    public void remove(final String... keys) {
        if(!run()) { return; }
        try{
            for (String key : keys) {
                remove(key);
            }
        } catch (Exception e) {
            logger.error("RedisUtils remove:"+RUN_MESSAGE+e.getMessage(), RUN_MESSAGE+e.getMessage());
        }
    }

    /**
     * 批量删除key
     *
     * @param pattern the pattern
     * @author PanMeiCheng
     * @date 2018年12月10日 23:05:40
     * @company
     */
    public static void removePattern(String pattern) {
        if(!run()) { return;}
        if(!listFlush()){
            return ;
        }
        try{
            if(pattern==null) {
                pattern = "";
            }
            Set<String> keys=getKyes(pattern);
            if (keys.size() > 0) {
                redisTemplate.delete(keys);
            }
        } catch (Exception e) {
            logger.error("RedisUtils removePattern:"+RUN_MESSAGE+e.getMessage(),RUN_MESSAGE+ e.getMessage());
        }
    }

    /**
     * Remove pattern shiro reids.
     *
     * @param pattern the pattern
     * @author PanMeiCheng
     * @date 2018年12月10日 23:05:40
     * @company
     */
    public static void removePatternShiroReids(String pattern){
        if(!run()) { return;}
        if(!listFlush()){
            return ;
        }
        try{
            if(pattern==null) {
                pattern = "";
            }
            Set<String> keys=getKyesShiroReids(pattern);
            if (keys.size() > 0){
                stringRedisTemplate.delete(keys);
            }
        } catch (Exception e) {
            logger.error("RedisUtils removePattern:"+RUN_MESSAGE+e.getMessage(),RUN_MESSAGE+ e.getMessage());
        }
    }

    /**
     * 获取keys
     *
     * @param pattern the pattern
     * @return the kyes
     */
    public static Set<String> getKyes(String pattern) {
        if(!run()) { return null; }
        try{
            if(pattern==null){ pattern=""; }
            Set<String> keys=stringRedisTemplate.keys("*"+pattern);
            Set<String> keysnew=new HashSet<String>();
            Iterator<String> it = keys.iterator();
            while (it.hasNext()) {
                keysnew.add(it.next().substring(7));
            }
           return keysnew;
        } catch (Exception e) {
            logger.error("RedisUtils getKyes:"+RUN_MESSAGE+e.getMessage(), e.getMessage());
            return null;
        }
    }

    /**
     * Gets kyes shiro reids.
     *
     * @param pattern the pattern
     * @return the kyes shiro reids
     */
    public static Set<String> getKyesShiroReids(String pattern) {
        if(!run()) { return null; }
        try{
            if(pattern==null){ pattern=""; }
            Set<String> keys=stringRedisTemplate.keys("*"+pattern);
            Set<String> keysnew=new HashSet<String>();
            Iterator<String> it = keys.iterator();
            while (it.hasNext()) {
                String tr = it.next();
                if(tr.contains(SHIRO_REDIS)) {
                    keysnew.add(tr);
                } else if(tr.contains(SHIRO_REDIS_OBJECT)) {
                    keysnew.add(tr.substring(8));
                }
            }
            return keysnew;
        } catch (Exception e) {
            logger.error("RedisUtils getKyes:"+RUN_MESSAGE+e.getMessage(), e.getMessage());
            return null;
        }
    }

    /**
     * Gets kyes all.
     *
     * @return the kyes all
     */
    public static Set<String> getKyesAll() {
        if(!run()) { return null; }
        try{
            Set<String> keys=stringRedisTemplate.keys("*");
            Set<String> keysnew=new HashSet<String>();
            Iterator<String> it = keys.iterator();
            while (it.hasNext()) {
                keysnew.add(it.next());
            }
            return keysnew;
        } catch (Exception e) {
            logger.error("RedisUtils getKyes:"+RUN_MESSAGE+e.getMessage(), e.getMessage());
            return null;
        }
    }

    /**
     * 获取Count
     *
     * @return the count
     */
    public static int getCount() {
        if(!run()) { return 0; }
        try{
            Set<String> keys=stringRedisTemplate.keys("*");
            return keys.size();
        } catch (Exception e) {
            logger.error("RedisUtils getCount:"+RUN_MESSAGE+e.getMessage(), e.getMessage());
            return 0;
        }
    }

    /**
     * Gets count shiro.
     *
     * @return the count shiro
     */
    public static int getCountShiro() {
        if(!run()||!isShireRedis()) { return 0; }
        try{
            Set<String> keys=stringRedisTemplate.keys(SHIRO_REDIS+"*");
            return keys.size();
        } catch (Exception e) {
            logger.error("RedisUtils getCount:"+RUN_MESSAGE+e.getMessage(), e.getMessage());
            return 0;
        }
    }

    /**
     * 删除对应的value
     *
     * @param key the key
     * @author PanMeiCheng
     * @date 2018年12月10日 23:05:40
     * @company
     */
    public static void remove(final String key) {
        if(!run()) { return ; }
        try{
            if(key.contains(SHIRO_REDIS)) {
                stringRedisTemplate.delete(key);
            }else{
                redisTemplate.delete(key);
            }
        } catch (Exception e) {
            logger.error("RedisUtils exists:"+RUN_MESSAGE+e.getMessage(), RUN_MESSAGE+e.getMessage());
        }
    }

    /**
     * 判断缓存中是否有对应的key
     *
     * @param key the key
     * @return boolean
     * @author PanMeiCheng
     * @date 2018年12月10日 23:05:40
     * @company
     */
    public static boolean exists(final String key) {
        if(!run()) { return false; }
        boolean retuslt=false;
        try{
            if(key.contains(SHIRO_REDIS)) {
                retuslt = stringRedisTemplate.hasKey(key);
            } else {
                retuslt = redisTemplate.hasKey(key);
            }
        } catch (Exception e) {
            logger.error("RedisUtils exists:"+RUN_MESSAGE+e.getMessage(), RUN_MESSAGE+e.getMessage());
        }
        return retuslt;
    }

    /**
     * 读取缓存
     *
     * @param key the key
     * @return object
     * @author PanMeiCheng
     * @date 2018年12月10日 23:05:40
     * @company
     */
    public static Object get(final String key) {
        if(!run()) { return null; }
        Object result = null;
        try{
            if(key.contains(SHIRO_REDIS)){
                ValueOperations<String, String> operationsString = stringRedisTemplate.opsForValue();
                result = operationsString.get(key);
            }else {
                ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
                result = operations.get(key);
            }
            return result;
        }catch (Exception e){
            logger.error("RedisUtils get:"+RUN_MESSAGE+e.getMessage(), RUN_MESSAGE+e.getMessage()+e);
        }
        return result;
    }

    /**
     * 写入缓存
     *
     * @param key   the key
     * @param value the value
     * @return boolean
     * @author PanMeiCheng
     * @date 2018年12月10日 23:05:40
     * @company
     */
    public static boolean set(final String key, Object value) {
        if(!run()) { return false; }
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
            result = true;
        } catch (Exception e) {
            logger.error("RedisUtils set:"+RUN_MESSAGE+e.getMessage(), RUN_MESSAGE+e.getMessage());
        }
        return result;
    }

    /**
     * 写入缓存
     *
     * @param key        the key
     * @param value      the value
     * @param expireTime the expire time
     * @return boolean
     * @author PanMeiCheng
     * @date 2018年12月10日 23:05:40
     * @company
     */
    public static boolean set(final String key, Object value, Long expireTime) {
        if(!run()) { return false; }
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
            result = true;
        } catch (Exception e) {
            logger.error("RedisUtils set:"+RUN_MESSAGE+e.getMessage(), RUN_MESSAGE+e.getMessage());
        }
        return result;
    }

    /**
     * Set boolean.
     *
     * @param key        the key
     * @param value      the value
     * @param expireTime the expire time
     * @param unit       the unit
     * @return the boolean
     * @author PanMeiCheng
     * @date 2018年12月10日 23:05:40
     * @company
     */
    public boolean set(final String key, Object value, Long expireTime,TimeUnit unit) {
        if(!run()) { return false; }
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            redisTemplate.expire(key, expireTime, unit);
            result = true;
        } catch (Exception e) {
            logger.error("RedisUtils set:"+RUN_MESSAGE+e.getMessage(), RUN_MESSAGE+e.getMessage());
        }
        return result;
    }

    private static boolean run(){
        if(Global.hasRedis()) {
            return true;
        }
        logger.info("未启用spring.redis.run");
        return false;
    }

    /**
     * Is run boolean.
     *
     * @return the boolean
     * @author PanMeiCheng
     * @date 2018年12月10日 23:05:40
     * @company
     */
    public static boolean isRun(){
        if(Global.hasRedis()) {
            return true;
        }
        logger.info("未启用spring.redis.run");
        return false;
    }

    /**
     * Is shire redis boolean.
     *
     * @return the boolean
     * @author PanMeiCheng
     * @date 2018年12月10日 23:05:41
     * @company
     */
    public static boolean isShireRedis(){
        if(!isRun()) {
            return false;
        }
        if(Global.hasRedis()) {
            logger.info("未启用shiro.redis");
            return false;
        }
        return true;
    }

    private static boolean listFlush(){
        if(Global.hasRedis()) {
            return true;
        }
        logger.info("未启用spring.redis.listFlush");
        return false;
    }
}