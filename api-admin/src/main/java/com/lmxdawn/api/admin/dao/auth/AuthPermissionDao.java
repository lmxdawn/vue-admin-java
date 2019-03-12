package com.lmxdawn.api.admin.dao.auth;

import com.lmxdawn.api.admin.entity.auth.AuthPermission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AuthPermissionDao {

    /**
     * 根据roleIds查询
     * @param roleIds 传入的id
     * @return
     */
    List<AuthPermission> listByRoleIdIn(List<Long> roleIds);

    /**
     * 根据 roleId 查询
     * @param roleId 传入的id
     * @return
     */
    List<AuthPermission> listByRoleId(Long roleId);


    /**
     * 批量插入
     * @param authPermissionList
     * @return
     */
    int insertAuthPermissionAll(List<AuthPermission> authPermissionList);

    /**
     * 根据角色id删除
     * @param roleId
     * @return
     */
    boolean deleteByRoleId(Long roleId);
    
}
