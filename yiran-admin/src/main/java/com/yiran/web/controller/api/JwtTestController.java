package com.yiran.web.controller.api;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.yiran.common.base.ResultWrapper;
import com.yiran.common.config.Global;
import com.yiran.common.page.TableDataInfo;
import com.yiran.framework.shiro.service.SysPasswordService;
import com.yiran.framework.util.JwtUtils;
import com.yiran.framework.web.base.BaseController;
import com.yiran.system.domain.SysUser;
import com.yiran.system.service.ISysUserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.WebAsyncTask;
import javax.servlet.http.HttpServletRequest;
@RestController
@RequestMapping("/jwt/test")
@Api(value="JWT示范接口", description="JWT示范接口")
public class JwtTestController extends BaseController {

	
    @PostMapping(value = "/test2")
    @ApiOperation(value="测试WebAsyncTask", notes="测试WebAsyncTask")
    public WebAsyncTask test2(HttpServletRequest request){
        WebAsyncTask test2 = new WebAsyncTask( () -> {
            String test = request.getParameter("test");
            System.out.println("dddddddddd");
            return "5464";
        });
        return test2;
    }
    @PostMapping(value = "/test3")
    @ApiOperation(value="测试body", notes="测试body")
    public WebAsyncTask test3(HttpServletRequest request,@RequestBody String bodyData){
        WebAsyncTask test2 = new WebAsyncTask( () -> {
            String test = request.getParameter("test");
            System.out.println("dddddddddd");
            return bodyData;
        });
        return test2;
    }
    @PostMapping(value = "/test4")
    @ApiOperation(value="测试beanBody", notes="测试beanBody")
    public WebAsyncTask test4(HttpServletRequest request,@RequestBody SysUser SysUser){
        WebAsyncTask test2 = new WebAsyncTask( () -> {
            return SysUser;
        });
        return test2;
    }
    @PostMapping(value = "/test5")
    @ApiOperation(value="测试bean", notes="测试bean")
    public WebAsyncTask test5(HttpServletRequest request, SysUser SysUser){
        WebAsyncTask test2 = new WebAsyncTask( () -> {
            return SysUser;
        });
        return test2;
    }

}