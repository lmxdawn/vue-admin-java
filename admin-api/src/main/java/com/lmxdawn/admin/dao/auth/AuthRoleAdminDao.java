package com.lmxdawn.admin.dao.auth;

import com.lmxdawn.admin.entity.auth.AuthRoleAdmin;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface AuthRoleAdminDao {

    /**
     * 根据 adminId 查询
     * @param adminId 传入的 adminId
     * @return
     */
    List<AuthRoleAdmin> listByAdminId(Long adminId);

    /**
     * 根据 role_id 查询 admin_id
     * @param roleId 传入的 roleId
     * @return
     */
    List<AuthRoleAdmin> listByRoleId(Long roleId);

}
