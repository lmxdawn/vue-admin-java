package com.lmxdawn.admin.service.admin;


import com.lmxdawn.admin.entity.AuthPermission;

import java.util.List;

public interface AuthPermissionService {


    List<AuthPermission> selectByRoleIdIn(List<Long> roleIds);

}
