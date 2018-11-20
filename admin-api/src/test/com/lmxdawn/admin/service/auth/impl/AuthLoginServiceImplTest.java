package com.lmxdawn.admin.service.auth.impl;

import com.lmxdawn.admin.BaseAdminApplicationTest;
import com.lmxdawn.admin.dto.admin.auth.LoginUserInfoDTO;
import com.lmxdawn.admin.entity.auth.AuthAdmin;
import com.lmxdawn.admin.form.admin.auth.LoginForm;
import com.lmxdawn.admin.form.admin.auth.UpdatePasswordForm;
import com.lmxdawn.admin.service.auth.AuthAdminService;
import com.lmxdawn.admin.service.auth.AuthLoginService;
import com.lmxdawn.admin.utils.PasswordUtils;
import org.junit.Test;

import javax.annotation.Resource;

import java.util.Map;

import static org.junit.Assert.*;

public class AuthLoginServiceImplTest extends BaseAdminApplicationTest {

    @Resource
    private AuthLoginService authLoginService;
    @Resource
    private AuthAdminService authAdminService;

    @Test
    public void loginToken() {

        LoginForm loginForm = new LoginForm();
        loginForm.setUserName("admin");
        loginForm.setPwd("admin");

        Map<String, Object> loginToken = authLoginService.loginToken(loginForm);
        System.out.println(loginToken);
        assertNotNull(loginToken);
    }

    @Test
    public void findByAdminId() {

        LoginUserInfoDTO loginUserInfoDTO = authLoginService.findByAdminId(1L);
        System.out.println(loginUserInfoDTO);
        assertNotNull(loginUserInfoDTO);
    }

    @Test
    public void updatePassword() {

        Long adminId = 1L;
        String oldPwd = "admin";
        String newPwd = "admin";
        UpdatePasswordForm updatePasswordForm = new UpdatePasswordForm();
        updatePasswordForm.setAdminId(adminId);
        updatePasswordForm.setNewPassword(newPwd);
        updatePasswordForm.setOldPassword(oldPwd);
        boolean b = authLoginService.updatePassword(updatePasswordForm);

        AuthAdmin authAdmin = authAdminService.findPwdById(adminId);

        assertNotNull(authAdmin);

        assertEquals(authAdmin.getPassword(), PasswordUtils.authAdminPwd(newPwd));
    }
}