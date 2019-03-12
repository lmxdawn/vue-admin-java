package com.lmxdawn.api.admin.res.auth;

import lombok.Data;

import java.util.List;

/**
 * 登录用户的信息视图
 */
@Data
public class LoginUserInfoResponse {

    private Long id;
    private String username;
    private String avatar;
    // 权限列表
    private List<String> authRules;
}
