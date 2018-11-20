package com.lmxdawn.admin.service.auth.impl;

import com.lmxdawn.admin.BaseAdminApplicationTest;
import com.lmxdawn.admin.entity.auth.AuthAdmin;
import com.lmxdawn.admin.service.auth.AuthAdminService;
import com.lmxdawn.admin.vo.PageSimpleVO;
import org.junit.Test;

import javax.annotation.Resource;

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
        String username = "admin";
        Long roleId = 1L;

        Map<String, Object> map = new HashMap<>();
        map.put("status", status);
        map.put("username", username);
        map.put("role_id", roleId);

        PageSimpleVO<AuthAdmin> authAdminPageSimpleVO = authAdminService.listAdminPage(page, limit, map);

        System.out.println(authAdminPageSimpleVO.getList());
        assertTrue(authAdminPageSimpleVO.getList().size() > 0);
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
        authAdmin.setUsername("sssfff");
        authAdmin.setCreate_time(new Date());
        authAdmin.setStatus(1);

        boolean b = authAdminService.insertAuthAdmin(authAdmin);
        assertTrue(b);
    }

    @Test
    public void updateAuthAdmin() {
    }
}