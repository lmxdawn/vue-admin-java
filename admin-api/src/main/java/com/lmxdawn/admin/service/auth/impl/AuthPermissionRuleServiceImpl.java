package com.lmxdawn.admin.service.auth.impl;

import com.lmxdawn.admin.dao.auth.AuthPermissionRuleDao;
import com.lmxdawn.admin.entity.auth.AuthPermissionRule;
import com.lmxdawn.admin.service.auth.AuthPermissionRuleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 *
 */
@Service
public class AuthPermissionRuleServiceImpl implements AuthPermissionRuleService {

    @Resource
    private AuthPermissionRuleDao authPermissionRuleDao;

    @Override
    public List<AuthPermissionRule> listByIdIn(List<Long> ids) {
        return authPermissionRuleDao.listByIdIn(ids);
    }
}
