package com.lmxdawn.api.admin.service.auth;


import com.lmxdawn.api.admin.entity.auth.AuthPermission;

import java.util.List;

public interface AuthPermissionService {


    List<AuthPermission> listByRoleIdIn(List<Long> roleIds);

}
