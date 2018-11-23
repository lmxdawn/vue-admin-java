package com.lmxdawn.api.admin.form.auth;

import com.lmxdawn.api.admin.form.ListPageForm;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 角色的查询表单
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AuthRoleQueryForm extends ListPageForm {
    private String name;
    private Integer status;

}
