package com.lmxdawn.admin.service.admin;


import com.lmxdawn.admin.entity.AuthRoleAdmin;

import java.util.List;

public interface AuthRoleAdminService {
    /**
     * 根据 adminid 获取角色id
     * @param adminId
     * @return
     */
    List<AuthRoleAdmin> selectByAdminId(Long adminId);

}
