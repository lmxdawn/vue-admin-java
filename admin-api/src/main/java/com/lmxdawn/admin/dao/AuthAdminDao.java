package com.lmxdawn.admin.dao;

import com.lmxdawn.admin.entity.AuthAdmin;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
public interface AuthAdminDao {
    
    /**
     * 查询列表
     * @return 列表
     */
    List<AuthAdmin> findByPage(Map<String,Object> map);
    
    /**
     * 根据id查询
     * @param id 传入的id
     * @return
     */
    AuthAdmin findById(Long id);

    /**
     * 根据Name
     * @param userName 用户名
     * @return
     */
    AuthAdmin findByUserName(String userName);
    
    /**
     * 插入
     * @param authAdmin
     * @return
     */
    boolean insert(AuthAdmin authAdmin);
    
    /**
     * 更新
     * @param authAdmin
     * @return
     */
    boolean update(AuthAdmin authAdmin);
    
    /**
     * 删除
     * @param id
     * @return
     */
    boolean delete(Long id);
    
}
