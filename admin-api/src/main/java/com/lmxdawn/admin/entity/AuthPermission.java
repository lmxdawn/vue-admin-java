package com.lmxdawn.admin.entity;

import lombok.Data;

/**
 * 权限授权表
 */
@Data
public class AuthPermission {

    private Long roleId;

    private Long permissionRuleId;

    private String type;

}
