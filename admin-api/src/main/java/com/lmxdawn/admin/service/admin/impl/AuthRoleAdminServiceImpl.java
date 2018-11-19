package com.lmxdawn.admin.service.admin.impl;

import com.lmxdawn.admin.dao.auth.AuthRoleAdminDao;
import com.lmxdawn.admin.entity.AuthRoleAdmin;
import com.lmxdawn.admin.service.admin.AuthRoleAdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AuthRoleAdminServiceImpl implements AuthRoleAdminService {

    @Resource
    private AuthRoleAdminDao authRoleAdminDao;

    @Override
    public List<AuthRoleAdmin> selectByAdminId(Long adminId) {
        return authRoleAdminDao.selectByAdminId(adminId);
    }
}
