package com.lmxdawn.admin.service.admin.impl;

import com.lmxdawn.admin.dao.auth.AuthPermissionDao;
import com.lmxdawn.admin.entity.AuthPermission;
import com.lmxdawn.admin.service.admin.AuthPermissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AuthPermissionServiceImpl implements AuthPermissionService {

    @Resource
    private AuthPermissionDao authPermissionDao;

    @Override
    public List<AuthPermission> selectByRoleIdIn(List<Long> roleIds) {
        return authPermissionDao.selectByRoleIdIn(roleIds);
    }
}
