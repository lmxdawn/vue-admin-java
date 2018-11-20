package com.lmxdawn.admin.dao.auth;

import com.lmxdawn.admin.entity.auth.AuthPermission;
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
    
}
