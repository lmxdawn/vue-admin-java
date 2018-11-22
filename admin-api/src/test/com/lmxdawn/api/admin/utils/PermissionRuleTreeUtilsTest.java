package com.lmxdawn.api.admin.utils;

import com.lmxdawn.api.BaseAdminApplicationTest;
import com.lmxdawn.api.admin.entity.auth.AuthPermissionRule;
import com.lmxdawn.api.admin.service.auth.AuthPermissionRuleService;
import com.lmxdawn.api.admin.vo.auth.AuthPermissionRuleMergeVO;
import org.junit.Test;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PermissionRuleTreeUtilsTest extends BaseAdminApplicationTest {

    @Resource
    private AuthPermissionRuleService authPermissionRuleService;

    @Test
    public void merge() {

        Map<String,Object> map = new HashMap<>();
        List<AuthPermissionRule> authPermissionRules = authPermissionRuleService.listAll(map);
        List<AuthPermissionRuleMergeVO> merge = PermissionRuleTreeUtils.merge(authPermissionRules,0L);
        System.out.println(merge);

    }
}