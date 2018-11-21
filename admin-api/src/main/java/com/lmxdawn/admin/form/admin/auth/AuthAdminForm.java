package com.lmxdawn.admin.form.admin.auth;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 管理员的表单类
 */
@Data
public class AuthAdminForm {
    // id
    private Long id;
    // 昵称
    @NotEmpty(message = "请输入用户名")
    private String username;
    // 登录密码
    private String password;
    // 状态
    @NotNull(message = "请选择状态")
    private Integer status;
    // 角色ids
    private List<Long> roles;
}
