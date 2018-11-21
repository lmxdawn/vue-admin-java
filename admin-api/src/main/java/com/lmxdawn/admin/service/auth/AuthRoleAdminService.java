package com.lmxdawn.admin.service.auth;


import com.lmxdawn.admin.entity.auth.AuthRoleAdmin;

import java.util.List;

public interface AuthRoleAdminService {

    List<AuthRoleAdmin> listByAdminId(Long adminId);

    List<AuthRoleAdmin> listByAdminIdIn(List<Long> adminIds);

    List<AuthRoleAdmin> listByRoleId(Long roleId);

    int insertAuthRoleAdminAll(List<AuthRoleAdmin> authRoleAdminList);

    int insertRolesAdminIdAll(List<Long> roles, Long adminId);

    boolean deleteByAdminId(Long adminId);

}
