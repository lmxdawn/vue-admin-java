package com.lmxdawn.admin.controller;

import com.lmxdawn.admin.entity.AuthAdmin;
import com.lmxdawn.admin.service.admin.AuthAdminService;
import com.lmxdawn.admin.utils.ResultVOUtil;
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
        PageSimpleVO<AuthAdmin> authAdminPageSimpleVO = authAdminService.findByPage(null, offset, limit);
        return ResultVOUtil.error(1, "ssss");
        // return ResultVOUtil.success(authAdminPageSimpleVO);
    }

}
