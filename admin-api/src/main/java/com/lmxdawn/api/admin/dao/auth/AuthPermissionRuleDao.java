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
    List<AuthPermissionRule> listAll(Map<String,Object> map);

    /**
     * 根据 父级 pid 查询
     * @param pid
     * @return
     */
    List<AuthPermissionRule> listByPid(Long pid);

}
