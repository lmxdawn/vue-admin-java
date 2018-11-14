package com.lmxdawn.admin.dao;

import com.lmxdawn.admin.entity.AuthPermissionRule;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface AuthPermissionRuleDao {

    /**
     * 查询列表
     * @return 列表
     */
    List<AuthPermissionRule> findByPage(Map<String, Object> map);
    
    /**
     * 根据id查询
     * @param id 传入的id
     * @return
     */
    AuthPermissionRule findById(Long id);
    
    /**
     * 插入
     * @param authAdmin
     * @return
     */
    boolean insert(AuthPermissionRule authAdmin);
    
    /**
     * 更新
     * @param authAdmin
     * @return
     */
    boolean update(AuthPermissionRule authAdmin);
    
    /**
     * 删除
     * @param id
     * @return
     */
    boolean delete(Long id);
    
}
