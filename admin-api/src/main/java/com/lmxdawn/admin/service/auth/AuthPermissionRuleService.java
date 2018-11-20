package com.lmxdawn.admin.service.auth;


import com.lmxdawn.admin.entity.auth.AuthPermissionRule;

import java.util.List;

public interface AuthPermissionRuleService {


    List<AuthPermissionRule> listByIdIn(List<Long> ids);

}
