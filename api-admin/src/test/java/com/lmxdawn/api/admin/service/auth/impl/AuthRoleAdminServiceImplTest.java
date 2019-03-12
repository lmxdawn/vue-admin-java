package com.lmxdawn.api.admin.service.auth.impl;

import com.lmxdawn.api.BaseApiAdminApplicationTest;
import com.lmxdawn.api.admin.entity.auth.AuthRoleAdmin;
import com.lmxdawn.api.admin.service.auth.AuthRoleAdminService;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class AuthRoleAdminServiceImplTest extends BaseApiAdminApplicationTest {

    @Resource
    private AuthRoleAdminService authRoleAdminService;

    @Test
    public void listByAdminId() {
    }

    @Test
    public void listByRoleId() {
    }

    @Test
    public void insertAuthRoleAdminAll() {

        List<AuthRoleAdmin> authRoleAdminList = new ArrayList<>();

        long len = 3;
        for (long i = 1; i <= len; i++) {
            AuthRoleAdmin authRoleAdmin = new AuthRoleAdmin();
            if (i % 2 == 0) {
                authRoleAdmin.setRoleId(i);
            }
            authRoleAdmin.setAdminId(i + 1);
            authRoleAdminList.add(authRoleAdmin);
        }
        int i = authRoleAdminService.insertAuthRoleAdminAll(authRoleAdminList);

        System.out.println(i);
        assertEquals(1, i);
    }
}