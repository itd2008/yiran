package com.yiran.api.facade;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.yiran.api.form.LoginForm;

import com.alibaba.fastjson.JSON;
import com.yiran.common.base.ResultWrapper;
import com.yiran.common.config.Global;
import com.yiran.framework.shiro.service.SysPasswordService;
import com.yiran.framework.util.JwtUtils;
import com.yiran.framework.web.base.BaseController;
import com.yiran.system.domain.SysUser;
import com.yiran.system.service.ISysUserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/yiran")
@Api(value="登录接口",description="系统登录接口")
public class LoginFacade  extends BaseController{
	@Autowired
    private ISysUserService sysUserService;
    @Autowired
    private SysPasswordService sysPasswordService;
    @Autowired
    @Qualifier("taskExecutor")
    private ThreadPoolTaskExecutor taskExecutor;
	@PostMapping("/loginUser")
    @ApiOperation(value = "登录",notes="登录")
    public ResultWrapper<Map<String,Object>> login(@RequestBody LoginForm form) throws Exception{
    	Map<String, Object> map = new HashMap<>();
    	System.out.println("api登录请求参数："+JSON.toJSONString(form));
    	//生成token
        String app_token =null;
        //用户登录
    	SysUser user = sysUserService.selectUserByPhoneNumber(form.getMobile());
    	if(user!=null){
    		String password = sysPasswordService.encryptPassword(user.getLoginName(), form.getPassword(), user.getSalt());
    		System.out.println("加密后的密码："+password);
    		if(!password.equals(user.getPassword())){
    			return ResultWrapper.error().newInstance("0003", "登录密码错误");
    		}
    		app_token = JwtUtils.createToken(user.getUserName());
    		map.put("token", app_token);
            map.put("expire", Global.getJwtOutTime());
            map.put("user", user);
    	}else{
    		return ResultWrapper.error().newInstance("0003", "用户名不存在");
    	}
        return ResultWrapper.ok().putData(map);
    }
}
