package com.lmxdawn.api.admin.service.auth.impl;

import com.lmxdawn.api.BaseAdminApplicationTest;
import com.lmxdawn.api.admin.service.auth.AuthLoginService;
import org.junit.Test;

import javax.annotation.Resource;


import java.util.List;

import static org.junit.Assert.*;

public class AuthLoginServiceImplTest extends BaseAdminApplicationTest {

    @Resource
    private AuthLoginService authLoginService;

    @Test
    public void listRuleByAdminId() {

        Long adminId = 2L;

        List<String> strings = authLoginService.listRuleByAdminId(adminId);


    }
}