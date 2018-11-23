package com.lmxdawn.api.admin.dao.auth;

import com.lmxdawn.api.admin.entity.auth.AuthPermissionRule;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;


@Mapper
public interface AuthPermissionRuleDao {

    /**
     * 根据ids查询 规则名称
     * @param ids 传入的ids
     * @return
     */
    List<AuthPermissionRule> listByIdIn(List<Long> ids);

    /**
     * 查询所有
     * @param map
     * @return
     */
    List<AuthPermissionRule> listAll();

    /**
     * 根据 父级 pid 查询
     * @param pid
     * @return
     */
    List<AuthPermissionRule> listByPid(Long pid);

    /**
     * 根据 规则名称查询
     * @param name
     * @return
     */
    AuthPermissionRule findByName(String name);

    /**
     * 插入
     * @param authPermissionRule
     * @return
     */
    boolean insertAuthPermissionRule(AuthPermissionRule authPermissionRule);

    /**
     * 更新
     * @param authPermissionRule
     * @return
     */
    boolean updateAuthPermissionRule(AuthPermissionRule authPermissionRule);

    /**
     * 删除
     * @param id
     * @return
     */
    boolean deleteById(Long id);

}
