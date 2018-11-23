package com.lmxdawn.api.admin.vo.auth;

import lombok.Data;

/**
 * 角色视图
 */
@Data
public class AuthRoleVO {

    private Long id;
    private String name;
    private Long pid;
    private Long status;
    private String remark;
    private Long listorder;

}
