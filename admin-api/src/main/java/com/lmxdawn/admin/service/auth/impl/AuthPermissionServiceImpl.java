package com.lmxdawn.admin.service.auth.impl;

import com.lmxdawn.admin.dao.auth.AuthPermissionDao;
import com.lmxdawn.admin.entity.auth.AuthPermission;
import com.lmxdawn.admin.service.auth.AuthPermissionService;
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
