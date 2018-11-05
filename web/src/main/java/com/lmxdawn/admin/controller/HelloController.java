package com.lmxdawn.admin.controller;

import com.lmxdawn.admin.entity.AuthAdmin;
import com.lmxdawn.admin.service.AuthAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HelloController {

    @Autowired
    private AuthAdminService authAdminService;

    @GetMapping("/hello")
    public List<AuthAdmin> hello() {
        List<AuthAdmin> authAdminList = authAdminService.queryList();
//        System.out.println(authAdminList.size());
       // log.log(authAdminList.size());
        return authAdminList;
    }

}
