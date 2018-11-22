package com.lmxdawn.api.admin.service.auth.impl;

import com.lmxdawn.api.BaseAdminApplicationTest;
import com.lmxdawn.api.admin.service.auth.AuthRoleService;
import com.lmxdawn.api.admin.vo.PageSimpleVO;
import com.lmxdawn.api.admin.vo.auth.AuthAdminRoleVO;
import org.junit.Test;

import javax.annotation.Resource;

import static org.junit.Assert.*;

public class AuthRoleServiceImplTest  extends BaseAdminApplicationTest {

    @Resource
    private AuthRoleService authRoleService;

    @Test
    public void listAdminPage() {
    }

    @Test
    public void listAuthAdminRolePage() {

        Integer page = 1;
        Integer limit = 20;

        PageSimpleVO<AuthAdminRoleVO> adminAuthAdminRoleVOPageSimpleVO = authRoleService.listAuthAdminRolePage(page, limit, null);
        System.out.println(adminAuthAdminRoleVOPageSimpleVO.getList().size());
        System.out.println(adminAuthAdminRoleVOPageSimpleVO);
        assertTrue(adminAuthAdminRoleVOPageSimpleVO.getList().size() > 0);
    }
}