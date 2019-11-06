package com.yiran.framework.config;

import com.alibaba.fastjson.JSON;
import com.yiran.common.base.AjaxResult;
import com.yiran.common.enums.JwtType;
import com.yiran.common.utils.EHCacheUtil;
import com.yiran.common.utils.ServletUtils;
import com.yiran.common.utils.StringUtils;
import com.yiran.framework.util.JwtUtils;
import com.yiran.system.domain.SysUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *Jwt 接口授权拦截器
 *@version 1.0
 */
@Component
public class JwtApiInterceptor implements HandlerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(JwtApiInterceptor.class);
    /**
     * 在请求处理之前进行调用（Controller方法调用之前）
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler) throws Exception {
        //进行token验证
        String token = request.getHeader("token");
        Object cache = EHCacheUtil.get(token);
        if (cache!=null){
            //缓存里已经能取到,说明token是正确有效的 ,缓存有效时间和token的一样
            //用缓存为什么用还用jwt? -- 可以减轻redis服务器压力以及jwt的计算压力 业务逻辑里取本地缓存的用户信息
            return true;
        }
        String verifyStr = JwtUtils.verifyToken(token);
        if (StringUtils.isNotBlank(verifyStr)){
            //缓存这个token的用户信息
            long liveTime = JwtUtils.getLiveTime(token);
            if (liveTime<2000) {//快过期了
                ServletUtils.renderString(response, AjaxResult.jwtResult(JwtType.permissionNo).json());
                return false;
            }
            SysUser tokenSysUser = JSON.parseObject(verifyStr, SysUser.class);
            EHCacheUtil.setValue("jwt",token,tokenSysUser, (int) liveTime/1000);
        }else{
            //token无效
            ServletUtils.renderString(response, AjaxResult.jwtResult(JwtType.permissionNo).json());
            return false;
        }
        return true;
    }

    /**
     * 请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）
     */
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler, ModelAndView modelAndView) throws Exception {

    }

    /**
     * 在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）
     */
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler, Exception e) throws Exception {

    }

}
