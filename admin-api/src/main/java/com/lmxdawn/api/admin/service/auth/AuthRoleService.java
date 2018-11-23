package com.lmxdawn.api.admin.service.auth;


import com.lmxdawn.api.admin.entity.auth.AuthRole;
import com.lmxdawn.api.admin.form.auth.AuthRoleQueryForm;

import java.util.List;

public interface AuthRoleService {

    List<AuthRole> listAdminPage(AuthRoleQueryForm authRoleQueryForm);

    List<AuthRole> listAuthAdminRolePage(Integer page, Integer limit, Integer status);

    AuthRole findByName(String name);

    boolean insertAuthRole(AuthRole authRole);

    boolean updateAuthRole(AuthRole authRole);

    boolean deleteById(Long id);

}
