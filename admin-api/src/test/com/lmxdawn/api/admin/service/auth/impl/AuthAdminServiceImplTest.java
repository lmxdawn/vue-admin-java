package com.lmxdawn.api.admin.service.auth.impl;

import com.github.pagehelper.PageInfo;
import com.lmxdawn.api.BaseAdminApplicationTest;
import com.lmxdawn.api.admin.entity.auth.AuthAdmin;
import com.lmxdawn.api.admin.form.admin.auth.AuthAdminForm;
import com.lmxdawn.api.admin.service.auth.AuthAdminService;
import org.junit.Test;

import javax.annotation.Resource;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class AuthAdminServiceImplTest  extends BaseAdminApplicationTest {

    @Resource
    private AuthAdminService authAdminService;

    @Test
    public void listAdminPage() {

        Integer page = 1;
        Integer limit = 20;
        Integer status = 1;
        String username = "api";
        Long roleId = 1L;

        Map<String, Object> map = new HashMap<>();
        map.put("status", status);
        map.put("username", username);
        map.put("fields", "id,username");

        PageInfo<AuthAdmin> authAdminPageInfo = authAdminService.listAdminPage(page, limit, map);

        System.out.println(authAdminPageInfo.getList());
        assertTrue(authAdminPageInfo.getList().size() > 0);
    }

    @Test
    public void findByUserName() {
    }

    @Test
    public void findById() {
    }

    @Test
    public void findPwdById() {
    }

    @Test
    public void insertAuthAdmin() {

        AuthAdmin authAdmin = new AuthAdmin();
        authAdmin.setUsername("sssfff4");
        authAdmin.setCreateTime(new Date());
        authAdmin.setStatus(1);

        boolean b = authAdminService.insertAuthAdmin(authAdmin);
        assertTrue(b);
    }

    @Test
    public void updateAuthAdmin() {
    }

    @Test
    public void insertAuthAdminForm() {

        AuthAdminForm authAdminForm = new AuthAdminForm();
        authAdminForm.setUsername("aaa");
        authAdminForm.setStatus(0);
        authAdminForm.setRoles(Arrays.asList(1L, 2L));

        AuthAdmin authAdmin = authAdminService.insertAuthAdminForm(authAdminForm);

        assertNotNull(authAdmin);
    }

    @Test
    public void updateAuthAdminForm() {

        AuthAdminForm authAdminForm = new AuthAdminForm();
        authAdminForm.setId(7L);
        authAdminForm.setUsername("aaa");
        authAdminForm.setStatus(0);
        authAdminForm.setRoles(Arrays.asList(2L, 4L));

        boolean b = authAdminService.updateAuthAdminForm(authAdminForm);
        assertTrue(b);
    }
}