package com.yiran.common.base;

import com.alibaba.fastjson.JSON;
import com.yiran.common.enums.JwtType;

import java.util.HashMap;

/**
 * 操作消息提醒
 * 
 * @author yiran
 */
public class AjaxResult extends HashMap<String, Object>
{
    private static final long serialVersionUID = 1L;

    /**
     * 初始化一个新创建的 Message 对象
     */
    public AjaxResult()
    {
    }

    /**
     * 返回错误消息
     * 
     * @return 错误消息
     */
    public static AjaxResult error()
    {
        return error(1, "操作失败");
    }

    /**
     * 返回错误消息
     * 
     * @param msg 内容
     * @return 错误消息
     */
    public static AjaxResult error(String msg)
    {
        return error(500, msg);
    }

    /**
     * 返回错误消息
     * 
     * @param code 错误码
     * @param msg 内容
     * @return 错误消息
     */
    public static AjaxResult error(int code, String msg)
    {
        AjaxResult json = new AjaxResult();
        json.put("code", code);
        json.put("msg", msg);
        return json;
    }

    /**
     * JWT返回专用
     * @param jwtType
     * @param data
     * @return
     */
    public static AjaxResult jwtResult(JwtType jwtType, Object data)
    {
        AjaxResult result = new AjaxResult();
        result.put("code", jwtType.code());
        result.put("msg", jwtType.msg());
        result.put("data", data);
        return result;
    }
    public static AjaxResult jwtResult(JwtType jwtType)
    {
        AjaxResult result = new AjaxResult();
        result.put("code", jwtType.code());
        result.put("msg", jwtType.msg());
        return result;
    }

    /**
     * 返回成功消息
     * 
     * @param msg 内容
     * @return 成功消息
     */
    public static AjaxResult success(String msg)
    {
        AjaxResult json = new AjaxResult();
        json.put("msg", msg);
        json.put("code", 0);
        return json;
    }
    /**
     * 返回成功消息
     *
     * @param msg 内容
     * @param obj 内容
     * @return 成功消息
     */
    public static AjaxResult success(String msg,Object obj)
    {
        AjaxResult json = new AjaxResult();
        json.put("msg", msg);
        json.put("code", 0);
        json.put("data", obj);
        return json;
    }
    /**
     * 返回成功消息
     *
     * @param obj 内容
     * @return 成功消息
     */
    public static AjaxResult success(Object obj)
    {
        AjaxResult json = new AjaxResult();
        json.put("msg", "操作成功");
        json.put("code", 0);
        json.put("data", obj);
        return json;
    }

    /**
     * 返回成功消息
     * 
     * @return 成功消息
     */
    public static AjaxResult success()
    {
        return AjaxResult.success("操作成功");
    }

    /**
     * 返回成功消息
     * 
     * @param key 键值
     * @param value 内容
     * @return 成功消息
     */
    @Override
    public AjaxResult put(String key, Object value)
    {
        super.put(key, value);
        return this;
    }

    /**
     * 转为json字符串
     * @return
     */
    public String json() {
        return JSON.toJSONString(this);
    }
}
