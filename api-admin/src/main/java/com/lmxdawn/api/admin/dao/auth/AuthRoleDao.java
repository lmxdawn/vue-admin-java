package com.lmxdawn.api.admin.dao.auth;

import com.lmxdawn.api.admin.entity.auth.AuthRole;
import com.lmxdawn.api.admin.req.auth.AuthRoleQueryRequest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AuthRoleDao {

    /**
     * 后台管理业务查询列表
     * @return 列表
     */
    List<AuthRole> listAdminPage(AuthRoleQueryRequest authRoleQueryRequest);

    /**
     * 返回id,name 字段的列表
     * @return 列表
     */
    List<AuthRole> listAuthAdminRolePage(Integer status);

    AuthRole findByName(String name);

    /**
     * 插入
     * @param authAdmin
     * @return
     */
    boolean insertAuthRole(AuthRole authAdmin);
    
    /**
     * 更新
     * @param authAdmin
     * @return
     */
    boolean updateAuthRole(AuthRole authAdmin);
    
    /**
     * 删除
     * @param id
     * @return
     */
    boolean deleteById(Long id);
    
}
