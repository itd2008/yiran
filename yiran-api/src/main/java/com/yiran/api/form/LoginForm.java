package com.yiran.api.form;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 登录表单
 *
 */
@ApiModel(value = "登录表单")
public class LoginForm {
    @ApiModelProperty(value = "手机号")
    @NotBlank(message="手机号不能为空")
    private String mobile;

    @ApiModelProperty(value = "密码")
    @NotBlank(message="密码不能为空")
    private String password;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
