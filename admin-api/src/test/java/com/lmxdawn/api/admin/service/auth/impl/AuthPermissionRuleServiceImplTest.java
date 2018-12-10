package com.lmxdawn.api.admin.service.auth.impl;

import com.lmxdawn.api.BaseAdminApplicationTest;
import com.lmxdawn.api.admin.entity.auth.AuthPermissionRule;
import com.lmxdawn.api.admin.service.auth.AuthPermissionRuleService;
import org.junit.Test;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class AuthPermissionRuleServiceImplTest extends BaseAdminApplicationTest {

    @Resource
    private AuthPermissionRuleService authPermissionRuleService;

    @Test
    public void listByIdIn() {
    }

    @Test
    public void listByPid() {
        List<AuthPermissionRule> authPermissionRuleList = authPermissionRuleService.listByPid(0L);

        assertTrue(authPermissionRuleList.size() > 0);
    }

    @Test
    public void listAll() {

        List<AuthPermissionRule> authPermissionRuleList = authPermissionRuleService.listAll();

        assertTrue(authPermissionRuleList.size() > 0);
    }
}