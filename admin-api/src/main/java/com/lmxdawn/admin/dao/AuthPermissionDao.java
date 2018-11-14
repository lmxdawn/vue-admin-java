package com.lmxdawn.admin.dao;

import com.lmxdawn.admin.entity.AuthPermission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface AuthPermissionDao {

    /**
     * 根据roleIds查询
     * @param roleIds 传入的id
     * @return
     */
    List<AuthPermission> findByRoleIdIn(List<Long> roleIds);
    
    /**
     * 插入
     * @param authAdmin
     * @return
     */
    boolean insert(AuthPermission authAdmin);
    
    /**
     * 更新
     * @param authAdmin
     * @return
     */
    boolean update(AuthPermission authAdmin);
    
    /**
     * 删除
     * @param id
     * @return
     */
    boolean delete(Long id);
    
}
