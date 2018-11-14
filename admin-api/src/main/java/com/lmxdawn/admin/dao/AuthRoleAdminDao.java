package com.lmxdawn.admin.dao;

import com.lmxdawn.admin.entity.AuthRoleAdmin;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface AuthRoleAdminDao {

    /**
     * 查询列表
     * @return 列表
     */
    List<AuthRoleAdmin> findByPage(Map<String, Object> map);
    
    /**
     * 根据 adminId 查询
     * @param adminId 传入的 adminId
     * @return
     */
    AuthRoleAdmin findByAdminId(Long adminId);
    
    /**
     * 插入
     * @param authAdmin
     * @return
     */
    boolean insert(AuthRoleAdmin authAdmin);
    
    /**
     * 更新
     * @param authAdmin
     * @return
     */
    boolean update(AuthRoleAdmin authAdmin);
    
    /**
     * 删除
     * @param id
     * @return
     */
    boolean delete(Long id);
    
}
