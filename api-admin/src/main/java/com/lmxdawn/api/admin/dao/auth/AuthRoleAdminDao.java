package com.lmxdawn.api.admin.dao.auth;

import com.lmxdawn.api.admin.entity.auth.AuthRoleAdmin;
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
     * 根据 多个 adminId 查询
     * @param adminIds 传入的 adminIds
     * @return
     */
    List<AuthRoleAdmin> listByAdminIdIn(List<Long> adminIds);

    /**
     * 根据 role_id 查询 admin_id
     * @param roleId 传入的 roleId
     * @return
     */
    List<AuthRoleAdmin> listByRoleId(Long roleId);

    /**
     * 批量插入
     * @param authRoleAdminList
     * @return
     */
    int insertAuthRoleAdminAll(List<AuthRoleAdmin> authRoleAdminList);


    /**
     * 根据 adminId 删除
     * @param adminId
     * @return
     */
    boolean deleteByAdminId(Long adminId);
}
