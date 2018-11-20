package com.lmxdawn.admin.dao.auth;

import com.lmxdawn.admin.entity.auth.AuthRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface AuthRoleDao {

    /**
     * 后台管理业务查询列表
     * @return 列表
     */
    List<AuthRole> listAdminPage(Map<String,Object> map);

    /**
     * 返回id,name 字段的列表
     * @return 列表
     */
    List<AuthRole> listAuthAdminRolePage(Map<String,Object> map);

    /**
     * 插入
     * @param authAdmin
     * @return
     */
    boolean insert(AuthRole authAdmin);
    
    /**
     * 更新
     * @param authAdmin
     * @return
     */
    boolean update(AuthRole authAdmin);
    
    /**
     * 删除
     * @param id
     * @return
     */
    boolean delete(Long id);
    
}
