package com.yiran.common.enums;

/**
 * JWT 接口返回状态
 * @date 2018-12-20
 * @company DM
 */
public enum JwtType {
    /**token无效*/
    permissionNo(100,"token无效"),
    /**token有效*/
    permissionOk(200,"token有效"),
    /**登录成功*/
    loginSuccess(200,"登录成功" ),
    /**Shiro在线*/
    permissionShiroOk(201,"Shiro在线"),
    /**系统错误*/
    error(500,"系统错误");
    private int code;
    private String msg;

    JwtType(int code, String msg){
        this.code = code;
        this.msg = msg;
    }
    public int code(){
        return this.code;
    }
    public String msg(){
        return this.msg;
    }
}
