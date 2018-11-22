package com.lmxdawn.admin.dao.auth;

import com.lmxdawn.admin.entity.auth.AuthPermissionRule;
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

}
