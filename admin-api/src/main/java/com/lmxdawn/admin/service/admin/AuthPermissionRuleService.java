package com.lmxdawn.admin.service.admin;


import com.lmxdawn.admin.entity.AuthPermissionRule;

import java.util.List;

public interface AuthPermissionRuleService {


    List<AuthPermissionRule> selectByIdIn(List<Long> ids);

}
