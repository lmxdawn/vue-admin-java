package com.lmxdawn.admin.dto;

/**
 * 登录用户的信息
 */
public class LoginUserInfoDTO {

    private Long id;
    private String username;
    private String avatar;
    // 权限列表
    private String[] authRules;
}
