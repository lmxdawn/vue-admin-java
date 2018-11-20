package com.lmxdawn.admin.controller;

import com.lmxdawn.admin.entity.auth.AuthAdmin;
import com.lmxdawn.admin.service.auth.AuthAdminService;
import com.lmxdawn.admin.utils.ResultVOUtils;
import com.lmxdawn.admin.vo.PageSimpleVO;
import com.lmxdawn.admin.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    private AuthAdminService authAdminService;

    @GetMapping("/hello")
    public ResultVO hello(
            @RequestParam(value = "offset") Integer offset
            , @RequestParam("offset") Integer limit
    ) {
        PageSimpleVO<AuthAdmin> authAdminPageSimpleVO = authAdminService.listAdminPage(0,1, null);
        return ResultVOUtils.error(1, "ssss");
        // return ResultVOUtils.success(authAdminPageSimpleVO);
    }

}
