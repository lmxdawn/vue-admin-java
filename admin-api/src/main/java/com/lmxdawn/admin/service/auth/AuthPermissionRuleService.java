package com.lmxdawn.admin.service.auth;


import com.lmxdawn.admin.entity.auth.AuthPermissionRule;

import java.util.List;
import java.util.Map;

public interface AuthPermissionRuleService {


    List<AuthPermissionRule> listByIdIn(List<Long> ids);


    List<AuthPermissionRule> listAll(Map<String,Object> map);

}
