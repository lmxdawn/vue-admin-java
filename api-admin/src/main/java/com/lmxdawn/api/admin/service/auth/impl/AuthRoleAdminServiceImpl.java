package com.lmxdawn.api.admin.service.auth.impl;

import com.lmxdawn.api.admin.dao.auth.AuthRoleAdminDao;
import com.lmxdawn.api.admin.entity.auth.AuthRoleAdmin;
import com.lmxdawn.api.admin.service.auth.AuthRoleAdminService;
import com.lmxdawn.api.common.constant.CacheConstant;
import com.lmxdawn.api.admin.util.CacheUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
     * 根据多个 adminId 查询角色列表
     * @param adminIds
     * @return
     */
    @Override
    public List<AuthRoleAdmin> listByAdminIdIn(List<Long> adminIds) {
        if (adminIds.isEmpty()) {
            return Collections.emptyList();
        }
        return authRoleAdminDao.listByAdminIdIn(adminIds);
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

    /**
     * 批量插入
     * @param authRoleAdminList
     * @return
     */
    @Override
    public int insertAuthRoleAdminAll(List<AuthRoleAdmin> authRoleAdminList) {

        if (authRoleAdminList == null || authRoleAdminList.isEmpty()) {
            return 0;
        }

        return authRoleAdminDao.insertAuthRoleAdminAll(authRoleAdminList);
    }

    /**
     * 根据 角色ids 和 管理员 adminId 批量插入
     * @param roles
     * @param adminId
     * @return
     */
    @Override
    public int insertRolesAdminIdAll(List<Long> roles, Long adminId) {

        List<AuthRoleAdmin> authRoleAdminList = roles.stream().map(aLong -> {
            AuthRoleAdmin authRoleAdmin = new AuthRoleAdmin();
            authRoleAdmin.setRoleId(aLong);
            authRoleAdmin.setAdminId(adminId);
            return authRoleAdmin;
        }).collect(Collectors.toList());
        if (!authRoleAdminList.isEmpty()) {
            return insertAuthRoleAdminAll(authRoleAdminList);
        }

        return 0;
    }

    /**
     * 根据 adminId 删除对应的权限
     * @param adminId
     * @return
     */
    @Override
    public boolean deleteByAdminId(Long adminId) {

        // 删除之前缓存权限规则
        String aarKey = String.format(CacheConstant.ADMIN_AUTH_RULES, adminId);
        CacheUtils.delete(aarKey);

        return authRoleAdminDao.deleteByAdminId(adminId);
    }
}
