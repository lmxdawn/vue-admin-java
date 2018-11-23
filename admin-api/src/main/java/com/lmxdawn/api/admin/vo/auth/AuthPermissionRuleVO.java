package com.lmxdawn.api.admin.vo.auth;

import lombok.Data;

/**
 * 权限规则的视图
 */
@Data
public class AuthPermissionRuleVO {

    private Long id;
    private Long pid;
    private String name;
    private String title;
    private Long status;
    private String condition;
    private Long listorder;

}
