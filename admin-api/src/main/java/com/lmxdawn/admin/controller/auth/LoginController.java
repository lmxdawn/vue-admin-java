package com.lmxdawn.admin.controller.auth;

import com.lmxdawn.admin.entity.AuthAdmin;
import com.lmxdawn.admin.enums.ResultEnum;
import com.lmxdawn.admin.form.LoginForm;
import com.lmxdawn.admin.service.AuthAdminService;
import com.lmxdawn.admin.utils.ResultVOUtil;
import com.lmxdawn.admin.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * 登录相关
 */
@RestController
public class LoginController {

    @Autowired
    private AuthAdminService authAdminService;

    /**
     * 用户登录
     * @return
     */
    @PostMapping(value = "/auth/login/index")
    public ResultVO index(@Valid LoginForm loginForm,
                          BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultVOUtil.error(ResultEnum.PARAM_VERIFY_FALL.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }

        AuthAdmin authAdmin = authAdminService.findByUserName(loginForm.getUserName());
        if (authAdmin == null) {
            return ResultVOUtil.error(ResultEnum.DATA_NOT);
        }

        Map<String, Object> map = new HashMap<>();
        map.put("id", authAdmin.getId());
        map.put("token", authAdmin.getId());
        return ResultVOUtil.success(map);
    }

}
