package com.lmxdawn.api.admin.form.auth;

import com.lmxdawn.api.admin.form.ListPageForm;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class AuthAdminQueryForm extends ListPageForm {

    private String username;

    private Integer status;

    private Long roleId;

    private List<Long> ids;

}
