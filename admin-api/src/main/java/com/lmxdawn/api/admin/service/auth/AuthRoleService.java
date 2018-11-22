package com.lmxdawn.api.admin.service.auth;


import com.lmxdawn.api.admin.entity.auth.AuthRole;
import com.lmxdawn.api.admin.vo.PageSimpleVO;
import com.lmxdawn.api.admin.vo.auth.AuthAdminRoleVO;

import java.util.Map;

public interface AuthRoleService {

    PageSimpleVO<AuthRole> listAdminPage(Integer page, Integer limit, Map<String, Object> map);

    PageSimpleVO<AuthAdminRoleVO> listAuthAdminRolePage(Integer page, Integer limit, Map<String, Object> map);

}
