package com.lmxdawn.api.admin.service.auth.impl;

import com.lmxdawn.api.admin.dao.auth.AuthPermissionDao;
import com.lmxdawn.api.admin.entity.auth.AuthPermission;
import com.lmxdawn.api.admin.service.auth.AuthPermissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AuthPermissionServiceImpl implements AuthPermissionService {

    @Resource
    private AuthPermissionDao authPermissionDao;

    @Override
    public List<AuthPermission> listByRoleIdIn(List<Long> roleIds) {
        return authPermissionDao.listByRoleIdIn(roleIds);
    }
}
