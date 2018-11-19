package com.lmxdawn.admin.service.admin.impl;

import com.lmxdawn.admin.dao.auth.AuthPermissionRuleDao;
import com.lmxdawn.admin.entity.AuthPermissionRule;
import com.lmxdawn.admin.service.admin.AuthPermissionRuleService;
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
    public List<AuthPermissionRule> selectByIdIn(List<Long> ids) {
        return authPermissionRuleDao.selectByIdIn(ids);
    }
}
