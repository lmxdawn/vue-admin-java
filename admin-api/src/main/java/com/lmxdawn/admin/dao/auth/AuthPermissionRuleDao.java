package com.lmxdawn.admin.dao.auth;

import com.lmxdawn.admin.entity.auth.AuthPermissionRule;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface AuthPermissionRuleDao {

    /**
     * 根据ids查询 规则名称
     * @param ids 传入的ids
     * @return
     */
    List<AuthPermissionRule> listByIdIn(List<Long> ids);
    
}
