package com.lmxdawn.admin.service.auth;


import com.lmxdawn.admin.entity.auth.AuthRoleAdmin;

import java.util.List;

public interface AuthRoleAdminService {

    List<AuthRoleAdmin> listByAdminId(Long adminId);

    List<AuthRoleAdmin> listByRoleId(Long roleId);

}
