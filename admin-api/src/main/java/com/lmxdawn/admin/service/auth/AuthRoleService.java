package com.lmxdawn.admin.service.auth;


import com.lmxdawn.admin.entity.auth.AuthRole;
import com.lmxdawn.admin.vo.PageSimpleVO;
import com.lmxdawn.admin.vo.admin.auth.AdminAuthAdminRoleVO;

import java.util.Map;

public interface AuthRoleService {

    PageSimpleVO<AuthRole> listAdminPage(Integer page, Integer limit, Map<String, Object> map);

    PageSimpleVO<AdminAuthAdminRoleVO> listAuthAdminRolePage(Integer page, Integer limit, Map<String, Object> map);

}
