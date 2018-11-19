package com.lmxdawn.admin.service.admin.impl;

import com.lmxdawn.admin.dto.admin.LoginUserInfoDTO;
import com.lmxdawn.admin.form.admin.LoginForm;
import com.lmxdawn.admin.service.admin.AuthLoginService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthLoginServiceImplTest {

    @Resource
    private AuthLoginService authLoginService;

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
}