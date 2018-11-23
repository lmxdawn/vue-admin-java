package com.lmxdawn.api.admin.form.auth;

import lombok.Data;

/**
 * 角色的提交保存表单
 */
@Data
public class AuthRoleSaveForm {
    private Long id;
    private String name;
    private Long pid;
    private Long status;
    private String remark;
    private Long listorder;
}
