package com.lmxdawn.api.admin.service.auth;


import com.lmxdawn.api.admin.entity.auth.AuthPermissionRule;

import java.util.List;
import java.util.Map;

public interface AuthPermissionRuleService {


    List<AuthPermissionRule> listByIdIn(List<Long> ids);


    List<AuthPermissionRule> listByPid(Long pid);

    List<AuthPermissionRule> listAll();

    boolean insertAuthPermissionRule(AuthPermissionRule authPermissionRule);

    boolean updateAuthPermissionRule(AuthPermissionRule authPermissionRule);

    boolean deleteById(Long id);


}
