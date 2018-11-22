package com.lmxdawn.admin.service.auth.impl;

import com.lmxdawn.admin.BaseAdminApplicationTest;
import com.lmxdawn.admin.entity.auth.AuthPermissionRule;
import com.lmxdawn.admin.service.auth.AuthPermissionRuleService;
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
    public void listAll() {

        Map<String,Object> map = new HashMap<>();
        map.put("status", 1);
        List<AuthPermissionRule> authPermissionRuleList = authPermissionRuleService.listAll(map);

        assertTrue(authPermissionRuleList.size() > 0);
    }
}