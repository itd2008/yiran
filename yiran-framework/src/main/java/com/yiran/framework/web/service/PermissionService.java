package com.yiran.framework.web.service;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;

/**
 * yiran首创 js调用 thymeleaf 实现按钮权限可见性
 * 
 * @author yiran
 */
@Service("permission")
public class PermissionService
{
    public String hasPermi(String permission)
    {
        return isPermittedOperator(permission) ? "" : "hidden";
    }

    private boolean isPermittedOperator(String permission)
    {
        return SecurityUtils.getSubject().isPermitted(permission);
    }
}
