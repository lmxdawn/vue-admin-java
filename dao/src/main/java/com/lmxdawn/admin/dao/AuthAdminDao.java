package com.lmxdawn.admin.dao;

import com.lmxdawn.admin.entity.AuthAdmin;

import java.util.List;

public interface AuthAdminDao {
    
    /**
     * 查询列表
     * @return 列表
     */
    List<AuthAdmin> queryAuthAdmin();
    
    /**
     * 根据id查询
     * @param id 传入的id
     * @return
     */
    AuthAdmin queryAuthAdminById(Long id);
    
    /**
     * 插入
     * @param authAdmin
     * @return
     */
    int insertAuthAdmin(AuthAdmin authAdmin);
    
    /**
     * 更新
     * @param authAdmin
     * @return
     */
    int updateAuthAdmin(AuthAdmin authAdmin);
    
    /**
     * 删除
     * @param id
     * @return
     */
    int deleteAuthAdminById(Long id);
    
}
