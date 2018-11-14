package com.lmxdawn.admin.service;


import com.lmxdawn.admin.entity.AuthPermission;

import java.util.List;

public interface AuthPermissionService {


    List<AuthPermission> findByRoleIdIn(List<Long> roleIds);

}
