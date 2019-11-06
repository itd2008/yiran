package com.yiran.web.controller.api;

import com.yiran.common.base.AjaxResult;
import com.yiran.common.config.Global;
import com.yiran.common.utils.RedisUtils;
import com.yiran.framework.web.base.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/rest/redis")
@Api(value="Redis云数据缓存接口", description="Redis云数据缓存接口")
public class RedisRestController extends BaseController {


    @RequestMapping(value = "/test",method ={RequestMethod.POST})
    @ApiOperation(value="Redis Test信息(Content-Type为text/html)", notes=" 测试Redis是否正常")
    public String  test(){
        RedisUtils.set("Redis Test", "Redis Test");
        String string= RedisUtils.get("Redis Test").toString();
        return string;
    }

    /**
     * 批量删除对应的key
     *
     * @param keys
     */
    @RequestMapping(value ="/removeList",method ={RequestMethod.POST})
    @ApiOperation(value="Redis remove接口(Content-Type为text/html)", notes="批量删除对应的key. keys=xx,xx,xx")
    public AjaxResult removeList(@RequestParam(required=false) String keys) {
        String [] keysList=keys.split(",");
        for(String key:keysList) {
            RedisUtils.remove(key);
        }
        return success("移除成功");
    }

    /**
     * 批量删除key
     *
     * @param pattern
     */
    @RequestMapping(value ="/removePattern",method ={RequestMethod.POST})
    @ApiOperation(value="Redis remove pattern接口(Content-Type为text/html)", notes="批量删除key. pattern匹配")
    public AjaxResult removePattern(@RequestParam(required=false) String pattern) {
        RedisUtils.removePattern(pattern);
        return success("移除成功！");
    }


    /**
     * 判断缓存中是否有对应的key
     *
     * @param key
     * @return
     */
    @RequestMapping(value ="/exists",method ={RequestMethod.POST})
    @ApiOperation(value="Redis exists接口(Content-Type为text/html)", notes="判断缓存中是否有对应的key=?")
    public AjaxResult exists(@RequestParam(required=false) String key) {
        if(RedisUtils.exists(key)) {
            return success("存在！");
        } else {
            return success("不存在！");
        }
    }

    /**
     * 读取缓存
     *
     * @param key
     * @return
     */
    @RequestMapping(value ="/get",method ={RequestMethod.GET})
    @ApiOperation(value="Redis get接口(Content-Type为text/html)", notes="读取缓存 key=?")
    public AjaxResult get(@RequestParam(required=false) String key) {
        if(RedisUtils.exists(key)){
            Object obj=RedisUtils.get(key);
            if(obj!=null) {
            	return AjaxResult.success(obj);
            }
        }else{
            return error("不存在！");
        }
		return null;
    }

    /**
     * 写入缓存
     *
     * @param key
     * @param value
     * @return
     */
    @RequestMapping(value ="/set",method ={RequestMethod.POST})
    @ApiOperation(value="Redis set接口(Content-Type为text/html)", notes="写入缓存 key=?&value=?")
    public AjaxResult set(@RequestParam(required=false) String key, @RequestParam(required=false)  String value) {
        if(RedisUtils.set(key,value)){
            AjaxResult result = success("添加/更新成功！");
            return result;
        }else{
            return error("添加/更新失败！");
        }
    }

    /**
     * 写入缓存
     *
     * @param key
     * @param value
     * @return
     */
    @RequestMapping(value ="/setExpireTime",method ={RequestMethod.POST})
    @ApiOperation(value="Redis set expireTime接口(Content-Type为text/html)", notes="写入缓存 key=?&value=?&expireTime=123")
    public AjaxResult set(@RequestParam(required=false) String key, @RequestParam(required=false) String value, @RequestParam(required=false) Long expireTime) {
        if(RedisUtils.set(key,value,expireTime)){
            AjaxResult result = success("添加/更新成功！");
            return result;
        }else{
            return error("添加/更新失败！");
        }
    }

    /**
     * 获取keys
     *
     * @param pattern
     */
    @RequestMapping(value ="/getKyes",method ={RequestMethod.POST})
    @ApiOperation(value="Redis get kyes接口(Content-Type为text/html)", notes="获取keys pattern=?")
    public AjaxResult getKyes(@RequestParam(required=false) String pattern) {
        Set<String> keys = RedisUtils.getKyes(pattern);
        AjaxResult result = success("获取Keys成功！");
        result.put("data",keys);
        return result;
    }

    @RequestMapping(value ="/getKyesAll",method ={RequestMethod.POST})
    @ApiOperation(value="Redis get kyes all接口(Content-Type为text/html)", notes="获取所有keys")
    public AjaxResult getKyesAll() {
        Set<String> keys = RedisUtils.getKyesAll();
        AjaxResult result = success("获取Keys成功！");
        result.put("data",keys);
        return result;
    }

    /**
     * 获取keys 数量
     *
     * @param pattern
     */
    @RequestMapping(value ="/getCount",method ={RequestMethod.POST})
    @ApiOperation(value="Redis get count接口(Content-Type为text/html)", notes="获取keys 数量")
    public AjaxResult getCount(@RequestParam(required=false) String pattern) {
        AjaxResult result = success("获取数量成功！");
        result.put("data",RedisUtils.getCount());
        return result;
    }

    @RequestMapping(value ="/getCountShiro",method ={RequestMethod.POST})
    @ApiOperation(value="Redis get count shiro接口(Content-Type为text/html)", notes="获取shiro缓存数量")
    public AjaxResult getCountShiro(@RequestParam(required=false) String pattern) {
        AjaxResult result = success("获取数量成功！");
        result.put("data",RedisUtils.getCountShiro());
        return result;
    }

    /**
     * 获取key的有效期（秒）
     * @param key
     */
    @RequestMapping(value ="/getExpireTime",method ={RequestMethod.POST})
    @ApiOperation(value="Redis get ExpireTime接口(Content-Type为text/html)", notes="获取key的有效期（秒）key=?")
    public AjaxResult getExpireTime(@RequestParam(required=false) String key){
        AjaxResult result = success("获取token的有效期（秒）成功！");
        result.put("data",RedisUtils.getExpireTime(key));
        return result;
    }

    /**
     * 获取缓存有效期成功
     */
    @RequestMapping(value ="/getExpire",method ={RequestMethod.POST})
    @ApiOperation(value="Redis get getExpire(Content-Type为text/html)", notes="获取配置的缓存有效期")
    public AjaxResult getExpire(){
        AjaxResult result = success("获取缓存有效期成功！");
        result.put("data",RedisUtils.getExpire());
        return result;
    }

    /**
     * 获取单点登录缓存有效期成功
     */
    @RequestMapping(value ="/getExpireShiro",method ={RequestMethod.POST})
    @ApiOperation(value="Redis get getExpireShiro(Content-Type为text/html)", notes="获取配置的shiro缓存有效期")
    public AjaxResult getExpireShiro(){
        AjaxResult result = success("获取单点登录缓存有效期成功！");
        result.put("data", Global.getConfig("shiro.redis.expireTimeShiro"));
        return result;
    }
}