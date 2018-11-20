package com.lmxdawn.admin.service.auth.impl;

import com.lmxdawn.admin.dao.auth.AuthRoleAdminDao;
import com.lmxdawn.admin.entity.auth.AuthRoleAdmin;
import com.lmxdawn.admin.service.auth.AuthRoleAdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AuthRoleAdminServiceImpl implements AuthRoleAdminService {

    @Resource
    private AuthRoleAdminDao authRoleAdminDao;

    /**
     * 根据 adminid 获取角色id
     * @param adminId
     * @return
     */
    @Override
    public List<AuthRoleAdmin> listByAdminId(Long adminId) {
        return authRoleAdminDao.listByAdminId(adminId);
    }

    /**
     * 根据 roleId 获取 管理员id
     * @param roleId
     * @return
     */
    @Override
    public List<AuthRoleAdmin> listByRoleId(Long roleId) {
        return authRoleAdminDao.listByRoleId(roleId);
    }
}
