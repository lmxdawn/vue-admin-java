package com.lmxdawn.api.admin.form.admin.auth;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * 登录验证
 */
@Data
public class LoginForm {

    @NotEmpty(message = "用户名不能为空")
    private String userName;

    @NotEmpty(message = "密码不能为空")
    private String pwd;

}
