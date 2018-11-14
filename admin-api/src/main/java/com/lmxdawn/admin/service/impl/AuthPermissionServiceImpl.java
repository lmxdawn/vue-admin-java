package com.lmxdawn.admin.service.impl;

import com.lmxdawn.admin.dao.AuthPermissionDao;
import com.lmxdawn.admin.entity.AuthPermission;
import com.lmxdawn.admin.service.AuthPermissionService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AuthPermissionServiceImpl implements AuthPermissionService {

    @Autowired
    private AuthPermissionDao authPermissionDao;

    @Override
    public List<AuthPermission> findByRoleIdIn(List<Long> roleIds) {
        return authPermissionDao.findByRoleIdIn(roleIds);
    }
}
